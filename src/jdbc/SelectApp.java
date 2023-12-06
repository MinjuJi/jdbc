package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SelectApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String sql = """
                SELECT book_no
                    , book_title
                    , book_writer
                    , book_price
                    , book_stock
                    , book_reg_date
                FROM sample_books
                """;
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        // String url = "jdbc:oracle:thin:@192.168.0.7:1521:xe";
        String username = "hr";
        String password = "zxcv1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int no = resultSet.getInt("book_no");
            String title = resultSet.getString("book_title");
            String writer = resultSet.getString("book_writer");
            int price = resultSet.getInt("book_price");
            int stock = resultSet.getInt("book_stock");
            Date date = resultSet.getDate("book_reg_date");

            System.out.printf("번호: %d\n", no);
            System.out.printf("번호: %s\n", title);
            System.out.printf("번호: %s\n", writer);
            System.out.printf("번호: %d\n", price);
            System.out.printf("번호: %d\n", stock);
            System.out.printf("번호: %tF\n", date);
            System.out.println();
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
