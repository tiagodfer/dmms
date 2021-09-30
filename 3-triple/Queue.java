public class Queue {
    private int initialPosition;
    private int finalPosition;
    private Request requests[];
    private int size;
    private int elements;
    
    public Queue(int size) {
        this.requests = new Request[size];
        this.size = size;
        this.initialPosition = -1;
        this.finalPosition = -1;
        this.elements = 0;
    }

    /**
     * Setters
     */
    public void setInitialPosition (int newInitialPosition) {
        this.initialPosition = newInitialPosition;
    }

    public void setFinalPosition (int newFinalPosition) {
        this.finalPosition = newFinalPosition;
    }

    public void setElements (int newElements) {
        this.elements = newElements;
    }

    /**
     * Getters
     */
    public int getInitialPosition () {
        return this.initialPosition;
    }

    public int getFinalPosition () {
        return this.finalPosition;
    }

    public Request[] getRequests () {
        return this.requests;
    }

    public int getSize () {
        return this.size;
    }
    
    public int getElements () {
        return this.elements;
    }
    
    /**
     * Métodos auxiliares
     */
    public boolean isFull() {
        return this.elements == this.size;
    }

    public boolean isEmpty() {
        return this.elements == 0;
    }
}
