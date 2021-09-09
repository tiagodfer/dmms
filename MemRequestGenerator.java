import java.util.Random;

public class MemRequestGenerator {
    private int minRequestSize;
    private int maxRequestSize;
    private int requestsQuantity;
    private int lastRequestId;

    public void setMinRequestSize (int newMinRequestSize) {
        this.minRequestSize = newMinRequestSize;
    }
    
    public void setMaxRequestSize (int newMaxRequestSize) {
        this.maxRequestSize = newMaxRequestSize;
    }

    public void setRequestsQuantity (int newRequestQuantity) {
        this.requestsQuantity = newRequestQuantity;
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

    public int getRequestsQuantity () {
        return this.requestsQuantity;
    }

    public int getLastRequestId () {
        return this.lastRequestId;
    }

    public void incrementLastRequestId () {
        this.lastRequestId++;
    }

    public void decrementRequestsQuantity () {
        this.requestsQuantity--;
    }

    public MemRequest generateRandomRequest (int minRequestSize, int maxRequestSize) {
        Random randomizer = new Random();
        System.out.println("-------------------------");
        MemRequest memRequest = new MemRequest(lastRequestId, randomizer.nextInt(maxRequestSize - minRequestSize + 1) + minRequestSize);
        this.decrementRequestsQuantity();
        this.incrementLastRequestId();
        System.out.println("Requisição " + memRequest.getId()  + " de " + memRequest.getSize() + " kByte(s) gerada.");
        return memRequest;
    }
}
