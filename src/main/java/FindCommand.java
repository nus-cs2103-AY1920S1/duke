class FindCommand implements Command {
    private String findString;
    public FindCommand(String findString) {
        this.findString = findString;
    }

    @Override
    public void execute(Ui uiManager, TaskList taskList, Storage storeManager) throws DukeException {
        int index = 1;
        uiManager.printFinding(this.findString);
        for(int i = 0; i < taskList.listSize(); i++) {
            Task currTask = taskList.getTask(i, uiManager);
            if(checkEqualInsensitive(currTask, this.findString)) {
                uiManager.printTask(index, currTask);
                index++;    
            }
        }

        // Print nothing found
        if(index == 1) {
            uiManager.printNotFound();
        }
    }

    private boolean checkEqualInsensitive(Task task, String findString) {
        return task.getTaskString().toLowerCase().contains(findString.toLowerCase());
    }
}