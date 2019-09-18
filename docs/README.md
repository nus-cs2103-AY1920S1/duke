# Duke - Interactive Task Tracker
<!-- ![User interface screenshot](Ui.png) -->
* This is a desktop task tracking application. It has a GUI but most of the user interaction happens using a CLI (Command Line interface)

# User Guide

## Features
##### Command Format
* Words in `<UPPER_CASE>` are parameters to be supplied by the user
  * E.g In `todo <DESCRIPTION>`, `DESCRIPTION` is a parameter which can be used as `todo grill chicken`.

### 1. Adding tasks to the tracker:
Three types of tasks, `TODO`, `EVENT` and `DEADLINE` can be added.

##### Format: `<TASK_TYPE> <PARAMETERS>`

#### 1.1) `todo` - Add a `TODO` task

###### Syntax:

`todo <DESCRIPTION>`

* Adds a new `TODO` with `DESCRIPTION` to the tracker.

###### Example usage:

`todo grill beef`

* Adds a new `TODO` to *grill beef*.

#### 1.2) `event` - Add an `EVENT` task

###### Syntax:

`event <DESCRIPTION> /at <DETAILS>`

* Adds a new `EVENT` with `DESCRIPTION` and `DETAILS` to the tracker.

###### Example usage:

    event roast pork /at The Supreme Cork

* Adds a new `EVENT` to *roast pork* at *The Supreme Cork*.

#### 1.3) `deadline` - Add a `DEADLINE` task

###### Syntax:

    deadline <DESCRIPTION> /by <DATETIME>

* Adds a new `DEADLINE` with `DESCRIPTION`, to be done by `DATETIME` to the tracker.

###### Example usage:

    deadline clean beans /by 031219 2359

###### Output:



* Adds a new `DEADLINE` to *clean beans* by 3rd December, 2019, 11.59pm.

### 2. List all tasks in the tracker:

Displays all tasks in the tracker to the screen.

###### Syntax: `list`

###### Example usage:

    todo boil toy
    list

