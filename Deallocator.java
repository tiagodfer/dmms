import java.util.Random;

public class Deallocator {
    private int maxRamUsage;
    private int freeRamThreshold;
    private int maxFragmentation;

    public void setMaxRamUsage (int newMaxRamUsage) {
        this.maxRamUsage = newMaxRamUsage;
    }

    public void setFreeRamThreshold (int newFreeRamThreshold) {
        this.freeRamThreshold = newFreeRamThreshold;
    }

    public void setMaxFragmentation (int newMaxFragmentation) {
        this.maxFragmentation = newMaxFragmentation;
    }

    public int getMaxRamUsage () {
        return this.maxRamUsage;
    }

    public int getFreeRamThreshold () {
        return this.freeRamThreshold;
    }

    public int getMaxFragmentation () {
        return this.maxFragmentation;
    }

    public boolean isOverLimit (float occupation) {
        if (occupation >= this.getMaxRamUsage()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void mergeBlocks (Heap heap, int block) {
        if (block != (heap.getBlockSize() - 1)) {
            if (!heap.getBlock(block + 1).isOccupied()) {
                heap.getBlock(block).addSize(heap.getBlock(block + 1).getSize());
                heap.getHeap().remove(block + 1);
                System.out.println("Bloco " + block + " e seu sucessor unidos.");
            }
        }
        if (block != 0) {
            if (!heap.getBlock(block - 1).isOccupied()) {
                heap.getBlock(block - 1).addSize(heap.getBlock(block).getSize());
                heap.getHeap().remove(block);
                System.out.println("Bloco " + block + " e seu antecessor unidos.");
            }
        }
    }

    public void kill (Heap heap, int block) {
        heap.getBlock(block).setOccupied(false);
        heap.removeOccupation(heap.calcOccupation(heap.getBlock(block).getSize()));
        System.out.println("Processo do bloco " + block + " encerrado.");
    }

    public void deallocate (Queue queue, Heap heap, RequestGenerator generator) {
        if (heap.isOccupiedHeaps() != 0) {
            if (this.isOverLimit(heap.getOccupation()) || heap.getFragmentation() > this.getMaxFragmentation()) {
                while (heap.getOccupation() > this.getFreeRamThreshold()) {
                    Random randomizer = new Random();
                    int rand = randomizer.nextInt(heap.isOccupiedHeaps());
                    for (int i = 0; i < heap.getBlockSize(); i++) {
                        if (heap.getBlock(i).isOccupied()) {
                            if (rand == 0) {
                                this.kill (heap, i);
                                this.mergeBlocks (heap, i);
                                break;
                            }
                            rand--;
                        }
                    }
                }
            }
            else if (queue.isFull()) {
                Random randomizer = new Random();
                int rand = randomizer.nextInt(heap.isOccupiedHeaps());
                for (int i = 0; i < heap.getBlockSize(); i++) {
                    if (heap.getBlock(i).isOccupied()) {
                        if (rand == 0) {
                            this.kill (heap, i);
                            this.mergeBlocks (heap, i);
                            break;
                        }
                        rand--;
                    }
                }
            }
            else {
                System.out.println("Desalocador desnecessário.");
            }
        }
    }
}
