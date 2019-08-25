public class Deadline extends Task {
    /**
     * Constructor
     *
     * @param s Input String specifying task
     */
    public Deadline(String s) {
        super(s);
    }

    /**
     * returns Task as formatted String
     *
     * @return formatted String
     */
    public String toString() {
        String unprocessed = super.toString();
        StringBuilder temp = new StringBuilder();

        boolean isFirst = true;
        if (unprocessed.contains("/")) {
            for (int i = 0; i < unprocessed.length(); i++) {
                char c = unprocessed.charAt(i);
                if (c == '/' && isFirst) {
                    temp.append("(by:");
                    i += 2;
                    isFirst = false;
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
