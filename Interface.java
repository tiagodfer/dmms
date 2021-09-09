public class Interface {
    public void greeting () {
        System.out.println("Bem vindo ao DMMS: Dymanic Memory Management System!");
        System.out.println("Vamos definir os parâmetros iniciais.");
        System.out.println("--------------------------------------------------------------------------------");
    }

    public void setHeapParms (HeapMap map, int heapSize) {
        map.setSize(heapSize);
        map.addBlock();
    }


    public void setReqParms (RequestGenerator generator, int randomMin, int randomMax, int reqQuant) {
        generator.setMinRequestSize(randomMin);
        generator.setMaxRequestSize(randomMax);
        generator.setRequestsQuantity(reqQuant);
    }
    


    public void setDeallocParms (Deallocator deallocator, int maxRamUsage, int freeRamThreshold) {
        deallocator.setMaxRamUsage(maxRamUsage);
        deallocator.setFreeRamThreshold(freeRamThreshold);
    }


    public void printParms (HeapMap map, RequestGenerator generator, Deallocator deallocator) {
        System.out.println("Tamanho do Heap de Memória definido como " + map.getSize() + " kByte(s).");
        System.out.println("Tamanho mínimo das requisições definido como " + generator.getMinRequestSize() + " kByte(s).");
        System.out.println("Tamanho mínimo das requisições definido como " + generator.getMaxRequestSize() + " kByte(s).");
        System.out.println("Quantidade de requisições a serem realizadas definido como " + generator.getRequestsQuantity() + ".");
        System.out.println("O máximo de ocupação da RAM definido como " + deallocator.getMaxRamUsage() + "%.");
        System.out.println("O limiar mínimo de ocupação de RAM para atuação do Desalocador definido como " + deallocator.getFreeRamThreshold() + "%.");
    }
}
