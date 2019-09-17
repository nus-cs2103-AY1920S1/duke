# DaDuke
# User Guide

Everything you need to know to run Duke on your computer.

![UserInterface](./Ui.png)

## Prerequisites
* Install Java on your computer, from [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html).

## Features 
**Things that you can do:**
1. `Create` - *You can add a new Task, either ToDos, Event or Deadline task.*

1. `Delete` - *You can remove a Task from the current TaskList.*

1. `Update` - *You can update the completion status of each Task.*

1. `Search` - *You can lookup Tasks related to a specific keyword.*

1. `List` - *You can list all the Tasks existing in the current TaskList.*

1. `Check Duplicates` - *This ensures no repeated Tasks are keyed into TaskList.*

## Commands 
**Duke supports the following commands:**

`todo <task name>` - creates a new ToDos Task (eg. todo Homework).

`event <task name> /at <dd/mm/yyyy hhmm>` - creates a new Event Task (eg. event Hackathon /at 17/09/2019 1400).

`deadline <task name> /by <dd/mm/yyyy hhmm>` - creates a new Deadline Task (eg. deadline Math Quiz /by 20/09/2019 2359).

`done <task number>` - marks the corresponding Task in the TaskList as done (eg. 'done 2' will mark the second task on the list as done).

`delete <task number>` - removes the Task from the list (eg. 'delete 3' will remove the third task on the list).

`list` - prints all current Tasks in TaskList.

`find <keyword>` - searches for an existing Task containing the keyword (keyword is CASE SENSITIVE).

`bye` - terminates the application
