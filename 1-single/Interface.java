public class Interface {
    // imprime na tela saudações iniciais
    public void greeting () {
        System.out.println("Bem vindo ao DMMS: Dymanic Memory Management System!");
        System.out.println("Vamos definir os parâmetros iniciais.");
        System.out.println("--------------------------------------------------------------------------------");
    }

    // passa valores do heap
    public void setHeapParams (Heap heap, int heapSize) {
        heap.setSize(heapSize);
        heap.addBlock();
    }

    // passa valores do gerados de requisições
    public void setReqParams (RequestGenerator generator, int randomMin, int randomMax, int reqQuant) {
        generator.setMinRequestSize(randomMin);
        generator.setMaxRequestSize(randomMax);
        generator.setRequestsQuantity(reqQuant);
    }

    // passa valores do desalocador
    public void setDeallocParams (Deallocator deallocator, int maxRamUsage, int freeRamThreshold) {
        deallocator.setMaxRamUsage(maxRamUsage);
        deallocator.setFreeRamThreshold(freeRamThreshold);
    }

    // passa valores do desfragmentador
    public void setDefragParams (Defragger defragger, int maxFragmentation) {
        defragger.setMaxFragmentation(maxFragmentation);
    }

    // imprime parâmetros para o usuário
    public void printParams (Heap heap, RequestGenerator generator, Deallocator deallocator, Defragger defragger) {
        System.out.println("Tamanho do Heap de Memória definido como " + heap.getSize() + " kByte(s).");
        System.out.println("Tamanho mínimo das requisições definido como " + generator.getMinRequestSize() + " kByte(s).");
        System.out.println("Tamanho mínimo das requisições definido como " + generator.getMaxRequestSize() + " kByte(s).");
        System.out.println("Quantidade de requisições a serem realizadas definido como " + generator.getRequestsQuantity() + ".");
        System.out.println("O máximo de ocupação da RAM definido como " + deallocator.getMaxRamUsage() + "%.");
        System.out.println("O limiar mínimo de ocupação de RAM para atuação do Desalocador definido como " + deallocator.getFreeRamThreshold() + "%.");
        System.out.println("O máximo de fragmentação definido como " + defragger.getMaxFragmentation() + "%.");
    }
}
