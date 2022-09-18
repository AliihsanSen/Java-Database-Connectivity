package Day04_JdbcPractice;

import java.sql.*;

public class Q02_Query02 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        /*
        SORU: Öğrenciler tablosundaki Erkek öğrencileri listeleyiniz?
         */

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();

        ResultSet veri = st.executeQuery("select * from ogrenciler where cinsiyet='E'");

        while (veri.next()) {
            System.out.printf("%-6d %-15.15s %-8s %-8s\n",
                    veri.getInt(1),
                    veri.getString(2),
                    veri.getString(3),
                    veri.getString(4));
        }
        con.close();
        st.close();
    }
}
