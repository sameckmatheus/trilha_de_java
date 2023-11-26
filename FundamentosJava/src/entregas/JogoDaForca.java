package entregas;

import java.util.Scanner;
import java.util.Random;

public class JogoDaForca {
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
		
		JogoForca forca = new JogoForca();
		forca.definirPalavra();
		
		Scanner entrada = new Scanner(System.in);
		
		while (!forca.theEnd()) {
			System.out.println("Palavra: " + String.join(" ", forca.getLetrasCorretas()));
            System.out.println("Letras erradas: " + String.join(", ", forca.getLetrasErradas()));
            System.out.println(forca.arteForca());
            System.out.print("Digite uma letra: ");
            
            String chute = entrada.nextLine().toLowerCase();
            String resultado = forca.verificarChute(chute);
            
            if (resultado != null) {
            	System.out.println(resultado);
            	break;
            }
		}
		
		entrada.close();
	}
}

class JogoForca {
	private final String[] palavrasSecretas = {"feno", "ferro", "manteiga"};
	private String palavraSecretaEscolhida = " ";
	private int erros = 0;
	private int acertos = 0;
	private boolean fim = false;
	private String[] letrasCorretas;
	private String[] letrasIncorretas;
	
	public void definirPalavra() {
		palavraSecretaEscolhida = palavrasSecretas[new Random().nextInt(palavrasSecretas.length)].toLowerCase();
		letrasCorretas = new String[palavraSecretaEscolhida.length()];
		letrasIncorretas = new String[6];
		
		for (int i = 0; i < palavraSecretaEscolhida.length(); i++) {
			letrasCorretas[i] = "_";
		}
	}
	
	public String verificarChute(String chute) {
		if (chute.length() == 1 && chute.matches("[a-z]")) {
			if (chuteArray(chute, letrasIncorretas)) {
				return "Você já tentou essa letras";
			}
		
			if (chuteArray(chute, letrasIncorretas)) {
				return "Você já tentou essa letra antes e errou!";
			}
			if (chutePalavra(chute)) {
				for (int i = 0; i < palavraSecretaEscolhida.length(); i++) {
					if (palavraSecretaEscolhida.charAt(i) == chute.charAt(0)) {
						letrasCorretas[i] = chute;
						acertos++;
					}
				}
				if (acertos == palavraSecretaEscolhida.length()) {
					fim = true;
	                return "Parabéns, você venceu! A palavra era " + palavraSecretaEscolhida;
				}
			} else {
				letrasIncorretas[erros] = chute;
				erros++;
				if (erros >= 6) {
					fim = true;
					return "Infelizmente, você perdeu... a palavra era: " + palavraSecretaEscolhida;
				}
			}
		} else {
			return "Entrada inválida!";
		}
		return null;
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
		
		if (erros < partesForca.length) {
		    return partesForca[erros];
		} else {
		    return partesForca[partesForca.length - 1];
		}
	}
	
	public boolean theEnd() {
		return fim;
	}
	
	private boolean chuteArray(String chute, String[] array) {
		for (String letra : array) {
			if (chute.equals(letra)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean chutePalavra(String chute) {
        return palavraSecretaEscolhida.contains(chute);
    }
	
	public String[] getLetrasCorretas() {
        return letrasCorretas;
    }

    public String[] getLetrasErradas() {
        return letrasIncorretas;
    }
}
