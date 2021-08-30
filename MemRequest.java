public class MemRequest {
    private int id;
    private int size; //words

    public MemRequest(int id, int size) {
        this.id = id; //talvez usar tipo AtomicInteger (java.util.concurrent.atomic) para automatizar o incremento
                      //https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html
        this.size = size;
    }

    public int getId() {
        return this.id;
    }

    public int getSize() {
        return this.size;
    }
}
