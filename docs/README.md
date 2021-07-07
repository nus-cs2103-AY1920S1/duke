# User Guide

## Features 

### Adding Tasks
Allows users to add three different types of tasks to the tasklist including tasks of Todo, Deadline and Event type.
* Todo: Task that have no date required to be associated with it.
* Deadline: Task with a deadline associated with it (may either be a date or both date and time)
* Event: Task that has a date, start time and end time associated with it.
All tasks have an attribute that allows users to view and modify whether it has been completed.

### Listing Tasks
Allows users to list all tasks that have been added to the tasklist. Always shows tasks in order of chronology (date added) until they have been sorted in order of other fields by the user. All tasks in the list are automatically saved into a 'data' folder in the same directory where the Duke program is opened. (this folder will be created if it does not exist.)

### Loading Saved Tasks
A newly opened session of the Duke program will load tasks that have been saved in the 'data' folder specified under the 'Listing Tasks' feature described above.

### Restoration of Deleted Data
If the data file or folder containing the data file has been deleted halfway through the execution of the program, the program automatically restores the file open the next operation executed (and performs that operation).

### Marking Tasks to be Done / Undone
Allows users to mark a tasks or groups of tasks to be completed, or yet to be completed.

### Deleting Tasks
Allows users to delete tasks from the tasklist.

### Finding Tasks using Keywords
Allows users to find tasks using keywords (including type of task, task description, date / time and state of whether task is done / undone)

### Sorting Tasks
Allows users to sort tasks by alphabetical order of task type, done state, alphabetical order of task description and chronological order of date / time.

### Undo and Redo Features
Allows users to revert a list back to a newer or older state. The number of versions to be reverted may be specified by the user. The version of tasklist that has been reverted to will automatically be saved into the tasklist. All versions of tasklists in the session will exist until the user clears the program's cache or until the program exits.

### Clearing of Cache
Allows users to clear the timeline containing the list of tasks, deleting all versions of tasks stored in the timeline, except for the current active version of the tasklist.

### Generation of Random Tasks
Allows users to randomly generate a list of tasks for demonstration, examples and testing purposes.

### Showing of Help
Users may view the description and usage of any command or group of commands, or view the full list of commands by simply keying in "help".

### Display of Software and Developer Information
Users may view information about the developer and software by keying in "about". Credits to users for external sources of photos labeled to be free for use are also displayed here.

### Data Validation
This Duke program automatically validates input, requiring them to be in the format specified. Precision of validation is such that it checks for the number of days in the month - even for leap years! An error message is displayed to the user in the event that the input data is invalid.

### Detection of Duplicate Tasks
Tasks with the exact same attributes (i.e. task type, description, date and time) as an existing task in the tasklist will not be allowed to be added into the tasklist. This ensures that a user does not accidentally add a duplicate task into the list.

### Cross-Platform Compatibility
This Duke program is designed to work for Mac, Windows and Linux OS. Previous issues with font compatibility across platforms have now been resolved in the latest release of Duke (V2.5). Credits to Ong Bing Jue ([https://github.com/bjhoohaha][https://github.com/bjhoohaha]) for helping to spot and resolve the bug that caused font compatibility issues across platforms.

## Usage

### `about` - Shows information about the developer and this Duke program.

Program displays the developer's photo and a short message from the developer, followed by brief details of the software, as well as credit for photos labeled free for use that have been used in this Duke program.

Requires input to be in the format `about`.

Example of usage: 

`about`

Expected outcome:

```
Hi! I'm Weihong, developer for this implementation of Duke - a task-managing chatbot designed at NUS for educational purposes, with details as follows!

Product Name: Duke
Version: V2.2
Category: Personal Assistant Chatbot
Software Requirements: Java 11; Mac / Linux / Windows OS
Developer: Neo Weihong

This product is free for non-commercial use and its code is publicly available open-source via: https://github.com/whneo97/duke.

Space background by Andy Holmes (https://unsplash.com/photos/LUpDjlJv4_c)
User profile icon by Smashicons (https://www.flaticon.com/authors/smashicons)
Duke profile icon by Freepik (https://www.flaticon.com/authors/freepik)

Hope you'll enjoy trying out this simple program!
```

### `bye` - Instructs the program to exit.

Program displays a goodbye message and waits for 0.9 seconds (for user to read the message) before closing.

Requires input to be in the format `bye`.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`

### `clearcache` - Clear all stored versions of the tasklist (except for the current active version).

Removes all versions of task lists in the timeline used for undo and redo operations, except for the current version. Since this operation cannot be under, after the user's command, the program will prompt for user to confirm by keying in 'CoNfiRMtOclEaR' before proceeding. This operation may be useful if the size of the timeline happens to get too large and slows down other operational procedures. The key will not be recognised on it's own without first  entering 'clearcache'. The process is aborted if input right after 'clearcache' does not match the required key and the user will have to enter 'clearcache' again if he/ she intends to re-initiate the process.

Requires input to be in the format `clearcache`.

Example of usage: 

`clearcache`

Expected outcome:

```
WARNING: THIS ACTION CANNOT BE UNDONE. Once executed, all versions other than the current version of the tasklist will be deleted and the undo / redo command will no longer be able to retrieve them even within this same session.

To confirm clearing of cache, type "CoNfiRMtOclEaR" with the same lettering cases. Otherwise, input any other character or string of text to cancel this operation.
```

User confirmation:
`CoNfiRMtOclEaR`

Expected outcome:
`All versions of task lists in the undo / redo timeline except for the current task has been removed as requested.`

### `deadline` - Adds a deadline into the tasklist.

Adds a deadline into the tasklist, and displays a confirmation message to the user if addition of the deadline into the tasklist is successful, with the new number of tasks in the tasklist.

Requires input to be in the format `deadline [task] /by [DD/MM/YYYY]`, or `deadline [task] /by [DD/MM/YYYY] [HHMM]`, where dates and times (24-hour clock) are valid.

Example of usage: 

`deadline submit presentation slides /by 23/12/2019`

Expected outcome:

```
Got it. I've added this task:
  [D][ ] submit presentation slides (by: 23/12/2019)
Now you have 5 tasks in the list.
```

### `delete` - Deletes a task or multiple tasks from the tasklist.

Deletes a task or groups of tasks from the tasklist, and displays a confirmation message to the user if deletion of the task from the tasklist is successful, with the new number of tasks in the tasklist.

Requires input to be in the format `delete [number representing task in tasklist]` or 
`delete [range1, range2, range3, ...]` or `delete all` to remove all tasks.

Example of usage: 

`delete 6-7, 1`

Expected outcome:

```
Noted. I've removed these tasks:
1.[D][ ] submit report on buying and selling (by: 20/09/2019 2359)
2.[T][ ] buy chocolate
3.[T][ ] buy milk
Now you have 4 tasks in the list.
```

### `done` - Marks a task or multiple tasks from the tasklist to be completed.

Marks a task or groups of tasks from the tasklist to be done, and displays a confirmation message to the user if marking of the task as done in the tasklist is successful.

Requires input to be in the format `done [number representing task in tasklist]` or 
`done [range1, range2, range3, ...]` or `done all` to mark all tasks as done.

Example of usage: 

`done 2, 4-5`

Expected outcome:

```
Nice! I've marked these tasks as done:
1.[E][+] concert (at: 21/02/2019 1500-1700)
2.[D][+] submit report (by: 25/09/2019)
3.[E][+] annual concert (at: 25/02/2020 2030-2230)
```

### `event` - Adds an event into the tasklist.

Adds an event into the tasklist, and displays a confirmation message to the user if addition of the event into the tasklist is successful, with the new number of tasks in the tasklist.

Requires input to be in the format `event [task] /at [DD/MM/YYYY] [HHMM]`, where dates and times (24-hour clock) are valid.

Example of usage: 

`event annual concert /at 21/02/2020 1930-2100`

Expected outcome:

```
Got it. I've added this task:
  [E][ ] annual concert (at: 25/02/2020 2030-2230)
Now you have 5 tasks in the list.
```

### `find` - Find tasks in the tasklist given keywords.


Finds tasks in the tasklist using given keywords specified by the user. Keywords are not case sensitive and could be names of tasks, substrings of it, type of tasks, date in DD/MM/YYYY format, time in HHMM format, whether or not the task is done in the form `done`, `not done`, or `undone`, or   `at` (for events) / `by` (for deadlines).

Requires input to be in the format `find [keyword]`. 

Example of usage: 

`find buy`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][ ] buy milk
2.[T][ ] buy chocolate
3.[D][ ] submit report on buying and selling (by: 20/09/2019 2359)
```

### `help` - Displays commands and their descriptions.

Shows a list of specified commands or all known commands so far, sorted in alphabetical order.

Requires input to be in the format `help` or `help [command1] [command2] etc.`.


Example of usage: 

`help about bye unknown todo`

Expected outcome:

```
bye: Instructs the program to exit.
Requires input to be in the format 'bye'.
eg. bye

todo: Adds a todo into the tasklist.
Requires input to be in the format 'todo [task]'.
eg. todo buy bread

unknown: Unknown command.

Note that all commands are NOT case sensitive.

Can't find what you're looking for?
Input 'help' for the full list of commands known to the this program and information on their usage.

You may also refer to this program's user guide via: http://whneo97.github.io/duke
```

### 

### `list` - Lists all tasks in the tasklist stored.

Displays a list of all tasks stored in the current tasklist. If the tasklist has yet to be sorted, it is by default sorted in chronological order (i.e. newest item is added to the back of the list).

Requires input to be in the format `list`.

Example of usage: 

`list`

Expected outcome:

```
Input 'help' for the full list of commands known to the this program and information on their usage.
Here are the tasks in your list:
1.[T][ ] buy milk
2.[E][ ] concert (at: 21/02/2019 1500-1700)
3.[E][ ] football match (at: 20/09/2019 2300-0100)
4.[D][+] submit report (by: 25/09/2019)
5.[E][ ] annual concert (at: 25/02/2020 2030-2230)
6.[T][ ] buy chocolate
7.[D][ ] submit report on buying and selling (by: 20/09/2019 2359)
```

### `random` - Generates a random list of tasks.

Generates a list of tasks that are randomly generated. Users are required to specify the number of random tasks to generate. This command OVERRIDES the existing list. An overriden list, however, may be retrieved using the 'undo' command.

Requires input to be in the format `random [size of random list]`.

Example of usage: 

`random 5`

Expected outcome:

```
Here are your randomly-generated list of Tasks:
1.[E][ ] rand  8cu7 xZnrw 8h (at: 27/11/4256 0244-1601)
2.[T][+] rand  M7FG VYi
3.[T][ ] rand  sVZDcm nP
4.[E][ ] rand  w0e6 T (at: 20/09/1140 1330-0725)
5.[E][+] rand  COPJ mn (at: 17/02/4833 1119-0405)

To revert back to the previous version of the TaskList, use the 'undo' command or input 'help' for more information on all commands known to Duke.
```

### `redo` - Reverts tasklist to a newer version / newer versions.

Reverts, if possible, the tasklist to the version before the most recent undo command was executed, or executes redo the number of times specified by the user.

Requires input to be in the format `redo` (performs redo once) or `redo [number of times to redo]`.


Example of usage:

`redo 2`

Expected outcome:

```
Redo successful. Current task list is now 2 versions newer than the one before redo was executed.

Input 'list' to see the version of the tasklist we have retrieved and saved for you.
```

### `sort` - Sorts the list based on the specified criteria.

Sorts the list (and saves it) based on the criteria requested for by the user. Criteria include alphabetical order of task type, done state, alphabetical order of task description and chronological order of date / time.

Requires input to be in the format `sort [criteria]`.

List of `[criteria]`: 
* `description`: Sorts the list of tasks by order of task descriptions in alphabetical order.
* `revdescription`: Sorts the list of tasks by order of task descriptions in reversed alphabetical order.
* `type`: Sorts the list of tasks by order of type of task in alphabetical order.
* `revtype`: Sorts the list of tasks by order of type of task in reversed alphabetical order.
* `date`: Sorts the list of tasks by order of date from soonest to latest.
* `revdate`: Sorts the list of tasks by order of date from latest to soonest.
* `id`: Sorts the list of tasks by order of creation from earliest to latest.
* `revid`: Sorts the list of tasks by order of creation from latest to earliest.
* `done`: Sorts the list of tasks by order of done status with done tasks arranged first.
* `undone`: Sorts the list of tasks by order of done status with undone tasks arranged first.

Example of usage:

`sort revdate`

Expected outcome:

```
Sorting successful! Here are the tasks in your list:
1.[T][ ] buy chocolate
2.[T][ ] buy milk
3.[E][ ] annual concert (at: 25/02/2020 2030-2230)
4.[D][+] submit report (by: 25/09/2019)
5.[D][ ] submit report on buying and selling (by: 20/09/2019 2359)
6.[E][ ] football match (at: 20/09/2019 2300-0100)
7.[E][ ] concert (at: 21/02/2019 1500-1700)
```

### `todo` - Adds a todo into the tasklist.

Adds a todo into the tasklist, and displays a confirmation message to the user if addition of the todo into the tasklist is successful, with the new number of tasks in the tasklist.

Requires input to be in the format `todo [task]`.

Example of usage: 

`todo buy bread`

Expected outcome:

```
Got it. I've added this task:
  [T][ ] buy bread
Now you have 8 tasks in the list.
```

### `undo` - Reverts tasklist to an older version / older versions.

Reverts, if possible, the tasklist to the version before the most recent command was executed, or executes undo the number of times specified by the user.

Requires input to be in the format `undo` (performs undo once) or `undo [number of times to undo]`.

Example of usage:

`undo 2`

Expected outcome:

```
Undo successful. Current task list is now 2 versions older than the one before undo was executed.

Input 'list' to see the version of the tasklist we have retrieved and saved for you.
```

### `undone` - Marks a task or multiple tasks from the tasklist as yet to be completed.

Marks a task or groups of tasks from the tasklist to be undone, and displays a confirmation message to the user if marking of the task as undone in the tasklist is successful.

Requires input to be in the format `done [number representing task in tasklist]` or 
`undone [range1, range2, range3, ...]` or `undone all` to mark all tasks as undone.

Example of usage: 

`undone 4`

Expected outcome:

```
Nice! I've marked this task as undone:
[D][ ] submit report (by: 25/09/2019)
```

## Credits

* Space background by Andy Holmes: https://unsplash.com/photos/LUpDjlJv4_c
* User profile icon by Smashicons: https://www.flaticon.com/authors/smashicons
* Duke profile icon by Freepik via: https://www.flaticon.com/authors/freepik
* Font by Andreas Larsen via: https://github.com/larsenwork/Gidole
* Ong Bing Jue (https://github.com/bjhoohaha) for help in spotting and resolving bug that caused font compatibility issues across platforms.


## References

* Scrollbar styling: https://blog.ngopal.com.np/2012/07/11/customize-scrollbar-via-css
* Setting font family: http://java-buddy.blogspot.com/2013/02/load-true-type-font-ttf-in-javafx.html
* Setting font colour: https://stackoverflow.com/questions/24702542/how-to-change-the-color-of-text-in-javafx-textfield
* Maintaining background aspect ratio: https://stackoverflow.com/questions/41559817/how-to-keep-aspect-ratio-of-a-background-image
* Setting scrollpane transparency: https://stackoverflow.com/questions/40253661/javafx-scrollpane-how-set-transparent-background
* Setting background image: https://stackoverflow.com/questions/16630487/correct-way-to-set-the-path-of-a-backgroundimage-in-javafx-css