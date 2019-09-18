# User Guide
Duke is a smart dog that can help keep track of a to-do list. Ever worry about not bringing Duke out for walks?
Ever worry about not feeding Duke? Worry not! Duke will remind you. 
~Woof

## Features 

#### Feature 1 
Add different types of Tasks to the to-do list. There are **3 types** of Tasks:
1. ToDo
2. Deadline
3. Event

#### Feature 2
List out all tasks on the to-do list.

#### Feature 3
Mark completed tasks as done. 

#### Feature 4
Delete tasks.

#### Feature 5
Find tasks using a given keyword.

#### Feature 6
Add multiple tags to tasks.



## Usage

### `todo` - Add a ToDo task

Add a ToDo task to the to-do list.

Example of usage: 

`todo play with duke (#fun #bonding)`

Expected outcome:

`1. [T][] play with duke (tags: fun bonding)`


### `deadline` - Add a Deadline task

Add a Deadline task to the to-do list. A Deadline task has to be completed by a given time.

Example of usage: | Expected outcome:
-----------------|---------------------
`deadline feed duke /by 12pm (#Reminder)` | `1. [D][] feed duke (by: 12pm) (tags: Reminder)`
`deadline visit the vet /by 12/02/2019 1700 (#Checkup #Health)` | `2. [D][] feed duke (by: 12pm) (tags: Reminder)`


### `event` - Add a Event task

Add an Event task to the to-do list. An Event task needs a time of occurrence.

Example of usage: | Expected outcome:
-----------------|---------------------
`event feed duke /at 12pm (#Reminder)` | `1. [E][] feed duke (at: 12pm) (tags: Reminder)`
`event visit the vet /at 12/02/2019 1700 (#Checkup #Health)` | `2. [E][] feed duke (at: 12pm) (tags: Reminder)`


### `list` - Show all the tasks on the to-do list.
Show all the tasks on the to-do list, including those mark as done, as well as tags.

Example of usage: 

`list`

Expected outcome:

`1. [T][] play with duke (tags: fun bonding)  2. [E][] wash duke (at: 3pm) (tags: fun bonding)`


### `done` - Mark a task as Done.

Mark a given task index on the tasklist as **done**.

Example of usage: 

`done 2`

Expected outcome:

Before: | After: 
--------|---------
`1. [T][] play with duke (tags: fun bonding) 2. [E][] wash duke (at: 3pm) (tags: fun bonding)`| `1. [T][] play with duke (tags: fun bonding)  2. [E][+] wash duke (at: 3pm) (tags: fun bonding)`


### `delete` - Delete a Task.
Delete task with the given index from the to-do list.

Example of usage: 

`delete 2`

Expected outcome:

Before: | After: 
--------|---------
`1. [T][] play with duke (tags: fun bonding)  2. [E][+] wash duke (at: 3pm) (tags: fun bonding)` | `1. [T][] play with duke (tags: fun bonding)`


### `find` - Finds tasks.

Finds all tasks that contains the given keyword.

Example of usage: 

`find play`

Expected outcome:

`1. [T][] play with duke (tags: fun bonding)`
