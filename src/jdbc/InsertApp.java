package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertApp {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. JDBC Driver를 드라이버 레지스트리에 등록시킨다.
        // Class.forName(클래스 전체경로): 지정된 클래스를 메모리에 로딩한다. 객체 생성 X
        Class.forName("oracle.jdbc.OracleDriver");

        // 2. Connection 객체를 획득한다.
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "hr";
        String password = "zxcv1234";

        Connection connection = DriverManager.getConnection(url, username, password);

        // 3. PreparedStatement 객체를 획득한다.
        String sql = """
                    INSERT INTO sample_books
                    (book_no, book_title, book_writer, book_price, book_stock)
                    VALUES
                    (?, ?, ?, ?, ?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 10);
        preparedStatement.setString(2, "이것이 자바다");
        preparedStatement.setString(3, "신용권");
        preparedStatement.setInt(4, 35_000);
        preparedStatement.setInt(5, 20);

        // 4. SQL을 서버로 전송하고 실행시킨다.
        int rowCount = preparedStatement.executeUpdate();
        System.out.println(rowCount + "개의 행이 추가되었습니다.");

        // 5. 사용했던 자원을 반납한다.
        preparedStatement.close();
        connection.close();
    }
}
