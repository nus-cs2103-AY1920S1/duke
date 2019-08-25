package com.leeyiyuan.ui;


import java.util.Scanner;

public class Ui {

    protected Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println();
    }

    public void showLine(String text) {
        System.out.println(text);
    }

    public void showError(String text) {
        System.out.println("Error: " + text);
    }

    public void showNumTasks(int num) {
        System.out.println(
                String.format("Now you have %d %s in the list.", num, num == 1 ? "task" : "tasks"));
    }

    public String readCommand() throws UiException {
        if (this.scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            throw new UiException("UI stream ended.");
        }
    }
}
