# Kappa - User Guide

## Brief Overview

Kappa is a CLI-based application on your Desktop with GUI-like features to help you track your tasks. This application is for people who prefers keep tracking of tasks on their Desktops using a Command-Line Interface.

![Overview of Kappa](./Ui.png)

## Features

#### 1. Keep track of your tasks

Kappa helps you to keep track of various kinds of tasks which includes ToDos, Deadlines and Events and display them.

#### 2. Marking your tasks as done

Kappa can help you mark your tasks as Done through the Done Command so that you can keep track of what tasks are completed.

#### 3. Tagging your tasks

Your tasks can also be tagged which helps you to customise your own task categories which makes tracking much easier!

#### 4. View all your tasks

The list command that Kappa provides can display all the tasks that you are currently tracking.

#### 5. Auto-save and store your tasks

Kappa has an auto-save feature which stores your tasks every time you add or modify them. With the storage feature, you can always close the application and come back another time, and your currently tracking tasks will still be there.

## Commands
* [ToDo](#todo-commands)
* [Deadline](#deadline-commands)
* [Event](#event-commands)
* [List](#list-commands)
* [Delete](#delete-commands)
* [Find](#find-commands)
* [Clear](#clear-commands)
* [Done](#done-commands)
* [Exit](#exit-commands)
* [Help](#help-commands)

## Command Format
Words that are enclosed with `<` and `>` are the parameters to be supplied by you.
Words that are enclosed with `[` and `]` are optional parameters to be supplied by you.
`...` represents one or more argument
Other keywords stated are compulsory and they should be included.
E.g. in `todo <Task Description> /t [#Tag1...]`, a valid input would be `todo Maths Homework /t #School #Maths`

`<Task Description>` is the description of the task to be supplied by you.
`[#Tag1...]` represents optional arguments to be supplied by you.

## Usage

### <a name="todo-commands"></a>`todo` - Add a ToDo task to your list

Syntax:

`todo <Task Description> /t [#Tag1...>`

Example:

`todo CS2103T Project /t #CS2103 #School` (With Tags)

`todo Laundry` (Without Tags)

Expected outcome:

`Adds a ToDo Task to your list.`

![Overview of Todo](./images/ToDoEx.png)

### <a name="deadline-commands"></a>`deadline` - Add a Deadline task to your list

Syntax:

`deadline <Task Description> /by <Date> /t [#Tag1...]`

`<Date>` must be in `DD/MM/YYYY HHMM` format for Kappa to read it properly.

Else, the customised date is taken and not parsed.

Example:

`deadline CS2103 Quiz /by 29/9/2019 1300 /t #CS2103 #School` (With Valid Date and Tags)

`deadline CS2103 Quiz /by Friday` (Without Valid Date)

Expected outcome:

`Adds a Deadline Task to your list.`

![Overview of Deadline](./images/DeadlineEx.png)

### <a name="event-commands"></a>`event` - Add an Event task to your list

Syntax:

`event <Task Description> /at <Date> /t [#Tag1...]`

`<Date>` must be in `DD/MM/YYYY HHMM` format for Kappa to read it properly.

Else, the customised date is taken and not parsed.

Example:

`event F1 Concert /at 24/9/2019 1800 /t #Social` (With Valid Date and Tags)

`event Birthday Party /at 5pm Thurs` (Without Valid Date)

Expected outcome:

`Adds an Event Task to your list.`

![Overview of Event](./images/EventEx.png)

### <a name="list-commands"></a>`list` - Display your tasks

Syntax:

`list`

Expected outcome:

`Displays all your tasks stored thurs far.`

![Overview of List](./images/ListEx.png)

### <a name="delete-commands"></a>`delete` - Delete your task

Syntax:

`delete <index>`

Example:

`delete 3`

Expected outcome:

`Deletes task based on specified index.`

![Overview of Delete1](./images/DeleteEx1.png)

![Overview of Delete2](./images/DeleteEx2.png)

### <a name="find-commands"></a>`find` - Find your task

Syntax:

`find <search term>`

Example:

`find school`

Expected outcome:

`Finds task by searching description based on specified search term.`

![Overview of Find](./images/FindEx.png)

### <a name="clear-commands"></a>`clear` - Clear your tasks

Syntax:

`clear`

Expected outcome:

`Clears storage of all tasks.`

![Overview of Clear1](./images/ClearEx1.png)

![Overview of Clear2](./images/ClearEx2.png)

### <a name="done-commands"></a>`done` - Mark your task as done

Syntax:

`done <index>`

Example:

`done 4`

Expected outcome:

`Marks the task at specified index as done.`

![Overview of Done1](./images/DoneEx1.png)

![Overview of Done2](./images/DoneEx2.png)

### <a name="exit-commands"></a>`exit` - Exit application

Syntax:

`bye`

Expected outcome:

`Terminates application with a goodbye popup message.`

![Overview of Exit](./images/ByeEx.png)

### <a name="help-commands"></a>`help` - Shows list of commands

Syntax:

`help`

Expected outcome:

`Gives an overview of all the commands in Kappa.`

![Overview of Help](./images/HelpEx.png)