package HawkerUncle.ui;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        System.out.println("  Hello! I'm HawkerUncle.HawkerUncle");
        System.out.println("  What can I do for you?");
    }
    public String readCommand() {
        Scanner s = new Scanner(System.in);
        return s.nextLine().trim();
    }
    public void showError(String s) {
        System.out.println(" OOPS!!! " + s);
    }
}
