import java.util.concurrent.atomic.*

/**
 * @author Korotkov Arsentiy
 */
class FAABasedQueue<E> : Queue<E> {
    private val enqIdx = AtomicLong(0)
    private val deqIdx = AtomicLong(0)

    private val head: AtomicReference<Segment>
    private val tail: AtomicReference<Segment>


    init {
        val zero = Segment(0)

        head = AtomicReference(zero)
        tail = AtomicReference(zero)
    }


    override fun enqueue(element: E) {
        while (true) {
            val curTail = tail.get()

            val i = enqIdx.getAndIncrement()

            val s = findSegment(curTail, i / SEGMENT_SIZE)
            tail.compareAndSet(curTail, s)

            if (s!!.cells.compareAndSet((i % SEGMENT_SIZE).toInt(), null, element)) {
                return
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun dequeue(): E? {
        while (true) {
            if (!shouldTryToDequeue()) {
                return null
            }

            val curHead = head.get()

            val i = deqIdx.getAndIncrement()

            val s = findSegment(curHead, i / SEGMENT_SIZE)
            head.compareAndSet(curHead, s)

            if (s!!.cells.compareAndSet((i % SEGMENT_SIZE).toInt(), null, POISONED)) {
                continue
            }

            return s.cells.get((i % SEGMENT_SIZE).toInt()) as E
        }
    }

    private fun findSegment(start: Segment, id: Long): Segment? {
        if (start.id == id) {
            return start
        } else {
            if (start.next.compareAndSet(null, Segment(start.id + 1))) {
                return start.next.get()
            }

            return findSegment(start.next.get()!!, id)
        }
    }

    private fun shouldTryToDequeue(): Boolean {
        while (true) {
            val curDeqIdx = deqIdx.get()
            val curEnqIdx = enqIdx.get()

            if (curDeqIdx != deqIdx.get()) {
                continue
            }

            return curDeqIdx < curEnqIdx
        }
    }
}

private class Segment(val id: Long) {
    val next = AtomicReference<Segment?>(null)
    val cells = AtomicReferenceArray<Any?>(SEGMENT_SIZE)
}

// DO NOT CHANGE THIS CONSTANT
private const val SEGMENT_SIZE = 2

private val POISONED = Any()
