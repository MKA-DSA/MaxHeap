import java.util.ArrayList;
import java.util.Collections;
public class MaxHeap {
    private ArrayList<Integer> heap;

    public MaxHeap(){ 
    	heap = new ArrayList<Integer>();
    }
    
    public MaxHeap(ArrayList<Integer> heap) {
    	this.heap = heapify(heap);
    }

    public int peek() {
    	if (heap.size() > 0) return heap.get(0);
    	return -1;
    }
    
    // parent: floor((i-1)/2)
    public void enqueue(int elt) {
    	heap.add(elt);
    	int i = heap.size() -1;
    	while(i > 0 && elt > heap.get((i-1)/2)) {
    		heap.set(i, heap.get((i-1)/2));
    		heap.set((i-1)/2, elt);
    		i = (i-1)/2;
    	}
    }
    
    private int largerChild(int pos) {
    	int left = pos*2+1;
    	int right = pos*2+2;
    	if (left >= heap.size()) return -1;
    	int newPos = left;
    	if (right < heap.size()) {
    		if (heap.get(right) > heap.get(left)) {
    			newPos = right;
    		}
    	}
    	return newPos;
    }
    
    public int dequeue(){ 
    	if(heap.size() == 0){return -1; }
    	if (heap.size() == 1) { return heap.remove(0); }
    	int toReturn = heap.get(0);
    	heap.set(0, heap.get(heap.size() - 1));
    	heap.remove(heap.size() - 1);
    	int i = 0;
    	int largerChild = largerChild(i); 
    	while(largerChild != -1 && heap.get(i) < heap.get(largerChild)) {
    		int temp = heap.get(i);
    		heap.set(i, heap.get(largerChild));
    		heap.set(largerChild, temp);
    		i = largerChild;
    		largerChild = largerChild(i);
    	}
    	return toReturn;
    }
    
    public static ArrayList<Integer> heapify(ArrayList<Integer> list) { 
    	for (int i = list.size()/2 - 1; i >= 0; i--) {
    		heapifyHelper(list, i);
    	}
    	return list;
    }
    
    private static void heapifyHelper(ArrayList<Integer> list, int pos) {
    	int left = 2 * pos + 1;
    	int right = 2 * pos + 2;
    	if (left >= list.size()) return;
    	int newPos = left;
    	if (right < list.size()) {
    		if (list.get(right) > list.get(left)) {
    			newPos = right;
    		}
    	}
    	if (list.get(newPos) > list.get(pos)) {
    		int temp = list.get(newPos);
    		list.set(newPos, list.get(pos));
    		list.set(pos, temp);
    		heapifyHelper(list, newPos);
    	}
    }

    public static void main(String[] args){
    	MaxHeap max = new MaxHeap();
    	for (int i = 0; i < 10; i++) {
    		max.enqueue(i);
        	//System.out.println(max.heap);

    	}
    	System.out.println(max.heap);
    	System.out.println(max.dequeue());
    	System.out.println(max.heap);
    	
    	/*ArrayList<Integer> ints = new ArrayList<Integer>();
    	for (int i = 0; i < 10; i++) {
    		ints.add(i);
    	}
    	System.out.println(ints);
    	MaxHeap max = new MaxHeap(ints);
    	System.out.println(max.heap);*/
    	
    }

}
