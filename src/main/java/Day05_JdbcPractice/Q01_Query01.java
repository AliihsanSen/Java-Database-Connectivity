package Day05_JdbcPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q01_Query01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        /*
        Statement ve Prestatement arasindaki fark nedir?
        prestatement;
         1-daha dinamik
         2-daha az yer kapliyor
         */

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        PreparedStatement ps = con.prepareStatement("insert into ogrenciler values(?,?,?,?)");
        ps.setInt(1, 200);
        ps.setString(2, "Veli Can");
        ps.setString(3, "12");
        ps.setString(4, "E");

        ps.executeUpdate();
        System.out.println("Veri girişi yapıldı.");
    }
}
