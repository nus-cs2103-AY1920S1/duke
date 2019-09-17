# User Guide

### <a name="toc"></a>Table of Contents
- [Introduction](#intro)
- [Features](#features)
  - [Task List](#f-tasklist)
  - [Different types of tasks](#f-types)
  - [Tracking task progress](#f-done)
  - [Task filtering](#f-find)
  - [Undoing of changes](#f-undo)
- [Usage](#usage)
  - [todo](#u-todo)
  - [deadline](#u-deadline)
  - [event](#u-event)
  - [list](#u-list)
  - [done](#u-done)
  - [delete](#u-delete)
  - [undo](#u-undo)
  - [bye](#u-bye)

## <a name="intro"></a>Introduction
Duke is a desktop application that helps keep track of the tasks that you have to do. This user guide explains the different features that have been implemented, the commands that are available to you, as well as examples on how to use the commands.

[:point_up_2:](#toc)

## <a name="features"></a>Features 

### <a name="f-tasklist"></a>1. Task List
Duke maintains a task list that you can add and remove different tasks into in order to keep track of them.

### <a name="f-types"></a>2. Different types of tasks
Duke supports 3 types of task types, "To-do", "Deadline", and "Event" tasks.

### <a name="f-done"></a>3. Tracking task progress
Duke allows you to indicate a tasks state of completion, whether it is undone or done.

### <a name="f-find"></a>4. Task filtering
Duke allows you to find tasks that contain your specified keywords.

### <a name="f-undo"></a>5. Undoing of changes
Duke allows you to undo changes in case you make any mistakes.

[:point_up_2:](#toc)

## <a name="usage"></a>Usage

### <a name="u-todo"></a>`todo` - Creates a "To-do" task
```
todo <task description>
```

The "To-do" task only contains a *task description*, which you may enter after the `todo` command, separated by a whitespace.

<details><summary>How to use:</summary>

#### Example of usage: 

```
todo grab lunch
```

#### Expected outcome:

```
_____________________________________________________
 Got it. I've added this task:
  [T][✗] grab lunch
 Now you have 1 task in your list.
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-deadline"></a>`deadline` - Creates a "Deadline" task
```
deadline <task description> /by <deadline time>
```

The "Deadline" task contains a *task description*, as well as a *deadline time*.

The task description should be entered after the `deadline` command, separated by a white space.

The deadline time should be entered after the `/by` keyword, separated by a white space. The `/by` keyword should also be separated from the task description by a whitespace.

Deadline time must be entered in the format `dd/MM/yyyy HHmm`. Note the use of 24-hour time format.

<details><summary>How to use:</summary>

#### Example of usage: 

```
deadline clean room /by 01/01/2020 0000
```

#### Expected outcome:

```
_____________________________________________________
 Got it. I've added this task:
  [D][✗] clean room (by: Wed Jan 01 00:00:00 SGT 2020)
 Now you have 2 tasks in your list.
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-event"></a>`event` - Creates a "Event" task
```
event <task description> /at <event time>
```

The "Event" task contains a *task description*, as well as a *event time*.

The task description should be entered after the `event` command, separated by a white space.

The event time should be entered after the `/at` keyword, separated by a white space. The `/at` keyword should also be separated from the task description by a whitespace.

Event time must be entered in the format `dd/MM/yyyy HHmm`. Note the use of 24-hour time format.

<details><summary>How to use:</summary>
 
#### Example of usage: 

```
event house warming /at 01/01/2020 2000
```

#### Expected outcome:

```
_____________________________________________________
 Got it. I've added this task:
  [E][✗] house warming (at: Wed Jan 01 20:00:00 SGT 2020)
 Now you have 3 tasks in your list.
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-list"></a>`list` - Lists all recorded events
```
list
```

Lists all the tasks currently stored within the task list.

<details><summary>How to use:</summary>
 
#### Example of usage:

```
list
```

#### Expected outcome:

```
_____________________________________________________
 Here are the tasks in your list:
  1.[T][✗] grab lunch
  2.[D][✗] clean room (by: Wed Jan 01 00:00:00 SGT 2020)
  3.[E][✗] house warming (at: Wed Jan 01 20:00:00 SGT 2020)
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-done"></a>`done` - Marks task as done
```
done <index>
```

Indicates a task as done, according to that task's *index* within the task list.

<details><summary>How to use:</summary>
 
#### Example of usage:

##### When the task list looks like this:

```
_____________________________________________________
 Here are the tasks in your list:
  1.[T][✗] grab lunch
  2.[D][✗] clean room (by: Wed Jan 01 00:00:00 SGT 2020)
  3.[E][✗] house warming (at: Wed Jan 01 20:00:00 SGT 2020)
_____________________________________________________
```

##### By doing the command:

```
done 2
```

#### Expected outcome:

```
_____________________________________________________
 Nice! I've marked this task as done:
  [D][✓] clean room (by: Wed Jan 01 00:00:00 SGT 2020)
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-delete"></a>`delete` - Deletes a task
```
delete <index>
```

Deletes a task according to that task's *index* within the task list.

<details><summary>How to use:</summary>
 
#### Example of usage:

##### When the task list looks like this:

```
_____________________________________________________
 Here are the tasks in your list:
  1.[T][✗] grab lunch
  2.[D][✓] clean room (by: Wed Jan 01 00:00:00 SGT 2020)
  3.[E][✗] house warming (at: Wed Jan 01 20:00:00 SGT 2020)
_____________________________________________________
```

##### By doing the command:

```
delete 1
```

#### Expected outcome:

```
_____________________________________________________
 Noted. I've removed this task:
  [T][✗] grab lunch
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-undo"></a>`undo` - Undoes a change
```
undo
```

Undoes the recent changes that have been made to the task list. Note that once you close the Duke application, all changes that have been made can no longer be undone.

Changes that can be undone include:
- adding of tasks to the task list
- deleting of tasks from the task list
- indicating a task as done

<details><summary>How to use:</summary>
 
#### Example of usage:

##### When the task list looks like this:

```
_____________________________________________________
 Noted. I've removed this task:
  [T][✗] grab lunch
_____________________________________________________

_____________________________________________________
 Here are the tasks in your list:
  1.[D][✓] clean room (by: Wed Jan 01 00:00:00 SGT 2020)
  2.[E][✗] house warming (at: Wed Jan 01 20:00:00 SGT 2020)
_____________________________________________________
```

##### By doing the command:

```
undo
```

#### Expected outcome:

```
_____________________________________________________
 Got it! The following task has been recovered:
  [T][✗] grab lunch
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-find"></a>`find` - Find tasks with a certain keyword
```
find <keyword>
```

Lists all the tasks within the current task list that contain the specified *keyword*.

<details><summary>How to use:</summary>
 
#### Example of usage:

##### When the task list looks like this:

```
_____________________________________________________
 Here are the tasks in your list:
  1.[E][✓] borrow book (at: Tue Oct 01 09:00:00 SGT 2019)
  2.[T][✗] read book
  3.[D][✗] return book (by: Fri Oct 04 17:00:00 SGT 2019)
  4.[T][✗] make dinner
  5.[E][✗] family gathering (at: Thu Oct 03 12:00:00 SGT 2019)
_____________________________________________________
```

##### By doing the command:

```
find book
```

#### Expected outcome:

```
_____________________________________________________
 Here are the matching tasks in your list:
  1.[E][✓] borrow book (at: Tue Oct 01 09:00:00 SGT 2019)
  2.[T][✗] read book
  3.[D][✗] return book (by: Fri Oct 04 17:00:00 SGT 2019)
_____________________________________________________
```
</details>

[:point_up_2:](#toc)

##

### <a name="u-bye"></a>`bye` - Stops Duke from running
```
bye
```

Says bye to Duke. Any further input made after the `bye` command will not do anything.

<details><summary>How to use:</summary>
 
#### Example of usage:

```
bye
```

#### Expected outcome:

```
_____________________________________________________
 Bye. Hope to see you again soon!
_____________________________________________________
```
</details>

[:point_up_2:](#toc)
