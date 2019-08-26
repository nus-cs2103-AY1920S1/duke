import java.text.SimpleDateFormat;
import java.util.Date;

    public abstract class Task {
        protected String taskDesc;
        protected boolean isDone;

        public Task(String desc) {
            this.taskDesc = desc;
            this.isDone = false;
        }

        public boolean getStatus() {
            return isDone;
        }

        public void markAsDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            if(isDone) {
                String printable = "[✓] " + taskDesc;
                return printable;
            } else {
                String printable ="[✗] " + taskDesc;
                return printable;
            }
        }

        public String getDesc() {
            return this.taskDesc;
        }

        public abstract String toFileFormat();
        public abstract Date getDate();


    }

