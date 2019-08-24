public class Deadline extends Task {
    public Deadline(String s) {
        super(s);
    }
    public String toString() {
        String unprocessed = super.toString();
        StringBuilder temp = new StringBuilder();
        if (unprocessed.contains("/")) {
            for (int i = 0; i < unprocessed.length(); i++) {
                char c = unprocessed.charAt(i);
                if (c == '/') {
                    temp.append("(by:");
                    i += 2;
                } else {
                    temp.append(c);
                }
                //Process char
            }
            return "[D]" + temp.append(')').toString();
        } else {
            return "[D]" + unprocessed;
        }



    }
}
