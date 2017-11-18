//kukfan

import java.util.ArrayList;
import java.util.Comparator;


public class ManTest {

    public static void main(String args[]) {

        PrioQueue pq = new BinHeap<Integer>(new NaturalOrderComparator<Integer>());

        StockTrade stockTrade = new StockTrade();
        PrioQueue<Bid> bids = stockTrade.getSellersQueue();

        bids.add(new Bid("B1", 7));
        bids.add(new Bid("B2", 6));
        bids.add(new Bid("B3", 2));
        bids.add(new Bid("B4", 9));
        bids.add(new Bid("B5", 0));
        System.out.println(bids.getArrayList());


        NameComparator comparator = new NameComparator();



        Bid testBid = new Bid("B4",50000);

        System.out.println("Try to remove B4, 5000");
        for(Bid b : bids){
            if(stockTrade.compareNames(comparator,b,testBid)){
                bids.remove(b);
            }
        }
        bids.add(testBid);

        System.out.println(bids.getArrayList());



    }
}