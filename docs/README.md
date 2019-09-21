[Ui]:Ui.png
[GuiExp]:GuiExp.gif
[AddTodo]:AddTodo.png
[AddDeadline]:AddDeadline.png
[AddEvent]:AddEvent.png
[List]:List.png
[Delete]:Delete.png
[MarkDone]:MarkDone.png
[Find]:Find.png
[Find2]:Find2.png
[Bye]:Bye.png

# Duke, The Handsome Bot

![Duke Ui][Ui]

**Author:** `Ang Chin Guan, Melvin` <br>
**Created on:** `August 2019` <br>
**License:** `MIT` <br>

## 1. What is Duke?

Duke is the *Handsome Bot* that manages your tasks in one place. 
If you love the commandline interface, 
and would like a simple terminal application to manage all your todos, deadlines and events,
look no further than Duke!
Blaze through your day by being productive with Duke today! 

## 2. Quick Start Guide

1. Ensure that `Java 11` or higher is installed on your computer. 
You can get them [here](https://www.oracle.com/technetwork/java/javase/downloads/index.html). 
2. Download the latest `duke-x.x.x.jar` from the [releases page](https://github.com/krusagiz/duke/releases).
3. Copy the `duke-x.x.x.jar` to a folder of your choice.
4. Double-click the `duke-x.x.x.jar` file. You should see a interactive window like this:
![GuiExperience][GuiExp]
5. Enter commands (case-sensitive) into the text box and press `Enter`. 
Alternatively, you can click the `Send` button to execute the command.
    - Some sample commands:
        - `list`: lists all of your tasks
        - `todo <TODO_DESCRIPTION>`: adds a Todo task with the description *TODO_DESCRIPTION* to Duke
        - `bye`: Exits and saves your data.
6. *Repeat* Step 5 until you feel good.
7. Use the `bye` command to prepare to exit and print the farewell message. 
(You **must** explicitly do this to save)
8. Press `Enter` again to exit.

## 3. Features

**Command format:**
- Commands and parameters are case-sensitive.
- Multi-word parameters do not have to be surrounded in quotation marks.
- Ordering of parameters are to be strictly followed.

### `todo` - Adds a todo to Duke

Adds a `todo` to Duke, it will be added to the `TaskList`.

Format: `todo <TODO_DESCRIPTION>` <br>

Example of usage: <br>
`todo Send a gift to Prof. Damith` 

Expected Outcome: <br>
![AddTodo][AddTodo]

### `deadline` - Adds a task with deadline to Duke

Adds a `deadline` to Duke, it will added to the `TaskList`

Format: `deadline <DEADLINE_DESCRIPTION> /by <dd/mm/yyyy hh:mm>`

Example of usage: <br>
`deadline Do Homework /by 17/10/2019 23:59`

Expected Outcome: <br>
![AddDeadline][AddDeadline]

### `event` - Adds an event to Duke

Adds an `event` to Duke, it will added to the `TaskList`

Format: `event <EVENT_DESCRIPTION> /at <dd/mm/yyyy hh:mm> - <dd/mm/yyyy hh:mm>`

Example of usage: <br>
`event BellCurveDance /at 19/09/2019 23:59 - 20/09/2019 00:00`

Expected Outcome: <br>
![AddEvent][AddEvent]

### `list` - Lists all events in the TaskList

Lists all tasks in the `TaskList`

Format: `list`

Example of usage: <br>
`list`

Expected Outcome: <br>
![List][List]

### `delete` - Deletes a specified task

Deletes a `Task` at the specified index.

Format: `delete <INDEX>`

Example of usage: <br>
`delete 5`

Expected Outcome: <br>
![Delete][Delete]

### `done` - Marks a specified task as done

Marks a `Task` at the specified index as done.

Format: `done <INDEX>`

Example of usage: <br>
`done 4`

Expected Outcome: <br>
![MarkDone][MarkDone]

### `find` - List the tasks that match a keyword

List the tasks that match a keyword (case-insensitive) or matches a date-like/time-like number sequence. 

Format: `find <Keyword>`

Example of usage: <br>
`find corn`

`find 1732`

Expected Outcome: <br>
`find corn`: <br>
![Find][Find]

`find 1732`: <br>
![Find2][Find2]

### `bye` - Exits and Saves the TaskList

Exits and Saves the tasks in the `TaskList`. Then prints the farewell.
Next input will close the window.

Format: `bye`

Example of usage: <br>
`bye`

Expected Outcome: <br>
![Bye][Bye]

## 4. FAQ

**Q**: How do I transfer my data to another computer? <br>
**A**: Install the application in the other computer 
and overwrite the empty `PATH\TO\YOUR\DUKE\INSTALLATION\data\duke.txt` data file it creates
with the file from your previous Duke application.

**Q**: Can this application sync with the cloud? <br>
**A**: *No*. The feature is planned for a future release. Stay tuned.



