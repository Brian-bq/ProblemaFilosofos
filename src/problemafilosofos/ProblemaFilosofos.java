package problemafilosofos;

/**
 *
 * @author Brian Queiroz
 */
public class ProblemaFilosofos {

    public static void main(String[] args) {
        
        Mesa mesa = new Mesa ();
        for (int filosofo = 0; filosofo < 5; filosofo++){
         new Filosofos("Filosofo_" + filosofo, mesa, filosofo).start();
        }
    }
}