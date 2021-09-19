public class Defragger {//extends Thread {
    private Heap heap;

    public Defragger (Heap heap) {
        this.heap = heap;
    }

    public void defragBlocks () {
        for (int i = 0; i < this.heap.getArraySize(); i++) {
            Block temp;
            if (this.heap.getBlock(i).isOccupied()) {
                temp = this.heap.getBlock(i);
                temp.setStart(0);
                this.heap.getArray().remove(i);
                this.heap.getArray().add(0, temp);
            }
        }
        for (int i = 1; i < this.heap.getArraySize(); i++) {
            this.heap.getBlock(i).setStart(this.heap.getBlock(i - 1).getSize() + this.heap.getBlock(i - 1).getStart());
        }
    }

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

    public void defrag () {
        for (int i = 0; i < this.heap.getArraySize(); i++) {
            if (!this.heap.getBlock(i).isOccupied()) {
                this.mergeBlock(i);
            }
        }
        this.defragBlocks();
        this.heap.calcFragmentation();
    }

    /*
    @Override
    public void run () {
        boolean work = true;
        while (this.heap.getAllocated() < DMMS.REQ_NUMBER) {
            try {
                DMMS.fullHeap.acquire();
                DMMS.lockHeap.acquire();
                if (this.heap.getFragmentation() > DMMS.MAX_FRAGMENTATION) {
                    this.defrag();
                    this.heap.calcFragmentation();
                }
                for (int i = 0; i < this.heap.getArraySize(); i++) {
                    System.out.println(this.heap.getBlock(i).isOccupied() + " " + this.heap.getBlock(i).getStart() + " " + this.heap.getBlock(i).getSize() + " " + this.heap.getFragmentation());
                }
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
            finally {
                DMMS.lockHeap.release();
                DMMS.emptyHeap.release();
            }
            if (this.heap.getAllocated() >= DMMS.REQ_NUMBER) {
                work = false;
            }
        }
    }
    */
}
