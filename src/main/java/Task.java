public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = perfectDescription(description);
        this.isDone = false;
    }

    public Task(String description, String done) {

        this.description = perfectDescription(description);
        if (done.trim().equals("1")) {
            this.isDone = true;
        }
    }

    public String perfectDescription(String description){
        String[] temp = description.split(" ");
        String result = "";
        for(String str: temp){
            if(!str.equals("")){
                result += " " + str.trim();
            }
        }
        return result.trim();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "V" : "X"); //return tick or X symbols
    }

    public abstract String getFormatToFile();

    @Override
    public String toString() {
//       System.out.println("debug: " + getStatusIcon());
        return "[" + getStatusIcon() + "] " + description;
    }
}