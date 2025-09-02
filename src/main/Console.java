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
            String userName; Integer userNum;
            String bookName; Integer bookNum;
            List<Book> bookList; List<User> userList;
            Book bookForBorrow; User userBorrowing;
            switch (getValue.enterBorrowOrReturn(scanner)) {
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
                    Boolean allBookUnavailable = serviceLibrary.checkAllBookUnavailable(bookList);
                    if (allBookUnavailable) {
                        // 이 borrow를 완전 끝내야함!!
                        System.out.println(String.format("You can't borrow '%s' !!", bookName));
                        break;
                    } else {
                        while (true) {
                            // bookNum
                            Integer bookListLength = bookList.size();
                            System.out.println("● Enter a number what you want to borrow");
                            bookNum = getValue.enterBookNum(scanner, bookListLength);
                            bookForBorrow = serviceLibrary.chooseBookFromBookList(bookNum, bookName);
                            if (bookForBorrow.getLoanAvailable()) {
                                break;
                            } else {
                                System.out.println(String.format("%s(%d) can't be borrowed.",
                                        bookName,
                                        bookNum
                                ));
                                System.out.println("Please try again !");
                            }
                        }
                        while (true) {
                            System.out.println("● Enter a your userName");
                            userName = getValue.enterUserName(scanner);
                            userList = serviceLibrary.getUserListByUserName(userName);
                            if (userList.isEmpty()) {
                                System.out.println(String.format("'%s' is not in this App's Repo, Please try again", userName));
                            } else {
                                System.out.println(String.format("This is a userList that has userName %s", userName));
                                for (int i = 0; i < userList.size(); i++) {
                                    System.out.println(String.format("%d. name → %s / id → %d / loanAvailable → %b",
                                            i + 1,
                                            userList.get(i).getUserName(),
                                            userList.get(i).getUserId(),
                                            userList.get(i).getBorrowAvailable()
                                    ));
                                }
                                Boolean allUserUnavailable = serviceLibrary.checkAllUserUnavailable(userList);
                                if (allUserUnavailable) {
                                    // 다시 user 입력; ( 전체 유저가 입력 불가능 상태도 있음...! )
                                } else {
                                    Integer userListLengthForBorrow = userList.size();
                                    while (true) {
                                        System.out.println("● Enter a user number from userList");
                                        userNum = getValue.enterUserNum(scanner, userListLengthForBorrow);
                                        userBorrowing = serviceLibrary.chooseUserFromUserList(userNum, userName);
                                        if (userBorrowing.getBorrowAvailable()) {
                                            serviceLibrary.borrowBook(bookForBorrow, userBorrowing);
                                            String borrowBookDoneMessage = String.format("--------------- \n <Completed> \n '%s(user)' borrowed '%s(book)'. \n---------------",
                                                    userBorrowing.getUserName(),
                                                    bookForBorrow.getBookName()
                                            );
                                            System.out.println(borrowBookDoneMessage);
                                            break;
                                        } else {
                                            System.out.println("The book you picked can't be borrowed !");
                                            System.out.println("Please try again !");

                                        }
                                    }
                                }
                            }
                        }
                    }
                  //  break; // 무소건 닿는데........

                case "return" :
                    while (true) { // return 구문 예외 처리하기!!!!
                        System.out.println("● Enter userName");
                        userName = getValue.enterUserName(scanner);
                        userList = serviceLibrary.getUserListByUserName(userName);
                        if (userList.isEmpty()) {
                            System.out.println(String.format("'%s' is not in this App's Repo, Please try again", userName));
                            break;
                        } else {
                            System.out.println(String.format("This is a userList that has userName %s", userName));
                            for (int i = 0; i < userList.size(); i++) { // return 구문에도 마저 예외처리 해주기!!
                                System.out.println(String.format("%d. name → %s / id → %d / loanAvailable → %b",
                                        i+1,
                                        userList.get(i).getUserName(),
                                        userList.get(i).getUserId(),
                                        userList.get(i).getBorrowAvailable()
                                        ));
                            }
                        }
                    }
                    Boolean allUserUnavailableForReturn = serviceLibrary.checkAllUserUnavailable(userList);
                    if (allUserUnavailableForReturn) {
                        // 이 return 완전히 멈춰야함!
                    } else {
                        Integer userListLengthForReturn = userList.size();
                        while (true) {
                            userNum = getValue.enterUserNum(scanner, userListLengthForReturn);
                            userBorrowing = serviceLibrary.chooseUserFromUserList(userNum, userName);
                            if (userBorrowing.getBorrowAvailable()) {
                                // return a book
                                System.out.println(String.format("%s(%d) didn't borrowed anything",
                                        userName,
                                        userNum
                                ));
                            } else {
                                String bookNameUserBorrowed = userBorrowing.getBorrowedBookName();
                                System.out.println(String.format("The book's name you borrowed is '%s'", bookNameUserBorrowed));
                                Book bookUserBorrowed = userBorrowing.getBorrowedBook();
                                serviceLibrary.returnBook(bookUserBorrowed, userBorrowing);
                                String returnBookDoneMessage = String.format("--------------- \n <Completed> \n '%s(%d)' returned '%s'. \n---------------",
                                        userBorrowing.getUserName(),
                                        userNum,
                                        bookUserBorrowed.getBookName()
                                );
                                System.out.println(returnBookDoneMessage);
                            }
                        }
                    }
                    // sign up 기능이 왜 없지......??
                    break;

                case "stop" :
                    isRunning = false;
                    break;
            }
        }
    }
}
