import java.util.Comparator;

public class SellerComparator implements Comparator<Bid>{
    @Override
    public int compare(Bid b1, Bid b2) {
        int diff = b1.price - b2.price;
        return diff;
    }
}
