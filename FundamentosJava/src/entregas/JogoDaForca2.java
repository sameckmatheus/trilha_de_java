package entregas;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class JogoDaForca2 {
    public static void main(String[] args) {
        String arteTitulo = """
                ######   #####   ######     ####     ###
                ##      ##   ##  ###  ##   ##  ##   ## ##
                ##      ##   ##  ##   ##  ##       ##   ##
                #####   ##   ##  ##  ##   ##       ##   ##
                ##      ##   ##  #####    ##       #######
                ##      ##   ##  ## ###    ##  ##  ##   ##
                ##       #####   ##  ###    ####   ##   ##
                """;
        System.out.println(arteTitulo);

        JogoForca2 forca = new JogoForca2();
        forca.definirPalavra();

        Scanner entrada = new Scanner(System.in);

        while (!forca.theEnd()) {
            System.out.println("Palavra: " + String.join(" ", forca.getLetrasCorretas()));
            System.out.println("Letras erradas: " + String.join(", ", forca.getLetrasErradas()));
            System.out.println(forca.arteForca());
            System.out.print("Digite uma letra: ");

            String chute = entrada.nextLine().toLowerCase();
            String resultado = forca.verificarChute(chute);

            if (!resultado.isEmpty()) {
                System.out.println(resultado);
            }
        }

        entrada.close();
    }
}

class JogoForca2 {
    private static final int MAX_ERROS = 6;
    private static final Random random = new Random();
    private final String[] palavrasSecretas = {"feno", "ferro", "manteiga"};
    private String palavraSecretaEscolhida;
    private int erros = 0;
    private boolean fim = false;
    private String[] letrasCorretas;
    private String[] letrasIncorretas;

    public void definirPalavra() {
        palavraSecretaEscolhida = palavrasSecretas[random.nextInt(palavrasSecretas.length)].toLowerCase();
        letrasCorretas = new String[palavraSecretaEscolhida.length()];
        letrasIncorretas = new String[MAX_ERROS];
        Arrays.fill(letrasCorretas, "_");
    }

    public String verificarChute(String chute) {
        if (chute.length() == 1 && chute.matches("[a-z]")) {
            if (chuteArray(chute, letrasIncorretas)) {
                return "Você já tentou essa letra antes e errou!";
            }
            if (chuteArray(chute, letrasCorretas)) {
                return "Você já tentou essa letra";
            }
            if (chutePalavra(chute)) {
                for (int i = 0; i < palavraSecretaEscolhida.length(); i++) {
                    if (palavraSecretaEscolhida.charAt(i) == chute.charAt(0)) {
                        letrasCorretas[i] = chute;
                    }
                }
                if (++erros >= MAX_ERROS || Arrays.stream(letrasCorretas).noneMatch("_"::equals)) {
                    fim = true;
                    return erros >= MAX_ERROS ? "Infelizmente, você perdeu... a palavra era: " + palavraSecretaEscolhida
                            : "Parabéns, você venceu! A palavra era: " + palavraSecretaEscolhida;
                }
            } else {
                if (erros < letrasIncorretas.length) {
                    letrasIncorretas[erros] = chute;
                    erros++;
                    if (erros >= letrasIncorretas.length) {
                        fim = true;
                        return "Infelizmente, você perdeu... a palavra era: " + palavraSecretaEscolhida;
                    }
                }
            }
        } else {
            return "Entrada inválida!";
        }
        return "";
    }

    public String arteForca() {
        String[] partesForca = {
            """
               _____ 
               |   |
               |
               |
               |
               |
            ___|___
            """,
            """
               _____ 
               |   |
               |   O
               |
               |
               |
            ___|___
            """,
            """
               _____ 
               |   |
               |   O
               |   |
               |
               |
            ___|___
            """,
            """
               _____ 
               |   |
               |   O
               |  /|
               |
               |
            ___|___
            """,
            """
               _____ 
               |   |
               |   O
               |  /|\
               |
               |
            ___|___
            """,
            """
               _____ 
               |   |
               |   O
               |  /|\
               |  /
               |
            ___|___
            """,
            """
               _____ 
               |   |
               |   O
               |  /|\
               |  / \
               |
            ___|___
            """
        };

        return erros < partesForca.length ? partesForca[erros] : partesForca[partesForca.length - 1];
    }

    public boolean theEnd() {
        return fim;
    }

    private static boolean chuteArray(String chute, String[] array) {
        return Arrays.asList(array).contains(chute);
    }

    private boolean chutePalavra(String chute) {
        return palavraSecretaEscolhida.contains(chute);
    }

    public String[] getLetrasCorretas() {
        return letrasCorretas;
    }

    public String[] getLetrasErradas() {
        return Arrays.copyOfRange(letrasIncorretas, 0, erros);
    }
}
