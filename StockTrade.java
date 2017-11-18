//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

//import java.util.ArrayList;
//import java.util.Comparator;
import java.util.Iterator;

public class StockTrade {
    private PrioQueue<Bid> sellersQueue;
    private PrioQueue<Bid> buyersQueue;

    public StockTrade(){
        this.sellersQueue = new BinHeap<Bid>(new SellerComparator());
        this.buyersQueue = new BinHeap<Bid>(new BuyerComparator());

    }

    public Transaction placeSellBid(Bid bid) {
        Iterator<Bid> sQi = sellersQueue.iterator();
        Bid bidToCompare = sQi.next();
        boolean placed = false;

        while(sQi.hasNext()){
            if(bidToCompare.equals(bid)){
                System.out.println(sellersQueue.getArrayList());
                return null;
            }
            else if(bidToCompare.name == bid.name && bidToCompare.price != bid.price){
                sellersQueue.remove(bid);
                Bid potentialBuyer = scanQueue4(buyersQueue,bid); //either matching buyer or null.
                if(potentialBuyer == null) {
                    sellersQueue.add(bid);
                    placed = true;
                    break;
                }
                else{
                    sellersQueue.remove(bid);
                    buyersQueue.remove(potentialBuyer);
                    return new Transaction(bid.name,potentialBuyer.name,bid.price);
                }

            }
            else{
                bidToCompare = sQi.next();
            }
        }
        if(!placed){
            sellersQueue.add(bid);
        }
        System.out.println(sellersQueue.getArrayList());
        return null;
    }
    public Transaction placeBuyBid(Bid bid) {
        Iterator<Bid> bQi = buyersQueue.iterator();
        Bid bidToCompare = bQi.next();
        boolean placed = false;

        while(bQi.hasNext()){
            if(bidToCompare.equals(bid)){
                System.out.println(buyersQueue.getArrayList());
                return null;
            }
            else if(bidToCompare.name == bid.name && bidToCompare.price != bid.price){
                buyersQueue.remove(bid);
                Bid potentialSeller = scanQueue4(sellersQueue,bid); //either matching buyer or null.


                if(potentialSeller == null) {
                    buyersQueue.add(bid);
                    placed = true;

                    break;
                }
                else{
                    buyersQueue.remove(bid);
                    sellersQueue.remove(potentialSeller);
                    return new Transaction(potentialSeller.name, bid.name, bid.price);
                }

            }
            else{
                bidToCompare = bQi.next();
            }
        }
        if(!placed){
            buyersQueue.add(bid);
        }
        System.out.println(buyersQueue.getArrayList());
        return null;
    }

    public Iterator<Bid> sellBidsIterator() {
        return sellersQueue.iterator();
    }

    public Iterator<Bid> buyBidsIterator() {
        return buyersQueue.iterator();
    }

    public PrioQueue<Bid> getSellersQueue(){
        return sellersQueue;
    }

    public boolean compareNames(NameComparator comp, Bid B1, Bid B2){
        return(comp.compare(B1,B2) == 0);
    }

    public Bid scanQueue4(PrioQueue<Bid> queue, Bid aBid){
        Iterator<Bid> qi;
        qi = (queue == buyersQueue)?buyersQueue.iterator():sellersQueue.iterator();
        Bid b = qi.next();
        while(qi.hasNext()){
            if(b.price == aBid.price){
                return b;
            }
            b = qi.next();
        }
        return null;
    }
}
