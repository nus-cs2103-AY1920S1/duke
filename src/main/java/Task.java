public class Task {
    String description;
    char type;
    int status;  //0=incomplete, 1=complete
    String timeframe;  //for deadlines or events

    Task(String description, char type, int status, String time) {
        this.description = description;
        this.type = type;
        this.status = status;
        this.timeframe = time;
    }

    void changeStatus(int status) {
        this.status = status;
    }
}
