package jdbc2prac;

import java.sql.SQLException;
import java.util.List;

public class ProductUI {
    private final ProductDAO productDAO = new ProductDAO();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("1. 전체조회 2. 상품번호 조회 3. 가격범위 조회 4. 신규등록 5. 삭제 6. 가격변경 7. 수량변경 0. 종료");
            System.out.print("메뉴 선택: ");
            int menuNo = scanner.nextInt();

            try {
                if (menuNo == 1) {
                    readTable();

                }
                if (menuNo == 2) {
                    readRecordByNo();
                }
                if (menuNo == 3) {
                    readRecordsByPriceRange();
                }
                if (menuNo == 4) {
                    registerRecord();
                }
                if (menuNo == 5) {
                    deleteRecordByNo();
                }
                if (menuNo == 6) {
                    updateRecordPrice();
                }
                if (menuNo == 7) {
                    updateRecordStock();
                }
                if (menuNo == 0) {
                    System.out.println("\n프로그램을 종료합니다.");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException("데이터베이스 작업중 오류가 발생하였습니다.");
            }
        }
    }

    private void readTable() throws SQLException {
        System.out.println("\n<모든 상품정보를 조회합니다>");

        List<Product> products = productDAO.getAllProducts();
        for (Product product : products) {
            System.out.printf("%d\n", product.getNo());
            System.out.printf("%s\n", product.getName());
            System.out.printf("%s\n", product.getMaker());
            System.out.printf("%,d\n", product.getPrice());
            System.out.printf("%,d\n", product.getDiscount());
            System.out.printf("%d\n", product.getStock());
            System.out.printf("%s\n", product.getSoldOut());
            System.out.printf("%tF\n", product.getCreateDate());
            System.out.printf("%tF\n", product.getUpdateDate());
            System.out.println("----------------------------");
        }
    }

    private void readRecordByNo() throws SQLException {
        System.out.println("\n<상품번호에 해당하는 상품정보를 조회합니다.>");
        System.out.print("조회하실 상품번호를 입력해주세요: ");
        int productNo = scanner.nextInt();

        Product product = productDAO.getProductByNo(productNo);
        System.out.printf("%d\n", product.getNo());
        System.out.printf("%s\n", product.getName());
        System.out.printf("%s\n", product.getMaker());
        System.out.printf("%,d\n", product.getPrice());
        System.out.printf("%,d\n", product.getDiscount());
        System.out.printf("%d\n", product.getStock());
        System.out.printf("%s\n", product.getSoldOut());
        System.out.printf("%tF\n", product.getCreateDate());
        System.out.printf("%tF\n", product.getUpdateDate());
        System.out.println("----------------------------");
    }

    private void readRecordsByPriceRange() throws SQLException {
        System.out.println("\n<해당 가격 범위에 속하는 상품정보를 조회합니다.>");
        System.out.print("최저가격을 입력해주세요: ");
        int minPrice = scanner.nextInt();
        System.out.print("최고가격을 입력해주세요: ");
        int maxPrice = scanner.nextInt();

        List<Product> products = productDAO.getProductByPriceRange(minPrice, maxPrice);
        for (Product product : products) {
            System.out.printf("%d\n", product.getNo());
            System.out.printf("%s\n", product.getName());
            System.out.printf("%s\n", product.getMaker());
            System.out.printf("%,d\n", product.getPrice());
            System.out.printf("%,d\n", product.getDiscount());
            System.out.printf("%d\n", product.getStock());
            System.out.printf("%s\n", product.getSoldOut());
            System.out.printf("%tF\n", product.getCreateDate());
            System.out.printf("%tF\n", product.getUpdateDate());
            System.out.println("----------------------------");
        }
    }

    private void registerRecord() throws SQLException {
        System.out.println("\n<상품을 등록합니다.>");
        System.out.println("상품 정보를 입력해주세요.");

        System.out.print("번호: ");
        int no = scanner.nextInt();

        System.out.print("상품명: ");
        String name = scanner.nextString();

        System.out.print("브랜드: ");
        String maker = scanner.nextString();

        System.out.print("가격: ");
        int price = scanner.nextInt();

        System.out.print("할인가격: ");
        int discount = scanner.nextInt();

        System.out.print("수량: ");
        int stock = scanner.nextInt();

        Product product = new Product(no, name, maker, price, discount, stock);
        productDAO.InsertProduct(product);
        System.out.println("상품 등록을 완료하였습니다.\n");
    }

    private void deleteRecordByNo() throws SQLException {
        System.out.println("\n<상품번호에 해당하는 상품정보를 삭제합니다.>");
        System.out.print("상품번호를 입력해주제요: ");
        int productNo = scanner.nextInt();

        productDAO.deleteProductByNo(productNo);
        System.out.println("상품번호에 해당하는 상품정보를 삭제 완료하였습니다.\n");
    }

    private void updateRecordPrice() throws SQLException {
        System.out.println("\n<상품번호, 가격, 할인가격을 전달받아서 해당 상품의 가격을 수정합니다.>");
        System.out.print("상품번호를 입력해주세요: ");
        int productNo = scanner.nextInt();

        System.out.print("가격을 입력해주세요: ");
        int productPrice = scanner.nextInt();

        System.out.print("할인가격을 입력해주세요: ");
        int productDiscount = scanner.nextInt();

        productDAO.updateProductPriceByNo(productNo, productPrice, productDiscount);
        System.out.println("상품의 가격을 수정하였습니다.\n");
    }

    private void updateRecordStock() throws SQLException {
        System.out.println("\n<상품번호, 수량을 전달받아서 해당 상품의 수량을 수정합니다.>");
        System.out.print("상품번호를 입력해주세요: ");
        int productNo = scanner.nextInt();

        System.out.print("수량을 입력해주세요: ");
        int productStock = scanner.nextInt();

        productDAO.updateProductStockByNo(productNo, productStock);
        System.out.println("상품의 수량을 수정하였습니다.\n");
    }
}
