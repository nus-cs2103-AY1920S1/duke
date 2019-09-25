# Snowball the Task Manager – User Guide

## About

Snowball is a Task Manager which helps to keep track of all the things you have to do.
Snowball communicates using a chat interface, so all you have to do is to type in your 
commands, and Snowball will handle the rest!

![Snowball image](https://github.com/dorcastan/duke/blob/master/src/main/resources/images/Snowball.png?raw=true)
>Hello! My name is Snowball. I'm here to help you organise your tasks!


## Features

### Basic Search Function

Ever had trouble trying to scan through a long list of tasks to find the one that you 
were looking for? Snowball eliminates this problem by looking up tasks for you. Simply
use the `find` command to search for any part of the task's details that is visible when 
tasks are displayed. 

### Different Types of Tasks

Snowball organises three types of tasks: To-Dos, Deadlines, and Events.

1. **To-Do**

    A To-Do is a generic task that consists of a description and completion status. 

    To-Dos are denoted by **[T]** in the displayed task list.
    
1. **Deadline**

    A Deadline is similar to a To-Do, but it also contains a due date/time which is displayed
    along with the Deadline. 
    
    Deadlines are marked with **[D]** in the task list.

1. **Event**

    An Event is a task which consists of a description and an event time. It differs from a
    Deadline in that Deadlines must be completed *by* a certain time, whereas events happen
    *at* their given time. 
    
    Events are denoted by **[E]** in the task list.

### Task Priority Levels

All tasks can be marked with a priority level that is either None, Low, Medium, or High 
(increasing order of importance).
When tasks are displayed, priority levels that are higher than None are also displayed before
the task that they are attached to.

Example:
>1\. [T][-] Say hello to Snowball!<br>
2\. [T][-] [Not so important] Bake muffins<br>
3\. [D][-] [Quite important] Read lecture notes (by: Thu, 12 Sep 19, 22:00)<br>
4\. [D][-] [Important!!] Write presentation script (by: Fri, 13 Sep 19, 14:00)<br>


## Usage

> **Command Format**
> * Words in UPPER-CASE are the parameters to be supplied by the user.
> * Items in [square brackets] are optional.
> * Main commands are case insensitive. `LIST` and `liSt` will work, but not `/AT`.
> * Parameters must be given in the specified order unless otherwise stated.

#### Displaying Tasks

##### `list`
* Displays the list of tasks that Snowball is currently managing. 
* Tasks types and completion status are shown along with the task description and time 
  (if any). Priority levels that are higher than None are also displayed.
* For example: `list`
    > 1\. [E][+] Project meeting (at: Tue, 10 Sep 19, 12:00)<br> 
    2\. [D][-] Submit presentation slides (by: Wed, 11 Sep 19, 23:59)<br>
    3\. [T][-] Go to sleep

##### `find PHRASE`
* Displays all tasks that contain `PHRASE` in their description, priority level, 
  index number, or time.
* Can be used to search for specific task numbers or priority levels. 
* `find` is case sensitive, i.e. `find meeting` will not find a task with the 
  description "Meeting with project group".
* For example: `find Sun`
    > 1\. [D][-] Read lecture notes (by: Sun, 18 Aug 19, 12:00)<br> 
    3\. [T][-] Go out on Sunday!
* Example #2: `find 1` displays all tasks that contain the number 1. However, it also
  shows tasks which have the number 1 in their descriptions.
    > 2\. [T][-] Read books<br>
    10\. [T][-] Draft interview questions<br>
    22\. [D][-] Submit report (by: Mon, 11 Nov 19, 12:00)
* Example #3: `find Important!` displays all tasks that are marked as Important.
    > 5\. [T][-] [Important!!] Message group about project deadline<br> 
    22\. [D][-] [Important!!] Submit report (by: Mon, 11 Nov 19, 12:00)<br>

#### Adding New Tasks

##### `todo DESCRIPTION`
* Adds a new To-Do task with the given description.
* Example: `todo Read textbook`
    >Got it. I've added this task:<br>
      [T][-] Read textbook<br>
    Now you have 5 tasks in the list.

##### `deadline DESCRIPTION /by TIME`
* Adds a new Deadline task with the given description and due time.
* `TIME` must be given in one of the following formats:
    * DOW, DD-MM-YY, hh:mm
    * DD-MM-YYYY hh:mm
    * DDMMYY hhmm
        * Note: no punctuation
    * ISO date time, i.e. YYYY-MM-DDThh:mm:ss
        * Note the 'T' between date and time
* If `TIME` does not match any valid format, the due date and time will be set 
  to the current date and time. 
* For example, you could type: `deadline Write project completion report /by 200919 1800`
    >Got it. I've added this task:<br>
      [D][-] Write project completion report (by: Fri, 20 Sep 2019, 18:00)<br>
    Now you have 6 tasks in the list.
* Example #2 - Snowball responds with an error message if `TIME` is not specified: `deadline Write project completion report`
    >Sorry, what's the deadline for this? 

##### `event DESCRIPTION /at TIME`
* Adds a new Event task with the given description and event time.
* `TIME` must be given in one of the following formats:
    * DOW, DD-MM-YY, hh:mm
    * DD-MM-YYYY hh:mm
    * DDMMYY hhmm
        * Note: no punctuation
    * ISO date time, i.e. YYYY-MM-DDThh:mm:ss
        * Note the 'T' between date and time
* If `TIME` does not match any valid format, the event date and time will be set 
  to the current date and time. 
* For example: `event Internship interview /at 270819 1500`
    >Got it. I've added this task:<br>
      [E][-] Internship interview (at: Tue, 27 Aug 2019, 15:00)<br>
    Now you have 7 tasks in the list.
* Again, an error message is shown if `TIME` is missing: `event Internship`
    >Sorry, I need to know when your event is!

#### Completing Tasks

##### `done INDEX`
* Marks the task with the given index number as done.
* `INDEX` should be the list index number of the task with no other symbols or 
  punctuation.
* For example: `done 1`
    >Nice! I've marked this task as done:<br>
    [T][+] Make user guide
* Invalid usage for a list with 2 tasks: `done 1.` or `done 5`
    >Sorry, I couldn't find the task you requested!

##### `undone INDEX`
* Marks the task with the given index number as undone.
* `INDEX` should be the list index number of the task with no other symbols or 
  punctuation.
* For example: `undone 1`
    >Oh dear. I've marked this task as undone:<br>
    [T][-] Make user guide
* Invalid usage for a list with 2 tasks: `undone 1.` or `undone 5`
    >Sorry, I couldn't find the task you requested!

#### Changing Task Priority Levels

##### `priority INDEX PRIORITY-LEVEL`
* Changes the priority of the task with the given `INDEX` to `PRIORITY-LEVEL`.
* `INDEX` should be the list index number of the task with no other symbols or 
  punctuation. Same as for the `done` and `undone` commands.
* `PRIORITY-LEVEL` should be one of the following:
    * High priority (Important!!): `high`
    * Medium priority (Quite important): `medium`, `mid`, or `quite`
    * Low priority (Not so important): `low`
    * No priority: `none` or `no`
* For example: `priority 1 high`
    >Okay! I've set this task's priority to Important!!:<br>
    [T][-] [Important!!] Make user guide

#### Removing Tasks

##### `delete INDEX`
* Deletes the task with the given index number. 
* **Use with caution**: deleted tasks cannot be recovered.
* `INDEX` should be the list index number of the task with no other symbols or 
  punctuation, as in the `done`, `undone`, and `priority` commands.
* Example usage: `delete 1`
    >Noted. I've deleted this task:<br>
      [D][-] Submit presentation slides (by: Wed, 11 Sep 19, 23:59)<br>
    Now you have 6 tasks in the list.

#### Saying Bye

##### `bye`
* Displays a goodbye message from Snowball. 
* Does not actually do anything useful. But it's always good manners to say bye before 
  leaving a chat!
* Example: `bye`
    >Bye. Hope to see you again soon!

## Command Summary

* `list` - Display all tasks.
* `find PHRASE` - Display tasks that contain `PHRASE`.
* `todo DESCRIPTION` - Add a To-Do with the given description.
* `deadline DESCRIPTION /by TIME` - Add a Deadline with the given description and due time.
* `event DESCRIPTION /at TIME` - Add an Event with the given description and event time.
* `done INDEX` - Mark the task with the given index number as done.
* `undone INDEX` - Mark the task with the given index number as undone.
* `priority INDEX PRIORITY-LEVEL` - Mark the given task with the given priority level.
* `delete INDEX` - Delete the task with the given index number.
* `bye` - Tell Snowball goodbye before you exit the application.
