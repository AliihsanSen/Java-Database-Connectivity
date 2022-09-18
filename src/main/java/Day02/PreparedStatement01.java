package Day02;

import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();

        /*
        1. Örnek: Prepared statement kullanarak company adı IBM olan
                  number_of_employees değerini 9999 olarak güncelleyin.
         */

        // 1 . ADIM : Prepared statement query'sini oluştur.
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";

        // 2 . ADIM : PreparedStatement objesini oluştur.
        PreparedStatement pst1 = con.prepareStatement(sql1);

        // 3 . ADIM : set...() methodlari ile soru işaretleri için değer girilecek.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        // 4 . ADIM : Execute query (Query çalıştır)
        int updateRowSayisi = pst1.executeUpdate();
        System.out.println("updateRowSayisi = " + updateRowSayisi);

        String sql2 = "SELECT * FROM companies";
        ResultSet result1 = st.executeQuery(sql2);

        while (result1.next()) {
            System.out.println(result1.getInt(1) + " --> " + result1.getString(2) + " --> " + result1.getInt(3));
        }
        System.out.println("================================");


        /*
         2. Örnek: company adı GOOGLE olan number_of_employees değerini 1500 olarak güncelleyin.
         */

        pst1.setInt(1, 15000);
        pst1.setString(2, "GOOGLE");


        int updateRowSayisi2 = pst1.executeUpdate();
        System.out.println("updateRowSayisi = " + updateRowSayisi2);

        String sql3 = "SELECT * FROM companies";
        ResultSet result2 = st.executeQuery(sql3);

        while (result2.next()) {
            System.out.println(result2.getInt(1) + " --> " + result2.getString(2) + " --> " + result2.getInt(3));
        }
        System.out.println("================================");

        /*
        3. Örnek: "SELECT * FROM <table name>" query'sini prepared statement ile kullanın.
         */
        read_Data(con, "companies");
        con.close();
        st.close();

    }

    // Bir tablonun istenilen datasını prepared statement ile çağırmak için kullanılan method
    public static void read_Data(Connection con, String tableName) {

        try {
            String query = String.format("SELECT * FROM %s", tableName); // Format() methodu dinamik String oluşturmak için kullanılır.

            Statement statement = con.createStatement();

            // SQL Query'i çalıştırır
            ResultSet r5 = statement.executeQuery(query); // Datayı çağırıp ResulSet konteynırına koyuyuoruz.

            while (r5.next()) { // Tüm datayı çağırdık.
                System.out.println(r5.getInt(1) + " --> " + r5.getString(2) + " --> " + r5.getInt(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
