package jdbc2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookDao {
    // 책정보를 저장하는 기능
    public void insertBook(Book book) throws SQLException {
        String sql = """
                INSERT INTO sample_books
                (book_no, book_title, book_writer, book_price, book_stock)
                VALUES
                (?, ?, ?, ?, ?)
                """;

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, book.getNo());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setString(3, book.getWriter());
        preparedStatement.setInt(4, book.getPrice());
        preparedStatement.setInt(5, book.getStock());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    // 책정보를 변경하는 기능
    public void updateBook(Book book) throws SQLException {
        String sql = """
                UPDATE sample_books
                SET
                    book_title = ?,
                    book_writer = ?,
                    book_price = ?,
                    book_stock = ?
                WHERE book_no = ?
                """;

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setString(2, book.getWriter());
        preparedStatement.setInt(3, book.getPrice());
        preparedStatement.setInt(4, book.getStock());
        preparedStatement.setInt(5, book.getNo());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    // 책정보를 삭제하는 기능
    public void deleteBookByNo(int bookNo) throws SQLException {
        String sql = """
                DELETE FROM sample_books
                WHERE book_no = ?
                """;

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, bookNo);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    // 모든 책정보를 조회하는 기능
    public List<Book> getAllBooks() throws SQLException {
        String sql = """
                SELECT book_no
                    , book_title
                    , book_writer
                    , book_price
                    , book_stock
                    , book_reg_date
                FROM sample_books
                ORDER BY book_no DESC
                """;

        List<Book> bookList = new ArrayList<>();

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int no = resultSet.getInt("book_no");
            String title = resultSet.getString("book_title");
            String writer = resultSet.getString("book_writer");
            int price = resultSet.getInt("book_price");
            int stock = resultSet.getInt("book_stock");
            Date date = resultSet.getDate("book_reg_date");

            Book book = new Book();
            book.setNo(no);
            book.setTitle(title);
            book.setWriter(writer);
            book.setPrice(price);
            book.setStock(stock);
            book.setRegDate(date);

            bookList.add(book);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bookList;
    }

    // 한 권의 책정보를 조회하는 기능
    public Book getBookByNo(int bookNo) throws SQLException {
        String sql = """
                SELECT book_no
                    , book_title
                    , book_writer
                    , book_price
                    , book_stock
                    , book_reg_date
                FROM sample_books
                WHERE book_no = ?
                """;

        Book book = null;

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, bookNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int no = resultSet.getInt("book_no");
            String title = resultSet.getString("book_title");
            String writer = resultSet.getString("book_writer");
            int price = resultSet.getInt("book_price");
            int stock = resultSet.getInt("book_stock");
            Date date = resultSet.getDate("book_reg_date");

            book = new Book();
            book.setNo(no);
            book.setTitle(title);
            book.setWriter(writer);
            book.setPrice(price);
            book.setStock(stock);
            book.setRegDate(date);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return book;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "hr";
        String password = "zxcv1234";
        return DriverManager.getConnection(url, username, password);
    }
}
