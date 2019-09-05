package com.core;

import com.util.json.ReadWriteFiles;
import java.io.FileNotFoundException;

public class SaveFile {
    private static final String SAVE_FILE = "./data/duke.json";

    public static void write(String content) {
        ReadWriteFiles.write(SAVE_FILE, content);
    }

    public static String read() {
        try {
            return ReadWriteFiles.read(SAVE_FILE);
        } catch (FileNotFoundException e) {
            return "";
        }
    }

}
