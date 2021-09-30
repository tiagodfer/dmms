import java.util.ArrayList;

public class Heap {
    // heap implementado como ArrayList para facilitar as trocas de posição dos elementos
    // e permitir alteração dinâmica do número de elementos
    private ArrayList<Block> heap;
    private int size;
    private float occupation;
    private float fragmentation;

    public Heap() {
        this.heap = new ArrayList<Block>();
        this.size = 0;
        this.occupation = 0;
        this.fragmentation = 0;
    }

    /**
     * calcFragmentation:
     * Calcula fragmentação do heap, verificando qual é o maior bloco vazio
     * e retornando a razão entre espaço livre e o maior bloco livre.
     */
    public float calcFragmentation () {
        int largestFreeBlockSize = 0;
        float freeSpace = 0;
        for (int i = 0; i < this.getHeapSize(); i++) {
            if (!this.getBlock(i).isOccupied()) {
                if (this.getBlock(i).getSize() > largestFreeBlockSize) {
                    largestFreeBlockSize = this.getBlock(i).getSize();
                }
                freeSpace += (float)this.getBlock(i).getSize();
            }
        }
        return (((freeSpace - largestFreeBlockSize) / freeSpace) * 100);
    }

    /**
     * getOccupiedBlocks:
     * Verifica quantos blocos estão ocupados.
     */
    public int getOccupiedBlocks () {
        int occupied = 0;
        for (int i = 0; i < this.getHeapSize(); i++) {
            if (this.getBlock(i).isOccupied()) {
                occupied++;
            }
        }
        return occupied;
    }
    /**
     * Setters
     */
    public void setSize (int newSize) {
        this.size = newSize;
    }

    public void setOccupation (float newOccupation) {
        this.occupation = newOccupation;
    }

    public void setFragmentation (float newFragmentation) {
        this.fragmentation = newFragmentation;
    }

    public ArrayList<Block> getHeap () {
        return this.heap;
    }

    public Block getBlock (int block) {
        return this.heap.get(block);
    }

    /**
     * Getters
     */
    public int getHeapSize () {
        return this.heap.size();
    }

    public int getSize () {
        return this.size;
    }

    public float getOccupation () {
        return this.occupation;
    }

    public float getFragmentation () {
        return this.fragmentation;
    }

    /**
     * Métodos auxiliares
     */
    public void addBlock () {
        this.heap.add(new Block(0, this.getSize(), false));
    }

    public void decOccupation (float newOccupation) {
        this.occupation -= newOccupation;
    }

    public void incOccupation (float newOccupation) {
        this.occupation += newOccupation;
    }

    public float calcOccupation (int allocSize) {
        return allocSize / (float)this.getSize() * 100;
    }

}
