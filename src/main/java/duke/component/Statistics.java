package duke.component;

public class Statistics {
    private static int numCompleted = 0;
    private static int numUncompleted = 0;

    public Statistics(int numCompleted, int numUncompleted){
        this.numCompleted = numCompleted;
        this.numUncompleted = numUncompleted;
    }

    public static int getNumCompleted() {
        return numCompleted;
    }

    public static int getNumUncompleted(){
        return numUncompleted;
    }

    public static void incrementCompleted(){
        numCompleted++;
    }

    public static void decrementCompleted(){
        numCompleted--;
    }

    public static void decrementUncompleted(){
        numUncompleted--;
    }

    public static void incrementUncompleted(){
        numUncompleted++;
    }


    public static String toStatisticsString(){
        return "Number of tasks completed: " + getNumCompleted()
                        + "\nNumber of tasks not completed: "
                        + getNumUncompleted() + "\n";
    }


}
