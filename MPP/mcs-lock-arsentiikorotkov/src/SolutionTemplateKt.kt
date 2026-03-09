import java.util.concurrent.atomic.*

class Solution(private val env: Environment) : Lock<Solution.Node> {
    private val tail = AtomicReference<Node?>(null) // shared, atomic

    override fun lock(): Node {
        val my = Node()
        my.locked.value = true

        val pred = tail.getAndSet(my)
        if (pred != null) {
            pred.next.value = my

            while (my.locked.value) {
                env.park()
            }
        }

        return my
    }

    override fun unlock(node: Node) {
        if (node.next.value == null) {
            if (tail.compareAndSet(node, null)) {
                return
            } else {
                while (node.next.value == null) { /*empty*/ }
            }
        }

        node.next.value!!.locked.set(false)
        env.unpark(node.next.value!!.thread)
    }

    class Node {
        val thread: Thread = Thread.currentThread() // запоминаем поток, которые создал узел
        val locked = AtomicReference(false)
        val next = AtomicReference<Node?>(null)
    }
}