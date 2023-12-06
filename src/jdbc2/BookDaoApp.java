package jdbc2;

public class BookDaoApp {
    public static void main(String[] args) throws Exception {

       /* 책정보를 저장하는 기능
        Book book1 = new Book();
        book1.setNo(101);
        book1.setTitle("자바의 정석 기초편");
        book1.setWriter("남궁성");
        book1.setPrice(28_000);
        book1.setStock(30);

        Book book2 = new Book();
        book2.setNo(102);
        book2.setTitle("개발자를 위한 DevOps");
        book2.setWriter("진명조");
        book2.setPrice(25_000);
        book2.setStock(5);

        BookDao bookDao = new BookDao();
        bookDao.insertBook(book1);
        bookDao.insertBook(book2);
        */


        /* 책정보를 변경하는 기능
        Book book1 = new Book();
        book1.setNo(101);
        book1.setTitle("자바의 정석");
        book1.setWriter("남궁성");
        book1.setPrice(30_000);
        book1.setStock(6);

        BookDao bookDao = new BookDao();
        bookDao.updateBook(book1);
        */


        /* 책정보를 삭제하는 기능
        BookDao bookDao = new BookDao();
        bookDao.deleteBookByNo(102);
        */


        /* 한 권의 책정보를 조회하는 기능
        BookDao bookDao = new BookDao();
        Book book = bookDao.getBookByNo(101);
        System.out.printf("%d, %s\n", book.getNo(), book.getTitle());
        */


        /* 모든 책정보를 조회하는 기능
        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.getAllBooks();
        for (Book b : books) {
            System.out.printf("%d, %s\n", b.getNo(), b.getTitle());
        }
        */
    }
}
