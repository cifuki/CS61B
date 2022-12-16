public class StringList {
    public String first;
    public StringList rest;

    public StringList(String first, StringList rest) {
        this.first = first;
        this.rest = rest;
    }

    public int length(StringList list) {
        int count = 0;
        while (list != null) {
            count += 1;
            list = list.rest;
        }
        return count;
    }

}
