package seedu.duke.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class TaskIdGenerator {
    private static final int NUMBER_OF_DIGITS = 5;
    static final TaskIdGenerator shared = new TaskIdGenerator();

    private Random random;
    private List<Long> listIDs;

    TaskIdGenerator() {
        random = new Random();
        listIDs = new ArrayList<>();
    }

    void setInitialListIDs(List<Long> listIDs) {
        this.listIDs = listIDs;
    }

    long generateAndRegisterNewID() {
        while (true) {
            long id = generateRandomID();
            if (listIDs.stream().noneMatch(value -> value == id)) {
                listIDs.add(id);
                return id;
            }
        }
    }

    private Long generateRandomID() {
        long id = random.nextInt(9) + 1;
        for (int i = 1; i < NUMBER_OF_DIGITS; ++i) {
            id = id * 10 + random.nextInt(10);
        }
        return id;
    }
}
