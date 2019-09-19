# User Guide for Daman
Daman is a Bot with can keep track of tasks for you. 
It is capable of storing tasks as a todo, deadline or event task, complete with time storage for the latter two.
It also includes functionality to delete, mark as done and find a given task.

## Features 
Daman boasts many features which include:

### Storing of Tasks
Daman stores your task list as a file whenever you add to or remove a task. 
This feature is persistent between different instances of Daman.

###Showing Tasks
Daman can show you all the current tasks in its list.

###Completing Tasks
Daman can mark you tasks as complete on the list.

###Deleting Tasks
Daman can delete a task of your choice from the list, if you do not want to see that task again.

###Finding Tasks
Daman can help you search for a task which includes a given keyword.

###Displaying Help
Daman is user-friendly and can give you a list of commands available if you forget them.

## Usage

### `bye` - Exits Daman
`bye` exits Daman.

### `help` - Displays list of commands
`help` lists out all the available commands and briefly describes them.

### `find` - Find tasks with keyword
`find` lists out all the tasks that contain the given keyword.

Example of usage: 

`find homework`

Expected outcome:

```
 1. [T][X] Do homework
 2. [D][X] Do CS2103 individual homework (by: 31/12/2019)
```

### `list` - List all tasks in tasklist
`list` displays all the tasks that are present in the tasklist.

Example of usage: 

`list`

Expected outcome:

```
 1. [T][X] Do homework
 2. [D][X] Do CS2103 individual homework (by: 31/12/2019)
 3. [E][X] Attend Damith's lecture (at: 31/12/2019)
 4. [E][X] Go fly kite (at: 31/12/2019)
```

### `delete` - Delete the specified task from the tasklist
`delete` deletes the specified task from the tasklist, this is irreversible.

Example of usage: 

`delete 1`

Expected outcome:

```
 Noted. I've removed this task:
   1. [T][X] Do homework
```

### `done` - Mark a task as done
`done` marks a task in the tasklist as done.

Example of usage: 

`done 1`

Expected outcome:

```
 Nice! I've marked this task as done:

   1. [T][V] Do CS2103 individual homework (by: 31/12/2019)
```

### `todo` - Add a todo task
`todo` adds a new todo task with a description

Example of usage: 

`todo Code some stuff`

Expected outcome:

```
 Got it. I've added this task:
   [T][X] Code some stuff
```
![event](https://raw.github.com/Wingedevil/duke/master/docs/todo.png)

### `deadline` - Add a deadline task
`deadline` adds a new deadline task with a description and a deadline

Example of usage: 

`deadline CS2105 assignment /by 05102019 2359`

Expected outcome:

```
 Got it. I've added this task:
   [D][X] CS2105 assignment (by: 05102019 2359)
```
![event](https://raw.github.com/Wingedevil/duke/master/docs/deadline.png)

### `event` - Add an event task
`event` adds a new event task with a description and a event time

Example of usage: 

`event CS2105 midterms /at 07102019`

Expected outcome:

```
 Got it. I've added this task:
   [E][X] CS2105 midterms (at: 07102019)
```
![event](https://raw.github.com/Wingedevil/duke/master/docs/event.png)