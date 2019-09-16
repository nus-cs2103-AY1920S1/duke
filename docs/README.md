# User Guide

## Introduction
Duke is a personal chatbot that helps you keep track of your personal tasks. It is designed in mind for individuals who are used to the **Command Line Interface (CLI)**. 

## Getting Started
1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest release of Duke from here.

## Features
#### 1. Adding a 'ToDo' task

Creates a ToDo task and adds it to your task list.

**Syntax** - `todo` `[task description]`

Example: `todo` `CS2103T Quiz`

You should see that the task has been added to your list:

![ToDo Image](https://github.com/bigjunnn/duke/blob/master/docs/ToDo.png)

#### 2. Adding a 'Deadline' task

Creates a deadline task and adds it to your task list.

**Syntax** - `deadline` `[task description]` `[/by]` `[date & time]`

Example: `deadline` `iP` `[/by]` `[17/09/2019 1000]`

You should see that the task has been added to your list:

![Deadline Image](https://github.com/bigjunnn/duke/blob/master/docs/Deadline.png)

Prompt for special format?

#### 3. Adding an 'Event' task

Creates an event task and adds it to your task list.

**Syntax** - `event` `[task description]` `[/at]` `[date & time]`

Example: `event` `concert` `[/at]` `[28/09/2019 2000]`

You should see that the task has been added to your list:

![Event Image](https://github.com/bigjunnn/duke/blob/master/docs/Event.png)

Prompt for special format?

#### 4. Listing tasks

List all current tasks. 

**Syntax** - `list`

Example: `list`

You should now see a list of your tasks:

![List Image](https://github.com/bigjunnn/duke/blob/master/docs/List.png)

#### 5. Finding tasks

Finds and returns you a list of tasks, based on your description.

**Syntax** - `find` `[description]`

Example: `find` `concert`

You should then see a list of tasks which contains the word "concert":

![Find Image](https://github.com/bigjunnn/duke/blob/master/docs/Find.png)

#### 6. Mark as done

Allows you to set a particular task as done, based on the number provided.

**Syntax** - `done` `[index]`

Example: `done` `2`

This will set task number 2 in the list as done and you should see something like this: 

![Done Image](https://github.com/bigjunnn/duke/blob/master/docs/Done.png)

#### 6. Delete tasks

Allows you to delete a particular task, based on the number provided.

**Syntax** - `delete` `[index]`

Example: `delete` `2`

This will remove task number 2 in the list and you should see something like this:

![Delete Image](https://github.com/bigjunnn/duke/blob/master/docs/Delete.png)

#### 7. In-App Help

Allows you to view a summary of available commands in the app.

**Syntax** - `help`

Example: `help`

You should now be able to view the available commands for the app:

![Help Image](https://github.com/bigjunnn/duke/blob/master/docs/Help.png)















