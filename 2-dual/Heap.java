import java.util.ArrayList;

public class Heap {
    private ArrayList<Block> heap;
    private int size;
    private int free;
    private int occupied;
    private int allocated;
    private float occupation;
    private float fragmentation;

    public Heap (int HEAP_SIZE) {
        this.heap = new ArrayList<Block>();
        this.size = HEAP_SIZE;
        this.occupied = 0;
        this.allocated = 0;
        this.occupation = 0;
        this.fragmentation = 0;
        this.getArray().add(new Block(-1, 0, this.getSize()));
    }

    /**
     * Setters
     */
    public void setSize (int newSize) {
        this.size = newSize;
    }
    
    public void setFree (int newFree) {
        this.free = newFree;
    }

    public void setOccupied (int newOccupied) {
        this.occupied = newOccupied;
    }

    public void setAllocated (int newAllocated) {
        this.allocated = newAllocated;
    }

    public void setOccupation (float newOccupation) {
        this.occupation = newOccupation;
    }

    public void setFragmentation (float newFragmentation) {
        this.fragmentation = newFragmentation;
    }
    
    /**
     * Getters
     */
    public ArrayList<Block> getArray () {
        return this.heap;
    }

    public Block getBlock (int block) {
        return this.heap.get(block);
    }

    public int getArraySize () {
        return this.heap.size();
    }

    public int getSize () {
        return this.size;
    }

    public int getFree () {
        return this.free;
    }

    public int getOccupied () {
        return this.occupied;
    }

    public int getAllocated () {
        return this.allocated;
    }

    public float getOccupation () {
        return this.occupation;
    }

    public float getFragmentation () {
        return this.fragmentation;
    }
    
    /**
     * Métodos auxiliares
     */
    public boolean isFull () {
        if (this.getOccupied() == this.getSize()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void decOccupied (int newOccupied) {
        this.occupied -= newOccupied;
    }

    public void incOccupied (int newOccupied) {
        this.occupied += newOccupied;
    }

    public void incAllocated () {
        this.allocated += 1;
    }

    public void calcFree () {
        this.free = this.getSize() - this.getOccupied();
    }

    public void calcOccupation () {
        this.occupation = (this.getOccupied() / (float)this.getSize()) * 100;
    }

    public int getOccupiedBlocks () {
        int occupied = 0;
        for (int i = 0; i < this.getArraySize(); i++) {
            if (this.getBlock(i).isOccupied()) {
                occupied++;
            }
        }
        return occupied;
    }

    /**
     * calcFragmentation:
     * Encontra maior bloco vazio e divide pelo espaço vazio.
     */
    public void calcFragmentation () {
        if (this.isFull()) {
            this.setFragmentation(0);
        }
        else {
            this.calcFree();
            float largestFreeBlockSize = 0;
            for (int i = 0; i < this.getArraySize(); i++) {
                if (!this.getBlock(i).isOccupied()) {
                    if (this.getBlock(i).getSize() > largestFreeBlockSize) {
                        largestFreeBlockSize = this.getBlock(i).getSize();
                    }
                }
            }
            this.setFragmentation(((this.getFree() - largestFreeBlockSize) / this.getFree()) * 100);
        }
    }
}
