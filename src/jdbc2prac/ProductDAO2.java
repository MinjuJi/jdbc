package jdbc2prac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import helper.JdbcTemplate;
import helper.RowMapper;

public class ProductDAO2 {
    public List<Product> getAllProducts() {
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

        return JdbcTemplate.selectList(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs) throws SQLException {
                int no = rs.getInt("product_no");
                String name = rs.getString("product_name");
                String maker = rs.getString("product_maker");
                int price = rs.getInt("product_price");
                int discount = rs.getInt("product_discount_price");
                int stock = rs.getInt("product_stock");
                String soldOut = rs.getString("product_sold_out");
                Date createDate = rs.getDate("product_create_date");
                Date updateDate = rs.getDate("product_update_date");
                Product product = new Product(no, name, maker, price, discount, stock, soldOut, createDate, updateDate);

                return product;
            }
        });
    }

    public Product getProductByNo(int productNo) {
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

        return JdbcTemplate.selectOne(sql, rs -> {
            int no = rs.getInt("product_no");
            String name = rs.getString("product_name");
            String maker = rs.getString("product_maker");
            int price = rs.getInt("product_price");
            int discount = rs.getInt("product_discount_price");
            int stock = rs.getInt("product_stock");
            String soldOut = rs.getString("product_sold_out");
            Date createDate = rs.getDate("product_create_date");
            Date updateDate = rs.getDate("product_update_date");
            Product product = new Product(no, name, maker, price, discount, stock, soldOut, createDate, updateDate);

            return product;
        }, productNo);
    }

    public List<Product> getProductsByPrice(int minPrice, int maxPrice) {
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

        return JdbcTemplate.selectList(sql, rs -> {
            int no = rs.getInt("product_no");
            String name = rs.getString("product_name");
            String maker = rs.getString("product_maker");
            int price = rs.getInt("product_price");
            int discount = rs.getInt("product_discount_price");
            int stock = rs.getInt("product_stock");
            String soldOut = rs.getString("product_sold_out");
            Date createDate = rs.getDate("product_create_date");
            Date updateDate = rs.getDate("product_update_date");
            Product product = new Product(no, name, maker, price, discount, stock, soldOut, createDate, updateDate);

            return product;
        }, minPrice, maxPrice);
    }

    public void insertProduct(Product product) {
        String sql = """
                INSERT INTO sample_products
                (product_no, product_name, product_maker, product_price, product_discount_price, product_stock)
                VALUES
                (?, ?, ?, ?, ?, ?)
                """;
        JdbcTemplate.insert(sql, product.getNo(), product.getName(), product.getMaker(), product.getPrice(),
                product.getDiscount(), product.getStock());
    }

    public void updateProductPrice(int no, int price, int discountPrice) {
        String sql = """
                UPDATE sample_products
                SET
                    product_price = ?,
                    product_discount_price = ?,
                    product_update_date = sysdate
                WHERE product_no = ?
                """;
        JdbcTemplate.update(sql, price, discountPrice, no);
    }

    public void updateProductStock(int no, int stock) {
        String sql = """
                UPDATE sample_products
                SET
                    product_stock = ?,
                    product_update_date = sysdate
                WHERE product_no = ?
                """;
        JdbcTemplate.update(sql, stock, no);
    }

    public void deleteProductByNo(int no) {
        String sql = """
                DELETE sample_products
                WHERE product_no = ?
                """;
        JdbcTemplate.delete(sql, no);
    }
}
