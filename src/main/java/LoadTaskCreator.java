class LoadTaskCreator implements TaskCreator {

    //https://stackoverflow.com/questions/16311651/java-string-split-by
    public TaskInterface createTask(String command) 
        throws OWOException {
        String[] cmdList = command.split(" \\| ");
        if (cmdList[0].toUpperCase().equals("T")) {
            return createToDo(command);
        } else if 
            (cmdList[0].toUpperCase().equals("D")) {
            return createDeadLine(command);
        } else if 
            (cmdList[0].toUpperCase().equals("E")) {
            return createEvent(command);
        } else {
            throw new OWOException("OOPS ;;w;;  UwU  ;;w;;  "
                + "OwO is sowwy,\n"
                + "but OwO doesn't knyow how to woad this");
            //+ "but OwO doesn't knyow what that means");
        } 
    }

    private TaskInterface createToDo(String command) {
        String[] cmdList = command.split(" \\| ");
        boolean isDone = ! cmdList[1].equals("0"); 
        String taskName = cmdList[2];
        return new ToDosImplementation(taskName, isDone);
    }

    private TaskInterface createDeadLine(String command) {
        String[] cmdList = command.split(" \\| ");
        boolean isDone = ! cmdList[1].equals("0"); 
        String taskName = cmdList[2];
        String date = cmdList.length < 4 ? "" : cmdList[3];
        return new DeadLinesImplementation(taskName, date,isDone);
    }

    private TaskInterface createEvent(String command) {
        String[] cmdList = command.split(" \\| ");
        boolean isDone = ! cmdList[1].equals("0"); 
        String taskName = cmdList[2];
        String date = cmdList.length < 4 ? "" : cmdList[3];
        return new EventsImplementation(taskName, date,isDone);
    }


}
