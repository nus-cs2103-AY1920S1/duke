# A User Guide for Duke

<img src="https://raw.githubusercontent.com/crazoter/duke/master/docs/Ui.png" alt="image of ui" width="200"/>

Download the latest release [here](https://github.com/crazoter/duke/releases).

## Features

### Manage todos, events and deadlines
* With Duke, you can add different types of tasks your list using [todo](#task), [deadline](#task) or [event](#task) commands.
* You can also view your list of tasks by using the [list](#list) command.
* You can mark a task as done using [done](#done), or [delete](#delete) to remove tasks, or [clear](#clear) to remove all tasks.
* You can also look for relevant tasks using the [find](#find) command.

### Auto-saves after every change
* All your changes are [automatically saved](#autosave). These changes persist even after you shut down the application.

### Do it fast with autocorrect
* The [autocorrect](#autocorrect) feature allows you to input commands quickly.
* It also allows you to quickly glance through all the features in the app.
* Autocorrect works with cursor keys and enter buttons.
* Your most recently used commands will be pushed to the top.

### Get help from the app
* The app contains the [help](#help) command to help you get acquainted with the commands.


## User Manual

### <a name="help"></a> Feature 1: Display Help
Shows list of functions and purpose.
Usage:
`help`

Expected outcome:

`list of functions and purpose`

### <a name="list"></a> Feature 2: Display List
List tasks that are on the todo list.

Usage:

`list`

Expected outcome:

`Here are the tasks in your list: ...`

### <a name="task"></a> Feature 3: Adding tasks
Add different types of tasks to be cleared (e.g. todo, event, deadline).

Usage:

`todo {description}`
`event {description} /at {date}`
`deadline {description} /by {date}`

Expected outcome:

Task is added to list.

### <a name="delete"></a>Feature 4: Task deletion
Delete tasks by index

Usage:

`delete {index}`

Expected outcome:

Task is removed from list.

Alternatively, you can also use `clear` to delete all tasks from the list.

### <a name="done"></a>Feature 5: Find tasks
Find tasks by keywords. Any task that contains said keyword will appear.

Usage:

`find {keyword}`

Expected outcome:

Tasks that contain the keyword will be listed.

### <a name="done"></a>Feature 6: Mark Task as done
Mark tasks by index

Usage:

`done {index}`

Expected outcome:

Task is marked as done.

### <a name="autosave"></a>Feature 7: Saving
All tasks are automatically saved whenever a task is added or modified.

### <a name="autocorrect"></a>Feature 8: Autocorrected commands
A list of possible commands will appear. Use the up and down arrow keys to navigate and press enter or right arrow key to autofill the command into the textbox.

### Feature 9: Exit
Usage:
`bye`

Expected outcome:
Window closes and Duke terminates.
