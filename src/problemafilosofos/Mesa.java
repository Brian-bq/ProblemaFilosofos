package problemafilosofos;
/**
 *
 * @author Brian Queiroz
 */
public class Mesa
{
   final static int pensado = 1;
   final static int comendo = 2;
   final static int fome = 3;
   final static int nFilosofos = 5;
   final static int pFilosofo = 0;
   final static int uFilosofo = nFilosofos - 1;
   boolean[] garfos = new boolean[nFilosofos];
   int[] filosofos = new int[nFilosofos];
   int[] tentou = new int[nFilosofos];

   public Mesa()
   {
      for (int i = 0; i < 5; i++)
      {
         garfos[i] = true;
         filosofos[i] = pensado;
         tentou[i] = 0;
      }
   }

   public synchronized void pegarGarfos (int filosofo)
   {
      filosofos[filosofo] = fome;
      while (filosofos[aEsquerda(filosofo)] == comendo || filosofos[aDireita(filosofo)] == comendo)
      {
         try
         {
            tentou[filosofo]++;
            wait();
         }
         catch (InterruptedException e)
         {
         }
      }
      System.out.println("O Filósofo morreu");
      tentou[filosofo] = 0;
      garfos[garfoEsquerdo(filosofo)] = false;
      garfos[garfoDireito(filosofo)] = false;
      filosofos[filosofo] = comendo;
      imprimeEstado();
      imprimeGarfo();
      imprimetentou();
   }

   public synchronized void retornaGarfo (int filosofo)
   {
      garfos[garfoEsquerdo(filosofo)] = true;
      garfos[garfoDireito(filosofo)] = true;
      if (filosofos[aEsquerda(filosofo)] == fome || filosofos[aDireita(filosofo)] == fome)
      {
         notifyAll();
      }
      filosofos[filosofo] = pensado;
      imprimeEstado();
      imprimeGarfo();
      imprimetentou();
   }

   public int aDireita (int filosofo)
   {
      int direito;
      if (filosofo == uFilosofo)
      {
         direito = pFilosofo;
      }
      else
      {
         direito = filosofo + 1;
      }
      return direito;
   }

   public int aEsquerda (int filosofo)
   {
      int esquerdo;
      if (filosofo == pFilosofo)
      {
         esquerdo = uFilosofo;
      }
      else
      {
         esquerdo = filosofo - 1;
      }
      return esquerdo;
   }

   public int garfoEsquerdo (int filosofo)
   {
      int garfoEsquerdo = filosofo;
      return garfoEsquerdo;
   }

   public int garfoDireito (int filosofo)
   {
      int garfoDireito;
      if (filosofo == uFilosofo)
      {
         garfoDireito = 0;
      }
      else
      {
         garfoDireito = filosofo + 1;
      }
      return garfoDireito;
   }

   public void imprimeEstado ()
   {
      String texto = "*";
      System.out.print("Filósofos = < ");
      for (int i = 0; i < nFilosofos; i++)
      {
         switch (filosofos[i])
         {
            case pensado :
               texto = "pensado,";
               break;
            case fome :
               texto = "fome,";
               break;
            case comendo :
               texto = "comendo,";
               break;
         }
         System.out.print(texto + " ");
      }
      System.out.println(">");
   }

   public void imprimeGarfo ()
   {
      String garfo = "*";
      System.out.print("Garfos = < ");
      for (int i = 0; i < nFilosofos; i++)
      {
         if (garfos[i])
         {
            garfo = "Esta livre";
         }
         else
         {
            garfo = "Esta ocupado";
         }
         System.out.print(garfo + " ");
      }
      System.out.println(">");
   }

   public void imprimetentou ()
   {
      System.out.print("Tentou comer = < ");
      for (int i = 0; i < nFilosofos; i++)
      {
         System.out.print(filosofos[i] + " ");
      }
      System.out.println(">");
   }
}