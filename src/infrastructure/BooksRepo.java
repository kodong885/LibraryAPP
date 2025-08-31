package infrastructure;

import domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksRepo {

    private static List<Book> books = new ArrayList<>();

    public List<Book> getBookListByBookName(String bookName) { // error;
        List<Book> bookList = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (bookName.equals(books.get(i).getBookName())) {
                bookList.add(books.get(i));
            } else continue;
        }
        return bookList;
    }

    public Book getBook(Integer bookNum, List<Book> books) {
        return books.get(bookNum-1);
    }


    static {
        // set books
        Book book1 = new Book(0L, "pythonBook", 1000);
        Book book2 = new Book(1L, "pythonBook", 1500);
        Book book3 = new Book(2L, "javaBook", 2000);
        Book book4 = new Book(3L, "javaScriptBook", 2000);
        Book book5 = new Book(4L, "sqlBook", 2500);
        Book book6 = new Book(5L, "javaBook", 3000);
        // add books to bookRepo
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
    }
}
