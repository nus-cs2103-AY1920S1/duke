public abstract class Task {
        protected String description;
        protected boolean isDone;
        protected Type type;

        public Task(String description) {
            this.description = description;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718");
        }

        public void markAsDone() {
            this.isDone = true;
        }

        @Override
        public String toString() {
            return String.format("[%s][%s] %s", type, getStatusIcon(), description);
        }

        public String toFile() {
            String doneState = isDone ? "1" : "0";
            return String.format("%s //| %s //| %s", type, doneState, description);
        }
}

