/**
* Autores:  Tiago Dias Ferreira
*           Marcello Silva Cruz
*           Matheus Barbosa Consul
*/

/**
* DMMS: Dynamic Memory Management System
*/
public class DMMS {
    private int finishedRequests = 0; // incrementada a cada requisição atendida, utilizada para controlar o while do método main

    /**
     * setters
     */
    public void setFinishedRequests (int newFinishedRequests) {
        this.finishedRequests = newFinishedRequests;
    }

    /**
     * getters
     */
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

            // passa os valores dos parâmetros para seus respectivos objetos
            params.greeting();
            params.setHeapParams(heap, new Integer(args[0]));
            params.setReqParams(generator, new Integer(args[1]), new Integer(args[2]), new Integer(args[3]));
            params.setDeallocParams(deallocator, new Integer(args[4]), new Integer(args[5]));
            params.setDefragParams(defragger, new Integer(args[6]));

            Request memRequest = new Request();
            Request allocRequest = new Request();

            /**
             * main:
             * Laço principal do programa, executa enquanto a quantidade de requisições atendidas
             * for menor que a quantidade total de requisições.
             */
            while (dmms.getFinishedRequests() < generator.getRequestsQuantity()) {
                System.out.println("------------");
                // só gera requisições se tem espaço na fila e se há ainda requisições pendentes a serem criadas
                if (!queue.isFull() && generator.getLastRequestId() != generator.getRequestsQuantity()) {
                    memRequest = generator.generateRandomRequest(generator.getMinRequestSize(), generator.getMaxRequestSize());
                }
                // só passa para a fila requisições enquanto houver requisição criada pendente
                if (generator.getLastRequestId() <= generator.getRequestsQuantity()) {
                    queue.addRequest(memRequest);
                }
                allocator.allocate(dmms, allocRequest, queue, heap);
                deallocator.deallocate(queue, heap, generator);
                defragger.defragHeap(heap);
            }
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
