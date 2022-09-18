package Day03;

import java.lang.reflect.Type;
import java.sql.*;

public class CallableStatement01 {

    /*
        Java'da methodlar return type sahibi olsa da, void olsa da method olarak adlandırılır.
        SQL'de ise data return ediyorsa "function" denir. Return yapmıyorsa "procedure" diye adlandırılır.
         */

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();

        /*
        1. Örnek: İki parametre ile çalışıp bu parametreleri toplayarak return
         yapan bir fonksiyon oluşturun.
         */

        // 1. ADIM : Fonksiyon kodunu yaz.

        String sql1 = "CREATE OR REPLACE FUNCTION toplamaF(x NUMERIC, y NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "END\n" +
                "$$";

        // 2. ADIM : Fonskiyonu çalıştır.

        st.execute(sql1);

        // 3. ADIM : Fonksiyonu çağır.

        CallableStatement cst1 = con.prepareCall("{? = call toplamaF(?, ?)}");

        // 4. ADIM : Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan.

        cst1.registerOutParameter(1,Types.NUMERIC);
        cst1.setInt(2,15);
        cst1.setInt(3,25);

        // 5. ADIM : Çalıştırmak için execute() methodunu kullan.

        cst1.execute();

        // 6. ADIM : Sonucu çağırmak için return data tipine göre 'get' methodlarından uygun olanı kullan.

        System.out.println(cst1.getBigDecimal(1));


    }
}
