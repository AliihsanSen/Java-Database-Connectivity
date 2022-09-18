package Day05_JdbcPractice;

import java.sql.*;

public class Q02_Query02 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();
        /*
        SORU: Öğrenciler tablosuna yen bir kayıt ekleyin (300, 'Sena Can', 12, 'K')
         */

        int s1 = st.executeUpdate("insert into ogrenciler values(300, 'Sena Can', 12, 'K')");
        System.out.println(s1 + " satır database eklendi.");

        /*
        SORU: Öğrenciler tablosuna yen bir kayıt ekleyin
        (400, 'Sena Can', 12, 'K')
        (401, 'Sena Can', 12, 'K')
        (402, 'Sena Can', 12, 'K')
         */

        // 1.YOL
        String veri[] = {"insert into ogrenciler values(400, 'Sena Can', 12, 'K')",
                "insert into ogrenciler values(401, 'Sena Can', 12, 'K')",
                "insert into ogrenciler values(402, 'Sena Can', 12, 'K')"};

        int count = 0;
        for (String each : veri) {
            count = count + st.executeUpdate(each);
        }
        System.out.println("Datalar eklendi.");

    }
}
