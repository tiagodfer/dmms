import java.util.ArrayList;

public final class Queue {
    // fila circular implementada como array de tamanho fixo
    private int initialPosition;
    private int finalPosition;
    private Request queue[];
    private int size;
    private int elements;
    
    public Queue(int size) {
        this.queue = new Request[size];
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

    public int getElements () {
        return this.elements;
    }

    public int getSize () {
        return this.size;
    }

    public Request getRequest () {
        Request memRequest = new Request();
        if(!isEmpty()) {
            memRequest = queue[this.getInitialPosition()];
        }
        return memRequest;
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

    /**
     * addRequest:
     * Insere requisição na fila circular.
     */
    public void addRequest(Request memRequest) {
        if (!isFull()) {
            // se a fila estava vazia, corrige posição inicial
            if (this.initialPosition == - 1) {
                this.setInitialPosition(0);
            }
            this.setFinalPosition((this.getFinalPosition() + 1) % this.getSize());
            this.queue[this.getFinalPosition()] = memRequest;
            this.setElements(this.getElements() + 1);
            System.out.println("Requisição " + queue[this.getFinalPosition()].getId() + " alocada na posição " + this.getFinalPosition() + " da fila circular.");
        }
    }

    /**
     * removeRequest:
     * Remove requisição da fila circular.
     */
    public Request removeRequest () {
        Request memRequest = new Request();
        if (!isEmpty()) {
            memRequest = queue[this.getInitialPosition()];
            // se chegou ao final da fila, altera variáveis para indicar que ficou vazia
            if (this. getInitialPosition() == this.getFinalPosition()) {
                this.setInitialPosition(-1);
                this.setFinalPosition(-1);
            }
            else {
                this.setInitialPosition((this.getInitialPosition() + 1) % this.getSize());
            }
            this.setElements(this.getElements() - 1); //atualiza número de elementos na fila
        }
        return memRequest;
    }
}
