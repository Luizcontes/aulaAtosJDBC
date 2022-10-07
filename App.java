import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Pessoa> pessoas = atualizarLista();        

        while (true) {
            System.out.println("CADASTRO DE USUARIOS");
            System.out.println();
            System.out.println("0 - Sair da aplicao");
            System.out.println("1 - Registrar uma pessoa");
            System.out.println("2 - Apagar uma pessoa");
            System.out.println("3 - Listar todas as pessoas");
            System.out.print("Escolha uma opcao: ");
            String teste = scanner.nextLine();

            Pessoa pessoaTmp;
            switch (teste) {
                case "0":
                    System.exit(0);
                    break;
                case "1":
                    DbConnection.inserirPessoa(
                        Pessoa.registrarUsurario(scanner, pessoas.size()+1)
                    );
                    break;
                case "2":
                    System.out.print("Insira o numero da pessoa para apagar: ");
                    
                    int opt = -1;
                    try {
                        opt = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("\nEste numero nao e valido...\n");
                        break;
                    }
                    
                    try {
                        pessoaTmp = pessoas.get(opt - 1);
                        DbConnection.removerUsuario(opt);
                        System.out.println("\nUsurio apagado com sucesso...\n");
                    } catch (Exception e){
                        System.out.println("\nUsuario inexistente na base de dados\n");
                    }
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.println("\nLISTAR PESSOAS\n");
                    for (int i=0; i<pessoas.size(); i++) {

                        pessoaTmp = pessoas.get(i);

                        System.out.println("Numero: " + pessoaTmp.numero);
                        System.out.println("Nome: " + pessoaTmp.nome);
                        System.out.println("Idade: " + pessoaTmp.idade);
                        System.out.println("Endereco: " + pessoaTmp.endereco + "\n");
                    }
                    break;
                default:
                    System.out.println("Opcao invalida, por favor digite a opcao correta.");
                    break;
            }

            pessoas = atualizarLista();
        }

        // scanner.close();
    }

    public static ArrayList<Pessoa> atualizarLista() {

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

        DbConnection.carregarDadosBD(pessoas);

        return pessoas;
    }

    public static void execute2() {

        final String db_url = "jdbc:mysql://localhost:3306/biblioteca";
        final String db_query = "INSERT INTO livros (ISBN, cod_autor, cod_categoria, cod_editora," +
                "ano, titulo) VALUES (" +
                "'9999999', 1, 1, 1, 1999, 'Memorias postumas de Brascubas');";
        final String db_user = "teste";
        final String db_password = "5noSRIdmm@";

        // Pessoa[] p;

        // int resultSetRows = 0;
        System.out.println("Iniciando conexao ao DB");

        try {

            Connection c = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Conectado ao MySQL");
            Statement statement = c.createStatement();
            int linhas = statement.executeUpdate(db_query);

            System.out.println("A query afetou " + linhas);

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }

    }

}