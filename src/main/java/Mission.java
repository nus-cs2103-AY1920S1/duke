public class Mission {
    enum MissionType {
        T, D, E;
    }
    String content;
    String state;
    String time;
    String atby;
    MissionType type;
    public Mission(String content, String type) {
        this.time = null;
        this.content = content;
        this.state = "✗";
        if(type.contentEquals("todo")) {
            this.type = MissionType.T;
        } else if(type.contentEquals("deadline")) {
            this.type = MissionType.D;
        } else if(type.contentEquals("event")) {
            this.type = MissionType.E;
        }
    }
    public Mission(String content, String type, String time, String atby) {
        this.time = time;
        this.content = content;
        this.state = "✗";
        this.atby = atby;
        if(type.contentEquals("todo")) {
            this.type = MissionType.T;
        } else if(type.contentEquals("deadline")) {
            this.type = MissionType.D;
        } else if(type.contentEquals("event")) {
            this.type = MissionType.E;
        }
}


    public void changeState() {
        if(this.state.contentEquals("✗")) {
            this.state = "✓";
        }
    }

    public void printMission() {
        if(time != null) {
            System.out.println("[" + type + "][" + state + "] " + content + " (" + atby + ":" + time + ")");
        } else {
            System.out.println("[" + type + "][" + state + "] " + content);
        }
    }


    public String getState() {
        return state;
    }

}
