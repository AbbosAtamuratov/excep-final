package exceptions;

import java.util.Scanner;

public class InsufficientDataException extends RuntimeException{
    public InsufficientDataException(String dataBit, String message) {
        super(message + " Введите недостающие данные:\n");
        Scanner sc = new Scanner(System.in);
        dataBit = sc.nextLine();
    }
}
