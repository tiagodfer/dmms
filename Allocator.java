public class Allocator {
    public boolean firstFit (MemRequest allocRequest, HeapMap memHeap) {
        boolean isAllocated = false;
        for (int i = 0; i < memHeap.getHeapSize(); i++) {
            if (!memHeap.getBlock(i).getOccupied() && memHeap.getBlock(i).getSize() >= allocRequest.getSize()) {
                if (memHeap.getBlock(i).getSize() > allocRequest.getSize()) {
                    memHeap.getArray().add(new Block((allocRequest.getSize() + memHeap.getBlock(i).getStart()), (memHeap.getBlock(i).getSize() - allocRequest.getSize()), false));
                    System.out.println("Criado novo bloco iniciado em " + memHeap.getBlock(i + 1).getStart() + " de " + memHeap.getBlock(i + 1).getSize() + " byte(s).");
                }
                memHeap.getBlock(i).setSize(allocRequest.getSize());
                memHeap.getBlock(i).setOccupied(true);
                memHeap.addOccupation(memHeap.calcOccupation(allocRequest.getSize()));
                System.out.println("Requisição " + allocRequest.getId()  + " alocada em memória do endereço " + memHeap.getBlock(i).getStart() + " até o endereço " + (memHeap.getBlock(i).getSize() + memHeap.getBlock(i).getStart() - 1) + ".");
                System.out.println("Heap está " + memHeap.getOccupation() + "% ocupado.");
                isAllocated = true;
                break;
            }
        }
        return isAllocated;
    }

    public void allocate (MemRequest allocRequest, CircularQueue queue, HeapMap memHeap) {
        allocRequest = queue.getRequest();
        if (this.firstFit(allocRequest, memHeap)) {
            queue.removeRequest();
        }
    }
}
