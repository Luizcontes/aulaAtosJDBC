import java.util.Scanner;

public class Pessoa {

    public int numero;
    public String nome;
    public int idade;
    public String endereco;

    public Pessoa(int numero, String nome, int idade, String endereco) {
        this.numero = numero;
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public static Pessoa registrarUsurario(Scanner scanner, int numero) {

        int staticIdade = 0;
        System.out.println("INSERIR USUARIO");
        System.out.println();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        while (true) {
            try {
                System.out.print("Idade: ");
                staticIdade = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("O valor inserido nao e um numero");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.print("Endereco: ");
        String endereco = scanner.nextLine();

        return new Pessoa(numero, nome, staticIdade, endereco);
    }

}
