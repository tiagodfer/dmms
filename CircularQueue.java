import java.util.ArrayList;

public final class CircularQueue {
    private int initialPosition;
    private int finalPosition;
    private MemRequest[] queue;
    private int size;
    private int elements;
    
    public CircularQueue(int size) {
        this.queue = new MemRequest[size];
        this.size = size;
        this.initialPosition = 0;
        this.finalPosition = 0;
        this.elements = 0;
    }

    public boolean isFull() {
        return this.elements == this.size;
    }

    public void addRequest(MemRequest object) {
        if (!isFull()) {
            this.queue[initialPosition++] = object;
            this.elements++;
            if (this.finalPosition == this.size) this.finalPosition = 0;
        }
    }

    public boolean isEmpty() {
        return this.elements == 0;
    }

    public void removeRequest() {
        if (!isEmpty()) {
            MemRequest request = queue[initialPosition++];
            this.elements--;
            if (this.initialPosition == this.size) this.initialPosition = 0;
            // inserir código para alocar a requisição na HEAP
        }
    }

    public int getElements() {
        return this.elements;
    }
}
