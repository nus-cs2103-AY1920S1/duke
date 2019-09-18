# User Guide

**Outline**
1. Introduction
1. Features
   1. Adding of tasks to Duke
   1. Modifying tasks in Duke
   1. Searching and listing tasks in Duke
1. Shortcut commands and modifying them
1. Command summary

-----------------------------------------------------------------------

## 1. Introduction
This is a modified Duke task manager named Pepeg for all your daily tasking needs.

## 2. Features
There are 3 main features of Duke: adding of tasks, modifying tasks & searching and listing of tasks.

### 2.i Adding of tasks to Duke
The list of tasks you can add to Duke: deadline, event, todo

#### Deadline `deadline` `{task to do}` `/by` `{date}`
Adds a task to Duke with its description and date to be done by.
Eg.
> deadline 2103T IP /by 19/9/2019 2359

#### Event `event` `{activity}` `/at` `{date}`
Adds an event to Duke with its description and date of the event.
Eg.
> event 2013T Lecture /at 20/9/2019 1400

#### Todo `todo` `{task to do}`
Adds a task to Duke with its description.
Eg.
> todo sleep early

### 2.ii Modifying tasks in Duke
There are 2 ways of modifying tasks in Duke: deletion of tasks & marking tasks as done

#### Delete `delete` `{index of task to be deleted}`
Deletes a task at the specified index and displays the deleted task.
Eg.
> delete 420

#### Done `done` `{index of task to be marked as done}`
Marks a task as done at the specified index and displays the done task.
Eg.
> done 1337

### 2.iii Searching and listing tasks in Duke
#### Find `find` `{keyword(s) to match}`
Finds tasks matching the given input keyword(s) and displays them.
Eg.
> find covfefe

#### List `list`
Displays all stored tasks in Duke.
Eg.
> list

## 3 Shortcut commands and modifying them
#### Default shortcuts
bye: `b`, deadline: `dead`, define: `def`, delete: `del`, done: `d`, event: `e`, find: `f`, list: `l` & todo: `t`

##### Define `define` `{shortcut to be replaced}` `{new shortcut}`
Redefine current shortcuts in Duke 
Eg.
> define b cy4l8rk1d

## 4. Command summary
Below is a list of all of the commands in Duke currently.

Name | Type | Command | Default shortcut
---- | ---- | ------- | --------
Deadline| Adding of tasks | `deadline` `{task to do}` `/by` `{date}` | `dead`
Event | Adding of tasks | `event` `{activity}` `/at` `{date}` | `e`
Todo | Adding of tasks | `todo` `{task to do}` | `t`
Delete | Modifying of tasks | `delete` `{index of task to be deleted}` | `del`
Done | Modifying of tasks | `done` `{index of task to be marked as done}` | `d`
Find | Accessing of tasks | `find` `{keyword(s) to match}` | `f`
List | Accessing of tasks | `list` | `l`
Define | Modifying shortcuts | `define` `{shortcut to be replaced}` `{new shortcut}` | `def`
Bye | Exit program | `bye` | `b`
