# Duke User Guide

Duke is an app to help you manage your tasks with command line.

![Duke Preview](Ui.png)

## Features

### 1. Getting help

Gets all available commands in Duke.

Format: `help`

### 2. Adding a task

There are 3 types of tasks in Duke. They are todo, event and deadline tasks.

#### 2.1 Adding a todo task

A todo task is the simplest task which only requires a description.

Format: `todo {description}`

Example: `todo attending CS2103T Lecture` will add a new todo task which description is 'attending CS2103T Lecture'.

#### 2.2 Adding an event task

An event task is a task with a date.

Format: `event {description} /at {full_date}`

Format for full_date: `{date}/{month}/{year} {hour}:{minutes}:{seconds}`

Example: `event computing career fair /at 11/09/2019 13:30:00` will add a new event task which description is computing career fair and the event is happening on 11 September 2019 at 1.30 p.m.

#### 2.3 Adding a deadline task

A deadline task is a task with due date.

Format: `deadline {description} /by {full_date}`

Format for full_date: `{date}/{month}/{year} {hour}:{minutes}:{seconds}`

Example: `deadline CS2103T Project /by 30/09/2019 23:59:00` will add a new deadline task which description is CS2103T Project and the deadline task dues on 30 September 2019 23:59.

### 3. Viewing Tasks

Shows list of all task.

Format: `list`

### 4. Deleting Tasks

Deletes a task from the list.

Format: `delete {index}`

Example: `delete 1` will delete the first task on the list.

### 5. Finishing Tasks

Marks a task as done.

Format: `done {index}`

Example: `done 1` will marks the first task as done.

### 6. Finding Tasks

Finds note based on a keyword.

Format: `find {keyword}`

Example: `find CS2103T Project` will display all notes with `CS2103T Project` in the description.

### 7. Getting Reminders

Gets a list of deadline task which due soon or even task which happening soon. This feature will always been called upon starting up Duke.

Format: `reminder`

### 8. Closing Duke

In order to close duke, use the bye command.

Format: `bye`
