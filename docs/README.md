# User Guide
## Introduction

This interactive application helps you manage your tasks.
## Features 

### Feature 1: Adding Todo tasks
You can add **todo** tasks to your list. These tasks do not have time constraints (e.g. deadlines).

### Feature 2: Adding tasks with a deadline
You can add tasks with **deadlines** to your list. 

### Feature 3: Adding scheduled events
You can add a scheduled event that has a starting date/time 
and/or ending date/time to your list.

### Feature 4: Listing all tasks
You can see all the tasks that are currently in your list.

### Feature 5: Marking as done
You can mark a task you have completed as done.

### Feature 6: Deleting from the list
You can delete a task from your list.

### Feature 7: Finding tasks
You can find all the tasks that contains a keyword you are interested in.

### Feature 8: Tagging tasks
You can tag your tasks with a descriptor.

### Feature 9: Finding tags
You can find all the tasks that have been tagged with a particular descriptor.

### Feature 10: Closing the window
You can close the window by typing bye.

## Usage

### `todo` - Adds a *Todo* to the list
Adds a **todo** task to the list. Descriptors can also be added to tag this task.

Format:

`todo [TASK_DESCRIPTION] [(optional)#DESCRIPTOR]`


Example of usage: 

`todo read book #important`

Expected outcome:

```
Got it, I've added this task:
   [T][NOT DONE] read book #important
Now you have 1 task in the list.
```

### `deadline` - Adds a task with a *deadline* to the list
Adds a **deadline** task to the list. Either a due date or due time should be specified.
However, a time should not be specified without a date. Descriptors can also be added to tag this task.


The date should be in the format DD/MM/YYYY. The time should be in the 24 hour notation.

Format:

`deadline [TASK_DESCRIPTION] /by [DUE_DATE] [(optional)DUE_TIME] [(optional)#DESCRIPTOR]`

Example of usage:

`deadline project /by 12/09/2019 2359 #important`

Expected outcome: 

```
Got it. I've added this task:
    [D][NOT DONE] project #important (by: 12th SEPTEMBER 2019, 11.59pm)
Now you have 2 tasks in your list.
```
### `event` - Adds a scheduled *event* to the list
Adds an **event** task to the list. A starting date/time should be specified. 
An ending date and time is optional. However, a time should not be specified without a date.
Descriptors can also be added to tag this task.

The date should be in the format DD/MM/YYYY. The time should be in the 24 hour notation.

Format:

`event [TASK_DESCRIPTION] /at [START_DATE] [(optional)START_TIME] /to 
[(optional)END_DATE] [(optional)END_TIME] [(optional)#DESCRIPTOR]`

Example of usage:

`event meeting /at 12/09/2019 1600 /to 12/09/2019 1700 #important`

Expected outcome: 

```
Got it. I've added this task:
    [E][NOT DONE] meeting #important (at: 12th SEPTEMBER 2019, 4pm to 12th SEPTEMBER 2019, 5pm)
Now you have 3 tasks in your list.
```

### `list` - Shows all tasks in the list
Shows all the tasks that are currently in the list.

Format:

`list`

Expected outcome: 

```
Here are the tasks in your list:
1. [T][NOT DONE] read book #important
2. [D][NOT DONE] project #important (by: 12th SEPTEMBER 2019, 11.59pm)
3. [E][NOT DONE] meeting #important (at: 12th SEPTEMBER 2019, 4pm to 12th SEPTEMBER 2019, 5pm)
```

### `done` - Marks task as done
Marks a specified task as done.

Format:

`done [LIST_INDEX_OF_TASK]`

Examples of usage:

`done 2`


Expected outcome: 

```
Nice! I've marked this task as done:
    [D][DONE] project #important (by: 12th SEPTEMBER 2019, 11.59pm)
```

### `delete` - Deletes a task from the list
Deletes a specified task from the list.

Format:

`delete [LIST_INDEX_OF_TASK]`

Examples of usage:

`delete 2`


Expected outcome: 

```
Noted. I've removed this task:
    [D][DONE] project #important (by: 12th SEPTEMBER 2019, 11.59pm)
Now you have 2 tasks in the list.
```

### `find` - Finds tasks that matches the keyword
Shows all the tasks that includes the matching keyword.

Format:

`find [KEYWORD]`

Examples of usage:

`find book`

Expected outcome: 

```
Here are the matching tasks in your list:
1. [T][NOT DONE] read book #important
```

### `tag` - Tags a tasks with a descriptor
Tags a specified task with a descriptor 

Format:

`tag [LIST_INDEX_OF_TASK] #[DESCRIPTOR]`

Examples of usage:

`tag 2 #cs2103`

Expected outcome: 

```
Got it. I've tagged this task:
    [E][NOT DONE] meeting #important #cs2103 (at: 12th SEPTEMBER 2019, 4pm to 12th SEPTEMBER 2019, 5pm)
```

### `#` - Finds tasks with tag
Shows all tasks that matches the tag.

Format:

`#[DESCRIPTOR]`

Examples of usage:

`#cs2103`

Expected outcome: 

```
These tasks are #cs2103!
    [E][NOT DONE] meeting #important #cs2103 (at: 12th SEPTEMBER 2019, 4pm to 12th SEPTEMBER 2019, 5pm)
```

### `bye` - Closes the application window
Closes the application window through user input.

Format:

`bye`

Expected outcome: 

```
Bye. Hope to see you again soon!
(application closes)
```
