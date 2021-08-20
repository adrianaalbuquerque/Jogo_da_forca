package projeto_jodo_da_forca;

/*
 * IFPB-POO-PROJETO1
 * Aplicação console para testar a classe JogoDaForca
 *
 */
import java.util.Scanner;

public class JogoDaForcaConsole {
    public static void main(String[] args) {

       Scanner teclado = new Scanner (System.in);
        try {
            JogoDaForca jogo = new JogoDaForca("palavras.txt");
            jogo.iniciar();
            String letra;
            do {
                System.out.println("\nPalavra = " + jogo.getPalavra());
                System.out.println("Dica = " + jogo.getDica());

                System.out.print("Digite uma letra da palavra: ");
                letra = teclado.nextLine();
                if (letra.isEmpty()){
                    System.out.println("Digite uma letra");
                } else {
                    //tratandoo
                    try {
                        if (jogo.adivinhou(letra)) {
                            System.out.println("Voce acertou a letra " + letra);
                            System.out.println("Total de acertos = " + jogo.getAcertos());
                        } else {
                            System.out.println("Voce errou a letra " + letra);
                            System.out.println("Total de erros = " + jogo.getErros());
                            System.out.println("Letras já digitadas = " + jogo.getLetrasErradas());
                            System.out.println("Penalidade: " + jogo.getPenalidade());
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            while(!jogo.terminou());

            System.out.println("\nPalavra = " + jogo.getPalavra());

            teclado.close();

            //System.out.println("GAME OVER");
            System.out.println("Resultado final = "+jogo.getResultado() );
        } catch(Exception e){
            System.out.println("Digite uma letra de palavra");
        }


    }
}

