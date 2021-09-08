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

    public boolean isFull (float occupation) {
        if (occupation >= this.getMaxRamUsage()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void mergeBlocks (HeapMap memHeap, int block) {
        if (block != (memHeap.getHeapSize() - 1)) {
            if (!memHeap.getBlock(block + 1).getOccupied()) {
                memHeap.getBlock(block).addSize(memHeap.getBlock(block + 1).getSize());
                memHeap.getArray().remove(block + 1);
                System.out.println("Bloco " + block + " e seu sucessor unidos.");
            }
        }
        if (block != 0) {
            if (!memHeap.getBlock(block - 1).getOccupied()) {
                memHeap.getBlock(block - 1).addSize(memHeap.getBlock(block).getSize());
                memHeap.getArray().remove(block);
                System.out.println("Bloco " + block + " e seu antecessor unidos.");
            }
        }
    }

    public void kill (HeapMap memHeap, int block) {
        memHeap.getBlock(block).setOccupied(false);
        memHeap.removeOccupation(memHeap.calcOccupation(memHeap.getBlock(block).getSize()));
    }

    public void deallocate (CircularQueue queue, HeapMap memHeap) {
        if (this.isFull(memHeap.getOccupation()) || !queue.isEmpty()) {
            while (memHeap.getOccupation() > this.getFreeRamThreshold()) {
                Random randomizer = new Random();
                int rand = randomizer.nextInt(memHeap.getOccupiedHeaps());
                for (int i = 0; i < memHeap.getHeapSize(); i++) {
                    if (memHeap.getBlock(i).getOccupied()) {
                        if (rand == 0) {
                            this.kill (memHeap, i);
                            this.mergeBlocks (memHeap, i);
                            break;
                        }
                        rand--;
                    }
                }
            }
        }
    }
}
