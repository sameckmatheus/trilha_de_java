package fundamentos;
import java.util.Scanner;

public class Exercicio008 {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Digite um número: ");
		
		double resp = entrada.nextDouble();
		
		if (resp < 0) {
			System.out.println("O número é negativo.");
		} else {
			System.out.println("O número é positivo.");
		}
	}
}
