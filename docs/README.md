# User Guide

## Features 

### Add a task
Adds a task onto the list

### Delete a task
Delet a task from the list

### Mark task as done
Update the states of the task from incompleted to completed.

### List task
Display all tasks added.

### Find task
Find tasks in the list.

## Usage

### `todo` - Adds a todo to list

Describe action and its outcome.

Example of usage: 

`todo read book`

Expected outcome:
     `Got it. I've added this task:`<br/>
      `[T][✘] read book`<br/>
      `Now you have 1 tasks in the list.`<br/>

### `Deadline` - Adds a Deadline to list

Describe action and its outcome.

Example of usage: 

`deadline read book /by 11/09/2019 1200`

Expected outcome:
     `Got it. I've added this task:`<br/>
      `[D][✘] read book (by: 11/09/2019 1200)`<br/>
      `Now you have 2 tasks in the list.`<br/>
      
### `Event` - Adds a Event to list

Describe action and its outcome.

Example of usage: 

`event read book /at 11/09/2019 1200`

Expected outcome:
     `Got it. I've added this task:`<br/>
      `[E][✘] read book (at: 11/09/2019 1200)`<br/>
      `Now you have 3 tasks in the list.`<br/>
      
### `Done` - Mark the task as done.

Describe action and its outcome.

Example of usage: 

`done 1`

Expected outcome:
     `Nice! I've marked this task as done:`<br/>
      `[✓]read book`<br/>
      
### `delete` - Delete the task from the list.

Describe action and its outcome.

Example of usage: 

`delete 1`

Expected outcome:
     `Noted. I've removed this task:`<br/>
      `[T][✓] read book`<br/>
      `Now you have 2 tasks in the list.`<br/>
      
### `find` - Find the task from the list.

Describe action and its outcome.

Example of usage: 

`find read book`

Expected outcome:
      `1.[T][✘] read book`<br/>
      `2.[D][✘] read book (by: 11/09/2019 1200)`<br/>
      `3.[E][✘] read book (at: 11/09/2019 1200)`<br/>
      
