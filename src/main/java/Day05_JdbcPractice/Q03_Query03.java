package Day05_JdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Q03_Query03 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();

        /*
        SORU: Öğrenciler tablosuna yen bir kayıt ekleyin (300, 'Sena Can', 12, 'K')
         */

        // 2. YOL

        String veri[] = {"insert into ogrenciler values(500, 'Sena Can', 12, 'K')",
                "insert into ogrenciler values(501, 'Sena Can', 12, 'K')",
                "insert into ogrenciler values(502, 'Sena Can', 12, 'K')"};

        for (String each : veri) {
            st.addBatch(each); // Yukarıdaki dataların hepsini birleştiriyor.
        }
        st.executeBatch(); // Dataları tek seferde gönderiyor.
    }
}
