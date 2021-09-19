public class HeapInfo {
    private int largestBlockSize;
    private int free;
    private float fragmentation;

    public void setLargestBlockSize (int newLargestBlockSize) {
        this.largestBlockSize = newLargestBlockSize;
    }

    public void setFree (int newFree) {
        this.free = newFree;
    }

    public void setFragmentation (float newFragmentation) {
        this.fragmentation = newFragmentation;
    }
}
