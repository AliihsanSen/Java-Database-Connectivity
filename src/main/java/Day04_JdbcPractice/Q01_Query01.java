package Day04_JdbcPractice;

import java.sql.*;

public class Q01_Query01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 1. ADIM : Driver Yükle

        Class.forName("org.postgresql.Driver");

        // 2. ADIM : Bağlantı oluştur

        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");

        // 3. ADIM : Statements tanımla

        Statement st = con.createStatement();

        // 4. ADIM : Query calistir.

        ResultSet veri = st.executeQuery("select * from ogrenciler");

        // 5. ADIM : Sonuçları yazdır

        while (veri.next()) {

            /*
             1. olarak index kullanarak sonuçları alma.

              System.out.println(veri.getInt(1) + " --> " + veri.getString(2) + " --> " +
                    veri.getString(3) + " --> " + veri.getString(4));

             */


            // 2. olarak sutun ismi kulanarak sonuç alma.

            System.out.printf("%-6d %-15.15s %-8s %-8s\n",
                    veri.getInt(1),
                    veri.getString(2),
                    veri.getString(3),
                    veri.getString(4));
        }

        // 6. ADIM : Connectionları kapat.

        con.close();
        st.close();
    }
}
