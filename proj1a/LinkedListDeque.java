public class LinkedListDeque<T> {

    /* 类基本成员变量 */
    private static int size;   //记录链表长度
    private Node<T> sentinelPrev; //前向哨兵节点
    private Node<T> sentinelNext; //后向哨兵节点

    public LinkedListDeque() {
        size = 0;
        sentinelPrev = (Node<T>) new Node<>(-1);
        sentinelNext = (Node<T>) new Node<>(1);

        sentinelPrev.next = sentinelNext;
        sentinelNext.prev = sentinelPrev;

        sentinelPrev.prev = null;
        sentinelNext.next = null;
    }

    /* Creates a deep copy of other */
//    public LinkedListDeque(LinkedListDeque other) {
//        LinkedListDeque newDeque = new LinkedListDeque();
//        Node iterate = other.sentinelPrev;
//
//        for (int i = 0; i < other.size(); i++) {
//            iterate = iterate.next;
//            newDeque.addLast(iterate);
//        }
//    }

    /* Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        Node<T> first = new Node<>(item);

        first.next = sentinelPrev.next;
        sentinelPrev.next.prev = first;
        first.prev = sentinelPrev;
        sentinelPrev.next = first;

        size += 1;

    }

    /* Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        Node<T> last = new Node<>(item);

        sentinelNext.prev.next = last;
        last.prev = sentinelNext.prev;
        last.next = sentinelNext;
        sentinelNext.prev = last;

        size += 1;
    }

    /* Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /* Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size >= 1) {
            Node<T> removeNode = sentinelPrev.next;
            sentinelPrev.next = sentinelPrev.next.next;
            sentinelPrev.next.prev = sentinelPrev;

            //释放删除节点
            T item = removeNode.item;
            removeNode.next = null;
            removeNode.prev = null;
            removeNode.item = null;

            size -= 1;
            return item;
        }

        return null;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size >= 1) {
            Node<T> removeNode = sentinelNext.prev;
            sentinelNext.prev.prev.next = sentinelNext;
            sentinelNext.prev = sentinelNext.prev.prev;

            //释放删除节点
            T item = removeNode.item;
            removeNode.next = null;
            removeNode.item = null;
            removeNode.prev = null;

            size -= 1;
            return item;
        }

        return null;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    public T get(int index) {
        if (size == 0 || index >= size) {
            return null;
        }

        Node search = sentinelPrev;

        for (int i = 0; i <= index; i++) {
            search = search.next;
        }

        return (T) search.item;
    }

    /**
     * recursion version of get
     */
    public T getRecursive(int index) {
        // sentinelPrev节点不变的情况下如何实现？
        if (size == 0 || index >= size) {
            return null;
        }

        return getRecursive(sentinelPrev.next, index);
    }

    private T getRecursive(Node node, int index) {
        if (index == 0) {
            return (T) node.item;
        }
        return getRecursive(node.next, index - 1);
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        Node printNode = sentinelPrev;
        for (int i = 0; i < size; i++) {
            printNode = printNode.next;
            if (i == size - 1) {
                System.out.println(printNode.item + "\n");
            } else {
                System.out.print(printNode.item + " ");
            }
        }
    }

}
