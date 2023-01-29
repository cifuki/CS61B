public class Node<T> {
    Node prev;
    Node next;
    T item;

    public Node(T x) {
        item = x;
        prev = null;
        next = null;
    }
}
