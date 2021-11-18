package operacions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operacions {

    public static void llistaAssigInfo(Connection con) {
        try {
            Statement comanda = con.createStatement();
            ResultSet rs = comanda.executeQuery("SELECT ASSIG_CODI, ASSIG_NOM FROM assignatures\n" +
                    "WHERE ASSIG_CODI IN\n" +
                    "      (SELECT ASSIGPROF_ASSIG_CODI FROM assignatures_professor\n" +
                    "      WHERE ASSIGPROF_PROF_DNI IN\n" +
                    "            (SELECT PROF_DNI FROM professor\n" +
                    "            WHERE PROF_DEPT_CODI IN\n" +
                    "                  (SELECT DEPT_CODI FROM departament\n" +
                    "                  WHERE UPPER(DEPT_NOM) = 'INFORMATICA I MATEMATICA APLICADA')));");

            System.out.println("\tCodi\tAssignatura\n-----------------------------------------------");
            while (rs.next()) {
                String codi = rs.getString(1);
                String nom = rs.getString(2);
                System.out.println("\t" + codi + "\t\t" + nom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void galaxiaVL(Connection con) {
        try {
            Statement comanda = con.createStatement();
            String sql = "ALTER TABLE alumnes ADD COLUMN ALUMN_GALAXIA VARCHAR(20)";
            try {
                comanda.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("La columna ja existeix");
            }
            comanda = con.createStatement();
            sql = "UPDATE alumnes set alumnes.ALUMN_GALAXIA = 'Via làctia' WHERE alumnes.ALUMN_GALAXIA IS NULL";
            try {
                comanda.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("Houston we have a problem");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void crearTaulaPG(Connection con) {
        try {
            Statement comanda = con.createStatement();
            String sql = "CREATE TABLE TASSIGOriolLamelas (\n" +
                    "   id_tassig serial PRIMARY KEY,\n" +
                    "   noms_tassig varchar(40) ARRAY\n" +
                    ");";
            comanda.executeUpdate(sql);
            try {
                comanda = con.createStatement();
                sql = "INSERT INTO TASSIGOriolLamelas(noms_tassig[0], noms_tassig[1])\n" +
                        "VALUES ('Accés a dades', 'Desenvolupament apps mòbils'),\n" +
                        "('ERP', 'Bases de dades');";
                comanda.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("No s'han pogut inserir les dades");
            }
        } catch (SQLException e) {
            System.out.println("Taula ja creada, pelacanyes!");
        }
    }

}
