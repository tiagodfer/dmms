public class Deallocator {
    private int maxRamUsage;
    private int freeRamThreshold;

    public void setMaxRamUsage (int newMaxRamUsage) {
        this.maxRamUsage = newMaxRamUsage;
    }

    public void setFreeRamThreshold (int newFreeRamThreshold) {
        this.freeRamThreshold = newFreeRamThreshold;
    }

    public int getMaxRamUsage () {
        return this.maxRamUsage;
    }

    public int getFreeRamThreshold () {
        return this.freeRamThreshold;
    }

    public void deallocate() {
        //criar função randômica para desalocar de acordo com
        // map.occupationLimit e map.freeMemoryLimit
    }
}
