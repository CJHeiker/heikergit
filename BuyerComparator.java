import java.util.Comparator;

public class BuyerComparator implements Comparator<Bid>{
    @Override
    public int compare(Bid b1, Bid b2) {
        int diff = b2.price - b1.price;
        return diff;
    }

}