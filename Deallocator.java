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

    public void mergeBlocks (HeapMap map, int block) {
        if (block != (map.getBlockSize() - 1)) {
            if (!map.getBlock(block + 1).getOccupied()) {
                map.getBlock(block).addSize(map.getBlock(block + 1).getSize());
                map.getArray().remove(block + 1);
                System.out.println("Bloco " + block + " e seu sucessor unidos.");
            }
        }
        if (block != 0) {
            if (!map.getBlock(block - 1).getOccupied()) {
                map.getBlock(block - 1).addSize(map.getBlock(block).getSize());
                map.getArray().remove(block);
                System.out.println("Bloco " + block + " e seu antecessor unidos.");
            }
        }
    }

    public void kill (HeapMap map, int block) {
        map.getBlock(block).setOccupied(false);
        map.removeOccupation(map.calcOccupation(map.getBlock(block).getSize()));
        System.out.println("Processo do bloco " + block + " encerrado.");
    }

    public void deallocate (Queue queue, HeapMap map, RequestGenerator generator) {
        if (map.getOccupiedHeaps() != 0) {
            if (this.isFull(map.getOccupation())) {
                while (map.getOccupation() > this.getFreeRamThreshold()) {
                    Random randomizer = new Random();
                    int rand = randomizer.nextInt(map.getOccupiedHeaps());
                    for (int i = 0; i < map.getBlockSize(); i++) {
                        if (map.getBlock(i).getOccupied()) {
                            if (rand == 0) {
                                this.kill (map, i);
                                this.mergeBlocks (map, i);
                                break;
                            }
                            rand--;
                        }
                    }
                }
            }
            else if (queue.isFull() || generator.getLastRequestId() == generator.getRequestsQuantity()) {
                Random randomizer = new Random();
                int rand = randomizer.nextInt(map.getOccupiedHeaps());
                for (int i = 0; i < map.getBlockSize(); i++) {
                    if (map.getBlock(i).getOccupied()) {
                        if (rand == 0) {
                            this.kill (map, i);
                            this.mergeBlocks (map, i);
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
