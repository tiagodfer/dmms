import java.util.ArrayList;

public class HeapMap {
    private ArrayList<Block> heap;
    private int size;
    private float occupation;

    public HeapMap() {
        this.heap = new ArrayList<Block>();
        this.size = 0;
        this.occupation = 0;
    }

    public void setSize (int newSize) {
        this.size = newSize;
    }

    public void setOccupation (float newOccupation) {
        this.occupation = newOccupation;
    }

    public int getOccupiedHeaps () {
        int occupied = 0;
        for (int i = 0; i < this.getBlockSize(); i++) {
            if (this.getBlock(i).getOccupied()) {
                occupied++;
            }
        }
        return occupied;
    }

    public ArrayList<Block> getArray () {
        return this.heap;
    }

    public Block getBlock (int block) {
        return this.heap.get(block);
    }

    public int getBlockSize () {
        return this.heap.size();
    }

    public int getSize () {
        return this.size;
    }

    public float getOccupation () {
        return this.occupation;
    }

    public void addBlock () {
        this.heap.add(new Block(0, this.getSize(), false));
    }

    public void removeOccupation (float newOccupation) {
        this.occupation -= newOccupation;
    }

    public void addOccupation (float newOccupation) {
        this.occupation += newOccupation;
    }

    public float calcOccupation (int allocSize) {
        return allocSize / (float)this.getSize() * 100;
    }
}
