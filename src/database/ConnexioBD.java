package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexioBD {

    public static void verificarDrivers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No s'ha trobat el controlador: com.mysql.cj.jdbc.Driver");
            System.exit(404);
        }
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("No s'ha trobat el controlador: org.postgresql.Driver");
            System.exit(404);
        }
    }

    public static Connection dbConnectionMySQL() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/UNIOriolLamelas";
        String usuari = "root";
        String pass = "";
        try {
            con = (Connection) DriverManager.getConnection(url, usuari, pass);
            System.out.println("Connectat a MySQL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Connection dbConnectionPostgres() {
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/uf2oriol";
        String usuari = "postgres";
        String pass = "Co030321";
        try {
            con = (Connection) DriverManager.getConnection(url, usuari, pass);
            System.out.println("Connectat a Postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
