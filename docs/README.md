# Duke - User Guide
##### A simple chatbot that helps to manage todos, events, and deadlines.


Three or more...

---

Hyphens

***

Asterisks

___

Underscores

## Prerequisite
1. Make sure you have at least **Java  11** installed on your computer.
2. download the latest version of **Duke.jar** found [here](https://github.com/austinsantoso/duke/releases).

## Features
Duke allows you to type commands to tell it what to do.
Duke comes with a variety of features to help you manage your tasks.

**Manage Tasks**
Duke allows you to:
1. add a task
2. delete a task
3. mark a task as done
4. display tasks

**Clasify Tasks**
Duke can clasify tasks to different types:
1. Todo
2. Event
3. Deadline

## Usage
**Here are the commands available to Duke:**

### Basic Features
***
##### `help` - Displays help
displays the help text.

**Example of usage:**
`help`

**Expected Outcome:**
A textbox containing a list of commands that can be used by duke, as well as their respective outcomes.
***
##### `list` - Displays current list
displays All lists currently saved.

**Example of usage:**
`list`

**Expected Outcome:**
A list containing all tasks stored currently stored.
***
##### `bye` - Closes the program.
Closes the program.

**Example of usage:**
`bye`

**Expected Outcome:**
The program will close.
***

### Adding Tasks
***
##### `todo [task name]` - Adds a todo task
adds a todo task to the list with the given task name.

**Example of usage:**
`todo borrow book from library` 

**Expected Outcome:**
Duke will respond with a new todo task with the given name.
***
##### `deadline [task name] /by [date]` - adds a deadline task
adds a deadline task to the list with the given task name and date.
The date must follow format `dd/MM/YYYY HH:mm`

**Example of usage:**
`deadline update duke /by 12/12/2019 23:59` 

**Expected Outcome:**
Duke will respond with a new deadline task with the given name and date.
***
##### `event [task name] /at [date]` - adds an event task
adds an event task to the list with the given task name and date.
The date must follow format `dd/MM/YYYY HH:mm`

**Example of usage:**
`event Lecture /at 20/09/2019 16:00` 

**Expected Outcome:**
Duke will respond with a new event task with the given name and date.
***
### Manipulating tasks
***
##### `done [task number]` - Completes a task
Marks the tasks with the given task number as completed.

**Example of usage:**
`done 1` 

**Expected Outcome:**
A message stating that the given `task number` has been succesfully marked as complete.    
***
##### `remove [task number]` - Removes a task
removes the tasks with the given task number from the list

**Example of usage:**
`remove 1` 

**Expected Outcome:**
A message stating that the given `task number` has been succesfully removed from the list.
***
