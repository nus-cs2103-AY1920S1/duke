# User Guide

## 1. Introduction
This product is called Duke. Duke is a smart chatbot which assist you in organising your task.

## 2. Features
There are 5 main features on Duke:

1. Add Task
    - add `todo` task
    - add `deadline` task
    - add `event` task
2. Delete Task
3. List Task
4. Mark Task as Done
5. Find Task

### 2.1 - Add Task
There are three different ways to add task into Duke.

#### 2.1.1 Todo
Command: `todo [task description]`

This will add the task into Duke as a normal task.

#### 2.1.2 Deadline
Command: `deadline [task description] /by date time`

`Note`
1. /by has to be added into deadline task to indicate the `data` and `time`
2. `date` must be in format [dd/mm/yyyy]
3. `time` must be in format [24 hour format]

eg. `deadline assignment /by 19/9/2019 1800`

This command will add the task into Duke as a deadline task with date and time.

#### 2.1.3 Event
Command: `event [task description] /at date time`

`Note`
1. /by has to be added into event task to indicate the `data` and `time`
2. `date` must be in format [dd/mm/yyyy]
3. `time` must be in format [24 hour format]

eg. `event project meeting /at 19/9/2019 1800`

This command will add the task into Duke as an event task with date and time.

#### 2.1.4 Mark Task as Done
Command: `done [index of task]`

This command will mark the respective task you choose as done. A tick will show at the respective task.

#### 2.1.5 Find Task
Command: `find [keyword]`

eg. `find homework`

This command will list out all the task that contains the keyword.

## 3. Exit
Command: `bye`

This command will exit Duke chatbot.
