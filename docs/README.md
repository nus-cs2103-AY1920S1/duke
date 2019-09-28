# User Guide

## Features

Add different types of tasks for Ice Bear to keep track of in a Task List.

Mark the tasks as done, delete them, and undo actions as you please!


## Adding Tasks
There are different types of tasks you can ask Ice Bear to keep track of.
   
They are Events, Deadlines, and To Do tasks!

### Add a Deadline Task - `deadline <description> /by <Date> <Time>`.

One of the tasks that Ice Bear can keep track of are Deadline Tasks.

Example of usage: 
`deadline Shop for Groceries /by 23/11/2019 1430`

Expected outcome:

```
Got it. Ice Bear has added this task:
  [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
Now you have 1 task in the list.
```
### Add an Event Task - `event <description> /at <Date> <Time>`.

Another type of task that Ice Bear can keep track of are Event Tasks.

Example of usage: 
`event Chloe's Birthday Party /at 9/8/2019 1700`

Expected outcome:

```
Got it. Ice Bear has added this task:
  [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
Now you have 2 tasks in the list.
```

### Add a Todo Task - `todo <description>`.

The last type of task that Ice Bear can keep track of are Todo Tasks.

Example of usage: 
`todo Clean up the fridge`

Expected outcome:

```
Got it. Ice Bear has added this task:
  [T][✘] Clean up the fridge
Now you have 3 tasks in the list.
```
## View and Edit the List of Tasks

Now that tasks have been added to the list, 
let's have a look at them, and see how we can make changes to our task list!

### View the List of Tasks - `list`

This command allows you to view the task list as a whole.

Each task is assigned a number based on its position in the list. 
These numbers are used to perform other list commands.

Example of usage:
`list`

Example of outcome: 
```
Here are the tasks in your list:
  1. [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
  2. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  3. [T][✘] Clean up the fridge
```

### Deleting Tasks - `delete <task ID>`

This command allows you to delete tasks from the task list, based on its index (1-based).

This index can be obtained from the result when you type the `list` command.

After the deletion of a task, all tasks

Example of usage: `delete 1`

Example of outcome:

`list`
```
Here are the tasks in your list:
  1. [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
  2. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  3. [T][✘] Clean up the fridge
```
`delete 1`
```
Ice Bear has removed this task:
  [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
Now you have 2 tasks in the list.
```
`list`
```
Here are the tasks in your list:
  1. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  2. [T][✘] Clean up the fridge
```

###Mark as Done - `done <Task ID>`

Ice Bear allows you to keep track of how much you have achieved in the task list.
This means you can mark a task as done, and this status is viewable in the task list.

Example of usage: `delete 1`

Example of outcome:

`list`
```
Here are the tasks in your list:
  1. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  2. [T][✘] Clean up the fridge
```
`done 1`
```
Okay. Ice Bear marked this task as done:
  [E][✓] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
```
`list`
```
Here are the tasks in your list:
  1. [E][✓] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  2. [T][✘] Clean up the fridge
```
###Undo a Command - `undo`

If you have made a change that you did not intend to make, 
this command will enable you to undo that change. Furthermore, previous actions
can also be undone using this command.

Example of usage:

`list`
```
Here are the tasks in your list:
  1. [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
  2. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  3. [T][✘] Clean up the fridge
```
`delete 1`
```
Ice Bear has removed this task:
  [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
Now you have 2 tasks in the list.
```
`list`
```
Here are the tasks in your list:
  1. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  2. [T][✘] Clean up the fridge
```
`undo`
```
Ice bear has successfully undone the previous action.
```
`list`
```
Here are the tasks in your list:
  1. [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
  2. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  3. [T][✘] Clean up the fridge
```
___
##Find a Task

Ice Bear also allows you to search for tasks in the list based on their name.

`list`
```
Here are the tasks in your list:
  1. [D][✘] Shop for Groceries (by: 23rd of November 2019, 2:30pm)
  2. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
  3. [T][✘] Clean up the fridge
```
`find Chloe`
```
Ice Bear has found these matching tasks in the list:
  1. [E][✘] Chloe's Birthday Party (at: 9th of August 2019, 5:00pm)
```