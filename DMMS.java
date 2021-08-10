/**
* Autores: Tiago Dias Ferreira and Marcello Silva Cruz
*/

/**
* SGDM: Simulador de Gerenciamento Dinâmico de Memória
*/
public class SGDM {

    // classe dos programas a serem alocados
    public class Program {
        String name;
        int priority;
        double RAMNeeded; // em bytes
        
        // setters
        public void setName (String newName) {
            this.name = newName;
        }
        
        public void setPriority (int newPriority) {
            this.priority = newPriority;
        }
        
        public void setRAMNeeded (int newRAMNeeded) {
            this.RAMNeeded = newRAMNeeded;
        }
        
        // getters
        public String getName () {
            return this.name;
        }
        
        public int getPriority () {
            return this.priority;
        }
        
        public double getRAMNeeded () {
            return this.RAMNeeded;
        }
    }

    // classe da RAM a ser gerenciada
    public class Memory {
        double size; // em bytes

        // setters
        public void setSize (double newSize) {
            this.size = newSize;
        }

        // getters
        public double getSize () {
            return this.size;
        }
    }
    
    // classe das Partições de Memória
    public class Partition {
        int start;
        int end;
        Program program;
        
        // setters
        public void setStart (int newStart) {
            this.start = newStart;
        }
        
        public void setEnd (int newEnd) {
            this.end = newEnd;
        }
        
        public void setProgram (Program newProgram) {
            this.program = newProgram;
        }
        
        // getters
        public int getStart () {
            return this.start;
        }
        
        public int getEnd () {
            return this.end;
        }
        
        public Program getProgram () {
            return this.program;
        }
    }

    // método que alocará um Program em Memory
    public static void allocateRAM (Program program) {
        System.out.println("Qual programa deseja alocar em memória?");
    }
    
    // método que encerrará um Program em Memory
    public static void freeRAM (Program program) {
        System.out.println("Qual programa deseja remover da memória?");
    }

    // método principal
    public static void main(String[] args) {
        System.out.println("DYNAMIC MEMORY MANAGEMENT SYSTEM");
    }
}
