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
        if (args.length >= 6) {
            DMMS dmms = new DMMS();

            HeapMap map = new HeapMap();
            RequestGenerator generator = new RequestGenerator();
            Deallocator deallocator = new Deallocator();
            Queue queue = new Queue(10);
            Allocator allocator = new Allocator();

            Interface params = new Interface();

            params.greeting();
            params.setHeapParms(map, new Integer(args[0]));
            params.setReqParms(generator, new Integer(args[1]), new Integer(args[2]), new Integer(args[3]));
            params.setDeallocParms(deallocator, new Integer(args[4]), new Integer(args[5]));
            params.printParms(map, generator,deallocator);

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
                allocator.allocate(dmms, allocRequest, queue, map);
                deallocator.deallocate(queue, map, generator);
            }
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
