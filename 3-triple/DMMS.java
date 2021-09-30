import java.util.concurrent.Semaphore;

public class DMMS {
    public static void main (String args[]) {
        if (args.length >= 7) {
            final int QUEUE_SIZE = 10;
            final int HEAP_SIZE = new Integer(args[0]);
            final int MIN_REQ_SIZE = new Integer(args[1]);
            final int MAX_REQ_SIZE = new Integer(args[2]);
            final int REQ_NUMBER = new Integer(args[3]);
            final int MAX_OCCUPATION = new Integer(args[4]);
            final int MIN_THRESHOLD = new Integer(args[5]);
            final int MAX_FRAGMENTATION = new Integer(args[6]);

            Queue queue = new Queue(10);
            Heap heap = new Heap(HEAP_SIZE);

            Semaphore lock = new Semaphore(1, true);
            Semaphore empty = new Semaphore(10, true);
            Semaphore full = new Semaphore(0, true);
            
            Semaphore lockHeap = new Semaphore(1, true);

            RequestGenerator reqGen = new RequestGenerator(MIN_REQ_SIZE, MAX_REQ_SIZE, REQ_NUMBER, lock, empty, full, queue);
            Defragger defragger = new Defragger(heap);
            Deallocator deallocator = new Deallocator(MAX_OCCUPATION, MIN_THRESHOLD, REQ_NUMBER, MAX_FRAGMENTATION, lockHeap, defragger, heap);
            Allocator allocator = new Allocator(MAX_OCCUPATION, MAX_FRAGMENTATION, REQ_NUMBER, lock, empty, full, lockHeap, deallocator, defragger, queue, heap);

            reqGen.start();
            allocator.start();
            deallocator.start();
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
