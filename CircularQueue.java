import java.util.ArrayList;

public final class CircularQueue {
    private int initialPosition;
    private int finalPosition;
    private MemRequest queue[];
    private int size;
    private int elements;
    
    public CircularQueue(int size) {
        this.queue = new MemRequest[size];
        this.size = size;
        this.initialPosition = -1;
        this.finalPosition = -1;
        this.elements = 0;
    }

    public boolean isFull() {
        return this.elements == this.size;
    }

    public void addRequest(MemRequest memRequest) {
        if (!isFull()) {
            if (this.initialPosition == - 1) this.setInitialPosition(0);
            this.setFinalPosition(this.getFinalPosition() + 1 % this.getSize());
            this.queue[this.getFinalPosition()] = memRequest;
            this.setElements(this.getElements() + 1);
            System.out.println("Requisição " + queue[this.getFinalPosition()].getId() + " alocada na posição " + this.getFinalPosition() + " da fila circular.");
        }
    }

    public boolean isEmpty() {
        return this.elements == 0;
    }

    public MemRequest removeRequest() {
        MemRequest memRequest = new MemRequest();
        if (!isEmpty()) {
            memRequest = queue[this.getInitialPosition()];
            if (this. getInitialPosition () == this.getFinalPosition()) {
                this.setInitialPosition(-1);
                this.setFinalPosition(-1);
            }
            else {
                this.setInitialPosition(this.getInitialPosition() + 1 % this.getSize());
            }
            this.elements--;
        }
        return memRequest;
    }

    public void setInitialPosition (int newInitialPosition) {
        this.initialPosition = newInitialPosition;
    }

    public void setFinalPosition (int newFinalPosition) {
        this.finalPosition = newFinalPosition;
    }

    public void setElements (int newElements) {
        this.elements = newElements;
    }

    public int getInitialPosition () {
        return this.initialPosition;
    }

    public int getFinalPosition () {
        return this.finalPosition;
    }

    public int getElements () {
        return this.elements;
    }

    public int getSize () {
        return this.size;
    }
}
