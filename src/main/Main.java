package main;

import database.ConnexioBD;
import operacions.Operacions;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConnexioBD.verificarDrivers();

        Connection mySqlCon = ConnexioBD.dbConnectionMySQL();

        Connection postgresCon = ConnexioBD.dbConnectionPostgres();

        boolean continua = true;
        do {
            Scanner teclat = new Scanner(System.in);
            System.out.println("\n-------------------------MENÚ ORIOL LAMELAS SÀNCHEZ-------------------------");

            System.out.println("\n1. Llistar assignatures dels professors d'informàtica\n" +
                    "2. Afegir camp Galaxia\n" +
                    "3. Crear taula a PostgresSQL\n" +
                    "4. Sortir");
            byte choice = teclat.nextByte();

            switch (choice) {
                case 1 -> Operacions.llistaAssigInfo(mySqlCon);
                case 2 -> Operacions.galaxiaVL(mySqlCon);
                case 3 -> Operacions.crearTaulaPG(postgresCon);
                case 4 -> continua = false;
                default -> System.out.println("No existeix");
            }
        } while (continua);
    }
}
