import java.util.ArrayList;

public class HeapMap {
    private ArrayList<Block> heap;
    private int size;
    private float occupation;

    public HeapMap() {
        this.heap = new ArrayList<Block>();
        this.size = 0;
        this.occupation = 0;
    }

    public void setSize (int newSize) {
        this.size = newSize;
    }

    public void setOccupation (float newOccupation) {
        this.occupation = newOccupation;
    }

    public int getSize () {
        return this.size;
    }

    public float getOccupation () {
        return this.occupation;
    }

    public void addBlock () {
        this.heap.add(new Block(0, this.getSize(), false));
    }

    public void addOccupation (float newOccupation) {
        this.occupation += newOccupation;
    }

    public void firstFit (MemRequest allocRequest, HeapMap memHeap) {
        for (int i = 0; i < memHeap.heap.size(); i++) {
            //tratar casos em que o espaço solicitado é igual ao restante, não precisando criar novo bloco
            if (!memHeap.heap.get(i).getOccupied() && memHeap.heap.get(i).getSize() >= allocRequest.getSize()) {
                if (memHeap.heap.get(i).getSize() > allocRequest.getSize()) {
                    memHeap.heap.add(new Block((allocRequest.getSize() + memHeap.heap.get(i).getStart()), (memHeap.heap.get(i).getSize() - allocRequest.getSize()), false));
                    System.out.println("Criado novo bloco iniciado em " + memHeap.heap.get(i + 1).getStart() + " de " + memHeap.heap.get(i + 1).getSize() + " byte(s).");
                }
                memHeap.heap.get(i).setSize(allocRequest.getSize());
                memHeap.heap.get(i).setOccupied(true);
                memHeap.addOccupation((float)allocRequest.getSize() / memHeap.getSize() * 100);
                System.out.println("Alocado em memória do endereço " + memHeap.heap.get(i).getStart() + " até o endereço " + (memHeap.heap.get(i).getSize() + memHeap.heap.get(i).getStart() - 1) + ".");
                System.out.println("Heap está " + memHeap.getOccupation() + "% ocupado.");
                break;
            }
        }
    }
}
