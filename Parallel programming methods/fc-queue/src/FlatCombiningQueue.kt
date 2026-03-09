import java.util.concurrent.*
import java.util.concurrent.atomic.*

/**
 * @author Korotkov Arsentiy
 */
class FlatCombiningQueue<E> : Queue<E> {
    private val queue = ArrayDeque<E>() // sequential queue
    private val combinerLock = AtomicBoolean(false) // unlocked initially
    private val tasksForCombiner = AtomicReferenceArray<Any?>(TASKS_FOR_COMBINER_SIZE)


    @Suppress("UNCHECKED_CAST")
    override fun enqueue(element: E) {
        while (true) {
            val index = randomCellIndex()

            if (combinerLock.compareAndSet(false, true)) {
                queue.addLast(element)
                update(true)
                return
            }

            if (tasksForCombiner.compareAndSet(index, null, element)) {
                while (true) {
                    update(false)

                    if (tasksForCombiner.get(index) is Result<*>) {
                        tasksForCombiner.set(index, null)

                        return
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun dequeue(): E? {
        while (true) {
            val index = randomCellIndex()

            if (combinerLock.compareAndSet(false, true)) {
                val res = queue.removeFirstOrNull()
                update(true)
                return res
            }

            if (tasksForCombiner.compareAndSet(index, null, Dequeue)) {
                while (true) {
                    update(false)

                    val res = tasksForCombiner.get(index)
                    if (res is Result<*>) {
                        tasksForCombiner.set(index, null)

                        return res.value as E?
                    }
                }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun update(access: Boolean) {
        if (access || combinerLock.compareAndSet(false, true)) {
            for (i in 0 until TASKS_FOR_COMBINER_SIZE) {
                when (val task = tasksForCombiner.get(i)) {
                    is Dequeue -> tasksForCombiner.set(i, Result(queue.removeFirstOrNull()))
                    !is Result<*> -> if (task != null) tasksForCombiner.set(i, Result(queue.addLast(task as E)))
                }
            }

            combinerLock.set(false)
        }
    }

    private fun randomCellIndex(): Int =
        ThreadLocalRandom.current().nextInt(tasksForCombiner.length())
}

private const val TASKS_FOR_COMBINER_SIZE = 3 // Do not change this constant!

private object Dequeue

private class Result<V>(
    val value: V
)