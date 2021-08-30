public class MemRequestGenerator {
    private long minRequestSize;
    private long maxRequestSize;
    private int requestsQuantity;

    public void setMinRequestSize (long newMinRequestSize) {
        this.minRequestSize = newMinRequestSize;
    }
    
    public void setMaxRequestSize (long newMaxRequestSize) {
        this.maxRequestSize = newMaxRequestSize;
    }

    public void setRequestsQuantity (int newRequestQuantity) {
        this.requestsQuantity = newRequestQuantity;
    }

    public long getMinRequestSize () {
        return this.minRequestSize;
    }

    public long getMaxRequestSize () {
        return this.maxRequestSize;
    }

    public int getRequestsQuantity () {
        return this.requestsQuantity;
    }

    public void generateRandomRequest () {
        System.out.println("Requisição gerada.");
    }
}
