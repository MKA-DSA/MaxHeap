import java.util.ArrayList;
import java.util.Collections;
public class MaxHeap<C extends Comparable> {
    private ArrayList<C> heap;

    public MaxHeap(){ 
    	heap = new ArrayList<C>();
    }
    
    public MaxHeap(ArrayList<C> heap) {
    	this.heap = heap;
    }

    public C peek() {
    	if(heap.size()==0) return null;
    	return heap.get(0);
    }
    // parent: floor((i-1)/2)
    public void enqueue(C elt) {
    	heap.add(elt);
    	//siftup
    	int i = heap.size()-1;
    	//elt1.compareTo(elt2) -> elt1 - elt2
    	while(i > 0 && heap.get((i-1)/2).compareTo(elt) < 0) {
    		heap.set(i,heap.get((i-1)/2));
    		heap.set((i-1)/2,elt);
    		i = (i-1)/2;
    	}
    }
    
    
    private int greaterChild(int pos) {
    	// return position of larger child if any
    	C curr = heap.get(pos);
    	if(2*pos+1 < heap.size()) {
    		C left = heap.get(2*pos + 1);
    		if (2*pos+2 < heap.size()) {
    			C right = heap.get(2*pos + 2);
    			if (right.compareTo(left) > 0 && right.compareTo(curr) > 0) {
    				return 2*pos + 2;
    			}
    			else if (right.compareTo(left) <= 0 && left.compareTo(curr) > 0) {
    				return 2*pos + 1;
    			}
    		}
    		else if (left.compareTo(curr) > 0) {
    			return 2*pos + 1;
    		}
    	}
    	
    	return -1;
    	// if no children return -1
    	
    }
    private static int greaterChild(ArrayList<Integer> heap,int pos) {
    	// return position of larger child if any
    	Integer curr = heap.get(pos);
    	if(2*pos+1 < heap.size()) {
    		Integer left = heap.get(2*pos + 1);
    		if (2*pos+2 < heap.size()) {
    			Integer right = heap.get(2*pos + 2);
    			if (right.compareTo(left) > 0 && right.compareTo(curr) > 0) {
    				return 2*pos + 2;
    			}
    			else if (right.compareTo(left) <= 0 && left.compareTo(curr) > 0) {
    				return 2*pos + 1;
    			}
    		}
    		else if (left.compareTo(curr) > 0) {
    			return 2*pos + 1;
    		}
    	}
    	return -1;
    }
    
    public C dequeue(){ 
    	if(heap.size()==0) return null;
    	C toReturn = heap.get(0);
    	heap.set(0, heap.get(heap.size()-1));
    	heap.remove(heap.size()-1);
    	//siftdown
    	int idx = 0;
    	// while idx less than one of its children and has any children
		int gc = greaterChild(idx);

    	while(idx < heap.size() && gc > -1) {
    		C temp = heap.get(gc);
    		heap.set(gc, heap.get(idx));
    		heap.set(idx, temp);
    		idx = gc;
    		gc = greaterChild(idx);
    	} 
    	return toReturn;
    }
    
    public static MaxHeap<Integer> heapify(ArrayList<Integer> list) { 
    	for(int i=list.size()/2 - 1; i>=0; i-- ) {
    		heapifyHelper(list, i);
    	}
    	return new MaxHeap<Integer>(list);
    }
    
    private static void heapifyHelper(ArrayList<Integer> list, int startPos) {
    	//static greaterChild(list, pos)
    	int gc = greaterChild(list, startPos);
    	if(gc < 0) return;
    	// swap startPos with gc
    	Integer t = list.get(gc);
    	list.set(gc, list.get(startPos));
    	list.set(startPos, t);
    	// recurse on gc
    	heapifyHelper(list, gc);
    	
    }

    public static void main(String[] args){
    	/*MaxHeap<Integer> max = new MaxHeap<Integer>();
    	for (int i = 0; i < 10; i++) {
    		max.enqueue(i);
    	}
    	System.out.println(max.heap);
    	System.out.println(max.dequeue());
    	System.out.println(max.heap);
    	*/
    	ArrayList<Integer> ints = new ArrayList<Integer>();
    	for (int i = 0; i < 10; i++) {
    		ints.add(i);
    	}
    	System.out.println(ints);
    	MaxHeap<Integer> max = heapify(ints);
    	System.out.println(max.heap);
    	
    }

}
