#Duke User Guide
Duke is an interactive chat-box style task manager which allows you to conveniently create and manage day
to day tasks primarily to-dos, events and deadlines in a simple and user-friendly environment.  

## Features 

### Task Creation
Duke allows you to easily create different tasks such as to-dos, events and deadlines which are then
stored on the system.

### Task Management
When certain tasks have been completed, Duke allows you to mark them as done thereby
allowing you to keep better track of your pending items.

### Task Deletion
Duke allows you to delete redundant tasks so you see only what you want to see.

### Task Search 
In case you have a long list of tasks, Duke will allow you to search tasks you are looking for
based on a particular keyword or even just parts of words.
  
## Usage

###1. Todo Creation
### `todo [todo description]`

Type and pass this command to Duke to create and store a new todo task. The todo's description
will be as specified by you.

Example of usage: 

`todo finish math homework`

Expected outcome:

```
Got it. I've added this task:
    [T][✗] math homework
Now you have 1 tasks in your list.
```


### 2. Task Creation - Deadline

### `deadline [deadline description] /by [DD/MM/YYYY] [hhmm]`

Type and pass this command to Duke to create and store a new deadline task. The deadline's description
and completion date and time will be as specified by you. To create a deadline successfully, follow
the above syntax exactly with the time being specified in 24h format.

Example of usage: 

`deadline get ready for the party /by 2/12/2019 1800`

Expected outcome:

```
Got it. I've added this task:
    [D][✗] get ready for the party (by: 2nd of December, 2019, 6pm)
Now you have 2 tasks in your list.
```
### 3. Task Creation - Event

### `event [event description] /at [DD/MM/YYY] [hhmm-hhmm]`

Type and pass this command to Duke to create and store a new event task. The event's description
and duration date and time will be as specified by you. To create an event successfully, follow
the above syntax exactly with the time being specified in 24h format.

Example of usage: 

`event party at school /at 4/12/2019 1800-2230`

Expected outcome:

```
Got it. I've added this task:
    [E][✗] party at school (at: 4th of December, 2019, 6pm-10.30pm)
Now you have 3 tasks in your list.
```
### 4. List
List down all the tasks stored in Duke.

### `list`

All the tasks stored within Duke will be shown by Duke in a list form.

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✗] math homework
2. [D][✗] get ready for the party (by: 2nd of December, 2019, 6pm)
3. [E][✗] party at school (at: 4th of December, 2019, 6pm-10.30pm)
```

### 5. Task Deletion
Delete any task stored in Duke which you no longer require or want to see.

### `delete [task index]`

Deletes the task as specified by the index you type in the command.

Example of usage: 

`delete 2`

Expected outcome:

````
Noted. I've removed this task:
[D][✗] get ready for the party (by: 2nd of December, 2019, 6pm)
Now you have 2 tasks in the list.
````
### 6. Mark task as Done
Mark any task stored in Duke as done after you have completed it.

### `done [task index]`

Marks the task as specified by the index you type in the command as done. 

Example of usage: 

`done 2`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] math homework
```
### 7. Search
Search Duke for any task based on a keyword.

### `find [search query]`

Duke will search for every task that matches the query you type, either fully or partially.

Example of usage (full word): 

`find homework`

Expected outcome:
```
1. [T][✓] math homework
```

Example of usage (partial word): 

`find scho`

Expected outcome:
```
1. [E][✗] party at school (at: 4th of December, 2019, 6pm-10.30pm)
```