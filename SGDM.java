/**
* Autores: Tiago Dias Ferreira and Marcello Silva Cruz
*/

/**
* SGDM: Simulador de Gerenciamento Dinâmico de Memória
*/
public class SGDM {

    // classe dos programas a serem alocados
    public class Program {
        private String name;
        private int priority;
        private double RAMNeeded; // em bytes
        
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

        // Utils
        public Object clone() {
            Program aClone = new Program();
            aClone.name = this.name;
            aClone.priority = this.priority;
            aClone.RAMNeeded = this.RAMNeeded;
            return aClone;
        }
    }

    // classe da RAM a ser gerenciada
    public class Memory {
        private double size; // em bytes

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
        private int start;
        private int end;
        private Program program;
        
        // setters
        public void setStart (int newStart) {
            this.start = newStart;
        }
        
        public void setEnd (int newEnd) {
            this.end = newEnd;
        }
        
        public void setProgram (Program newProgram) {
            /* rever a forma de atribuição, pois pode estar errada
               pois objetos são passados por referência e não por valor
               https://www.codejava.net/coding/java-getter-and-setter-tutorial-from-basics-to-best-practices */
            /* this.program = newProgram; */
            this.program = (Program) newProgram.clone();
        }
        
        // getters
        public int getStart () {
            return this.start;
        }
        
        public int getEnd () {
            return this.end;
        }
        
        public Program getProgram () {
            /* rever a forma de atribuição, pois pode estar errada
               pois objetos são passados por referência e não por valor
               https://www.codejava.net/coding/java-getter-and-setter-tutorial-from-basics-to-best-practices */
            /* return this.program; */
            return (Program) this.program.clone();
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
