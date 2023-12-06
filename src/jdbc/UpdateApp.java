package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String sql = """
                UPDATE sample_books
                SET
                    book_title = ?,
                    book_writer = ?,
                    book_price = ?,
                    book_stock = ?
                WHERE
                    book_no = ?
                """;

        // JDBC Driver를 메모리에 로딩한다
        Class.forName("oracle.jdbc.OracleDriver");

        // 데이터베이스와 연결을 담당하는 Connection 객체를 획득한다.
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "hr";
        String password = "zxcv1234";
        Connection connection = DriverManager.getConnection(url, username, password);

        // SQL 전송을 담당하는 PreparedStatement 객체를 획득한다.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // SQL의 ?에 바인딩할 값을 순서대로 설정헌다.
        preparedStatement.setString(1, "이것이 자바다");
        preparedStatement.setString(2, "신용권");
        preparedStatement.setInt(3, 30_000);
        preparedStatement.setInt(4, 2);
        preparedStatement.setInt(5, 10);

        // SQL을 데이터베이스 보내서 실행시키고 결과를 반환받는다.
        int rowCount = preparedStatement.executeUpdate();
        System.out.printf("%d개의 행이 변경되었습니다", rowCount);

        // 사용했던 자원을 반납한다.
        preparedStatement.close();
        connection.close();

    }
}