package DMMS;

import DMMS.memRequest;
// import java.util.Random;
import java.util.ArrayList;

public final class circularQueue {
    private int initialPosition;
    private int finalPosition;
    private memRequest[] queue;
    private int size;
    private int minRequests;
    private int maxRequests;
    private int elements;
    
    public circularQueue(int size, int minRequests, int maxRequests) {
        this.queue = new memRequest[size];
        this.size = size;
        this.minRequests = minRequests;
        this.maxRequests = maxRequests;
        this.initialPosition = 0;
        this.finalPosition = 0;
        this.elements = 0;
    }

    private boolean isFull() {
        return this.elements == this.size;
    }

    private void addRequest(memRequest object) {
        if (!isFull()) {
            this.queue[initialPosition++] = object;
            this.elements++;
            if (this.finalPosition == this.size) this.finalPosition = 0;
        }
    }

    private boolean isEmpty() {
        return this.elements == 0;
    }

    private void removeRequest() {
        if (!isEmpty()) {
            memRequest request = queue[initialPosition++];
            this.elements--;
            if (this.initialPosition == this.size) this.initialPosition = 0;
            // inserir código para alocar a requisição na HEAP
        }
    }

    public int getMinRequests() {
        return this.minRequests;
    }

    public int getMaxRequests() {
        return this.maxRequests;
    }

    public int getElements() {
        return this.elements;
    }
}
