/**
* Autores:  Tiago Dias Ferreira
*           Marcello Silva Cruz
*           Matheus Barbosa Consul
*/

/**
* DMMS: Dynamic Memory Management System
*/
public class DMMS {
    private int finishedRequests = 0;
    
    public void setFinishedRequests (int newFinishedRequests) {
        this.finishedRequests = newFinishedRequests;
    }

    public int getFinishedRequests () {
        return this.finishedRequests;
    }

    public static void main(String args[]) {
        if (args.length >= 7) {
            DMMS dmms = new DMMS();

            Heap heap = new Heap();
            RequestGenerator generator = new RequestGenerator();
            Deallocator deallocator = new Deallocator();
            Defragger defragger = new Defragger();
            Queue queue = new Queue(10);
            Allocator allocator = new Allocator();

            Interface params = new Interface();

            params.greeting();
            params.setHeapParams(heap, new Integer(args[0]));
            params.setReqParams(generator, new Integer(args[1]), new Integer(args[2]), new Integer(args[3]));
            params.setDeallocParams(deallocator, new Integer(args[4]), new Integer(args[5]));
            params.setDefragParams(defragger, new Integer(args[6]));
            //params.printParams(heap, generator, deallocator, defragger);

            Request memRequest = new Request();
            Request allocRequest = new Request();
           
            while (dmms.getFinishedRequests() < generator.getRequestsQuantity()) {
                System.out.println("------------");
                if (!queue.isFull() && generator.getLastRequestId() != generator.getRequestsQuantity()) {
                    memRequest = generator.generateRandomRequest(generator.getMinRequestSize(), generator.getMaxRequestSize());
                }
                if (generator.getLastRequestId() <= generator.getRequestsQuantity()) {
                    queue.addRequest(memRequest);
                }
                allocator.allocate(dmms, allocRequest, queue, heap);
                deallocator.deallocate(queue, heap, generator);
                defragger.defragHeap(heap);
                /*
                for (int i = 0; i < heap.getHeapSize(); i++) {
                    System.out.println(heap.getBlock(i).isOccupied() + " " + heap.getBlock(i).getStart() + " " + heap.getBlock(i).getSize() + " " + heap.getFragmentation());
                }
                */
            }
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
