package pao.library.io;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberedOptions {
    ArrayList<String> options;

    public NumberedOptions(ArrayList<String> options) {
        this.options = options;
    }

    public int prompt() {
        while (true) {
            for (int i = 0; i < options.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + options.get(i));
            }
            System.out.print("Choose an option (1-" + options.size() + "): ");

            Scanner input = new Scanner(System.in);
            int option;
            try {
                option = input.nextInt();
            } catch (InputMismatchException exception) {
                // The user didn't enter a number
                System.out.println("\nNot a number. Try again.\n");
                continue;
            }
            // The user didn't enter a number in the interval [1-options]
            if (option < 1 || option > options.size()) {
                System.out.println("\nOption not in [1-" + options.size() + "]. Try again.\n");
                continue;
            }

            return option;
        }
    }
}
