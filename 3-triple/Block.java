public class Block {
    private int requestId;
    private int start;
    private int size;

    public Block (int requestId, int start, int size) {
        this.requestId = requestId;
        this.start = start;
        this.size = size;
    }

    /**
     * Setters
     */
    public void setRequestId (int newRequestId) {
        this.requestId = newRequestId;
    }

    public void setStart (int newStart) {
        this.start = newStart;
    }

    public void setSize (int newSize) {
        this.size = newSize;
    }

    /**
     * Getters
     */
    public int getRequestId () {
        return this.requestId;
    }

    public int getStart () {
        return this.start;
    }

    public int getSize () {
        return this.size;
    }

    public int getEnd () {
        return this.size + this.start - 1;
    }

    /**
     * Métodos auxiliares
     */
    public boolean isOccupied () {
        if (this.getRequestId() == - 1) {
            return false;
        }
        else {
            return true;
        }
    }

    public void incSize (int newSize) {
        this.size += newSize;
    }
}
