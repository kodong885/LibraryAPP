package service;

import domain.Book;
import domain.User;
import infrastructure.BooksRepo;
import infrastructure.UsersRepo;

import java.util.List;

public class ServiceLibrary {


    public List<Book> getBookListByBookName(String bookName) {
        BooksRepo booksRepo = new BooksRepo();
        List<Book> books = booksRepo.getBookListByBookName(bookName);
        return books;
    }

    public List<User> getUserListByUserName(String userName) {
        UsersRepo usersRepo = new UsersRepo();
        List<User> users = usersRepo.getUserListByUserName(userName);
        return users;
    }


    public Integer getUserLengthByUserName(String userName) {
        UsersRepo usersRepo = new UsersRepo();
        List<User> users = usersRepo.getUserListByUserName(userName);
        return users.size();
    }

    public User chooseUserFromUserList(Integer userNum, String userName) { // error!!!!!
        UsersRepo usersRepo = new UsersRepo();
        List<User> users = usersRepo.getUserListByUserName(userName);
        return  usersRepo.getUser(userNum, users);
    }

    public Book getBookUserBorrowed(User user) {
        return user.getBorrowedBook();
    }

    public String getBookNameUserBorrowed(User user) {
        return user.getBorrowedBookName();
    }

    public Book chooseBookFromBookList(Integer bookNum, String bookName) {
        BooksRepo booksRepo = new BooksRepo();
        List<Book> books = booksRepo.getBookListByBookName(bookName);
        return booksRepo.getBook(bookNum, books);
    }

    public Boolean checkAllBookUnavailable(List<Book> bookList) {
        // bookList 내부, 모든 book이 Unavailable할 경우 확인
    }

    public void borrowBook(Book book, User user) {
        book.setBookLoanAvailable(false);
        user.setUserBorrowedBook(book);
        user.setUserBorrowAvailable(false);
    }

    public void returnBook(Book book, User user) {
        book.setBookLoanAvailable(true);
        user.setUserBorrowedBook(null);
        user.setUserBorrowAvailable(true);
    }

}


