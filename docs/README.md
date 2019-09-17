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

![ToDo Image](https://imgur.com/2f2VI2x)

#### 2. Adding a 'Deadline' task

Creates a deadline task and adds it to your task list.

**Syntax** - `deadline` `[task description]` `[/by]` `[date & time]`

Example: `deadline` `iP` `[/by]` `[17/09/2019 1000]`

You should see that the task has been added to your list:

![Deadline Image](https://imgur.com/A5Ja9WS)

:warning: Date & Time must be entered in format dd/mm/yyyy HHmm

#### 3. Adding an 'Event' task

Creates an event task and adds it to your task list.

**Syntax** - `event` `[task description]` `[/at]` `[date & time]`

Example: `event` `concert` `[/at]` `[28/09/2019 2000]`

You should see that the task has been added to your list:

![Event Image](https://imgur.com/vfYzrup)

:warning: Date & Time must be entered in format dd/mm/yyyy HHmm

#### 4. Listing tasks

List all current tasks. 

**Syntax** - `list`

Example: `list`

You should now see a list of your tasks:

![List Image](https://imgur.com/3wgh0sh)

#### 5. Finding tasks

Finds and returns you a list of tasks, based on your description.

**Syntax** - `find` `[description]`

Example: `find` `concert`

You should then see a list of tasks which contains the word "concert":

![Find Image](https://imgur.com/0GSMPtU)

#### 6. Mark as done

Allows you to set a particular task as done, based on the number provided.

**Syntax** - `done` `[index]`

Example: `done` `2`

This will set task number 2 in the list as done and you should see something like this: 

![Done Image](https://imgur.com/KMDVrQa)

#### 6. Delete tasks

Allows you to delete a particular task, based on the number provided.

**Syntax** - `delete` `[index]`

Example: `delete` `2`

This will remove task number 2 in the list and you should see something like this:

![Delete Image](https://imgur.com/mqKjZ9w)

#### 7. In-App Help

Allows you to view a summary of available commands in the app.

**Syntax** - `help`

Example: `help`

You should now be able to view the available commands for the app:

![Help Image](https://imgur.com/rSZAAeI)















