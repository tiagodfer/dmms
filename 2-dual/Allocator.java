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

    public Request getRequest () {
        Request memRequest = new Request();
        if(!this.queue.isEmpty()) {
            memRequest = this.queue.getRequests()[this.queue.getInitialPosition()];
        }
        return memRequest;
    }

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

    public boolean firstFit (Request request) {
        boolean isFit = false;
        for (int i = 0; i < this.heap.getArraySize(); i++) {
            if (!this.heap.getBlock(i).isOccupied() &&
                this.heap.getBlock(i).getSize() >= request.getSize()) {
                if (this.heap.getBlock(i).getSize() > request.getSize()) {
                    this.heap.getArray().add((i + 1), new Block(-1,
                                                               request.getSize() + this.heap.getBlock(i).getStart(),
                                                               this.heap.getBlock(i).getSize() - request.getSize()));
                    System.out.println("Criado novo bloco iniciado em " + this.heap.getBlock(i + 1).getStart() +
                                       " de " + this.heap.getBlock(i + 1).getSize() + " byte(s).");
                }
                this.heap.getBlock(i).setRequestId(request.getId());
                this.heap.getBlock(i).setSize(request.getSize());
                this.heap.incOccupied(request.getSize());
                this.heap.calcOccupation();
                this.heap.incAllocated();
                System.out.println("Requisição " + request.getId() +
                                   " alocada em memória no endereço " + this.heap.getBlock(i).getStart() +
                                   " até o endereço " + (this.heap.getBlock(i).getEnd()) + ".");
                isFit = true;
                break;
            }
        }
        return isFit;
    }

    public boolean allocate () {
        boolean isFit = this.firstFit(this.getRequest());
        if (isFit) {
            this.delRequest();
        }
        this.heap.calcFragmentation();
        return isFit;
    }

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
            /*
            System.out.println("Current heap:");
            for (int i = 0; i < this.heap.getArraySize(); i++) {
                System.out.println(this.heap.getBlock(i).isOccupied() + " " + this.heap.getBlock(i).getStart() + " " + this.heap.getBlock(i).getSize() + " " + this.heap.getFragmentation());
            }
            System.out.println("RAM Tot: " + this.heap.getSize());
            System.out.println("RAM Ocup: " + this.heap.getOccupied());
            System.out.println("RAM Livre: " + this.heap.getFree());
            System.out.println("Atendidos: " + this.heap.getAllocated());
            */
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
