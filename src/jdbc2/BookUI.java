package jdbc2;

import java.sql.SQLException;
import java.util.List;

public class BookUI {
    private BookDao bookDao = new BookDao();
    private Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        System.out.println("\n1. 전체조회 2. 상세조회 3. 등록 4. 수정 5. 삭제 0. 종료");
        System.out.print("메뉴선택: ");
        int menuNo = scanner.nextInt();
        try {
            switch (menuNo) {
                case 1:
                    전체조회();
                    break;
                case 2:
                    상세조회();
                    break;
                case 3:
                    등록();
                    break;
                case 4:
                    수정();
                    break;
                case 5:
                    삭제();
                    break;
                case 0:
                    종료();
                    break;
            }
        } catch (Exception e) {
            System.out.println("데이터베이스 작업중 오류가 발생하였습니다.");
        }
        showMenu();
    }

    private void 전체조회() throws SQLException {
        System.out.println("\n<전체조회>");
        List<Book> books = bookDao.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("도서 정보가 존재하지 않습니다.");
            return;
        }
        for (Book book : books) {
            System.out.printf("번호: %d\n", book.getNo());
            System.out.printf("가격: %,d\n", book.getPrice());
            System.out.printf("수량: %d\n", book.getStock());
            System.out.printf("저자: %s\n", book.getWriter());
            System.out.printf("제목: %s\n", book.getTitle());
            System.out.println();
        }
    }

    private void 상세조회() throws SQLException {
        System.out.println("\n<상세조회>");
        System.out.println("책번호를 입력해서 도서 상세정보를 확인하세요.");
        System.out.print("책번호: ");
        int bookNo = scanner.nextInt();

        Book book = bookDao.getBookByNo(bookNo);
        if (book == null) {
            System.out.printf("책번호: %d 책정보가 존재하지 않습니다", bookNo);
            return;
        }
        System.out.printf("번호: %d\n", book.getNo());
        System.out.printf("제목: %s\n", book.getTitle());
        System.out.printf("저자: %s\n", book.getWriter());
        System.out.printf("가격: %,d\n", book.getPrice());
        System.out.printf("수량: %d\n", book.getStock());
        System.out.printf("등록일: %tF\n", book.getRegDate());

    }

    private void 등록() throws SQLException {
        System.out.println("\n<신규 도서 등록>");
        System.out.println("신규 도서 정보를 입력하세요.");

        System.out.print("번호: ");
        int no = scanner.nextInt();

        System.out.print("제목: ");
        String title = scanner.nextString();

        System.out.print("저자: ");
        String writer = scanner.nextString();

        System.out.print("가격: ");
        int price = scanner.nextInt();

        System.out.print("수량: ");
        int stock = scanner.nextInt();

        Book book = new Book();
        book.setNo(no);
        book.setTitle(title);
        book.setWriter(writer);
        book.setPrice(price);
        book.setStock(stock);

        bookDao.insertBook(book);
        System.out.println("신규 도서를 등록하였습니다.\n");
    }

    private void 수정() throws SQLException {
        // 추후 구현 예정
    }

    private void 삭제() throws SQLException {
        System.out.println("\n<도서 정보 삭제>");
        System.out.println("책번호를 입력받아서 책정보를 삭제합니다.");
        System.out.print("책번호 입력: ");
        int bookNo = scanner.nextInt();

        bookDao.deleteBookByNo(bookNo);
        System.out.printf("책번호 %d의 책정보가 삭제되었습니다.\n", bookNo);

    }

    private void 종료() {
        System.out.println("프로그램을 종료합니다.");
        System.exit(0);
    }
}
