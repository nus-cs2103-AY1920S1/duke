# User Guide
Text-based personal assistant with CLI input.

![Duke UI](/docs/Ui.png)

## Features 

Duke is a text-based personal assistant that helps you keep track of tasks. Supported task types include:
1. Todos
2. Deadlines
3. Events

Duke provides commands to manage your tasks. You can:
* List tasks
* Find tasks by keyword
* Add tasks
* Mark tasks as done
* Delete tasks
* Undo the last change

Duke uses a GUI to display output in a more intuitive way than purely CLI-based programs, while its CLI-based input allows power users to work faster with Duke than with purely GUI-based personal assistants.

## Usage

To interact with Duke, enter a command into the text box at the bottom, then press 'Enter' or click the 'Send' button. Documentation for each command is given below.

### `list` - Lists all tasks

Displays all tasks in a numbered list. Each task's index number can be used to identify it for other commands.

#### Example usage:
```
list
````

#### Expected outcome:

##### If there are tasks in the list:
```
1. [E][✗] Start of Week 7 (at: 30 of September 2019)
2. [D][✓] Finalise iP (by: This week)
```

##### If there are no tasks in the list:
```
No tasks yet.
```

### `find` - Finds and displays a task

Finds the tasks containing the specified text and displays them. The search is not case-sensitive. Each task is listed with its original index number as displayed by `list`.

#### Example usage:
```
find iP
```
```
find september
```
#### Example outcome:
```
Here are the matching tasks in your list:
2. [D][✓] Finalise iP (by: This week)
```
```
Here are the matching tasks in your list:
1. [E][✗] Start of Week 7 (at: 30 of September 2019)
```

### `todo` - Adds a todo

Add a todo to the list. A todo only has a description.

#### Example usage:
```
todo Read a book
```

#### Expected outcome:
```
added: [T][✗] Read a book
```

### `deadline` - Adds a deadline

Add a deadline to the list. A deadline has a description and a due date.

#### Example usage:
```
deadline Submit code /by 27/09/19
```
```
deadline Submit code /by 27/09/2019 1430
```
```
deadline Submit code /by End of the week
```

#### Expected outcome:
```
added: [D][✗] Submit code (by: 27 of September 2019)
```
```
added: [D][✗] Submit code (by: 27 of September 2019 2:30PM)
```
```
added: [D][✗] Submit code (by: End of the week)
```

### `event` - Adds an event

Add an event to the list. An event has a description and a date.

#### Example usage:
```
event Movie night /at 01/10/19
```
```
event Movie night /at 01/10/2019 2115
```
```
event Movie night /at Next Tuesday
```

#### Expected outcome:
```
added: [E][✗] Movie night (at: 1 of October 2019)
```
```
added: [E][✗] Movie night (at: 1 of October 2019 9:15PM)
```
```
added: [E][✗] Movie night (at: Next Tuesday)
```

### `done` - Marks a task as done

Marks a specified task as done. Tasks are specified by their index number as displayed by `list` or `find`. Done tasks will remain in the list until removed by `delete`.

#### Example usage:
```
done 3
```

#### Expected outcome:
```
Nice! I've marked this task as done:
[D][✓] Submit code (by: 27 of September 2019)
```

### `delete` - Deletes a task

Deletes a specified task. Tasks are specified by their index number as displayed by `list` or `find`.

#### Example usage:
```
delete 3
```

#### Expected outcome:
```
Noted. I've removed this task:
    [D][✓] Submit code (by: 27 of September 2019)
Now you have 2 tasks in the list.
```

### `undo` - Reverts the last change

Reverts the last change. Commands such as `list` or `find` are not considered changes. Cannot `undo` twice in a row.

#### Example usage:
```
undo
```

#### Expected outcome:
```
Okay, I've undone the last 'delete' command.
```

### `bye` - Quits Duke

Quits Duke and closes the application.

#### Example usage:
```
bye
```

#### Expected outcome:
```
Bye! Hope to see you again soon!
```
