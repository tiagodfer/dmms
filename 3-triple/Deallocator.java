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

    /**
     * kill:
     * Decrementa quantidade de blocos ocupados, marca bloco como desocupado
     * e calcula ocupação do heap.
     */
    public void kill (int block) {
        this.heap.decOccupied(this.heap.getBlock(block).getSize());
        this.heap.calcOccupation();
        System.out.println("Processo " + this.heap.getBlock(block).getRequestId()  + " do bloco " + block + " encerrado.");
        this.heap.getBlock(block).setRequestId(-1);
    }

    /**
     * deallocate:
     * Varre heap em busca de bloco rand-ésimo bloco ocupado e o descupa,
     * repete até alcançar limite mínimo de ocupação do heap.
     * Calcula fragmentação.
     */
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

    /**
     * run:
     * Método principal do desalocador, adquire o semáforo necessário para acesso ao heap,
     * aciona desfragmentador caso necessário.
     */
    @Override
    public void run () {
        boolean work = true;
        while (work) {
            try {
                this.lockHeap.acquire();
                if (this.heap.getOccupation() > this.getMaxOccupation()) {
                    this.deallocate();
                }
                
                if (this.heap.getFragmentation() > this.getMaxFragmentation()) {
                    this.defragger.defrag();
                }
                if (this.heap.getAllocated() >= this.getReqNumber()) {
                    work = false;
                }
            }
            
            catch (InterruptedException e) {
                System.out.println("Erro: " + e + "!");
            }
            
            finally {
                this.lockHeap.release();
            }
        }
    }
}
