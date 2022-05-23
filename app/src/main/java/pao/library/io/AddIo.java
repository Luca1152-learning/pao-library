package pao.library.io;

import java.util.ArrayList;
import java.util.Arrays;

public class AddIo {
    private static final NumberedOptions ADD_OPTIONS = new NumberedOptions(new ArrayList<>(Arrays.asList(
            "Add book", // 1
            "Add author", // 2
            "Add publisher", // 3
            "Add category", // 4
            "Exit" // 5
    )));

    public void prompt() {

    }
}
