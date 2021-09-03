/**
* Autores: Tiago Dias Ferreira and Marcello Silva Cruz
*/

/**
* DMMS: Simulador de Gerenciamento Dinâmico de Memória
*/
public class DMMS {

    // classe dos programas a serem alocados
    public class Program {
        private String name;
        private int priority;
        private int RAMNeeded; // em kBytes
        
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
        
        public int getRAMNeeded () {
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
    public class memoryHeap {
        private int size; // em bytes

        // setters
        public void setSize (int newSize) {
            this.size = newSize;
        }

        // getters
        public int getSize () {
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
    public void allocateRAM (Program program) {
    }
    
    // método que encerrará um Program em Memory
    public void freeRAM (Program program) {
    }

    // método principal
    public static void main(String args[]) {
        if (args.length >= 6) {
            DMMS memManager = new DMMS();
            DMMS.memoryHeap memHeap = memManager.new memoryHeap();
            MemRequestGenerator reqGenerator = new MemRequestGenerator();
            Deallocator memDeallocator = new Deallocator();
            CircularQueue queue = new CircularQueue(10);

            Interface tui = new Interface();
            Interface.args params = tui.new args();

            params.greeting();
            params.setHeapParms(memHeap, new Integer(args[0]));
            params.setReqParms(reqGenerator, new Integer(args[1]), new Integer(args[2]), new Integer(args[3]));
            params.setDeallocParms(memDeallocator, new Integer(args[4]), new Integer(args[5]));
            params.printParms(memHeap, reqGenerator,memDeallocator);

            while (reqGenerator.getRequestsQuantity() > 0) {
                while (!queue.isFull()) {
                    MemRequest memRequest = reqGenerator.generateRandomRequest(reqGenerator.getMinRequestSize(), reqGenerator.getMaxRequestSize());
                    queue.addRequest(memRequest);
                }
            }
        }
        else {
            System.out.println("Número insuficiente de argumentos!");
        }
    }
}
