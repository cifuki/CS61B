public class ArrayDeque<T> {
    private int size;
    private T[] array;

    /** constructor */
    public ArrayDeque() {
        size = 0;
        array = (T[]) new Object[8];
    }

//    public ArrayDeque(int len) {
//        size = 0;
//        array = (T[]) new Object[len];
//    }

    /** Creates a deep copy of other. */
//    public ArrayDeque(ArrayDeque other) {
//        int len = other.size;
//        ArrayDeque newArrayDeque = new ArrayDeque(len);
//        for (int i = 0; i < size; i++) {
//            newArrayDeque.array[i] = other.array[i];
//            newArrayDeque.size += 1;
//        }
//    }

    /** 数组扩容 */
    private T[] expansion() {
        T[] newArray;

        // 扩容需满足条件：当数组长度大于16时，利用率大于 25%
        // 并且扩容需要考虑扩容效率，不简单地使用'+'，而是使用'*'
        if (size <= 3) {
            newArray = (T[]) new Object[size * 2];
        } else {
            newArray = (T[]) new Object[(int) ((size + 1) / 0.25)];
        }
        return newArray;
    }

    /** 数组右移 */
    private T[] moveRight(T[] newArray) {
        for (int i = 0; i < size; i++) {
            newArray[i + 1] = array[i];
        }
        return newArray;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        // 如何才能用常数时间完成节点添加，若考虑到扩容，则不可能用常量时间
        // 如果列表为空，直接就可以item[0] = item;
        // 如果列表不为空：1.首先判断加完后长度是否大于8，若大于则扩容；2.如果长度小于等于8，则需要移动整个数组，给第一个空出位置，O(n)
        if (size != 0) {
            T[] newArray;
            newArray = expansion();
            array = moveRight(newArray);
        }
        array[0] = item;
        size += 1;

    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size < array.length) {
            array[size] = item;
            size += 1;
        } else {
            T[] newArray = expansion();
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            newArray[size] = item;
            array = newArray;
            size += 1;

        }
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                System.out.println(array[i]);
            } else {
                System.out.print(array[i]);
            }
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        //创建一个新的数组并复制2...n个元素
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T item = array[0];
            array[0] = null;
            size -= 1;
            return item;
        }
        T[] newArray = (T[]) new Object[size - 1];
        T item = array[0];
        for (int i = 1; i < size; i++) {
            newArray[i - 1] = array[i];
        }
        array = newArray;
        size -= 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = array[size - 1];
        T[] newArray = (T[]) new Object[size - 1];
        for (int i = 0; i < size - 1; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        size -= 1;
        return item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[index];
    }

}
