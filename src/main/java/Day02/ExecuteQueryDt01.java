package Day02;

import java.sql.*;

public class ExecuteQueryDt01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techproed", "postgres", "247361mr");
        Statement st = con.createStatement();

        /*
        1. Örnek: companies tablosundan en yüksek ikinci number_of_employees
        değeri olan company ve number_of_employees değerlerini çağırın.

        --> 1.YOL OFFSET ve FETCH NEXT kullanarak
         */
        String sql1 = "SELECT company,number_of_employees \n" +
                "from companies\n" +
                "order by number_of_employees desc\n" +
                "offset 1 row\n" +
                "fetch next 1 row only";

        ResultSet result1 = st.executeQuery(sql1);

        while (result1.next()) {
            System.out.println("1.YOL = " + result1.getString(1) + " --> " + result1.getInt(2));
        }

        /*
        --> 2.YOL SUBQUERY kullanarak
         */

        String sql2 = "SELECT company,number_of_employees \n" +
                "from companies\n" +
                "where number_of_employees = (SELECT max(number_of_employees)\n" +
                "\t\t\t\t\t\t\tfrom companies\n" +
                "\t\t\t\t\t\t\twhere number_of_employees < \n" +
                "\t\t\t\t\t\t\t(SELECT max(number_of_employees)\n" +
                "\t\t\t\t\t\t\tfrom companies))";

        ResultSet result2 = st.executeQuery(sql2);

        while (result2.next()) {
            System.out.println("2.YOL = " + result2.getString(1) + " --> " + result2.getInt(2));
        }

        con.close();
        st.close();
        result1.close();
        result2.close();

    }
}
