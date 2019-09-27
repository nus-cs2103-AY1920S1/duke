# User Guide

## 1. Introduction
Duke is for those who prefer to use a desktop chat bot for managing their tasks. More importantly, Duke is optimised for those who prefer to work with a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your tasks done faster than traditional GUI apps. Interested? Jump in!

## 2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest duke.jar here.
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Double-click the file to start the chat bot. The GUI should appear in a few seconds.
![Image of Ui](./Ui.png)
5. Type the command in the text field and press Enter to say it to the Duke chat bot. e.g. typing list and pressing Enter will cause Duke to respond in the GUI with a list of existing tasks 
6. Some example commands you can try:
    * `list` : lists all contacts .
    * `todo workout` : adds an uncompleted todo with description workout as the last task in list. 
    * `delete 1` : deletes the 1st task shown in the current list  if it exists.
    * `exit` : exits the app.
7. Refer to Section 3, “Features” for details of each command.

## 3. Features 
Kindly note that `datetime` inputs must comply with `DD/MM/YYY HHMM` in 24-hour time format. Do note that every date must be accompanied by a time as there is no default time.

### `todo` - Adds a todo
Adds an uncompleted todo task as the last item of task list.

Format: `todo <description>`

Example of usage: `todo eat healthy`

Expected outcome: 
```
Got it. I've added this task:
[T][✘] eat healthy
Now you have 1 task in the list.
```

### `deadline` - Adds a deadline
Adds an uncompleted deadline with `description` due by `datetime` as the last item of task list.

Format: `deadline <description> /by <datetime>`

Example of usage: `deadline return book /by 2/12/2019 1800`

Expected outcome:
```
Got it. I've added this task:
[D][✘] return book (by: 2nd of December 2019, 6pm)
Now you have 2 tasks in the list.
```

### `event` - Adds an event
Adds an uncompleted event with `description` occurring at `datetime` as the last item of task list.

Format: `event <description> /at <datetime>`

Example of usage: `event club activities /at 6/11/2019 0800`

Expected outcome:
```
Got it. I've added this task:
[E][✘] club activities (at: 6th of November 2019, 8am)
Now you have 2 tasks in the list.
```

### `list` - Lists all tasks
Shows a list of all tasks in the app.

Format: `list`

Example of usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][✓] make coffee
2.[T][✘] eat healthy
```

### `find` - Locates tasks with description matching a phrase
Finds all tasks which descriptions contain the search string.

Format: `find <search string>`

Example of usage: `find eat hea`
Expected outcome:
```
Here are the tasks in your list:
1.[T][✘] eat healthy
2.[T][✓] eat healthier
```

### `done` - Mark a task as completed
Marks the task at `index` as completed.

Format: `done <index>`

Example of usage: `done 2`
```
Nice! I've marked this task as done:
[T][✓] eat healthy
```

### `archive` - Archives completed tasks
Stores all completed tasks in an archive file, keeping only uncompleted tasks in the app.

Format: `archive`

Example of usage: `archive`

Expected outcome:
```
Archived all done tasks at YOUR_DUKE_PATH/duke/src/main/data/archive.txt
```

### `delete` - Deletes a task
Deletes the task at `index` from the app.

Format: `delete <index>`

Example of usage: `delete 4`

Expected outcome: 
```
Noted. I've removed this task:
[T][✘] eat healthy
Now you have 3 tasks in the list.
```

### `bye` - Exits the program
Terminates Duke chat bot.

Format: `bye`

Example of usage: `bye`

Expected outcome: program exits.

## 4. FAQ
Q: How do I transfer my data to another Computer?
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke folder.

## 5. Command Summary
- todo `todo <description>`
    - e.g. `todo eat healthy`
- deadline `deadline <description> /by <datetime>`
    - e.g. `deadline return book /by 2/12/2019 1800`
- event `event <description> /at <datetime>`
    - e.g. `event club activities /at 6/11/2019 0800`
- list `list`
- find `find <search string>`
    - e.g. `find eat hea`
- done `done <index>`
    - e.g. `done 2`
- archive `archive`
- delete `delete <index>`
    e.g. `delete 4`
- exit `bye`