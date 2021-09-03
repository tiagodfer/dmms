public class HeapMap {
    public int[][] structure; //talvez mudar a visibilidade e implementar métodos SET e GET
    public int[] heap; //idem ao anterior
    private int lastId;
    private int size;
    private int occupation;

    public HeapMap() {
        this.size = 0;
        this.occupation = 0;
        this.lastId = 0;
        this.heap = new int[size];
        this.structure = new int[size][3];
        clearHeap(this.size, 3);
    }

    private void clearHeap(int size, int columns) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < columns; j++) {
                this.structure[i][j] = 0;
            }
            this.heap[i] = 0;
        }
    }

    //Verificar se haverá necessidade de SETTERS e se necessitamos de todos esses GETTERS
    public void setSize (int newSize) {
        this.size = newSize;
    }

    public int getLastId() {
        return this.lastId;
    }

    public int getSize() {
        return this.size;
    }

    public int getOccupation() {
        return this.occupation;
    }
}
