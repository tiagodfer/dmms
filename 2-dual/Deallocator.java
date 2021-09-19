import java.util.Random;

public class Deallocator {//extends Thread {
    private int minThreshold;
    private Heap heap;

    public Deallocator (int MIN_THRESHOLD, Heap heap) {
        this.minThreshold = MIN_THRESHOLD;
        this.heap = heap;
    }

    public int getMinThreshold () {
        return this.minThreshold;
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
    
    /*
    @Override
    public void run () {
        boolean work = true;
        while (work) {
            try {
                DMMS.fullHeap.acquire();
                DMMS.lockHeap.acquire();
                if (this.heap.getOccupation() > DMMS.MAX_OCCUPATION) {
                    this.deallocate();
                    for (int i = 0; i < this.heap.getArraySize(); i++) {
                        System.out.println(this.heap.getBlock(i).isOccupied() + " " + this.heap.getBlock(i).getStart() + " " + this.heap.getBlock(i).getSize() + " " + this.heap.getFragmentation());
                    }
                }
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
            finally {
                DMMS.lockHeap.release();
                DMMS.emptyHeap.release();
            }
            if (this.heap.getAllocated() >= DMMS.REQ_NUMBER) {
                work = false;
            }
        }
    }
    */
}
