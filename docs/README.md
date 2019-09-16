# User Guide
Welcome to Duke's User Guide.

![Duke](Ui.png)

## Features 
These are the available features to assist you while using Duke. 

### Add
This feature allow the users to add 3 different kind of tasks into the list as follows:
1. Todo
2. Deadlines
3. Events

### Done
This feature will set the indicated task to done, indicated with a "O".

### Delete
This feature will delete the indicated task.

### List
This feature will list all tasks currently in the list.

### Find
This feature will find the indicated keyword in the list.

### Sort
List will be sorted in this order Todo > Deadline > Event

## Usage

### `todo/deadline/event`
Adds task into the list

Examples of usage:

`todo Add more pictures into OP1 slides`

`deadline Ready OP1 presentation slides /by 11/10/2019 2359`

`event OP1 Meeting presentation /at 12/10/2019 0800`


Expected outcome:

`[T][X] Add more pictures into OP1 slides added into list`

`[D][X] Ready OP1 presentation slides (by: 11/10/2019 2359) added into list`

`[E][X] OP1 Meeting presentation (at:12/10/2019 0800) added into list`

---

### `done`
Marks the task as done

Examples of usage:

`done 1`

Expected outcome:

`Duke will mark task 1 as done. Changing [X] to [O]`

---

### `delete`
Deletes the indicated task.

Examples of usage:

`delete 1`

Expected outcome:

`Duke will delete task 1`

---

### `list`
List all the task that are currently in the list.

Examples of usage:

`list`

Expected outcome:

`All tasks in list will be listed out, with all of the information provided`

---

### `find`
List all the task that are currently found by the keyword provided.

Examples of usage:

`find slides`

Expected outcome:

`All tasks that are found in list will be listed out, with all of the information provided`

---