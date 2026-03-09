import java.util.concurrent.*
import java.util.concurrent.atomic.*

/**
 * @author Korotkov Arsentiy
 */
open class TreiberStackWithElimination<E> : Stack<E> {
    private val stack = TreiberStack<E>()

    // TODO: Try to optimize concurrent push and pop operations,
    // TODO: synchronizing them in an `eliminationArray` cell.
    private val eliminationArray = AtomicReferenceArray<Any?>(ELIMINATION_ARRAY_SIZE)

    override fun push(element: E) {
        if (tryPushElimination(element)) {
            return
        }

        stack.push(element)
    }

    protected open fun tryPushElimination(element: E): Boolean {
        val index = randomCellIndex()

        if (eliminationArray.compareAndSet(index, CELL_STATE_EMPTY, element)) {
            for (i in 0 until ELIMINATION_WAIT_CYCLES) {
                if (eliminationArray.compareAndSet(index, CELL_STATE_RETRIEVED, CELL_STATE_EMPTY)) {
                    return true
                }
            }

            return if (eliminationArray.compareAndSet(index, element, CELL_STATE_EMPTY)) {
                false
            } else {
                eliminationArray.set(index, CELL_STATE_EMPTY)
                true
            }

        } else {
            return false
        }
    }

    override fun pop(): E? = tryPopElimination() ?: stack.pop()

    @Suppress("UNCHECKED_CAST")
    private fun tryPopElimination(): E? {
        val index = randomCellIndex()

        val res = eliminationArray.getAndUpdate(index) { x: Any? ->
            if (x != CELL_STATE_EMPTY && x != CELL_STATE_RETRIEVED) CELL_STATE_RETRIEVED else x
        }

        return (if (res == CELL_STATE_RETRIEVED) CELL_STATE_EMPTY else res) as E
    }

    private fun randomCellIndex(): Int =
        ThreadLocalRandom.current().nextInt(eliminationArray.length())

    companion object {
        private const val ELIMINATION_ARRAY_SIZE = 2
        private const val ELIMINATION_WAIT_CYCLES = 1

        // Initially, all cells are in EMPTY state.
        private val CELL_STATE_EMPTY = null

        // `tryPopElimination()` moves the cell state
        // to `RETRIEVED` if the cell contains element.
        private val CELL_STATE_RETRIEVED = Any()
    }
}
