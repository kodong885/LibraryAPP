package utils;

import java.util.Scanner;

public class GetConsoleValue {

    public String enterBorrowOrReturn(Scanner scanner) {
        while (true) {
            try {
                String function = scanner.nextLine();
                if (function.equals("borrow") || function.equals("return") || function.equals("stop")) {
                    return function;

                } else {
                    System.out.println("you can only enter one of 'borrow' and 'return' and 'stop'");
                    System.out.println("Please try again !");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("you can only enter String type here");
                System.out.println("Please try again !");
            }
        }

    }

    public String enterBookName(Scanner scanner) {
        while (true) {
            try {
                System.out.println("bookName >> ");
                String bookName = scanner.nextLine();
                return bookName;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("you can only enter String type here");
                System.out.println("Please try again !");
            }
        }
    }

    public Integer enterBookNum(Scanner scanner, Integer bookListLengthByBookName) {
        while (true) {
            try {
                System.out.println("bookNum >> ");
                Integer bookNum = scanner.nextInt();
                scanner.nextLine();
                if (bookNum < 1 || bookNum > bookListLengthByBookName) {
                    System.out.println("Please select num within bookListLength! ");
                    System.out.println("Please try again!");
                } else {
                    return bookNum;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("you can only enter Integer type here");
                System.out.println("Please try again !");
            }
        }
    }

    public String enterUserName(Scanner scanner) {
        while (true) {
            try {
                System.out.println("userName >>");
                String userName = scanner.nextLine(); // 234 또 "234"으로 받음!!
                return userName;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("you can only enter String type here");
                System.out.println("Please try again !");
            }
        }
    }

    public Integer enterUserNum(Scanner scanner, Integer userListLengthByUserName) {
        while (true) {
            try {
                System.out.println("UserNum >>");
                Integer userNum = scanner.nextInt();
                scanner.nextLine();
                if (userNum < 1 || userNum > userListLengthByUserName) {
                    System.out.println("Please select num within userListLength!");
                    System.out.println("Please try again!");
                } else {
                    return userNum;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("you can only enter Integer type here");
                System.out.println("Please try again !");
            }
        }
    }
}
