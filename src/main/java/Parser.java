class Parser {

    private static String subString(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; i++) {
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();
    }

    public static int findIdx(String[] words, String s) {
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(s))
                return i;
        }
        return -1;
    }

    private static Task parseTask(String[] words) {
        if(words[0].equals("todo")) {
            return new ToDo(subString(words, 1, words.length));
        } else if (words[0].equals("deadline")) {
            int i = findIdx(words, "/by");
            String description = subString(words, 1, i);
            String by = subString(words, i + 1, words.length);
            return new Deadline(description, by);
        } else {
            int i = findIdx(words, "/at");
            String description = subString(words, 1, i);
            String at = subString(words, i + 1, words.length);
            return new Event(description, at);
        }
    }

    private static void checkIllegalInstruction(String[] words) throws DukeException {
        String fw = words[0];
        if (!(fw.equals("done") || fw.equals("todo") || fw.equals("deadline") || fw.equals("event")
                    || fw.equals("delete") || fw.equals("list") || fw.equals("bye"))) {
            throw new DukeException(" \u2639  OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
        if ((fw.equals("todo") || fw.equals("deadline") || fw.equals("event")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The description of a " + fw + " cannot be empty.");
        }
        if ((fw.equals("deadline") && findIdx(words, "/by") == -1) || 
                (fw.equals("event") && findIdx(words, "/at") == -1)) {
            throw new DukeException(" \u2639  OOPS!!! The time of a " + fw + " cannot be empty.");
                }
        if ((fw.equals("done") || fw.equals("delete")) && words.length < 2) {
            throw new DukeException(" \u2639  OOPS!!! The task number of a " + fw + " cannot be empty.");
        }
    }

    public static Command parse(String s) throws DukeException {
        String[] words = s.split(" ");
        checkIllegalInstruction(words);
        if (words[0].equals("bye")) {
            return new ExitCommand();
        } else if (words[0].equals("done")) {
            return new DoneCommand(Integer.parseInt(words[1]));
        } else if (words[0].equals("delete")) {
            return new DeleteCommand(Integer.parseInt(words[1]));
        } else if (words[0].equals("list")) {
            return new ListCommand();
        } else {
            Task t = parseTask(words);
            return new AddCommand(t);
        }
    }

}

