package duke.task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task {
    protected String desc;
    protected boolean isDone;
    static String gap = "  ";

    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean done) {
        this.desc = desc;
        this.isDone = done;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        setDone(true);
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getDoneStatus() {
        return isDone() ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + this.getDoneStatus() + "] " + getDesc();
    }
}
