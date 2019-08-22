package com.leeyiyuan;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<String>();        
        
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < items.size(); i++) {
                    System.out.println(String.format(
                                "%d. %s", 
                                i + 1, 
                                items.get(i)));
                }
            } else {
                items.add(input);
                System.out.println(String.format("added: %s", input));
            }
        }
    }
}
