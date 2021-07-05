# Handler - Interactive Task Tracker
 ![User interface screenshot](Ui.png)
* This is a desktop task tracking application. It has a GUI but most of the user interaction happens using a CLI (Command Line interface)

# User Guide
The features available in the task tracker are summarised below.

## Features

##### Commands overview
* [todo](#11-todo---add-a-todo-task) - Create a new task with a description
* [event](#12-event---add-an-event-task) - Create a new task with a description and location/time
* [deadline](#13-deadline---add-a-deadline-task) - Create a new task with a description and deadline
* [list](#2-list-all-tasks) - List all tasks
* [delete](#3-delete-a-task) - Delete a specified task
* [done](#4-mark-a-task-as-done) - Marks a task as done
* [remindme](#6-get-reminders-for-tasks) - Shows reminders for specified task types
* [bye](#7-exit-the-application) - Exits the application

##### Command Format
* Words in `<UPPER_CASE>` are parameters to be supplied by the user
  * E.g In `todo <DESCRIPTION>`, `DESCRIPTION` is a parameter which can be used as `todo grill chicken`.

---

### 1. Adding tasks to the tracker
Three types of tasks, `TODO`, `EVENT` and `DEADLINE` can be added.

##### Format: `<TASK_TYPE> <PARAMETERS>`

#### 1.1) `todo` - Add a `TODO` task

###### Syntax:

`todo <DESCRIPTION>`

* Adds a new `TODO` with `DESCRIPTION` to the tracker.

###### Example usage:

`todo grill beef`

###### Output:

    [T][X] grill beef

* Adds a new `TODO` to *grill beef*.

#### 1.2) `event` - Add an `EVENT` task

###### Syntax:

`event <DESCRIPTION> /at <DETAILS>`

* Adds a new `EVENT` with `DESCRIPTION` and `DETAILS` to the tracker.

###### Example usage:

    event roast pork /at The Supreme Cork

###### Output:

    [E][X] roast pork (at: The Supreme Cork)

* Adds a new `EVENT` to *roast pork* at *The Supreme Cork*.

#### 1.3) `deadline` - Add a `DEADLINE` task

###### Syntax:

    deadline <DESCRIPTION> /by <DATETIME>

* Adds a new `DEADLINE` with `DESCRIPTION`, to be done by `DATETIME` to the tracker.

###### Example usage:

    deadline clean beans /by 031219 2359

###### Output:

    [D][X] clean beans (by: 03 December 2019, 11:59PM, GMT+8

* Adds a new `DEADLINE` to *clean beans* by 3rd December, 2019, 11.59pm.

---

### 2. List all task
Displays all tasks in the tracker to the screen.

###### Syntax: `list`

###### Example usage:

    todo boil toy
    list

###### Output:

    1. [T][X] boil toy  // only showing 'list' output

---

### 3. Delete a task

Delete a particular task from the tracker.

###### Syntax: `delete <TASK_INDEX>`
* `TASK_INDEX` is the numbering of the task when the `list` command is executed.

###### Example usage:

    todo boil toy
    event rush brush /at Dush
    list
    delete 2

###### Output:

    1. [T][X] boil toy  // before delete
    2. [E][X] rush brush (at: Dush)

    1. [T][X] boil toy  // after delete

---

### 4. Mark a task as done

###### Syntax: `done <TASK_INDEX>`

Marks a particular task in the tracker as complete.
* `TASK_INDEX` is the numbering of the task when the `list` command is executed.

###### Example Usage:

    todo boil toy
    event rush brush /at Dush
    list
    done 2
    list

###### Output:

    1. [T][X] boil toy  // before 'done'
    2. [E][X] rush brush (at: Dush)

    1. [T][X] boil toy  // after 'done'
    2. [E][O] rush brush (at: Dush)

---

### 5. Find a task

Searches and retrieves a task with the `KEYWORD` in the `DESCRIPTION`

###### Syntax: `find <KEYWORD>`
* `KEYWORD` is the string keyword.

###### Example Usage:

    todo boil toy
    event rush brush /at Dush
    find ush

###### Output:

    1. [E][X] rush brush (/at: Dush)

---

### 6. Get reminders for tasks:

Get a reminder for tasks due soon, as well as overdue deadlines.

###### Syntax: `remindme <TASK_TYPE>`
* `TASK_TYPE` can be either `todo`, `event`, `deadline` or `all`.

###### Example Usage:

    todo boil toy  // assume today's date is 18/09/2019, 6.55PM, Timezone GMT+8
    event rush brush /at Dush
    deadline grill mill /by 080919 2359
    deadline steal meal /by 080920 2359
    remindme event
    remindme deadline
    remindme all

###### Output:

    // remindme event
    1. [E][X] rush brush (at: Dush)

    // remindme deadline
    1. [D][X] steal meal (by: 08 September 2020, 11:59PM, GMT+8)

    Oh no, you have some overdue tasks!

    1. [D][X] grill mill (by: 08 September 2019, 11:59PM, GMT+8)

    // remindme all
    DEADLINE
    1. [D][X] steal meal (by: 08 September 2020, 11:59PM, GMT+8)

    Oh no, you have some overdue tasks!
    1. [D][X] grill mill (by: 08 September 2019, 11:59PM, GMT+8)

    EVENT
    1. [E][X] rush brush (at: Dush)

    TODO
    1. [T][X] boil toy

---

### 7. Exit the Application:

###### Syntax: `bye`

###### Example Usage:

    todo boil toy
    bye

###### Output:

* No output

---