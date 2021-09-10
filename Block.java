public class Block {
    private int start;
    private int size;
    private boolean occupied;

    public Block (int start, int size, boolean occupied) {
        this.start = start;
        this.size = size;
        this.occupied = occupied;
    }

    public void addSize (int newSize) {
        this.size += newSize;
    }

    public void setStart (int newStart) {
        this.start = newStart;
    }

    public void setSize (int newSize) {
        this.size = newSize;
    }

    public void setOccupied (boolean newOccupied) {
        this.occupied = newOccupied;
    }

    public int getStart () {
        return this.start;
    }

    public int getSize () {
        return this.size;
    }

    public boolean isOccupied () {
        return this.occupied;
    }
}
