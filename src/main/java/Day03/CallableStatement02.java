package Day03;

import java.sql.*;

public class CallableStatement02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        2. Örnek: Koninin hacmini hesaplayan bir function yazın.
         */

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();

        String sql1 = "CREATE OR REPLACE FUNCTION koniHacmi(r NUMERIC, h NUMERIC)\n" +
                "RETURNS NUMERIC\n" +
                "LANGUAGE plpgsql\n" +
                "AS\n" +
                "$$\n" +
                "BEGIN\n" +
                "\n" +
                "RETURN 3.14 * r * r * h / 3;\n" +
                "\n" +
                "END\n" +
                "$$";

        // 2. ADIM : Fonskiyonu çalıştır.

        st.execute(sql1);

        // 3. ADIM : Fonksiyonu çağır.

        CallableStatement cst1 = con.prepareCall("{? = call koniHacmi(?, ?)}");

        // 4. ADIM : Return için registerOutParameter() methodunu, parametreler için set() methodlarından uygun olanları kullan.

        cst1.registerOutParameter(1, Types.NUMERIC);
        cst1.setInt(2, 3);
        cst1.setInt(3, 5);

        // 5. ADIM : Çalıştırmak için execute() methodunu kullan.

        cst1.execute();

        // 6. ADIM : Sonucu çağırmak için return data tipine göre 'get' methodlarından uygun olanı kullan.

        System.out.println(cst1.getBigDecimal(1));


    }


}
