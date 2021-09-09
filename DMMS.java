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

            HeapMap memHeap = new HeapMap();
            MemRequestGenerator reqGenerator = new MemRequestGenerator();
            Deallocator memDeallocator = new Deallocator();
            CircularQueue queue = new CircularQueue(10);
            Allocator memAllocator = new Allocator();

            Interface tui = new Interface();
            Interface.args params = tui.new args();

            params.greeting();
            params.setHeapParms(memHeap, new Integer(args[0]));
            params.setReqParms(reqGenerator, new Integer(args[1]), new Integer(args[2]), new Integer(args[3]));
            params.setDeallocParms(memDeallocator, new Integer(args[4]), new Integer(args[5]));
            params.printParms(memHeap, reqGenerator,memDeallocator);

            MemRequest memRequest = new MemRequest();
            MemRequest allocRequest = new MemRequest();
           
            while (dmms.getFinishedRequests() < reqGenerator.getRequestsQuantity()) {
                System.out.println("------------");
                if (!queue.isFull() && reqGenerator.getLastRequestId() != reqGenerator.getRequestsQuantity()) {
                    memRequest = reqGenerator.generateRandomRequest(reqGenerator.getMinRequestSize(), reqGenerator.getMaxRequestSize());
                }
                if (reqGenerator.getLastRequestId() <= reqGenerator.getRequestsQuantity()) {
                    queue.addRequest(memRequest);
                }
                memAllocator.allocate(dmms, allocRequest, queue, memHeap);
                memDeallocator.deallocate(queue, memHeap, reqGenerator);
            }
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
