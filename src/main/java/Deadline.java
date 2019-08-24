public class Deadline extends Task {
    public Deadline(String s) {
        super(s);
    }
    public String toString() {
        String unprocessed = super.toString();
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < unprocessed.length(); i++) {
            char c = unprocessed.charAt(i);
            if (c == '/') {
                temp.append("(by:");
                i += 2;
            } else {
                temp.append(c);
            }
        }

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
            //Process char
        }


        return "[D]" + temp.append(')').toString();
    }
}
