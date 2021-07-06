# Duke - User Guide

**By Chen Su, 2019**

```
1. Introduction
2. Quick Start
3. Features
    3.1 Adding a task: todo/deadline/event
    3.2 Lising task list: list
    3.3 Marking task(s) as done: done
    3.4 Deleting task(s): delete
    3.5 Searching by keyword: find
4. Command Summary
```

##1. Introduction
Duke is for those who prefer to use a desktop app for managing tasks. 
More importantly, Duke is optimized for those who prefer to work 
with a Command Line Interface (CLI) while still having the benefits of a Graphical 
User Interface (GUI). If you can type fast, Duke can get your contact management 
tasks done faster than traditional GUI apps. Interested? Jump to the [Section 2, 
“Quick Start”](#2.-Quick-Start) to get started. Enjoy!

##2. Quick Start
1. Ensure you have `Java 11` or above installed in your Computer.
1. Download the latest `duke.jar` here.
1. Copy the file to the folder you want to use as the home folder for your Duke.
1. Double-click the file to start the app. The GUI should appear in a few seconds.
1. Type your command at the bottom of the GUI and press `Enter` or click `Send` button 
 at the right bottom to execute it.
1. Refer to [Section 3, "Features"](#3.-Features) for details of each command.

##3. Features
###3.1 Adding a task

> There are 3 types of tasks for you to better categorize your tasks. 

####(a) Add a todo task: `todo`
Adds a todo task into the task list.<br>
Format: `todo ACTIVITY_NAME`<br>
Example:<br>
* `todo read newspaper`<br>
  Adds a todo task `read newspaper` into the task list.

####(b) Add a deadline task: `deadline`
Adds a deadline task that needs to be done by a particular time into the task list.<br>
Format: `deadline ACTIVITY_NAME / by TIME`<br>
Example:<br>
* `deadline return book / by Sunday`<br>
  Adds a deadline task `return book` that needs to be done by Sunday into the task list.

####(c) Add an event task: `event`
Adds a deadline task that happens at a particular time into the task list.<br>
Format: `event ACTIVITY_NAME / at TIME`<br>
Example:<br>
* `event midterm / at Oct 5`<br>
  Adds a event task `midterm` that happens on Oct 5 into the task list.
  
###3.2 Listing task list: `list`
Shows the list of existing tasks in the task list.<br>
Format: `list`<br>

###3.3 Marking task(s) as done: `done`
Marks task(s) with particular index as done.<br>
Format: `done INDEX` or `done RANGE` or `done INDEX...`<br>
Example:<br>
* `done 1`<br>
  Marks the 1st task in the list as done.<br>
  `done 2-5`<br>
  Marks the tasks with index 2, 3, 4, 5 as done.<br>
  `done 3,5`<br>
  Marks the task with index 3 and 5 as done.<br>

###3.4 Deleting tasks(s): `delete`
Deletes task(s) with particular index.<br>
Format: `delete INDEX` or `delete RANGE` or `delete INDEX...`<br>
Example:<br>
* `delete 1`<br>
  Deletes the 1st task in the list.<br>
  `delete 2-5`<br>
  Deletes the tasks with index 2, 3, 4, 5.<br>
  `delete 3,5`<br>
  Deletes the task with index 3 and 5.<br>
  
###3.5 Searching by keyword: `find`
Searches the tasks according to the keywords.<br>
Format: `find KEYWORD`<br>
Example:<br>
* `find todo`<br>
  Gives result(s) of all tasks that contains "todo" in their descriptions.<br>
  `find read book`<br>
  Gives result(s) of all tasks that contains "read book" in their descriptions.<br>

##4. Command Summary
Command | Format
-------|---------------
**Add** | `todo ACTIVITY_NAME`<br> `deadline ACTIVITY_NAME / by TIME`<br>`event ACTIVITY_NAME / at TIME`
**List** | `list`
**Done** | `done INDEX` or <br>`done RANGE` or <br>`done INDEX...`
**Delete** |`delete INDEX` or <br>`delete RANGE` or <br>`delete INDEX...`
**Search** | `find KEYWORD`

# Feedback, Bug Reports

* If you have feedback or bug reports, please post in [chensu2436/duke issue tracker](https://github.com/chensu2436/duke/issues).
* Pull requests are welcomed too.
