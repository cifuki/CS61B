//public class SLList {
//    private IntNode first;
//    private int size = 0;
//
//    public SLList(int x) {
//        first = new IntNode(x, null);
//    }
//
//    public int getFirst() {
//        return first.item;
//    }
//
//    /** Adds an item to the front of the list. */
//    public void addFirst(int x) {
//        first = new IntNode(x, first);
//        size += 1;
//    }
//
//    /** Adds an item to the end of the list. */
//    public void addLast(int x) {
//        IntNode p = first;
//        while (p.next != null) {
//            p = p.next;
//        }
//        p.next = new IntNode(x, null);
//    }
//
//    /** Returns the number of items in the list using recursion. */
//    public int size(IntNode p) {
//        if (p.next == null) {
//            return 1;
//        }
//        return size(p.next) + 1;
//    }
//}


public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first;
    private int size;

    public SLList() {
        first = null;
        size = 0;
    }

    public SLList(int x) {
        first = new IntNode(x, null);
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
        size += 1;
    }

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        IntNode p = first;

        if (p != null) {
            /* Advance p to the end of the list. */
            while (p.next != null) {
                p = p.next;
            }
            p.next = new IntNode(x, null);
        } else {
            first = new IntNode(x, null);
        }
    }

    /** Crashes when you call addLast on an empty SLList. Fix it. */
    public static void main(String[] args) {
        SLList x = new SLList();
        x.addLast(5);
    }
}