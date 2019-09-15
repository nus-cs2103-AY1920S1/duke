# User Guide

## Features 

### Feature 1 


## Usage
### `todo` - Adds a To Do task

Example of usage: 

`todo <description>`<br />
`todo read book`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[T][✘] read book
Now you have 1 tasks in the list.
____________________________________________________________
```
### `deadline` - Adds a Deadline task.

Example of usage: 

`deadline <description> /by DD/MM/YYYY HHmm` <br />
`deadline do homework /by 2/12/2019 1800`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[D][✘] do homework (by: Mon, 2 Dec 2019 18:00:00)
Now you have 2 tasks in the list.
____________________________________________________________
```

### `event` - Adds an Event task.

Example of usage: 

`event <description> /at DD/MM/YYYY HHmm` <br />
`event project meeting /at 15/12/2019 1800`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
[E][✘] project meeting (at: Sun, 15 Dec 2019 18:00:00)
Now you have 3 tasks in the list.
____________________________________________________________
```

### `list` - Shows list of tasks.

Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
1.[T][✘] read book
2.[D][✘] do homework (by: Mon, 2 Dec 2019 18:00:00)
3.[E][✘] project meeting (at: Sun, 15 Dec 2019 18:00:00)
____________________________________________________________
```
### `done` - Updates task at index as done.

Example of usage: 

`done <index>` <br />
`done 2`

Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done:
[D][✓] do homework (by: Mon, 2 Dec 2019 18:00:00)
____________________________________________________________
```
### `delete` - Deletes task at index

Example of usage: 

`done <index>` <br />
`delete 3`

Expected outcome:

```
____________________________________________________________
Noted. I've removed this task: 
[E][✘] project meeting (at: Sun, 15 Dec 2019 18:00:00)
Now you have 2 tasks in the list.
____________________________________________________________
```


### `find` - Finds tasks that match specified keyword.

Example of usage: 

`find <description>`<br />
`find book`

Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][✘] read book
____________________________________________________________
```

