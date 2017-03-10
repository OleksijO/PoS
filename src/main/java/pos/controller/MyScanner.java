package pos.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Oleksii_Onysymchuk on 3/9/2017.
 */
public class MyScanner {
    public static final int VALUE_IN_CASE_OF_NON_NUMERIC_INPUT=0;
    private Scanner scanner = new Scanner(System.in);

    public int inputChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return VALUE_IN_CASE_OF_NON_NUMERIC_INPUT;
        }
    }
}
