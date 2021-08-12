/**
* A princípio podemos fazer um programa que rode direto no terminal,
* não sei se é necessário fazer uma GUI para o nosso programa
*/
package UI;

import java.util.Scanner;
import SGDM.CircularQueue;

public class TUI {
    public static void main (String[] args) {
        System.out.println("DYNAMIC MEMORY MANAGEMENT SYSTEM");

        Scanner heapSizeScan = new Scanner(System.in);
        System.out.println("Digite o tamanho da Heap de Memória:");
        ??? = heapSizeScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
                
        Scanner randomMinScan = new Scanner(System.in);
        System.out.println("Digite o tamanho mínimo das requisições geradas randomicamente:");
        ??? = randomMinScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
                
        Scanner randomMaxScan = new Scanner(System.in);
        System.out.println("Digite o tamanho máximo das requisições geradas randomicamente:");
        ??? = randomMaxScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
        
        Scanner reqNumberScan = new Scanner(System.in);
        System.out.println("Digite a quantidade de requisições a serem realizadas:");
        ??? = reqNumberScan.nextInt(); //tô vendo ainda como passar as coisas por parâmetro em Java
        
        Scanner maxRamUsageScan = new Scanner(System.in);
        System.out.println("Digite o limite máximo de utilização da RAM:");
        ??? = maxRamUsageScan.nextInt(); //tô vendo ainda como passar as coisas por parâmetro em Java
        
        Scanner freeRamThresholdScan = new Scanner(System.in);
        System.out.println("Digite o limite mínimo a ser atingido ao liberar RAM:");
        ??? = freeRamThresholdScan.nextInt(); //tô vendo ainda como passar as coisas por parâmetro em Java
    }
}
