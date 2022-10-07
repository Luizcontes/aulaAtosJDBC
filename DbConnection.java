import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DbConnection {

    public static void carregarDadosBD(ArrayList<Pessoa> pessoas) {

        final String db_url = "jdbc:mysql://localhost:3306/pessoas";
        final String db_query = "SELECT * FROM pessoas";
        final String db_user = "teste";
        final String db_password = "5noSRIdmm@";

        int numero = 0;
        String nome = "";
        int idade = 0;
        String endereco = "";

        // System.out.println("Iniciando conexao ao DB");

        try (Connection c = DriverManager.getConnection(db_url, db_user, db_password);
                Statement statement = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultSet = statement.executeQuery(db_query);) {

            // System.out.println("Conectado ao MySQL");

            while (resultSet.next()) {
                numero = Integer.parseInt(resultSet.getString(1));
                nome = resultSet.getString(2);
                idade = Integer.parseInt(resultSet.getString(3));
                endereco = resultSet.getString(4);

                pessoas.add(new Pessoa(numero, nome, idade, endereco));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }

    }

    public static void inserirPessoa(Pessoa pessoa) {

        final String db_url = "jdbc:mysql://localhost:3306/pessoas";
        final String db_query = "INSERT INTO pessoas  VALUES(" +
                pessoa.numero + "," + "'" +pessoa.nome + "'" + "," + pessoa.idade + "," +  "'" + pessoa.endereco + "'" +");";
        final String db_user = "teste";
        final String db_password = "5noSRIdmm@";

        try {

            Connection c = DriverManager.getConnection(db_url, db_user, db_password);
            Statement statement = c.createStatement();
            statement.executeUpdate(db_query);

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

    public static void removerUsuario(int numero) {

        final String db_url = "jdbc:mysql://localhost:3306/pessoas";
        final String db_query = "DELETE FROM pessoas WHERE numero = " + numero + ";";
        final String db_user = "teste";
        final String db_password = "5noSRIdmm@";

        try {

            Connection c = DriverManager.getConnection(db_url, db_user, db_password);
            Statement statement = c.createStatement();
            statement.executeUpdate(db_query);

        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }
}
