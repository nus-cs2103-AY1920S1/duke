# User Guide

## Features 

### Add a task to your list
Add a task to your list. There are 3 types of tasks: a todo, a deadline, and an event.
The difference lies in the associated date.

Todo | Deadline | Event
-----|----------|------
No associated date | To be done by a specified date | To be done at a specified date

## Usage

### `todo` - Add a todo to your list

Add a todo to your list. 

Format: `todo [Description]`
   
 * `Description` - The description of the todo.

Example of usage:

    todo Buy milk

Expected outcome:

    Got it. I've added this task:
      [T][✘] Buy milk
    Now you have 1 task in the list.

### `deadline` - Add a deadline to your list

Add a deadline to your list.

Format: `deadline [Description] /by [Date]`
 * `Description` - The description of the deadline.
 * `Date` - The deadline date in the format of `dd/MM/yyyy hhmm`.

Example of usage:

    deadline Do homework /by 12/12/2019 2359
    
Example outcome:
    
    Got it. I've added this task:
      [D][✘] Do homework (by: 12 December 2019 11:59PM)
    Now you have 2 tasks in the list.
    
### `event` - Add an event to your list

Add an event to your list.

Format: `event [Description] /at [Date]`
 * `Description` - The description of the event
 * `Date` - The event date in the format of `dd/MM/yyyy hhmm`
 
Example of usage:

    event Group meeting /at 12/09/2019 1600
    
Example outcome:

    Got it. I've added this task:
      [E][✘] Group meeting (at: 12 September 2019 04:00PM)
    Now you have 3 tasks in the list.
   
-
### List all your tasks
List all your tasks to see what you have to do.

## Usage

### `list` - List all your tasks 

List all your tasks.

Format: `list`

Example of usage:

    list
    
Example outcome:

    Here are the tasks in your list:
    1. [T][✘] Buy milk
    2. [D][✘] Do homework (by: 12 December 2019 11:59PM)
    3. [E][✘] Group meeting (at: 12 September 2019 04:00PM)
   
-
### Mark a task as done
Mark a task as done.

## Usage

### `done` - Mark a task as done

Mark a task as done. A done task will still show up in the list, but the task icon is `[✓]` 
instead of `[✘]`

Format: `done [Index]`
 * `Index` - The index of the task in the list.
 
Example of usage:

    done 2
    
Example outcome:

    Nice! I've marked this task as done:
      [D][✓] Do homework (by: 12 December 2019 11:59PM)
 
-
### Delete a task from the list
Delete a task from the list.

## Usage

### `delete` - Delete a task from the list

Delete a task from the list. A deleted task will not show up in the list. 
A task can be deleted regardless of whether it is done.

Format: `delete [Index]`
 * `Index` - The index of the task in the list.
 
Example of usage:

    delete 2
    
Example outcome:

    Noted. I've removed this task:
      [D][✓] Do homework (by: 12 December 2019 11:59PM)
    Now you have 2 tasks in the list.
 
-
### Find all tasks in the list containing a keyword
Find all task in the list containing a keyword.

## Usage

### `find` - Find all tasks in the list containing a keyword

Find all tasks in the list containing a keyword. 
The keyword is matched to the description of the tasks.
The keyword is case-sensitive and can contain spaces.

Format: `find [Keyword]`
 * `Keyword` - The keyword to find in the description of the tasks

Example of usage:

    find meeting
    
Example outcome:

    Here are the matching tasks in the list
    1. [E][✘] Group meeting (at: 12 September 2019 04:00PM)
    
-
### Sort the tasks
Sort the tasks in the list.

## Usage

### `sort` - Sort the tasks

Sort the tasks in the list in a specified order.

Format: `sort [Mode]`
 * `Mode` - The sorting mode.
 
Available modes:
 * `chrono` 
   * Sort the tasks in a chronological order. As todos don't have an associated date, they
   will be in the bottom of the list.
 * `lexi`
   * Sort the tasks in a lexicographical order by their description.
 * `done`
   * Move all tasks which are not done yet to the top of the list.
 * `deadline`
   * Move all deadlines to the top of the list. 
 * `event`
   * Move all events to the top of the list.
 * `todo`
   * Move all todos to the top of the list.
   
Example of usage:

    sort chrono
    
Example outcome:

    I have sorted your tasks!
    1. [E][✘] Group meeting (at: 12 September 2019 04:00PM)
    2. [D][✓] Publish Duke (by: 17 September 2019 11:59PM)
    3. [E][✘] Scratch hooman (at: 12 June 2021 08:12PM)
    4. [D][✘] Beg for forgiveness (by: 31 December 2021 11:59PM)
    5. [T][✓] Smile awkwardly
    6. [T][✘] Buy milk