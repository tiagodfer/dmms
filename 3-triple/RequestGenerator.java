import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

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
        System.out.println("Requisição " + newReq.getId()  + " de " + newReq.getSize() + " kByte(s) alocada na posição " + this.queue.getFinalPosition() + " da fila circular.");
    }

    public Request genRequest () {
        this.incLastRequestId();
        Request newReq = new Request(this.getLastRequestId(), (ThreadLocalRandom.current().nextInt(this.maxRequestSize - this.minRequestSize + 1) + this.minRequestSize));
        return newReq;
    }

    @Override
    public void run () {
        Request newReq = new Request();
        while (this.getLastRequestId() + 1 < this.getRequestsNumber()) {
            try {
                newReq = this.genRequest();
                this.empty.acquire();
                this.lock.acquire();
                this.addRequest(newReq);
            }
            catch (InterruptedException e) {
                System.out.println("Erro: " + e + "!");
            }
            finally {
                this.lock.release();
                this.full.release();
            }
        }
    }
}
