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
        return usersRepo.getUser(userNum, users);
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
        int bookNumLoanAvailable = 0;
        for (int i = 0; i < bookList.size(); i++) {
            if (!bookList.get(i).getLoanAvailable()) {
                // bookLoanAvailable ▶ false
                bookNumLoanAvailable++; // 여기 수정함!! ( i → bookNumLoanAvailable )  → 테스트해봐야함!!!
            }
        }
        if (bookNumLoanAvailable == bookList.size()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkAllUserUnavailable(List<User> userList) {
        int userNumLoanAvailable = 0;
        for (int i = 0; i < userList.size(); i++) {
            if (!userList.get(i).getBorrowAvailable()) {
                // bookLoanAvailable ▶ false
                userNumLoanAvailable++;
            }
        }
        if (userNumLoanAvailable == userList.size()) {
            return true;
        } else {
            return false;
        }
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



