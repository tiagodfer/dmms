import java.util.concurrent.Semaphore;
import java.util.Random;

public class RequestGenerator extends Thread {
    private int minRequestSize;
    private int maxRequestSize;
    private int requestsNumber;
    private int lastRequestId;
    private Semaphore lock;
    private Semaphore empty;
    private Semaphore full;
    private Queue queue;

    public RequestGenerator (int MIN_REQ_SIZE, int MAX_REQ_SIZE, int REQ_NUMBER, Semaphore lock, Semaphore empty, Semaphore full, Queue queue) {
        this.minRequestSize = MIN_REQ_SIZE;
        this.maxRequestSize = MAX_REQ_SIZE;
        this.requestsNumber = REQ_NUMBER;
        this.lastRequestId = -1;
        this.lock = lock;
        this.empty = empty;
        this.full = full;
        this.queue = queue;
    }

    public void setMinRequestSize (int newMinRequestSize) {
        this.minRequestSize = newMinRequestSize;
    }
    
    public void setMaxRequestSize (int newMaxRequestSize) {
        this.maxRequestSize = newMaxRequestSize;
    }

    public void setRequestsNumber (int newRequestNumber) {
        this.requestsNumber = newRequestNumber;
    }

    public void setLastRequestId (int newLastRequestId) {
        this.lastRequestId = newLastRequestId;
    }

    public int getMinRequestSize () {
        return this.minRequestSize;
    }

    public int getMaxRequestSize () {
        return this.maxRequestSize;
    }

    public int getRequestsNumber () {
        return this.requestsNumber;
    }

    public int getLastRequestId () {
        return this.lastRequestId;
    }

    public void incLastRequestId () {
        this.lastRequestId++;
    }

    public void addRequest (Request newReq) {
        if (this.queue.getInitialPosition() == - 1) {
            this.queue.setInitialPosition(0);
        }
        this.queue.setFinalPosition((this.queue.getFinalPosition() + 1) % this.queue.getSize());
        this.queue.getRequests()[this.queue.getFinalPosition()] = newReq;
        this.queue.setElements(this.queue.getElements() + 1);
    }

    public void genRequest () {
        Random rand = new Random();
        this.incLastRequestId();
        Request newReq = new Request(this.getLastRequestId(), (rand.nextInt(this.maxRequestSize - this.minRequestSize + 1) + this.minRequestSize));
        this.addRequest(newReq);
        System.out.println("Requisição " + newReq.getId()  + " de " + newReq.getSize() + " kByte(s) alocada na posição " + this.queue.getFinalPosition() + " da fila circular.");
    }

    @Override
    public void run () {
        boolean work = true;
        while (work) {
            try {
                //System.out.println("GENERATOR: acquiring emptyQueue.");
                this.empty.acquire();
                //System.out.println("GENERATOR: emptyQueue acquired.");
                //System.out.println("GENERATOR: acquiring lockQueue.");
                this.lock.acquire();
                //System.out.println("GENERATOR: lockQueue acquired.");
                //if (!this.queue.isFull()) {
                    this.genRequest();
                //}
                if (this.getLastRequestId() + 1 >= this.getRequestsNumber()) {
                    work = false;
                }
            }
            catch (InterruptedException e) {
                System.out.println("Erro: " + e + "!");
            }
            finally {
                //System.out.println("GENERATOR: releasing lockQueue.");
                this.lock.release();
                //System.out.println("GENERATOR: lockQueue released.");
                //System.out.println("GENERATOR: releasing fullQueue.");
                this.full.release();
                //System.out.println("GENERATOR: fullQueue released.");
            }
        }
    }
}
