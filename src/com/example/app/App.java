package com.example.app;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class App {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();

            String sql = "SELECT * FROM karyawan";

            rs = stmt.executeQuery(sql);

            ArrayList<ArrayList<String>> karyawan = new ArrayList<ArrayList<String>>();

            while (rs.next()) {

                var nama = rs.getString("nama");
                var gaji = rs.getInt("gaji");
                var jabatan = rs.getString("jabatan");
                var region = rs.getString("region");

                /**
                 * Soal 6 A
                 *
                 * if gaji >= 15.000.000
                 *    tunjangan = gaji * 10 / 100
                 * else if gaji >= 10.000.000
                 *    tunjangan = gaji * 12 / 100
                 * else
                 *    tunjangan = gaji * 15 / 100
                 */
                int tunjangan;
                if (gaji >= 15_000_000) {
                    tunjangan = gaji * 10 / 100;
                } else if (gaji >= 10_000_000) {
                    tunjangan = gaji * 12 / 100;
                } else {
                    tunjangan = gaji * 15 / 100;
                }

                /**
                 * Soal 6 B
                 *
                 * int total = gaji + tunjangan
                 *
                 * if region == Jakarta
                 *   total = total - total * 2.5 / 100
                 * if region == bandung
                 *  total = total - total * 2 / 100
                 * else
                 *   total = total - total * 1.8 / 100
                 *
                 * int bonus
                 *
                 * manager = 250_000
                 * ass. manager = 175_000
                 * senior officer = 150_000
                 * middle officer = 125_000
                 * junior officer = 100_000
                 */
                int bonus = switch (jabatan) {
                    case "Manager" -> 250_000;
                    case "Ass. Manager" -> 175_000;
                    case "Senior Officer" -> 150_000;
                    case "Middle Officer" -> 125_000;
                    case "Junior Office" -> 100_000;
                    default -> 0;
                };

                int total = gaji + tunjangan + bonus;

                switch (region) {
                    case "Jakarta":
                        total = (int) (total - (total * 2.5 / 100));
                        break;
                    case "Bandung":
                        total = total - (total * 2 / 100);
                        break;
                    default:
                        total = (int) (total - (total * 1.8 / 100));

                }

                ArrayList<String> inner = new ArrayList<String>();
                inner.add(nama);
                inner.add(String.valueOf(gaji));
                inner.add(jabatan);
                inner.add(region);
                inner.add(String.valueOf(tunjangan));
                inner.add(String.valueOf(bonus));
                inner.add(String.valueOf(total));

                karyawan.add(inner);

            }

            System.out.println("Data Karyawan:");
            System.out.println("Nama - Gaji - Jabatan - Region - Tunjangan - Bonus - Total");

            for (ArrayList<String> strings : karyawan) {
                System.out.println(strings);
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
