/**
* A princípio podemos fazer um programa que rode direto no terminal,
* não sei se é necessário fazer uma GUI para o nosso programa
*/
import java.util.Scanner;

public class Interface {
    public void scanner () {
        System.out.println("DYNAMIC MEMORY MANAGEMENT SYSTEM");

        Scanner heapSizeScan = new Scanner(System.in);
        System.out.println("Digite o tamanho da Heap de Memória:");
        //??? = heapSizeScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
                
        Scanner randomMinScan = new Scanner(System.in);
        System.out.println("Digite o tamanho mínimo das requisições geradas randomicamente:");
        //??? = randomMinScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
                
        Scanner randomMaxScan = new Scanner(System.in);
        System.out.println("Digite o tamanho máximo das requisições geradas randomicamente:");
        //??? = randomMaxScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
        
        Scanner reqQuantScan = new Scanner(System.in);
        System.out.println("Digite a quantidade de requisições a serem realizadas:");
        //??? = reqNumberScan.nextInt(); //tô vendo ainda como passar as coisas por parâmetro em Java
        
        Scanner maxRamUsageScan = new Scanner(System.in);
        System.out.println("Digite o limite máximo de utilização da RAM:");
        //??? = maxRamUsageScan.nextInt(); //tô vendo ainda como passar as coisas por parâmetro em Java
        
        Scanner freeRamThresholdScan = new Scanner(System.in);
        System.out.println("Digite o limite mínimo a ser atingido ao liberar RAM:");
        //??? = freeRamThresholdScan.nextInt(); //tô vendo ainda como passar as coisas por parâmetro em Java
    }

    public class args {
        
        public void greeting () {
            System.out.println("Bem vindo ao DMMS: Dymanic Memory Management Systemi!");
            System.out.println("Vamos definir os parâmetros iniciais.");
            System.out.println("--------------------------------------------------------------------------------");
        }

        public void setHeapParms (DMMS.memoryHeap memHeap, long heapSize) {
            memHeap.setSize(heapSize);
        }


        public void setReqParms (MemRequestGenerator reqGenerator, long randomMin, long randomMax, int reqQuant) {
            reqGenerator.setMinRequestSize(randomMin);
            reqGenerator.setMaxRequestSize(randomMax);
            reqGenerator.setRequestsQuantity(reqQuant);
        }
        


        public void setDeallocParms (Deallocator memDeallocator, int maxRamUsage, int freeRamThreshold) {
            memDeallocator.setMaxRamUsage(maxRamUsage);
            memDeallocator.setFreeRamThreshold(freeRamThreshold);
        }


        public void printParms (DMMS.memoryHeap memHeap, MemRequestGenerator reqGenerator, Deallocator memDeallocator) {
            System.out.println("Tamanho do Heap de Memória definido como " + memHeap.getSize() + " byte(s).");
            System.out.println("Tamanho mínimo das requisições definido como " + reqGenerator.getMinRequestSize() + " byte(s).");
            System.out.println("Tamanho mínimo das requisições definido como " + reqGenerator.getMaxRequestSize() + " byte(s).");
            System.out.println("Quantidade de requisições a serem realizadas definido como " + reqGenerator.getRequestsQuantity() + ".");
            System.out.println("O máximo de ocupação da RAM definido como " + memDeallocator.getMaxRamUsage() + "%.");
            System.out.println("O limiar mínimo de ocupação de RAM para atuação do Desalocador definido como " + memDeallocator.getFreeRamThreshold() + "%.");
        }
    }
}
