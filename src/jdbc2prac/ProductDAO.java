package jdbc2prac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO {
    // 모든 상품정보를 반환하는 기능
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = """
                SELECT product_no
                    , product_name
                    , product_maker
                    , product_price
                    , product_discount_price
                    , product_stock
                    , product_sold_out
                    , product_create_date
                    , product_update_date
                FROM sample_products
                ORDER BY product_no DESC
                """;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int no = resultSet.getInt("product_no");
            String name = resultSet.getString("product_name");
            String maker = resultSet.getString("product_maker");
            int price = resultSet.getInt("product_price");
            int discount = resultSet.getInt("product_discount_price");
            int stock = resultSet.getInt("product_stock");
            String soldOut = resultSet.getString("product_sold_out");
            Date createDate = resultSet.getDate("product_create_date");
            Date updateDate = resultSet.getDate("product_update_date");

            Product product = new Product(no, name, maker, price, discount, stock, soldOut, createDate, updateDate);
            products.add(product);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return products;
    }

    // 상품번호에 해당하는 상품정보를 반환하는 기능
    public Product getProductByNo(int productNo) throws SQLException {
        String sql = """
                SELECT product_no
                    , product_name
                    , product_maker
                    , product_price
                    , product_discount_price
                    , product_stock
                    , product_sold_out
                    , product_create_date
                    , product_update_date
                FROM sample_products
                WHERE product_no = ?
                """;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productNo);
        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.next();
        int no = resultSet.getInt("product_no");
        String name = resultSet.getString("product_name");
        String maker = resultSet.getString("product_maker");
        int price = resultSet.getInt("product_price");
        int discount = resultSet.getInt("product_discount_price");
        int stock = resultSet.getInt("product_stock");
        String soldOut = resultSet.getString("product_sold_out");
        Date createDate = resultSet.getDate("product_create_date");
        Date updateDate = resultSet.getDate("product_update_date");

        Product product = new Product(no, name, maker, price, discount, stock, soldOut, createDate, updateDate);

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return product;
    }

    // 최저가격, 최고가격을 전달받아서 해당 가격 범위에 속하는 상품정보를 반환하는 기능
    public List<Product> getProductByPriceRange(int minPrice, int maxPrice) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = """
                SELECT product_no
                    , product_name
                    , product_maker
                    , product_price
                    , product_discount_price
                    , product_stock
                    , product_sold_out
                    , product_create_date
                    , product_update_date
                FROM sample_products
                WHERE product_price BETWEEN ? AND ?
                ORDER BY product_price ASC
                """;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, minPrice);
        preparedStatement.setInt(2, maxPrice);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int no = resultSet.getInt("product_no");
            String name = resultSet.getString("product_name");
            String maker = resultSet.getString("product_maker");
            int price = resultSet.getInt("product_price");
            int discount = resultSet.getInt("product_discount_price");
            int stock = resultSet.getInt("product_stock");
            String soldOut = resultSet.getString("product_sold_out");
            Date createDate = resultSet.getDate("product_create_date");
            Date updateDate = resultSet.getDate("product_update_date");

            Product product = new Product(no, name, maker, price, discount, stock, soldOut, createDate, updateDate);
            products.add(product);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return products;
    }

    // 신규 상품정보를 저장하는 기능
    public void InsertProduct(Product product) throws SQLException {
        String sql = """
                INSERT INTO sample_products
                (product_no, product_name, product_maker, product_price, product_discount_price, product_stock)
                VALUES
                (?, ?, ?, ?, ?, ?)
                """;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1, product.getNo());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setString(3, product.getMaker());
        preparedStatement.setInt(4, product.getPrice());
        preparedStatement.setInt(5, product.getPrice());
        preparedStatement.setInt(6, product.getStock());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    // 상품번호를 전달받아서 해당 상품정보를 삭제하는 기능
    public void deleteProductByNo(int productNo) throws SQLException {
        String sql = """
                DELETE FROM sample_products
                WHERE product_no = ?
                """;

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productNo);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }

    // 상품번호, 가격, 할인가격을 전달받아서 해당 상품의 가격을 수정하는 기능
    public void updateProductPriceByNo(int productNo, int productPrice, int productDiscount) throws SQLException {
        String sql = """
                UPDATE sample_products
                SET
                    product_price = ?,
                    product_discount_price = ?,
                    product_update_date = sysdate
                WHERE product_no = ?
                """;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productPrice);
        preparedStatement.setInt(2, productDiscount);
        preparedStatement.setInt(3, productNo);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    // 상품번호, 수량을 전달받아서 해당상품의 수량을 변경하는 기능
    public void updateProductStockByNo(int productNo, int productStock) throws SQLException {
        String sql = """
                UPDATE sample_products
                SET
                    product_stock = ?,
                    product_update_date = sysdate
                WHERE product_no = ?
                """;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, productStock);
        preparedStatement.setInt(2, productNo);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
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
