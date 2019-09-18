# User Guide

## 1. Introduction
Duke is a chat bot that allows you to add, save and print task list. It has a command 
line Interface (CLI) for you to type the command and Duke will do what you input! 

## 2. Quick Start 
1. Ensure you have Java `11` or above installed in your Computer

1. Download the latest `Duke.jar` [here](https://github.com/Auxinnn/duke/releases/download/v0.1.3/duke-0.1.3.jar).

1. Copy the file to the folder you want to use as the home folder for your Duke.

1. Double-click the file to start the app. The GUI should appear in a few seconds.\
![Ui](Ui.png)

1. Type the command in the command box and press <kbd>Enter</kbd> to execute it.\
e.g. typing `list` and pressing <kbd>Enter</kbd> will print the task list.

1. Some example commands you can try:\
    * `list` : lists all tasks

    * `todo Return book` : adds a task called `Return book` to the `Duke`.

    * `delete 3` : deletes the 3rd task shown in the current list

    * `exit` : exits the app

1. Refer to `Section 3, “Features”` for details of each command.

## 3. Features

### 3.1 Adding a to-do task: `todo`
Adds a to-do task to the Duke.\
Format: `todo DESCRIPTION`\
Examples:
* `Todo Read book`
* `Todo English project`

### 3.2 Adding a deadline task: `deadline`
Adds a task with deadline.\
Format: `deadline DESCRIPTION /by DD/MM/YYYY HHmm`\
Examples:
* `deadline Assignemnt /by 20/9/2019 2359`

### 3.3 Adding a event: `event`
Adds a event with duration.\
Format: `event DESCRIPTION /at DD/MM/YYYY HHmm-HHmm`\
Examples:
* `event Project meeting /at 18/9/2019 1400-1600`

### 3.4 Listing all tasks: `list`
Shows a list of all tasks in the Duke.\
Format: `list`

### 3.5 Marking a task as done: `done`
Mark a specific task in the list as done.\
Format: `done INDEX`\
Examples:
* `done 2`

### 3.6 Deleting a task: `delete`
Delete a specific task from the list.\
Format: `delete INDEX`\
Examples:
* `delete 3`

### 3.7 Finding tasks by a keyword: `find`
Find all matched tasks from the list sorted by keyword.\
Format: `find KEYWORD`\
Examples:
* `find book`

### 3.8 Clearing all tasks: `clear`
Clear all tasks from the list.\
Format: `clear`

## 4. FAQ

Q: How do I transfer my data to another Computer?\
A: Install the app in the other computer and overwrite the data file it creates at 
`\duke\data\duke.txt` with the file that contains the data of your previous Duke 
folder.
