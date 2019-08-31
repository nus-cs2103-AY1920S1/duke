package Util;

import java.io.*;
import java.lang.*;
import java.util.*;

public class createFile {
    private Formatter x;

    public void openFile() {
        try {
            x = new Formatter("data/duke.txt");
        } catch(Exception e) {
            System.out.println("Error in creating file");
        }
    }

    public void closeFile() {
        x.close();
    }

}
