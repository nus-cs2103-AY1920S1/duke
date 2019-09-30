package duke.task;

import java.time.LocalDateTime;

public interface Timeable {
    public void updateTime(LocalDateTime updtTime);

    public String getTime();
}
