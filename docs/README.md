# User Guide

Welcome from Duke User Guide. Here, you will find how to use Duke Application.

## Table of Contents

- [What is Duke?](#what-is-duke)
    - [Image of Duke](#image-of-duke)
    
- [Commands](#commands)
    - [`list` - Lists down all the task](#list---lists-down-all-the-task)
    - [`todo <Description>` - to add a todo task](#todo-description---to-add-a-todo-task)
    - [`deadline <Description> /by <Date in DD/MM/YYYY> <Time in HHMM>`- to add a deadline](#deadline-description-by-date-in-ddmmyyyy-time-in-hhmm--to-add-a-deadline)
    - [`event <Description>' /at <Date in DD/MM/YYYY> <Time in HHMM>` - to add an event](#event-description-at-date-in-ddmmyyyy-time-in-hhmm---to-add-an-event)
    - [`delete <Task Number>` - to delete a task](#delete-task-number---to-delete-a-task)
    - [`done <Task Number>` - to mark a task as done](#done-task-number---to-mark-a-task-as-done)
    - [`undo` - to undo previous actions](#undo---to-undo-previous-actions)
    - [`find <keyword>` - to list down tasks with particular keyword](#find-keyword---to-list-down-tasks-with-particular-keyword)
    - [`bye` - to exit Duke](#bye---to-exit-duke)

## What is Duke?
 **Duke** is a friendly chatbot application which allow you to manage your daily tasks by keeping track of them.
 
### Image of Duke
![Image of Duke](Ui.png)
 

## Commands

### `list` - Lists down all the task

This command will list down all the task being tracked by Duke

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:

1. [T][] read book
```

---

### `todo <Description>` - to add a todo task

This command will add a task without any time attribute into the list of tasks.

Example of usage: 

`todo buy bread`

Expected outcome:

```
Got it. I've added this task:
    [T][ ] buy bread
Now you have <Number of Tasks> in the list.
```
---

### `deadline <Description> /by <Date in DD/MM/YYYY> <Time in HHMM>`- to add a deadline

This command will add a task with a deadline into the list of tasks.

Example of usage: 

`deadline return book /by 25/09/2019 1500`

Expected outcome:

```
Got it. I've added this task:
    [D][ ] return book (by: 25/09/2019, Wed, 3:00PM)
Now you have <Number of Tasks> in the list.
```

---

### `event <Description>' /at <Date in DD/MM/YYYY> <Time in HHMM>` - to add an event

This command will add an event with a time attribute into the list of tasks.

Example of usage: 

`event meeting /at 30/09/2019 1400`

Expected outcome:

```
Got it. I've added this task:
    [E][ ] meeting (at: 30/09/2019, Wed, 2:00PM)
Now you have <Number of Tasks> in the list.
```

---

### `delete <Task Number>` - to delete a task

This command will delete an event located at a particular index number.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
    [T][ ] buy bread
Now you have <Number of Tasks> in the list.
```

---

### `done <Task Number>` - to mark a task as done

This command will mark a task / deadline / event located at a particular index number.

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
    [D][O] return book (by: 25/09/2019, Wed, 3:00PM)
```

---

### `undo` - to undo previous actions

This command will undo your previous done actions such as deleting, adding, marking as done.

Example of usage: 

`undo`

Expected outcome:

```
You have just undid your previous action!
Here are the tasks in your list:

1. [T][ ] buy bread
2. [D][ ] return book (by: 25/09/2019, Wed, 3:00PM)
3. [E][ ] meeting (at: 30/09/2019, Wed, 2:00PM)
    
```

---

### `find <keyword>` - to list down tasks with particular keyword

This command will list down all the tasks with contains keyword in their description.

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:

1. [D][ ] return book (by: 25/09/2019, Wed, 3:00PM)
```

---

### `bye` - to exit Duke

This command will close the duke application.

Example of usage: 

`find book`

Expected outcome:

```
Bye. Hope to see you again soon!
```
---