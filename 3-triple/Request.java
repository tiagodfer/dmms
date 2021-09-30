public class Request {
    private int id;
    private int size;

    public Request () {
        this.id = -1;
        this.size = -1;
    }

    public Request (int id, int size) {
        this.id = id;
        this.size = size;
    }

    /**
     * Setters
     */
    public void setId (int newId) {
        this.id = newId;
    }

    public void setSize (int newSize) {
        this.size = newSize;
    }

    /**
     * Getters
     */
    public int getId () {
        return this.id;
    }

    public int getSize () {
        return this.size;
    }
}
