package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String sql = """
                DELETE FROM sample_books
                WHERE book_no = ?
                """;

        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "hr";
        String password = "zxcv1234";
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, 10);
        int rowCount = preparedStatement.executeUpdate();
        System.out.printf("%d개의 행이 삭제되었습니다.", rowCount);

        preparedStatement.close();
        connection.close();
    }
}
