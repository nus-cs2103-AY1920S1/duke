# User Guide
## Introduction
This is a user guide for the Individual Project segment of the CS2103T module.
While lacking in serious functionality, this segment provides 
a strong base for the understanding of the addressbook code. 

The duke program is a task-tracker that allows its user to add 3 
types of tasks - To do tasks, Deadline task and Event tasks. The 
specifics of each task will be elaborated below. 

In addition, the user can view all the tasks, mark the status of each task,
delete each task, find a keyword, tag a task and find a tag. 
The specifics of each feature will be elaborated 
below.

## What the app looks like
![Screenshot of the app](Ui.png)

## Tasks 
### 1  - Todo tasks
The user can add a simple to do task to the program.

#### Command
To add a todo task, type the following into the program.
For example, a user would like to "eat an apple". Thus, the user 
can input the following command:
```
todo eat an apple
```
The task will be displayed as: 
```
[T][X] eat an apple
```
### 2  - Deadline tasks
The user can add a simple task with a pre-determined 
deadline to the program.

#### Command
To add a deadline task, type the following into the program.
For example, a user would like to "return a book by 31st October
2019 6.30pm". Thus, the user can input the following command:
```
deadline return book /by 2019-10-31T18:30:00
```
The task will be displayed as: 
```
[D][X] return book (by: 31 OCTOBER 1830 hours)
```

### 3  - Event tasks
The user can add an event task with a pre-determined 
starting time and ending time to the program.

#### Command
To add a event task, type the following into the program.
For example, a user has a "project meeting on the 20th October from
6.30am to 7.30am". Thus, the user can input the following command:
```
event project meeting /at 2019-10-20T06:30:00 2019-10-20T07:30:00
```
The task will be displayed as: 
```
[E][X] project meeting (at: 20 OCTOBER 0630 hours to 20 OCTOBER 0730 hours)
```

## Features 
### 1 - List tasks
The user can list all the tasks in the tasklist by typing the
following command:
#### Command
```
list
```
The tasks will be displayed in this style: 
```
1 [T][X] eat an apple
2.[D][X] return book (by: 31 OCTOBER 1830 hours)
3.[E][X] project meeting (at: 20 OCTOBER 0630 hours to 20 OCTOBER 0730 hours)
```

### 2 - Mark status of task
The user can mark a task as "Done" using this command:

#### Command
For example, if he wants to mark the 1st task as done:
```
done 1
```
The task will be displayed as: 
```
1 [T][✓] eat an apple
```

### 3 - Delete a task
The user can delete a task by its index.

#### Command
```
delete 1
```
The task will be removed and not be displayed when a user types
the 'list' command.


### 4 - Find a keyword
The user can search for a task using a keyword.

#### Command
For example, the user wants to find all the tasks that mention
the word 'book'. Thus, the user can input the following command:
```
find book
```
Tasks containing the keyword book will be displayed:
```
1.[T][X] return a book
2.[E][X] book a meeting room (at: 20 OCTOBER 0630 hours to 20 OCTOBER 0730 hours)
```
### 5 - Tag a task
The user can tag a task with a keyword.

#### Command
For example, the user wants to tag a task a #homework: 
```
tag 1 #homework
```


### 6 - Find a #tag
The user can find tasks categorised according to their tags.

#### Command
To find all tasks with the tag #homework, the user can input the following command:
```
find #homework
```
Tasks tagged with #homework will be displayed as: 
```
1. [E][X] project meeting (at: 20 OCTOBER 0630 hours to 20 OCTOBER 0730 hours)
2. [T][X] buy a textbook
```