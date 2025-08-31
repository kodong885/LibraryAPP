package service;

import domain.Book;
import domain.User;
import infrastructure.BooksRepo;
import infrastructure.UsersRepo;
import utils.GetConsoleValue;

import java.util.List;
import java.util.Scanner;

public class ServiceLibrary {

    public Boolean showAllBookByBookName(String bookName) {
        BooksRepo booksRepo = new BooksRepo();
        List<Book> books = booksRepo.getBookListByBookName(bookName);
        if (books.size() == 0) {
            return false;
        } else {
            System.out.println(String.format("This is bookList that has bookName '%s'", bookName));
            System.out.println(books);
            for (int i = 0; i < books.size(); i++) {
                System.out.println(String.format("%d. name → %s / id → %d", i+1, books.get(i).getBookName(), books.get(i).getBookId()));
            }
            return true;
        }
    }

    public Integer getBookListLengthByBookName(String bookName) {
        BooksRepo booksRepo = new BooksRepo();
        List<Book> books = booksRepo.getBookListByBookName(bookName);
        return books.size();
    }

    public Book chooseBookFromBookList(Integer bookNum, String bookName) {
        BooksRepo booksRepo = new BooksRepo();
        List<Book> books = booksRepo.getBookListByBookName(bookName);
        return booksRepo.getBook(bookNum, books);
    }


    public Boolean showUserListByUserName(String userName) {
        UsersRepo usersRepo = new UsersRepo();
        List<User> users = usersRepo.getUserListByUserName(userName);
        if (users.size() == 0) {
            return false;
        } else {
            System.out.println(String.format("This is a userList that has userName %s", userName));
            System.out.println(users);
            for (int i = 0; i < users.size(); i++) {
                System.out.println(String.format("%d. name → %s / id → %d ", i+1, users.get(i).getUserName(), users.get(i).getUserId()));
            }
            return true;
        }
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
