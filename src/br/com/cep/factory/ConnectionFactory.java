package br.com.cep.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory  {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "SUA_SENHA";

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/buscacep";

    public static Connection createConnectionToMySQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        return connection;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection con = createConnectionToMySQL();

        if(con!=null){
            System.out.println("Conexão estabelecida");
            con.close();
        }
    }
}
