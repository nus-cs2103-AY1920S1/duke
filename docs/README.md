# User Guide

Duke is a task management and to-do app designed for anyone who wants to get their life in order!

## Features 

### Manage different types of tasks
In Duke, there are 3 types of tasks:
1. Todo
   1. A todo is simply something you wish to do.
1. Deadline
   1. A deadline is a task that contains a deadline so you know when it has to be done!
1. Event
   1. An event is a task that contains an event date, so that you can remember to go for it!

Each task can be added, marked as done, or deleted when you do not need it anymore.
You can also search through your list of tasks using keywords, so that you can narrow down what you're looking for among all the things you have to attend to.

### Undoing actions
If you ever find yourself deleting tasks by accident or performing any unintended action, Duke keeps a complete history of all the actions you have done in each session so that you can undo things all the way back to the beginning!

### Auto-save
Your tasks will always be saved on your computer each time you do anything, so you never have to worry about forgetting to save your tasks!

## Usage

### `todo <description>` - Add a Todo

A todo will be created with the description `<description>`, and added into your task list.

Example of usage: 

`todo Buy cheese and broccoli`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] Buy cheese and broccoli
Now you have 1 tasks in the list.
```

### `deadline <description> /by DD/MM/YYYY HHHH` - Add a Deadline

A deadline will be created with the description `<description>` and deadline `DD/MM/YYYY HHHH`, and added into your task list. Note that the time format is in a 24hr format.

Example of usage: 

`deadline Math assignment /by 19/09/2019 2359`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] Math assignment (by: Thu Sep 19 23:59:00 SGT 2019)
Now you have 2 tasks in the list.
```

### `event <description> /at DD/MM/YYYY HHHH` - Add an Event

An event will be created with the description `<description>` and event date `DD/MM/YYYY HHHH`, and added into your task list. Note that the time format is in a 24hr format.

Example of usage: 

`event Mid Autumn Festival /at 19/09/2019 2359`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] Mid Autumn Festival (at: Thu Sep 19 23:59:00 SGT 2019)
Now you have 3 tasks in the list.
```

### `list` - List all tasks

All your tasks will be listed

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✘] Buy cheese and broccoli
2. [D][✘] Math assignment (by: Thu Sep 19 23:59:00 SGT 2019)
3. [E][✘] Mid Autumn Festival (at: Thu Sep 19 23:59:00 SGT 2019)
```

### `done <task number>` - Mark task as done

The task indexed at `<task number>` will be marked as done

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
2. [D][✔] Math assignment (by: Thu Sep 19 23:59:00 SGT 2019)
```

### `find <keyword>` - Find tasks using a search keyword

All tasks containing `<keyword>` will be listed.

Example of usage: 

`find Autumn`

Expected outcome:

```
Here are the matching tasks in your list:
3. [E][✘] Mid Autumn Festival (at: Thu Sep 19 23:59:00 SGT 2019)
```

### `delete <task number>` - Delete task

The task indexed at `<task number>` will be removed from the list

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
  [D][✔] Math assignment (by: Thu Sep 19 23:59:00 SGT 2019)
Now you have 2 tasks in the list.
```

### `undo` - Undo last action

The latest action will be undone. More specifically, one of these 3 things will happen depending on what the last action was:
1. Added tasks will be removed
2. Deleted tasks will be re-added
3. Tasks marked as done will become undone.

Example of usage: 

`undo`

Expected outcome:

```
Noted. I've added back this task:
  [D][✔] Math assignment (by: Thu Sep 19 23:59:00 SGT 2019)
Now you have 3 tasks in the list.
```