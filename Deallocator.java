import java.util.Random;

public class Deallocator {
    private int maxRamUsage;
    private int freeRamThreshold;

    public void setMaxRamUsage (int newMaxRamUsage) {
        this.maxRamUsage = newMaxRamUsage;
    }

    public void setFreeRamThreshold (int newFreeRamThreshold) {
        this.freeRamThreshold = newFreeRamThreshold;
    }

    public int getMaxRamUsage () {
        return this.maxRamUsage;
    }

    public int getFreeRamThreshold () {
        return this.freeRamThreshold;
    }

    public boolean isOverLimit (float occupation) {
        if (occupation >= this.getMaxRamUsage()) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isOverThreshold (float occupation) {
        if (occupation >= this.getFreeRamThreshold()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void kill (Heap heap, int block) {
        heap.getBlock(block).setOccupied(false);
        heap.decOccupation(heap.calcOccupation(heap.getBlock(block).getSize()));
        System.out.println("Processo do bloco " + block + " encerrado.");
    }

    public void deallocate (Queue queue, Heap heap, RequestGenerator generator) {
        if (this.isOverLimit(heap.getOccupation())) {
            while (this.isOverThreshold(heap.getOccupation())) {
                Random randomizer = new Random();
                int rand = randomizer.nextInt(heap.getOccupiedBlocks());
                for (int i = 0; i < heap.getHeapSize(); i++) {
                    if (heap.getBlock(i).isOccupied()) {
                        if (rand-- == 0) {
                            this.kill (heap, i);
                            break;
                        }
                    }
                }
            }
        }
    }
}
