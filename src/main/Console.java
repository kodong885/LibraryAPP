package main;

import domain.Book;
import domain.User;
import service.ServiceLibrary;
import utils.GetConsoleValue;

import java.util.List;
import java.util.Scanner;

public class Console {

    public void run(Scanner scanner, ServiceLibrary serviceLibrary, GetConsoleValue getValue) {

        Boolean isRunning = true;
        while (isRunning) {
            System.out.println("● Which Function do you want to execute? (borrow or return book) / (stop)");
            System.out.println("(borrow/return/stop) >>");
            switch (getValue.enterBorrowOrReturn(scanner)) {
                String userName; Integer userNum;
                String bookName; Integer bookNum;
                List<Book> bookList; List<User> userList;
                case "borrow" :
                    while (true) {
                        // set bookName, bookList;
                        System.out.println("● Enter a bookName you want to borrow");
                        bookName = getValue.enterBookName(scanner);
                        bookList = serviceLibrary.getBookListByBookName(bookName);
                        if (bookList.isEmpty()) {
                            System.out.println(String.format("'%s' is not in this App's Repo, Please try again!", bookName));
                        } else {
                            System.out.println(String.format("This is bookList that has bookName '%s'", bookName));
                            for (int i = 0; i < bookList.size(); i++) {
                                System.out.println(String.format("%d. name → %s / id → %d / loanAvailable → %b",
                                        i+1,
                                        bookList.get(i).getBookName(),
                                        bookList.get(i).getBookId(),
                                        bookList.get(i).getLoanAvailable()));
                            }
                            break;
                        }
                    }
                    while (true) {
                        // bookNum
                        Integer bookListLength = bookList.size();
                        if (serviceLibrary.checkAllBookUnavailable(bookList)) {
                            System.out.println(String.format("You can't borrow '%s' !!", bookName));
                            break; // 그냥 이 전체를 끝내야함!!!
                        } else {
                            System.out.println("● Enter a number what you want to borrow");
                            bookNum = getValue.enterBookNum(scanner, bookListLength);
                            Book bookForBorrow = serviceLibrary.chooseBookFromBookList(bookNum, bookName);
                            if (bookForBorrow.getLoanAvailable()) {
                                break;
                            } else {
                                System.out.println("book(%d) can't be borrowed.");
                                System.out.println("Please try again !");
                        }
                    }
                    break;
                // ------------------------- ( book 선정 ↑ );
                while (true) {
                    System.out.println("● Enter a your userName");
                    userName = getValue.enterUserName(scanner);
                    userList = serviceLibrary.getUserListByUserName(userName);
                    if (userList.isEmpty()) {
                        System.out.println(String.format("'%s' is not in this App's Repo, Please try again", userName));
                    } else {
                        System.out.println(String.format("This is a userList that has userName %s", userName));
                        for (int i = 0; i < userList.size(); i++) {
                            System.out.println(String.format("%d. name → %s / id → %d ", i+1, userList.get(i).getUserName(), userList.get(i).getUserId()));
                        }
                        break;
                    }
                }
                Integer userListLengthForBorrow = userList.size();
                while (true) {
                    System.out.println("● Enter a user number from userList ");
                    userNum = getValue.enterUserNum(scanner, userListLengthForBorrow);
                    User userBorrowing = serviceLibrary.chooseUserFromUserList(userNum, userName);
                    if (userBorrowing.getBorrowAvailable()) { // book의 setAvailable이 true인지, false인지 !!!!!!!!!!!!!!!;
                        break;
                    } else {
                        System.out.println("The book you picked can't be borrowed !");
                        System.out.println("Please try again !");

                    }
                }

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
                        userList = serviceLibrary.getUserListByUserName(userName);
                        if (userList.isEmpty()) {
                            System.out.println(String.format("'%s' is not in this App's Repo, Please try again", userName));
                            break;
                        } else {
                            System.out.println(String.format("This is a userList that has userName %s", userName));
                            for (int i = 0; i < userList.size(); i++) { // return 구문에도 마저 예외처리 해주기!!
                                System.out.println(String.format("%d. name → %s / id → %d ", i+1, userList.get(i).getUserName(), userList.get(i).getUserId()));
                            }
                        }
                    }
                    Integer userListLengthForReturn = userList.size();
                    User user;
                    userNum = getValue.enterUserNum(scanner, userListLengthForReturn);
                    user = serviceLibrary.chooseUserFromUserList(userNum, userName);
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
