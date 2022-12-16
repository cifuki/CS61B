public class SquareList {
    public static IntList square(IntList L) {
        IntList recur = L;
        if (recur.rest != null) {
            square(recur.rest);
        }
        recur.first = recur.first * recur.first;
        return recur;
    }

    public static IntList squareDestructive(IntList L) {
        while (L != null) {
            L.first = L.first * L.first;
            L = L.rest;
        }
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(2, null);
        L = new IntList(5, L);
        L = new IntList(1, L);

        square(L);
        squareDestructive(L);
    }
}
