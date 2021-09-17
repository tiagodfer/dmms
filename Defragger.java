public class Defragger {
    private int maxFragmentation;

    public void setMaxFragmentation (int newMaxFragmentation) {
        this.maxFragmentation = newMaxFragmentation;
    }

    public int getMaxFragmentation () {
        return this.maxFragmentation;
    }

    public void defragBlocks (Heap heap) {
        for (int i = 0; i < heap.getHeapSize(); i++) {
            Block temp;
            if (heap.getBlock(i).isOccupied()) {
                temp = heap.getBlock(i);
                temp.setStart(0);
                heap.getHeap().remove(i);
                heap.getHeap().add(0, temp);
            }
        }
        for (int i = 1; i < heap.getHeapSize(); i++) {
            heap.getBlock(i).setStart(heap.getBlock(i - 1).getSize() + heap.getBlock(i - 1).getStart());
        }
    }

    public void mergeBlock (Heap heap, int block) {
        if (block != (heap.getHeapSize() - 1)) {
            if (!heap.getBlock(block + 1).isOccupied()) {
                heap.getBlock(block).addSize(heap.getBlock(block + 1).getSize());
                heap.getHeap().remove(block + 1);
                System.out.println("Bloco " + block + " e seu sucessor unidos.");
            }
        }
        if (block != 0) {
            if (!heap.getBlock(block - 1).isOccupied()) {
                heap.getBlock(block - 1).addSize(heap.getBlock(block).getSize());
                heap.getHeap().remove(block);
                System.out.println("Bloco " + block + " e seu antecessor unidos.");
            }
        }
    }

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
