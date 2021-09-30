import java.util.Random;

public class RequestGenerator {
    private int minRequestSize;
    private int maxRequestSize;
    private int requestsQuantity;
    private int lastRequestId;

    /**
     * Setters
     */
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

    /**
     * Getters
     */
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

    /**
     * Métodos auxiliares
     */
    public void incrementLastRequestId () {
        this.lastRequestId++;
    }

    /**
     * generateRandomRequest:
     * Gera requisição de tamanho aleatório e o retorna.
     */
    public Request generateRandomRequest (int minRequestSize, int maxRequestSize) {
        Random randomizer = new Random();
        Request memRequest = new Request(lastRequestId, randomizer.nextInt(maxRequestSize - minRequestSize + 1) + minRequestSize);
        this.incrementLastRequestId();
        System.out.println("Requisição " + memRequest.getId()  + " de " + memRequest.getSize() + " kByte(s) gerada.");
        return memRequest;
    }
}
