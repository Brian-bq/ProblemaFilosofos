package problemafilosofos;
/**
 *
 * @author Brian Queiroz
 */

public class Filosofos extends Thread{
    final static int tempoM = 100;
    Mesa mesa;
    int filosofo;

    public Filosofos (String nome, Mesa mesaJantar, int filoso){
        super(nome);
        mesa = mesaJantar;
        filosofo = filoso;
    }

    public void run (){
        int tempo = 0;
        int rodadas = 0;
        while (rodadas <=15){
            tempo = (int) (Math.random() * tempoM);
            pensa(tempo);
            getGarfo();
            tempo = (int) (Math.random() * tempoM);
            come(tempo);
            retornaGarfo();
            rodadas ++;
            System.out.println("________________________________________________________");
        }
    }

    public void pensa (int tempo){
        try{
            sleep(tempo);
        }catch (InterruptedException e){
            System.out.println("O Filófoso pensou em demasia");
        }
    }

    public void come (int tempo){
        try{
            sleep(tempo);
        }catch (InterruptedException e){
            System.out.println("O Filósofo comeu demais");
        }
    }

    public void getGarfo(){
        mesa.pegarGarfos(filosofo);
    }

    public void retornaGarfo(){
        mesa.retornaGarfo(filosofo);
    }
}
