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

        Scanner heapScan = new Scanner(System.in);
        System.out.println("Digite o tamanho da Heap de Memória:");
        ??? = heapScan.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
                
        Scanner randomMin = new Scanner(System.in);
        System.out.println("Digite o tamanho mínimo das requisições geradas randomicamente:");
        ??? = randomMin.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
                
        Scanner randomMax = new Scanner(System.in);
        System.out.println("Digite o tamanho máximo das requisições geradas randomicamente:");
        ??? = randomMax.nextLong(); //tô vendo ainda como passar as coisas por parâmetro em Java
    }
}
