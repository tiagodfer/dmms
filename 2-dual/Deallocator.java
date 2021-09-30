import java.util.Random;

public class Deallocator {
    private int minThreshold;
    private Heap heap;

    public Deallocator (int MIN_THRESHOLD, Heap heap) {
        this.minThreshold = MIN_THRESHOLD;
        this.heap = heap;
    }

    /**
     * Getters
     */
    public int getMinThreshold () {
        return this.minThreshold;
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
}
