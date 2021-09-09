public class Allocator {
    public boolean firstFit (DMMS dmms, MemRequest allocRequest, HeapMap memHeap) {
        boolean isAllocated = false;
        for (int i = 0; i < memHeap.getHeapSize(); i++) {
            if (!memHeap.getBlock(i).getOccupied() && memHeap.getBlock(i).getSize() >= allocRequest.getSize()) {
                if (memHeap.getBlock(i).getSize() > allocRequest.getSize()) {
                    memHeap.getArray().add((i + 1), new Block((allocRequest.getSize() + memHeap.getBlock(i).getStart()), (memHeap.getBlock(i).getSize() - allocRequest.getSize()), false));
                    System.out.println("Criado novo bloco iniciado em " + memHeap.getBlock(i + 1).getStart() + " de " + memHeap.getBlock(i + 1).getSize() + " byte(s).");
                }
                memHeap.getBlock(i).setSize(allocRequest.getSize());
                memHeap.getBlock(i).setOccupied(true);
                memHeap.addOccupation(memHeap.calcOccupation(allocRequest.getSize()));
                System.out.println("Requisição " + allocRequest.getId()  + " alocada em memória do endereço " + memHeap.getBlock(i).getStart() + " até o endereço " + (memHeap.getBlock(i).getSize() + memHeap.getBlock(i).getStart() - 1) + ".");
                System.out.println("Heap está " + memHeap.getOccupation() + "% ocupado.");
                dmms.setFinishedRequests(dmms.getFinishedRequests() + 1);
                isAllocated = true;
                break;
            }
        }
        return isAllocated;
    }

    public void allocate (DMMS dmms, MemRequest allocRequest, CircularQueue queue, HeapMap memHeap) {
        if (!queue.isEmpty()) {
            allocRequest = queue.getRequest();
            if (this.firstFit(dmms, allocRequest, memHeap)) {
                queue.removeRequest();
            }
        }
    }
}
