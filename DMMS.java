/**
* Autores: Tiago Dias Ferreira and Marcello Silva Cruz
*/

/**
* DMMS: Simulador de Gerenciamento Dinâmico de Memória
*/
public class DMMS {
    // método principal
    public static void main(String args[]) {
        if (args.length >= 6) {
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

            while (reqGenerator.getRequestsQuantity() > 0 && !queue.isFull()) {
                    MemRequest memRequest = reqGenerator.generateRandomRequest(reqGenerator.getMinRequestSize(), reqGenerator.getMaxRequestSize());
                    MemRequest allocRequest = new MemRequest();
                    
                    queue.addRequest(memRequest);
                    memDeallocator.deallocate(queue, memHeap);
                    memAllocator.allocate(allocRequest, queue, memHeap);
            }
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
