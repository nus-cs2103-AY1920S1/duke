
    public class Task {
        protected String taskDesc;
        protected boolean isDone;
        protected int taskID;

        public Task(String desc, int taskID) {
            this.taskDesc = desc;
            this.taskID = taskID;
            this.isDone = false;
        }

        public boolean getStatus() {
            return isDone;
        }

        public void markAsDone() {
            isDone = true;
        }

        public String printTask() {
            if(isDone) {
                String printable = taskID + ".[✓] " + taskDesc;
                return printable;
            } else {
                String printable = taskID + ".[✗] " + taskDesc;
                return printable;
            }
        }
    }

