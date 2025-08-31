package main;

import service.ServiceLibrary;
import utils.GetConsoleValue;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ServiceLibrary serviceLibrary = new ServiceLibrary();
        GetConsoleValue getValue = new GetConsoleValue();

        Console console = new Console();
        console.run(scanner, serviceLibrary, getValue);

    }
}
