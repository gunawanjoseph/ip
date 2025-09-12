package HawkerUncle.ui;

import java.util.Scanner;

public class Ui {
    public String getWelcome() {
        return "Hello! I'm HawkerUncle. What can I do for you?";
    }
    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine().trim();
    }
    public String showError(String s) {
        return " OOPS!!! " + s;
    }
}
