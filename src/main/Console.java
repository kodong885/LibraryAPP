package main;

import domain.Book;
import domain.User;
import service.ServiceLibrary;
import utils.GetConsoleValue;

import java.util.Scanner;

public class Console {

    public void run(Scanner scanner, ServiceLibrary serviceLibrary, GetConsoleValue getValue) {

        Boolean isRunning = true;
        while (isRunning) {
            String userName; Integer userNum;
            String bookName; Integer bookNum;
            System.out.println("● Which Function do you want to execute? (borrow or return book) / (stop)");
            System.out.println("(borrow/return/stop) >>");
            switch (getValue.enterBorrowOrReturn(scanner)) {
                case "borrow" :
                    while (true) {
                        System.out.println("● Enter a bookName you want to borrow");
                        bookName = getValue.enterBookName(scanner);
                        if (serviceLibrary.showAllBookByBookName(bookName)) {
                            break;
                        } else {
                            System.out.println(String.format("'%s' is not in this App's Repo, Please try again!", bookName));
                        }
                    }
                    Integer bookListLength = serviceLibrary.getBookListLengthByBookName(bookName);
                    System.out.println("● Enter a number what you want to borrow");
                    bookNum = getValue.enterBookNum(scanner, bookListLength);
                    Book bookForBorrow = serviceLibrary.chooseBookFromBookList(bookNum, bookName);

                    while (true) {
                        System.out.println("● Enter a your userName");
                        userName = getValue.enterUserName(scanner);
                        if (serviceLibrary.showUserListByUserName(userName)) {
                            break;
                        } else {
                            System.out.println(String.format("'%s' is not in this App's Repo, Please try again", userName));
                        }
                    }

                    Integer userListLengthForBorrow = serviceLibrary.getUserLengthByUserName(userName);
                    System.out.println("● Enter a number what you want to borrow");
                    userNum = getValue.enterUserNum(scanner, userListLengthForBorrow);
                    User userBorrowing = serviceLibrary.chooseUserFromUserList(userNum, userName);

                    serviceLibrary.borrowBook(bookForBorrow, userBorrowing);
                    String borrowBookDoneMessage = String.format("--------------- \n <Completed> \n '%s(user)' borrowed '%s(book)'. \n---------------",
                        userBorrowing.getUserName(),
                        bookForBorrow.getBookName()
                    );
                    System.out.println(borrowBookDoneMessage);
                    break;

                case "return" :
                    while (true) {
                        System.out.println("● Enter userName");
                        userName = getValue.enterUserName(scanner);
                        if (serviceLibrary.showUserListByUserName(userName)) {
                            break;
                        } else {
                            System.out.println(String.format("'%s' is not in this App's Repo, Please try again", userName));
                        }
                    }
                    Integer userListLengthForReturn = serviceLibrary.getUserLengthByUserName(userName); // error!! --> userListLength를 전역변수(?)로 해야할까...??
                    userNum = getValue.enterUserNum(scanner, userListLengthForReturn);
                    User user = serviceLibrary.chooseUserFromUserList(userNum, userName);

                    String bookNameUserBorrowed = serviceLibrary.getBookNameUserBorrowed(user);
                    System.out.println(String.format("The book's name you borrowed is '%s'", bookNameUserBorrowed));

                    Book bookUserBorrowed = serviceLibrary.getBookUserBorrowed(user);
                    serviceLibrary.returnBook(bookUserBorrowed, user);
                    String returnBookDoneMessage = String.format("--------------- \n <Completed> \n '%s(user)' returned '%s(book)'. \n---------------",
                            user.getUserName(),
                            bookUserBorrowed.getBookName()
                            );
                    System.out.println(returnBookDoneMessage);
                    // sign up 기능이 왜 없지......??
                    break;

                case "stop" :
                    isRunning = false;
                    break;
            }
        }

    }
}
