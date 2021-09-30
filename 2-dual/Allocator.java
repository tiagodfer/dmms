import java.util.concurrent.Semaphore;

public class Allocator extends Thread {
    private int maxOccupation;
    private int maxFragmentation;
    private int reqNumber;
    private Semaphore lock;
    private Semaphore empty;
    private Semaphore full;
    private Deallocator deallocator;
    private Defragger defragger;
    private Queue queue;
    private Heap heap;

    public Allocator (int MAX_OCCUPATION, int MAX_FRAGMENTATION, int REQ_NUMBER, Semaphore lock, Semaphore empty, Semaphore full, Deallocator deallocator, Defragger defragger, Queue queue, Heap heap) {
        this.maxOccupation = MAX_OCCUPATION;
        this.maxFragmentation = MAX_FRAGMENTATION;
        this.reqNumber = REQ_NUMBER;
        this.lock = lock;
        this.empty = empty;
        this.full = full;
        this.deallocator = deallocator;
        this.defragger = defragger;
        this.queue = queue;
        this.heap = heap;
    }

    /**
     * Getters
     */
    public int getMaxOccupation () {
        return this.maxOccupation;
    }

    public int getMaxFragmentation () {
        return this.maxFragmentation;
    }

    public int getReqNumber () {
        return this.reqNumber;
    }

    public Deallocator getDeallocator () {
        return this.deallocator;
    }

    public Defragger getDefragger () {
        return this.defragger;
    }

    /**
     * getRequest:
     * Verifica se fila circular não está vazia e retorna a requisição.
     */
    public Request getRequest () {
        Request memRequest = new Request();
        if(!this.queue.isEmpty()) {
            memRequest = this.queue.getRequests()[this.queue.getInitialPosition()];
        }
        return memRequest;
    }

    /**
     * delRequest:
     * Remove requisição da fila, atualiza quantidade de elementos na fila e
     * a próxima posição a ser lida.
     */
    public void delRequest () {
        if (!this.queue.isEmpty()) {
            if (this.queue.getInitialPosition() == this.queue.getFinalPosition()) {
                this.queue.setInitialPosition(-1);
                this.queue.setFinalPosition(-1);
            }
            else {
                this.queue.setInitialPosition((this.queue.getInitialPosition() + 1) % this.queue.getSize());
            }
            this.queue.setElements(this.queue.getElements() - 1);
        }
    }

    /**
     * firstFit:
     * Verifica se requisição cabe em algum dos blocos livres; em caso positivo,
     * aloca requisição no primeiro bloco encontrado; caso este bloco seja maior
     * que o necessário, divide-o.
     * Retorna true caso tenha conseguido alocar.
     */
    public boolean firstFit (Request request) {
        boolean isFit = false;
        // varre o heap em busca do primeiro bloco livre onde a requisição caiba
        for (int i = 0; i < this.heap.getArraySize(); i++) {
            if (!this.heap.getBlock(i).isOccupied() &&
                this.heap.getBlock(i).getSize() >= request.getSize()) {
                // o bloco sendo maior que a requisição, divide-o
                if (this.heap.getBlock(i).getSize() > request.getSize()) {
                    this.heap.getArray().add((i + 1), new Block(-1,
                                                               request.getSize() + this.heap.getBlock(i).getStart(),
                                                               this.heap.getBlock(i).getSize() - request.getSize()));
                    System.out.println("Criado novo bloco iniciado em " + this.heap.getBlock(i + 1).getStart() +
                                       " de " + this.heap.getBlock(i + 1).getSize() + " byte(s).");
                }
                // atualiza dados do bloco ocupado
                this.heap.getBlock(i).setRequestId(request.getId());
                this.heap.getBlock(i).setSize(request.getSize());
                // atualiza dados do bloco ocupado
                this.heap.incOccupied(request.getSize());
                this.heap.calcOccupation();
                this.heap.incAllocated();
                // outputs
                System.out.println("Requisição " + request.getId() +
                                   " alocada em memória no endereço " + this.heap.getBlock(i).getStart() +
                                   " até o endereço " + (this.heap.getBlock(i).getEnd()) + ".");
                // retorna true
                isFit = true;
                break;
            }
        }
        return isFit;
    }

    /**
     * allocate:
     * Caso requisição possa ser alocada, aloca na heap e remove da fila.
     * Calcula fragmentação.
     */
    public boolean allocate () {
        boolean isFit = this.firstFit(this.getRequest());
        if (isFit) {
            this.delRequest();
        }
        this.heap.calcFragmentation();
        return isFit;
    }

    /**
     * run:
     * Método principal do Alocador, adquire os semáforos necessários, tenta alocar,
     * caso aloque, aciona desfragmentador para verificare se deve agir.
     */
    @Override
    public void run () {
        boolean isFit = false;
        while (this.heap.getAllocated() < this.getReqNumber()) {
            if (this.heap.getOccupation() > this.getMaxOccupation()) {
                this.getDeallocator().deallocate();
            }
            if (this.heap.getFragmentation() > this.getMaxFragmentation()) {
                this.getDefragger().defrag();
            }
            try {
                this.full.acquire();
                this.lock.acquire();
                isFit = this.allocate();
            }
            catch (InterruptedException e) {
                System.out.println("Erro: " + e + "!");
            }
            finally {
                this.lock.release();
                if (isFit) {
                    this.empty.release();
                }
                else {
                    this.full.release();
                }
            }
        }
    }
}
