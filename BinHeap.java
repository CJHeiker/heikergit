import java.util.Iterator;
import java.util.Comparator;
import java.util.ArrayList;

public class BinHeap<E> implements PrioQueue<E> {
    private ArrayList<E> prioList;
    private Comparator<? super E> comp;

    public BinHeap(Comparator<? super E> comp) {
        prioList = new ArrayList<>();
        this.comp = comp;
    }



    public void add(E e) {

        if(prioList.isEmpty()){
            prioList.add(0, null);
            prioList.add(e);
        }

        else{
            prioList.add(e); //adding on last (index = prioList.size() - 1)

            newShiftUp(prioList.size() - 1);
        }

    }

    public void remove(E e) {
        if(prioList.isEmpty() || prioList.size() == 1){return;}
        int index;
        int i = 1;

        while(comp.compare(prioList.get(i),e) != 0 && i < prioList.size() - 1){
            i++;
        }
        index = i;

        if(comp.compare(prioList.get(index),e) == 0){
            if(index == prioList.size() - 1){
                prioList.remove(prioList.size() - 1);
            }
            else {
                E lastElement = prioList.get(prioList.size() - 1);

                prioList.set(index, lastElement);
                prioList.remove(prioList.size() - 1);

                newShiftUp(index);
                shiftDown(index);
            }

        }

    }

    public E poll() {
        if(prioList.isEmpty() || prioList.size() == 1){return null;}
        E lastElement = prioList.get(prioList.size() - 1);
        E topElement = prioList.get(1);
        prioList.set(1, lastElement);
        prioList.remove(prioList.size() - 1);
        shiftDown(1);
        return topElement;
    }

    public E peek() {

        if(prioList.isEmpty() || prioList.size() == 1){return null;} //list is empty or only index 0 exists
        //System.out.println(prioList);
        return prioList.get(1); //return root, it has the highest priority.
    }


    public Iterator<E> iterator() {
        return new myIterator();
    }

    public class  myIterator implements Iterator<E>{
        int i = 1;
            @Override
            public boolean hasNext() {

                return (prioList.size() > i);
            }

            @Override
            public E next() {

                if(hasNext()) {
                    i++;
                    return prioList.get(i - 1);
                }
                return null;
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

    }

    public void newShiftUp(int currentIndex){

        if(prioList.get(currentIndex/2) != null && comp.compare(prioList.get(currentIndex), prioList.get(currentIndex/2)) < 0){

            prioList.add(prioList.get(currentIndex/2));
            prioList.set(currentIndex/2, prioList.get(currentIndex));
            prioList.set(currentIndex, prioList.get(prioList.size() - 1));
            prioList.remove(prioList.size() - 1);
            if(currentIndex/2 != 1){
                newShiftUp(currentIndex/2);
            }
        }
    }

    //shiftDown does not work on empty lists and such
    public void shiftDown(int currentIndex){

        if(hasChild(currentIndex) == 2){
            if((comp.compare(prioList.get(currentIndex*2 + 1),prioList.get(currentIndex*2)) > 0) && (comp.compare(prioList.get(currentIndex),prioList.get(currentIndex*2)) > 0)){
                swap(2*currentIndex,currentIndex); //Left Child is swapped with current
                shiftDown(2*currentIndex); //recursive call with new current index set as left child.
            }

            else if(comp.compare(prioList.get(currentIndex),prioList.get(2 * currentIndex + 1)) > 0){
                swap(2*currentIndex + 1,currentIndex);//Right child is swapped with current
                shiftDown(2*currentIndex + 1); //recursive call with new current index set as right child.
            }
        }
        else if(hasChild(currentIndex) == 1 && comp.compare(prioList.get(currentIndex),prioList.get(currentIndex*2)) > 0){
            swap(2*currentIndex,currentIndex); //swap left(only) child with current. last operation.
        }//else no rc or lc; do nothing.

    }

    public int hasChild(int index){ //returns 2 if right child, returns 1 if only left child, returns 0 if no children.
        if(2*index + 1 < prioList.size()){return 2;}
        if (2*index < prioList.size() ){return 1;}
        else{return 0;}
    }

    public void swap(int childIndex, int currentIndex){
        prioList.add(prioList.get(currentIndex)); //add to last to save value
        prioList.set(currentIndex,prioList.get(childIndex)); //set current as child
        prioList.set(childIndex, prioList.get(prioList.size() - 1)); //set child as current (swap complete)
        prioList.remove(prioList.size() - 1); //remove last value again

    }

    public ArrayList<E> getArrayList(){
        return prioList;
    }


}




