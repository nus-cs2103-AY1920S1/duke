# User Guide

_Duke_ is like a personal assistant that helps you keep track of things.

This user guide gives summary of features and commands available in _Duke_.

<img src="Ui.png">

## Setting Up

This program requires users to have Java 11 or above. If opening the `.jar` files through double-clicking does not work try running it through command line:

    java -jar path/to/file/duke-0.2.3.jar

## Features 

### **1. Add tasks**

Records task to be noted of but yet to be completed to a list of tasks.

Tracks 3 types of tasks:
1. **ToDos**: Tasks without any date or time attached,just a description of what should be done
2. **Deadlines**: Tasks that should be done by a specific date and time
3. **Events**: Tasks that start and end at a specific time

### **2. Mark tasks as done**

Ability to mark tasks as done. Users will be notified if task has already been marked completed.

### 3. **Delete tasks from list**

Delete tasks from list of tasks.

### 4. **Saving list of tasks**

Tasks currently in the list are saved to a `.txt` file in the `data` folder.

### 5. **View current list of tasks**

Lists of all the tasks that are currently in the list, tasks are listed in order of when they were added.

### 6. **Understanding dates and times of a specific format**

_Deadline_ and _Event_ tasks added with the date and time specified in a certain format will be recognised and converted to a new format.

_Example: `2/12/2019 1800` to `2 December 2019, 6:00 PM`_

### 7. **Search by keywords**

Searches for tasks which contain matching keywords; searches are not case-sensitive. Multiple keywords can be searched in a go, as long as a task has descriptions matching any keyword provided, it will be shown.

### 8. **View statistics and insights**

Shows a list of numbers and statistics about the items managed by _Duke_ and the history of actions the user has taken.

Information includes:

- Tasks done
    - Number done so far (since the first use of _Duke_)
    - Number done in the past 7 days
    - Number of _ToDo_, _Deadline_, _Event_ tasks done individually
    - List of done tasks so far
- Tasks deleted
    - Number deleted so far
    - Number deleted that was **not** done
    - List of deleted tasks so far
- Search
    - List of keywords searched so far
    - List of keywords searched in the past 7 days

## Usage

### `todo` - Add a task of type _ToDo_

Adds a _ToDo_ task to the list of tasks.

Format: `todo task-description`

Example of usage: 

	todo read book

Expected outcome:

    ___________________________________
    Noted. I've added this task:
    [T][x] read book
    Now you have 4 tasks in the list.
    ___________________________________

### `deadline` - Add a task of type _Deadline_

Adds a _Deadline_ task to the list of tasks. `/by` descriptions of form `dd/MM/yyy HHmm` are recognised by _Duke_ and will be parsed into a more readable form. Time recognised is in the 24-hour clock format.

Format: `deadline task-name-due /by date-time`

Example of usage: 

	deadline MA4270 assignment /by Nov
	deadline cs2103 iP /by 18/09/2019 2359

Expected outcome:

    ___________________________________
    Noted. I've added this task:
    [D][x] MA4270 assignment (by: Nov)
    Now you have 5 tasks in the list.
    ___________________________________
    ___________________________________
    Noted. I've added this task:
    [D][x] cs2103 iP (by: 18 September 2019, 11:59 PM)
    Now you have 6 tasks in the list.
    ___________________________________

### `event` - Add a task of type _Event_

Adds an _Event_ task to the list of tasks. Similar to _Deadline_ tasks, `/at` descriptions of form `dd/MM/yyy HHmm` are recognised by _Duke_ and will be parsed into a more readable form.

Format: `event event-name /at location-time-duration`

Example of usage: 

	event Student Life Fair /at UTown, 10am-4pm


Expected outcome:

    ___________________________________
    Noted. I've added this task:
    [E][x] Student Life Fair (at: UTown, 10am-4pm)
    Now you have 7 tasks in the list.
    ___________________________________

### `find` - Finds tasks which match keywords

Finds tasks which has descriptions matching given keyword(s). This command is not case-sensitive. Multiple keywords can be searched in a go. Tasks that match any of the given keywords will be listed. Note that keywords have to match words character for character (but differences in upper and lower casing allowed). Keywords that are character subsets of a longer word will not be matched. 

For example, searching `book` will not return a task that has `books` in its description, but a task with `BOOK` will be returned.

Format: `keyword keyword1 [keyword2 ...]`

Example of usage: 

	find BOOK

Expected outcome:

    ___________________________________
    Here are the matching tasks in your list:
    1. [T][x] read book
    ___________________________________

### `list` - Lists out all tasks in list

Lists out all tasks in list, in the order of when they were added, numbering them with indexes.

Example of usage: 

	`list`

Expected outcome:

    ___________________________________
    Here are the tasks in your list:
    1. [D][x] MA4270 assignment (by: Nov)
    2. [D][x] cs2103 iP (by: 18 September 2019, 11:59 PM)
    3. [E][x] Student Life Fair (at: UTown, 10am-4pm)
    4. [T][x] read book
    ___________________________________

### `done` - Marks a task in the list as done

Marks a specified task, identified by its index in the list, as done.

Format: `done task-index`

Example of usage: 

	done 1

Expected outcome:

    ___________________________________
    Nice! I've marked this task as done:
    [D][v] MA4270 assignment (by: Nov)
    ___________________________________

### `delete` - Deletes a task from the list

Deletes a specified task from the list, identified by its index in the list. This action cannot be undone.

Format: `delete task-index`

Example of usage: 

	delete 1

Expected outcome:

    ___________________________________
    Noted. I've removed this task:
    [D][v] MA4270 assignment (by: Nov)
    Now you have 6 tasks in the list.
    ___________________________________

### `stats` - Gives summary statistics on _Duke_

Gives some numbers about the tasks _Duke_ is and has handled, also gives some information about past searches for keywords.

In the list of words searched so far, the keywords are presented in an array where each sub-array of keywords represent the words searched in a certain session of using _Duke_. A new session of using _Duke_ starts when the application is started to the point when it is exited.

Example of usage: 

	stats

Expected outcome:

    ___________________________________
	No. of tasks marked done so far: 1
	List of tasks done so far:
	[[T][v] read book]
	No. of tasks marked done in the past 7 days: 1
	No. of ToDo tasks marked done so far: 1
	No. of Deadline tasks marked done so far: 0
	No. of Event tasks marked done so far: 0

	No. of tasks deleted so far: 0
	List of tasks deleted so far:
	[]
	No. of tasks deleted that was not done: 0

	List of words searched so far: [[BOOK], [assignment, CS]]
	List of words searched in the past 7 days: []
    ___________________________________

### `bye` - Exits _Duke_

Exits the program.

Example of usage:

	bye

Expected outcome:

    ___________________________________
    Bye. Hope to see you again soon!
    ___________________________________
