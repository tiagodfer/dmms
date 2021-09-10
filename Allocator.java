public class Allocator {
    public boolean firstFit (DMMS dmms, Request allocRequest, Heap heap) {
        boolean isAllocated = false;
        for (int i = 0; i < heap.getBlockSize(); i++) {
            if (!heap.getBlock(i).isOccupied() && heap.getBlock(i).getSize() >= allocRequest.getSize()) {
                if (heap.getBlock(i).getSize() > allocRequest.getSize()) {
                    heap.getHeap().add((i + 1), new Block((allocRequest.getSize() + heap.getBlock(i).getStart()), (heap.getBlock(i).getSize() - allocRequest.getSize()), false));
                    System.out.println("Criado novo bloco iniciado em " + heap.getBlock(i + 1).getStart() + " de " + heap.getBlock(i + 1).getSize() + " byte(s).");
                }
                heap.getBlock(i).setSize(allocRequest.getSize());
                heap.getBlock(i).setOccupied(true);
                heap.addOccupation(heap.calcOccupation(allocRequest.getSize()));
                heap.setFragmentation(heap.calcFragmentation());
                System.out.println("Requisição " + allocRequest.getId()  + " alocada em memória do endereço " + heap.getBlock(i).getStart() + " até o endereço " + (heap.getBlock(i).getSize() + heap.getBlock(i).getStart() - 1) + ".");
                System.out.println("Heap está " + heap.getOccupation() + "% ocupado.");
                System.out.println("Heap está " + heap.getFragmentation() + "% fragmentado.");
                dmms.setFinishedRequests(dmms.getFinishedRequests() + 1);
                isAllocated = true;
                break;
            }
        }
        return isAllocated;
    }

    public void allocate (DMMS dmms, Request allocRequest, Queue queue, Heap heap) {
        if (!queue.isEmpty()) {
            allocRequest = queue.getRequest();
            if (this.firstFit(dmms, allocRequest, heap)) {
                queue.removeRequest();
            }
        }
    }
}
