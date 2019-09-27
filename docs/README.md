# User Guide
## 1. Introduction
Duke is a CLI personal assistant that keeps track of the tasks that you need to complete.

## 2. Features 
> Notation
> * Words in UPPER_CASE need to be supplied by the user.
> * Date Format used in the app is in 'yyyyMMd HHmm'.


#### 2.1 Add a task
---
There are 3 types of tasks that Duke can keep track of:
 
 * ToDo - A plain old ToDo.
 * Deadline - A Todo with a deadline.
 * Event - An event with a date.
 
To add a task, simply call one of the following commands matching the task that you wish to keep track of.

Command: `[TYPE OF TASK] [DESCRIPTION]`
    
#### 2.1.1 Add ToDo
Command: `todo [DESCRIPTION]`

Example: `todo return book`

#### 2.1.2 Add Deadline
Command: `deadline [DESCRIPTION] /by [DATE_TIME]`

Example: `deadline CS Tutorial /by 20191021`

#### 2.1.3 Add Event
Command: `event [DESCRIPTION] /at [DATE_TIME]`

Example: `event workout /at 20/09/2019 1700`

### 2.2 List all tasks 
---
List all the tasks that Duke has.

Command: `list`


### 2.3 Mark task as Completed
---
Mark a task with the `[INDEX]`as completed. The index can be retrieved by calling the `list` command first.

Command: `done [INDEX]`

Example: `done 1`

Condition: 
`[INDEX]` should point to one of the tasks in the list. The hard restriction is  0 < `[INDEX]` < n where n is the size of the list.


### 2.4 Delete a task 
---
Delete a task at the index `[INDEX]`.

Command: `delete [INDEX]`

Example: `delete 3`

Condition: 
`[INDEX]` should point to one of the tasks in the list. The hard restriction is  0 < `[INDEX]` < n where n is the size of the list.


### 2.5 Find tasks 
---
Find all tasks that contain the supplied `[PATTERN]` in their description.

Command: `find [PATTERN]`

Example: 

Consider the following tasks in your list.
```
Here are the tasks in your list:

1.[T][✓] read book
2.[D][✗] return book (by: 20190801 2345)
3.[E][✗] project meeting (at: 20190901 1800)
4.[T][✓] join sports club
5.[T][✗] borrow book
```
`find ook` will return the following list.

```
Here are the matching tasks in your list:
1.[T][✓] read book
2.[D][✗] return book (by: 20190801 2345)
3.[E][✗] project meeting (at: 20190901 1800)
4.[T][✓] join sports club
5.[T][✗] borrow book
```
### 2.6 Exit 
---
Exit the program. The updated task list will be saved to disk upon exit.

Command: `bye`