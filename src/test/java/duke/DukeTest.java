package duke;

import org.junit.jupiter.api.Test;

import java.io.File;

import static duke.Duke.SAVE_FILE_NAME;
import static duke.Duke.WORKING_DIRECTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {

    @Test
    /**
     * This test should test the output generated for greeting.
     */
    void shouldOutputGreeting() {
        Duke duke = new Duke();
        assertEquals(duke.initAndGreet(), "Hello! I'm Duke\nWhat can I do for you?\n");
    }

    @Test
    /**
     * This test tests the clear and todo functions, as well as the save file.
     */
    void clearTodoShouldUpdateAndOutputAndSaveCorrectly() {
        Duke duke = initializeEmptyDuke();
        addSampleTodosToTasklist(duke);
        clearAndCheckTasklistIsEmpty(duke);
        saveFileShouldBeEmpty(duke);
    }

    @Test
    void basicCheckingOfEventsValidation() {
        Duke duke = initializeEmptyDuke();
        addSampleEventsToTasklist(duke);
        String response = duke.getResponse("list");
        assertEquals(response, "Here are the tasks in your list:\n"
                + "1 [E][" + Unicode.CROSS + "] text (at: 02 of December 2019, 7:00PM)\n");
        clearAndCheckTasklistIsEmpty(duke);
    }

    @Test
    void basicCheckingOfDeadlinesValidation() {
        Duke duke = initializeEmptyDuke();
        addSampleDeadlinesToTasklist(duke);
        String response = duke.getResponse("list");
        assertEquals(response, "Here are the tasks in your list:\n"
                + "1 [D][" + Unicode.CROSS + "] text (by: 01 of March 2019, 8:00PM)\n");
        clearAndCheckTasklistIsEmpty(duke);
    }

    @Test
    void basicCheckingOfDoneWithAllTypesOfTasks() {
        Duke duke = initializeEmptyDuke();
        populateDukeTasklistWithSamples(duke);
        setDoneOutOfAndWithinBounds(duke);
        String response = duke.getResponse("list");
        assertEquals(response, "Here are the tasks in your list:\n"
                + "1 [T][" + Unicode.TICK + "] /at\n"
                + "2 [T][" + Unicode.TICK + "] lmao\n"
                + "3 [E][" + Unicode.CROSS + "] text (at: 02 of December 2019, 7:00PM)\n"
                + "4 [D][" + Unicode.TICK + "] text (by: 01 of March 2019, 8:00PM)\n");
        clearAndCheckTasklistIsEmpty(duke);
    }

    @Test
    void basicCheckingOfPersistentFileSaving() {
        Duke duke = initializeEmptyDuke();
        populateDukeTasklistWithSamples(duke);
        setDoneOutOfAndWithinBounds(duke);
        Duke nextDuke = new Duke();
        nextDuke.initAndGreet();
        String response = nextDuke.getResponse("list");
        assertEquals(response, "Here are the tasks in your list:\n"
                + "1 [T][" + Unicode.TICK + "] /at\n"
                + "2 [T][" + Unicode.TICK + "] lmao\n"
                + "3 [E][" + Unicode.CROSS + "] text (at: 02 of December 2019, 7:00PM)\n"
                + "4 [D][" + Unicode.TICK + "] text (by: 01 of March 2019, 8:00PM)\n");
        clearAndCheckTasklistIsEmpty(nextDuke);
    }

    @Test
    void populateAndDelete() {
        Duke duke = initializeEmptyDuke();
        populateDukeTasklistWithSamples(duke);
        setDeleteOutOfAndWithinBounds(duke);
        String response = duke.getResponse("list");
        assertEquals(response, "Here are the tasks in your list:\n"
                + "1 [T][" + Unicode.CROSS + "] lmao\n");
        clearAndCheckTasklistIsEmpty(duke);
    }

    @Test
    void populateAndFind() {
        Duke duke = initializeEmptyDuke();
        populateDukeTasklistWithSamples(duke);
        String response;
        response = duke.getResponse("find ");
        assertEquals(response, Unicode.SAD_FACE + "OOPS!!! find cannot have an empty description\n");
        response = duke.getResponse("find ooof");
        assertEquals(response, "_________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "\n"
                + "_________________________________________\n");
        response = duke.getResponse("find a");
        assertEquals(response, "_________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "\n"
                + "1. [T][" + Unicode.CROSS + "] /at\n"
                + "2. [T][" + Unicode.CROSS + "] lmao\n"
                + "3. [E][" + Unicode.CROSS + "] text (at: 02 of December 2019, 7:00PM)\n"
                + "4. [D][" + Unicode.CROSS + "] text (by: 01 of March 2019, 8:00PM)\n"
                + "_________________________________________\n");
        response = duke.getResponse("find lmao");
        assertEquals(response, "_________________________________________\n"
                + "Here are the matching tasks in your list:\n"
                + "\n"
                + "2. [T][" + Unicode.CROSS + "] lmao\n"
                + "_________________________________________\n");
        clearAndCheckTasklistIsEmpty(duke);
    }

    /**
     * Return a duke ready for testing.
     *
     * @return duke
     */
    Duke initializeEmptyDuke() {
        Duke duke = new Duke();
        duke.initAndGreet();
        clearAndCheckTasklistIsEmpty(duke);
        return duke;
    }

    /**
     * Add sample todo tasks to tasklist.
     *
     * @param duke duke to be tested
     */
    void addSampleTodosToTasklist(Duke duke) {
        String response;
        response = duke.getResponse("todo");
        assertEquals(response, Unicode.SAD_FACE + "OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        response = duke.getResponse("todo /at");
        assertEquals(response, "Got it. I've added this task: \n"
                + "  [T][" + Unicode.CROSS + "] /at\n"
                + "Now you have 1 tasks in the list.\n");
        saveFileShouldExist(duke);
        saveFileShouldNotBeEmpty(duke);
        response = duke.getResponse("todo lmao");
        assertEquals(response, "Got it. I've added this task: \n"
                + "  [T][" + Unicode.CROSS + "] lmao\n"
                + "Now you have 2 tasks in the list.\n");
        response = duke.getResponse("list");
        assertEquals(response, "Here are the tasks in your list:\n"
                + "1 [T][" + Unicode.CROSS + "] /at\n"
                + "2 [T][" + Unicode.CROSS + "] lmao\n");
    }

    /**
     * Add sample events tasks.
     *
     * @param duke duke to be tested
     */
    void addSampleEventsToTasklist(Duke duke) {
        String response;
        response = duke.getResponse("event");
        response = duke.getResponse("event ");
        response = duke.getResponse("event text");
        response = duke.getResponse("event text /at");
        response = duke.getResponse("event text /at text");
        response = duke.getResponse("event text /at 2/12/2019 1900");
        saveFileShouldExist(duke);
        saveFileShouldNotBeEmpty(duke);
    }

    /**
     * Add sample deadline tasks.
     *
     * @param duke duke to be tested
     */
    void addSampleDeadlinesToTasklist(Duke duke) {
        String response;
        response = duke.getResponse("deadline");
        response = duke.getResponse("deadline ");
        response = duke.getResponse("deadline text");
        response = duke.getResponse("deadline text /by");
        response = duke.getResponse("deadline text /by text");
        response = duke.getResponse("deadline text /by 1/3/2019 2000");
        saveFileShouldExist(duke);
        saveFileShouldNotBeEmpty(duke);
    }


    /**
     * Tests the clear function while also clearing the tasklist in duke.
     *
     * @param duke duke to be tested
     */
    void clearAndCheckTasklistIsEmpty(Duke duke) {
        String response;
        response = duke.getResponse("clear");
        assertEquals(response, "Emptied task list\n");
        response = duke.getResponse("list");
        assertEquals(response, "There are no tasks in your list.\n");
    }

    /**
     * Checks the save file has been created.
     *
     * @param duke duke to be tested
     */
    void saveFileShouldExist(Duke duke) {
        File file = new File(WORKING_DIRECTORY + File.separator + SAVE_FILE_NAME);
        assertTrue(file.exists());
    }

    void saveFileShouldBeEmpty(Duke duke) {
        saveFileShouldExist(duke);
        File file = new File(WORKING_DIRECTORY + File.separator + SAVE_FILE_NAME);
        assertEquals(file.length(), 0);
    }

    void saveFileShouldNotBeEmpty(Duke duke) {
        saveFileShouldExist(duke);
        File file = new File(WORKING_DIRECTORY + File.separator + SAVE_FILE_NAME);
        assertNotEquals(file.length(), 0);
    }

    /**
     * Ensure the done feature updates properly.
     *
     * @param duke duke to be tested
     */
    void setDoneOutOfAndWithinBounds(Duke duke) {
        duke.getResponse("done -1");
        duke.getResponse("done 0");
        duke.getResponse("done 1");
        duke.getResponse("done 2");
        duke.getResponse("done 4");
        duke.getResponse("done 99");
    }

    /**
     * Ensure the delete feature updates properly.
     *
     * @param duke duke to be tested
     */
    void setDeleteOutOfAndWithinBounds(Duke duke) {
        duke.getResponse("delete -1");
        duke.getResponse("delete 4");
        duke.getResponse("delete 0");
        duke.getResponse("delete 1");
        duke.getResponse("delete 2");
        duke.getResponse("delete 99");
    }

    /**
     * Populate duke with various tasks.
     *
     * @param duke duke to be tested
     */
    void populateDukeTasklistWithSamples(Duke duke) {
        addSampleTodosToTasklist(duke);
        addSampleEventsToTasklist(duke);
        addSampleDeadlinesToTasklist(duke);
    }
}