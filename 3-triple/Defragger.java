public class Defragger {
    private Heap heap;

    public Defragger (Heap heap) {
        this.heap = heap;
    }

    /**
     * defragBlocks:
     * Coloca todos os blocos ocupados no início do heap e realiza ajustes necessários.
     */
    public void defragBlocks () {
        // coloca blocos ocupados no início do heap
        for (int i = 0; i < this.heap.getArraySize(); i++) {
            Block temp;
            if (this.heap.getBlock(i).isOccupied()) {
                temp = this.heap.getBlock(i);
                temp.setStart(0);
                this.heap.getArray().remove(i);
                this.heap.getArray().add(0, temp);
            }
        }
        // calcula novas posições de inicio dos blocos
        for (int i = 1; i < this.heap.getArraySize(); i++) {
            this.heap.getBlock(i).setStart(this.heap.getBlock(i - 1).getSize() + this.heap.getBlock(i - 1).getStart());
        }
    }

    /**
     * mergeBlock:
     * Une bloco vazio com seu antecessor vazio e sucessor vazio, se houver.
     */
    public void mergeBlock (int block) {
        if (block != (this.heap.getArraySize() - 1)) {
            if (!this.heap.getBlock(block + 1).isOccupied()) {
                this.heap.getBlock(block).incSize(this.heap.getBlock(block + 1).getSize());
                this.heap.getArray().remove(block + 1);
                System.out.println("Bloco " + block + " e seu sucessor unidos.");
            }
        }
        if (block != 0) {
            if (!this.heap.getBlock(block - 1).isOccupied()) {
                this.heap.getBlock(block - 1).incSize(this.heap.getBlock(block).getSize());
                this.heap.getArray().remove(block);
                System.out.println("Bloco " + block + " e seu antecessor unidos.");
            }
        }
    }

    /**
     * defrag:
     * Método principal, varre todos blocos vazios do heap e os une,
     * desfragmenta heap e atualiza índice de fragmentação.
     */
    public void defrag () {
        this.defragBlocks();
        for (int i = 0; i < this.heap.getArraySize(); i++) {
            if (!this.heap.getBlock(i).isOccupied()) {
                this.mergeBlock(i);
            }
        }
        this.heap.calcFragmentation();
    }
}
