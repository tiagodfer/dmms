package DMMS;

public class memRequest {
    private int id;
    private int size; //words

    public memRequest(int id, int size) {
        this.id = id; //talvez usar tipo AtomicInteger (java.util.concurrent.atomic) para automatizar o incremento
                      //https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/atomic/AtomicInteger.html
        this.size = size;
    }
}