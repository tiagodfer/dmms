public class Allocator {
    public void allocate (MemRequest allocRequest, CircularQueue queue, HeapMap memHeap) {
        allocRequest = queue.removeRequest();
        memHeap.firstFit(allocRequest, memHeap);
    }
}
