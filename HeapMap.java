package DMMS;

public class heapMap {
    public int[][] heapStrucutre; //talvez mudar a visibilidade e implementar métodos SET e GET
    public int[] heap; //idem ao anterior
    private int lastId;
    private int heapSize;
    private float occupationLimit; //percentual (entre zero e 1)
    private float freeMemoryLimit; //percentual (entre zero e 1)
    private int heapOccupation;

    public heapMap(float occupationLimit, int heapSize) {
        this.heapSize = heapSize;
        this.occupationLimit = occupationLimit;
        this.heapOccupation = 0;
        this.lastId = 0;
        this.heap = new int[heapSize];
        this.heapStrucutre = new int[heapSize][3];
        clearHeap(this.heapSize, 3);
    }

    private clearHeap(int heapSize, int columns) {
        for (int i = 0; i < heapSize; i++) {
            for (int j = 0; j < columns; j++) {
                this.heapStructure[i][j] = 0;
            }
            this.heap[i] = 0;
        }
    }

    //Verificar se haverá necessidade de SETTERS e se necessitamos de todos esses GETTERS
    public int getLastId() {
        return this.lastId;
    }

    public int getHeapSize() {
        return this.heapSize;
    }

    public int getOccupationLimit() {
        return this.occupationLimit;
    }

    public int getFreeMemoryLimit() {
        return this.heapOccupation;
    }

    public int getHeapOccupation() {
        return this.heapOccupation;
    }
}
