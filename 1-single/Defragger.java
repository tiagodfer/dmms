public class Defragger {
    private int maxFragmentation;

    /**
     * Setters
     */
    public void setMaxFragmentation (int newMaxFragmentation) {
        this.maxFragmentation = newMaxFragmentation;
    }

    /**
     * Getters
     */
    public int getMaxFragmentation () {
        return this.maxFragmentation;
    }

    /**
     * defragBlocks:
     * Traz blocos ocupados para inicio do arraylist e recalcula suas posições de inicio.
     */
    public void defragBlocks (Heap heap) {
        // laço que traz blocos ocupados para o início do arraylist
        for (int i = 0; i < heap.getHeapSize(); i++) {
            Block temp;
            if (heap.getBlock(i).isOccupied()) {
                temp = heap.getBlock(i);
                temp.setStart(0);
                heap.getHeap().remove(i);
                heap.getHeap().add(0, temp);
            }
        }
        // laço que recalcula posições de inicio dos blocos
        for (int i = 1; i < heap.getHeapSize(); i++) {
            heap.getBlock(i).setStart(heap.getBlock(i - 1).getSize() + heap.getBlock(i - 1).getStart());
        }
    }

    /**
     * mergeBlock:
     * Une blocos vazios adjacentes.
     */
    public void mergeBlock (Heap heap, int block) {
        // verifica se bloco anterior é vazio, caso positivo, une-os
        if (block != (heap.getHeapSize() - 1)) {
            if (!heap.getBlock(block + 1).isOccupied()) {
                heap.getBlock(block).addSize(heap.getBlock(block + 1).getSize());
                heap.getHeap().remove(block + 1);
                System.out.println("Bloco " + block + " e seu sucessor unidos.");
            }
        }
        // verifica se bloco posterior é vazio, caso positivo, une-os
        if (block != 0) {
            if (!heap.getBlock(block - 1).isOccupied()) {
                heap.getBlock(block - 1).addSize(heap.getBlock(block).getSize());
                heap.getHeap().remove(block);
                System.out.println("Bloco " + block + " e seu antecessor unidos.");
            }
        }
    }

    /**
     * defragHeap:
     * Caso fragmentação esteja acima do limite, desfragmenta o heap de memória.
     */
    public void defragHeap (Heap heap) {
        if (heap.getFragmentation() > this.getMaxFragmentation()) {
            for (int i = 0; i < heap.getHeapSize(); i++) {
                if (!heap.getBlock(i).isOccupied()) {
                    this.mergeBlock(heap, i);
                }
            }
            defragBlocks(heap);
        }
    }
}
