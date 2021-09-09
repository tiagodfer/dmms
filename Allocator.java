public class Allocator {
    public boolean firstFit (DMMS dmms, Request allocRequest, HeapMap map) {
        boolean isAllocated = false;
        for (int i = 0; i < map.getBlockSize(); i++) {
            if (!map.getBlock(i).getOccupied() && map.getBlock(i).getSize() >= allocRequest.getSize()) {
                if (map.getBlock(i).getSize() > allocRequest.getSize()) {
                    map.getArray().add((i + 1), new Block((allocRequest.getSize() + map.getBlock(i).getStart()), (map.getBlock(i).getSize() - allocRequest.getSize()), false));
                    System.out.println("Criado novo bloco iniciado em " + map.getBlock(i + 1).getStart() + " de " + map.getBlock(i + 1).getSize() + " byte(s).");
                }
                map.getBlock(i).setSize(allocRequest.getSize());
                map.getBlock(i).setOccupied(true);
                map.addOccupation(map.calcOccupation(allocRequest.getSize()));
                System.out.println("Requisição " + allocRequest.getId()  + " alocada em memória do endereço " + map.getBlock(i).getStart() + " até o endereço " + (map.getBlock(i).getSize() + map.getBlock(i).getStart() - 1) + ".");
                System.out.println("Heap está " + map.getOccupation() + "% ocupado.");
                dmms.setFinishedRequests(dmms.getFinishedRequests() + 1);
                isAllocated = true;
                break;
            }
        }
        return isAllocated;
    }

    public void allocate (DMMS dmms, Request allocRequest, Queue queue, HeapMap map) {
        if (!queue.isEmpty()) {
            allocRequest = queue.getRequest();
            if (this.firstFit(dmms, allocRequest, map)) {
                queue.removeRequest();
            }
        }
    }
}
