# User Guide

## Features 
Duke is a task management tools and you can establish and modify tasks in duke conveniently. 
DUke is basically CLI and if you can type fast you can get a better use experience than GUI 
tools. 

### Get help
You can get help in the Duke.
### Create task
You can create 3 different types of tasks in duke, which are `event`, `deadline`, and `todo`. 
Each task have 4 features, which are type, stage, content and time(optional).
### Delete task
You can delete a current task from your task list by task number.
### List all current task
You can list all current task with all information of the task.
### Find task
You can find tasks by their content
### Done task
You can mark tasks as done

### 
## Usage

### 1. `help` - to find help in Duke
The format of the command is `help`

Example of usage: 

`help`

Expected outcome:

`Welcome to Duke!……` etc.

### 2. `event` - to add a event in duke
The format of the command is `event <event content> /at <time in dd-mm-yyyy-time>`

Example of usage:

`event attend lecture /at 01-01-1970-1200`

Expected outcome:

```$xslt
Got it. I have added this task:
[E][✗]attend lecture (at:01-01-1970-1200)
Now you have 1 tasks in the list.
```

### 3. `dealine` - to add a deadline in duke
The format of the command is `deadline <deadline content> /by <time in dd-mm-yyyy-time>`

Example of usage:

`deadline submit assignment-0 /by 01-01-1970-1200`

Expected outcome:
```$xslt
Got it. I have added this task:
[D][✗]submit assignment-0 (by:01-01-1970-1200)
Now you have 1 tasks in the list.
```
### 4. `todo` - to add a todo in duke
The format of the command is `todo <todo content> /by <time in dd-mm-yyyy-time>`

Example of usage:

`todo buy fruits /by 01-01-1970-1200`

Expected outcome:
```$xslt
Got it. I have added this task:
[T][✗]buy fruits (by:01-01-1970-1200)
Now you have 1 tasks in the list.
```
### 5. `list` - to list all current tasks in duke
The format of the command is `list`.

Example of usage:

`list`

Expected outcome:
```$xslt
Here are the tasks in your list；
1.[E][✗]attend lecture (at:01-01-1970-1200)
2.[D][✗]submit assignment-0 (by:01-01-1970-1200)
3.[T][✗]buy fruits (by:01-01-1970-1200)
```

### 6. `delete` - to delete a task in duke by task number
The format of the command is `delete <task number>`
or `delete all` to delete all the task in duke list.

Example of usage:

`delete 1`

Expected outcome:
```$xslt
Noted. I've removed this task:
[E][✗]attend lecture (at:01-01-1970-1200)
Now you have 2 tasks in the list.
```

### 7. `done` - mark a task in duke as done by task number
The format of the command is `done <task number>`

Example of usage:

`done 2`

Expected outcome:
```$xslt
Nice! I have marked is task as done:
[T][✓]buy fruits (by:01-01-1970-1200)
```


### 8. `find` - to find a task in duke by content
The format of the command is `find <keyword>`

Example of usage:

`find buy`

Expected outcome:
```$xslt
Here are the matching tasks in our list:
2.[T][✓]buy fruits (by:01-01-1970-1200)
```

### 2. `bye` - to be prepared to exit duke 
The format of the command is `bye`

Example of usage:

`bye`

Expected outcome:
```$xslt
Bye.
Hope to see you again soon!
```