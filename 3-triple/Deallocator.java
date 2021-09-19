import java.util.concurrent.Semaphore;
import java.util.Random;

public class Deallocator extends Thread {
    private int maxOccupation;
    private int minThreshold;
    private int reqNumber;
    private int maxFragmentation;
    private Semaphore lockHeap;
    private Defragger defragger;
    private Heap heap;

    public Deallocator (int MAX_OCCUPATION, int MIN_THRESHOLD, int REQ_NUMBER, int MAX_FRAGMENTATION, Semaphore lockHeap, Defragger defragger, Heap heap) {
        this.maxOccupation = MAX_OCCUPATION;
        this.minThreshold = MIN_THRESHOLD;
        this.reqNumber = REQ_NUMBER;
        this.maxFragmentation = MAX_FRAGMENTATION;
        this.defragger = defragger;
        this.lockHeap = lockHeap;
        this.heap = heap;
    }

    public int getMaxOccupation () {
        return this.maxOccupation;
    }

    public int getMinThreshold () {
        return this.minThreshold;
    }

    public int getReqNumber () {
        return this.reqNumber;
    }

    public int getMaxFragmentation () {
        return this.maxFragmentation;
    }

    public void kill (int block) {
        this.heap.decOccupied(this.heap.getBlock(block).getSize());
        this.heap.calcOccupation();
        System.out.println("Processo " + this.heap.getBlock(block).getRequestId()  + " do bloco " + block + " encerrado.");
        this.heap.getBlock(block).setRequestId(-1);
    }

    public void deallocate () {
        while (this.heap.getOccupation() >= this.getMinThreshold()) {
            Random randomizer = new Random();
            int rand = randomizer.nextInt(this.heap.getOccupiedBlocks());
            for (int i = 0; i < this.heap.getArraySize(); i++) {
                if (this.heap.getBlock(i).isOccupied()) {
                    if (rand-- == 0) {
                        this.kill(i);
                        break;
                    }
                }
            }
        }
        this.heap.calcFragmentation();
    }
    
    @Override
    public void run () {
        boolean work = true;
        while (work) {
            try {
                //this.fullHeap.acquire();
                //System.out.println("DEALLOCATOR: acquiring lockHeap.");
                this.lockHeap.acquire();
                //System.out.println("DEALLOCATOR: lockHeap acquired.");
                if (this.heap.getOccupation() > this.getMaxOccupation()) {
                    this.deallocate();
                }
                
                if (this.heap.getFragmentation() > this.getMaxFragmentation()) {
                    this.defragger.defrag();
                }
                
                /*
                for (int i = 0; i < this.heap.getArraySize(); i++) {
                    System.out.println(this.heap.getBlock(i).isOccupied() + " " + this.heap.getBlock(i).getStart() + " " + this.heap.getBlock(i).getSize() + " " + this.heap.getFragmentation());
                }
                */
                if (this.heap.getAllocated() >= this.getReqNumber()) {
                    work = false;
                }
            }
            
            catch (InterruptedException e) {
                System.out.println("Erro: " + e + "!");
            }
            
            finally {
                //System.out.println("DEALLOCATOR: releasing lockHeap.");
                this.lockHeap.release();
                //System.out.println("DEALLOCATOR: lockHeap released.");
                //this.emptyHeap.release();
            }
        }
    }
}
