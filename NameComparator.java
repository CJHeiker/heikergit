import java.util.Comparator;

public class NameComparator implements Comparator<Bid> {
    @Override
    public int compare(Bid b1, Bid b2) {
        return b1.name.compareTo(b2.name);
    }
}