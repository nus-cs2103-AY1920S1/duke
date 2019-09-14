# User Guide

### What is Duke?
Duke is your friendly chatbot which helps to keep track of your daily tasks! It's a To-Do List where you can add and 
modify your tasks.

## Features 

1. Add Task - Add new tasks into your list to keep track of them.
1. Delete Task - Delete old tasks that you have completed or no longer have use for.
1. Modify Task - Update details of tasks.
1. Find Task - Filters the list of tasks based on a keyword.
1. Complete Task - Marks the task as completed.
1. List Task - Lists the tasks you have.
## List of commands

* `list` : Displays information about your tasks.
* `bye` : Quits Duke.
* `todo <Description>` : Adds a To-Do into your list.
* `deadline <Description> /by <Time>` : Adds a Deadline into your list. Deadlines must have time of format of format `Year(yyyy)/Month(mm)/Date(dd) Hour(hh)Minute(mm)`, eg 2019-09-14 1800
* `event <Description> /at <Time>` : Adds an Event into your list. Events must have time of format of format `Year(yyyy)/Month(mm)/Date(dd) Hour(hh)Minute(mm)`, eg 2019-09-14 1800
* `find <Keyword>` : Filters list by `Keyword`.
* `done <Number in the list>` : Mark the task at `Number` in the list as done.
* `delete <Number in the list>` : Deletes the task at `Number` from the list.
* `update <Number in the list> descrip <New Description>` : Updates the task at `Number`'s Description.
* `update <Number in the list> time <New Description>` : Updates the task at `Number`'s Time.
