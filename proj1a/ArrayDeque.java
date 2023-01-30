public class ArrayDeque<T> {
    private T[] array;
    private int left;
    private int right;
    private int length;

    public ArrayDeque() {
        left = 0;
        right = 0;
        length = 8;
        array = (T[]) new Object[length];
    }

    public int size() {
        return (length - left + right) % length;
    }

    /** 当前向后向指针重合时，则代表为空 */
    public boolean isEmpty() {
        return left == right;
    }

    /**
     * 数组还有一个位置的时候进行扩容，否则插入后数组就满了
     * 同时可避免 left == right 也可能数组满的情况
     */
    private boolean isFull() {
        return size() == length - 1;
    }

    public void addLast(T item) {
        //判断数组是否满了，满了则需要扩容
        if (isFull()) {
            resize((int) (length * 1.5));
        }
        //将item插入后向指针所在的位置

        array[right] = item;
        right = (right + 1 + length) % length;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize((int) (length * 1.5));
        }
        //将item插入前向指针所在的位置

        left = (left -1 + length) % length;
        array[left] = item;
    }

    /**
     * 1.从后向指针拿
     * 2.因为后向指针当前位置无内容，则需要先移动指针再拿，再赋空值
     * 3.拿完判断是否需要缩容
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        //从后向指针拿
        right = (right - 1) % length;
        T res = array[right];
        array[right] = null;
        //判断是否利用率低，如果是则需要缩容
        if (isLowUsage()) {
            resize((int) (size() * 0.5));
        }
        return res;
    }

    /**
     * 1.从前向指针拿
     * 2.因为前向指针当前位置有内容，则需要先拿，赋空值，再移动指针
     * 3.拿完判断是否需要缩容
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T res = array[left];
        array[left] = null;
        left = (left + 1) % length;
        //判断是否利用率低
        if (isLowUsage()) {
            resize((int) (size() * 0.5));
        }
        return res;
    }

    private boolean isLowUsage() {
        return size() == 16 && size() / length < 0.25;
    }

    /**
     * 以前向指针为准，把前向指针指向的内容以此放到新的数组中
     * 数组变长后，指针指向保持不变，即前向指针当前有内容，后向指针当前无内容
     */
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int size = 0;
        if (right > left) {
            for (int i = left, j = 0; i < right; i++, j++) {
                newArray[j] = array[i];
                size += 1;
            }
        } else {
            for (int i = left, j = 0; i != right; i++, j++) {
                i = i % length;
                newArray[j] = array[i];
                size += 1;
            }
        }

        array = newArray;
        length = capacity;
        left = 0;
        right = size;
    }

    /**
     * 根据前向指针顺序打印
     */
    public void printDeque() {
        if (left < right) {
            for (int i = left; i < right; i++) {
                if (i == right - 1) {
                    System.out.println(array[i]);
                }
                System.out.print(array[i]);
            }
        } else {
            for (int i = left; i != right; i++) {
                i = i % length;
                if (i == right - 1) {
                    System.out.println(array[i]);
                } else {
                    System.out.print(array[i]);
                }
            }
        }
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index < left && index >= right) {
            return null;
        }

        return array[index];
    }
}