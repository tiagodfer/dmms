public class Allocator {
    /**
     * firstFit:
     * Verifica, em ordem crescente, se há um bloco vazio e de tamanho suficiente para receber a requisição.
     */
    public boolean firstFit (DMMS dmms, Request allocRequest, Heap heap) {
        boolean isAllocated = false;

        for (int i = 0; i < heap.getHeapSize(); i++) {
            if (!heap.getBlock(i).isOccupied() && heap.getBlock(i).getSize() >= allocRequest.getSize()) {
                // caso bloco seja maior que o necessário, será dividido
                if (heap.getBlock(i).getSize() > allocRequest.getSize()) {
                    heap.getHeap().add((i + 1), new Block((allocRequest.getSize() + heap.getBlock(i).getStart()), (heap.getBlock(i).getSize() - allocRequest.getSize()), false));
                    System.out.println("Criado novo bloco iniciado em " + heap.getBlock(i + 1).getStart() + " de " + heap.getBlock(i + 1).getSize() + " byte(s).");
                }
                // novas infos do bloco
                heap.getBlock(i).setSize(allocRequest.getSize());
                heap.getBlock(i).setOccupied(true);
                // calcula ocupação e fragmentação
                heap.incOccupation(heap.calcOccupation(allocRequest.getSize()));
                heap.setFragmentation(heap.calcFragmentation());
                // prints
                System.out.println("Requisição " + allocRequest.getId()  + " alocada em memória do endereço " + heap.getBlock(i).getStart() + " até o endereço " + (heap.getBlock(i).getSize() + heap.getBlock(i).getStart() - 1) + ".");
                System.out.println("Heap está " + heap.getOccupation() + "% ocupado.");
                System.out.println("Heap está " + heap.getFragmentation() + "% fragmentado.");
                // incrementa requisições atendidas
                dmms.setFinishedRequests(dmms.getFinishedRequests() + 1);
                isAllocated = true;
                break;
            }
        }
        return isAllocated;
    }

    public void allocate (DMMS dmms, Request allocRequest, Queue queue, Heap heap) {
        // caso fila contenha requisições
        if (!queue.isEmpty()) {
            // copia dados da requisição
            allocRequest = queue.getRequest();
            // caso possa ser alocada, aloca no heap e remove da fila
            if (this.firstFit(dmms, allocRequest, heap)) {
                queue.removeRequest();
            }
        }
    }
}
