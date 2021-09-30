public class Block {
    private int start;
    private int size;
    private boolean occupied;

    public Block (int start, int size, boolean occupied) {
        this.start = start;
        this.size = size;
        this.occupied = occupied;
    }

    /**
     * Setters
     */
    public void setStart (int newStart) {
        this.start = newStart;
    }

    public void setSize (int newSize) {
        this.size = newSize;
    }

    public void setOccupied (boolean newOccupied) {
        this.occupied = newOccupied;
    }

    /**
     * Getters
     */
    public int getStart () {
        return this.start;
    }

    public int getSize () {
        return this.size;
    }

    /**
     * Métodos auxiliares
     */
    public void addSize (int newSize) {
        this.size += newSize;
    }

    public boolean isOccupied () {
        return this.occupied;
    }
}
