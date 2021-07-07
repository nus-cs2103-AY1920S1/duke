package duke.task;

import duke.date.DukeDateStub;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTaskTest {

    @Test
    void getDueDate_sampleDate_equals() {
        DukeDateStub dds = new DukeDateStub(2019, 9, 1, 17, 22);
        DeadlineTask dt = new DeadlineTask("essay 1",
                                           false,
                                            dds);

        assertEquals(dds, dt.getDueDate());
    }

    @Test
    void getDateAsString_sampleDate_equals() {
        DukeDateStub dds = new DukeDateStub(2019, 9, 1, 17, 22);
        DeadlineTask dt = new DeadlineTask("essay 1",
                false,
                dds);

        assertEquals(dds.format(false), dt.getDateAsString());
    }

    @Test
    void getStatus() {
        DukeDateStub dds = new DukeDateStub(2019, 9, 1, 17, 22);
        DeadlineTask dt = new DeadlineTask("essay 1",
                false,
                dds);

        String expected = String.format("[D][✘] essay 1 (by: %s)", dds.format(false));
        assertEquals(expected, dt.getStatus());
    }
}