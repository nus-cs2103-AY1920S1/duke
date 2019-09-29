# User Guide

## 1. Introduction
Hi! Tsuki Task Manager is a girl's school themed app that helps you organize your tasks, ranging from todos, events to deadlines. Specifically, Duke is optimized for those who prefer to work with a Command Line Interface (CLI) while still enjoys a pleasant Graphical User Interface (GUI). If you can type comfortably fast, Duke can get your bucket list management more lightweight and fun than traditional GUI apps. Interested? Jump to the section 2, `Quick Start` to get started.

![Sample usage](Ui.png)

## 2. Quick Start
1. Ensure you have Java `11` or above installed in your computer.
2. Download the latest `tsuki.jar` here.
3. Copy the file to the folder you want to use as the home folder for your duke task manager.
4. Open the terminal and type `java -jar tsuki.jar` to start the app. The GUI should appear in a few seconds.
5. Type the command in the command box and press `Enter` to execute it. <br />
  e.g. typing  `help` and pressing `Enter` will list all the current task stored in the database.

## Features
* no duplicate tasks.
  `Oops! No duplicate tasks.`
* checks format error. Some examples:
  1. unknown command : `Oops! I'm sorry, but I don't know what that means :-(`
  2. list + unwanted arguments: `Oops! The list command should just be "list". `
  3. event without time : `Oops! The event command should be event description /at time.`
  4. Task number out of bounds: `Oops! The task No. you refer to is non-existent. Try another one.`

## Usage

### 3.0 `help` - get help

View all the available commands.

Example of usage:

`help`

### 3.1 `todo <task_content>` - add an todo task

Add a task into task manager.

Example of usage:

`todo CS2103T homework`

Expected outcome:

```
Got it! I've added this task:
   [T][x] CS2103T homework
Now you have 1 task in the list.
```

### 3.2 `time <task_content> /for <duration>` - add an timed task
Add a task that needs to be finished within the speculated time limit.

Example of usage:
`time website design /for 2hrs`

Expected outcome:
```
Got it! I've added this task:
   [T][x] website design (for: 2hrs)
Now you have 1 task in the list.
```

### 3.3 `deadline <task_content> /by <time>` - add an deadline

Add an deadline task into task manager.

Example of usage:

`deadline shower /by tonight`

Expected outcome:

```
Got it! I've added this task:
   [D][x] shower (by: tonight)
Now you have 2 tasks in the list.
```

Alternatively, you can also enter time in DD/MM/YYYY HHMM format. <br />

Example of usage:

`deadline lunch /by 8/12/2008 1800`

Expected outcome:

```
Got it! I've added this task:
   [D][x] lunch (by: 8th of December 2008, 6pm)
Now you have 3 tasks in the list.
```

### 3.4 `event` - add an event

Add an upcoming event.

Example of usage:

`event performance /at 9:30`

Expected outcome:

```
Got it! I've added this task:
   [E][x] performance (at: 9:30)
Now you have 4 tasks in the list.
```

Alternatively, you can also enter time in DD/MM/YYYY HHMM format. <br />

Example of usage:

`deadline dota /at 27/09/2019 1530`

Expected outcome:

```
Got it! I've added this task:
   [E][x] dota (at: 27th of September 2019, 3.30pm)
Now you have 3 tasks in the list.
```

### 3.5 `done <Task_ID>` - mark a task as finished

Mark a task as finished.

Example of usage:
`done 1`

Expected outcome:
```
Nice! I've marked this task as done:
  [D][] shower (by: tonight)
```

### 3.6 `delete <Task_ID>` - delete a task

Delete a task by number.

Example of usage:
`delete 1`

Expected outcome:
```
Noted. I've removed this task:
  [D][x] shower (by: tonight)
Now you have 2 tasks in the list.
```
### 3.7 `list` - list out all available tasks
Output a list of commands that are currently stored in the database.

Example of usage:
`list`

Expected outcome:
```
Here are the tasks in your list:
  ...
```

Or, if there is nothing in the list yet:

`Your task list is empty.`

### 3.8 `find <keyword>` - find tasks by keyword

Find tasks that matches the keywords you enter. Can use more than 1 keyword.

Example of usage:
`find shower`

Expected outcome:
```
Here are the matching tasks in your list:
  ...
```

Or, if there is no tasks find:

`Oops, no matching task.`

### 3.9 `bye` - quit

Quit the app.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
And the program will exit in 3 seconds.
