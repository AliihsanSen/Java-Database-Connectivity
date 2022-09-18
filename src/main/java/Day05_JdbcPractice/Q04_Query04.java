package Day05_JdbcPractice;

import static Day05_JdbcPractice.DatabaseUtility.*;

public class Q04_Query04 {

    public static void main(String[] args) {

        createConnection();

        String query = "select * from ogrenciler";
        System.out.println("Sutun isimleri :" + getColumnNames(query));

        System.out.println("Okul numaraları : "+ getColumnData(query,"okul_no"));
    }
}
