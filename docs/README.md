# User Guide
## Features
Welcome to Hi!Bro's User Guide. You are able to add tasks and check them by using this useful tool.

### Storage feature
1. Hi!bro can read all tasks from `data/tasks.txt`.
2. save new tasks to the same location.

### Manage feature
1. add different types of task
2. delete a task
3. mark a task as done
4. list all the tasks in the taskList

### Search feature
You can search for tasks which are matched to the keyword.

## Usage
### Adding different types of tasks
#### `todo [taskName]`
Adds a new todo task.

**Example of usage:**

`todo task 1`

**Expected outcome:**
```
Got it. I've added this task:
  [T][✘] task 1
Now you have 1 tasks in the list.
```

#### `deadline [taskName] /by [dd/mm/yyyy HHmm]`
Add a new deadline task.

**Example of usage:**

`deadline task 2 /by 01/01/2009 0900`

**Expected outcome:**
```
Got it. I've added this task:
  [T][✘] task 2 (by: Jan 01, 2009 0900)
Now you have 2 tasks in the list.
```


#### `event [taskName] /at [dd/mm/yyyy HHmm]`
Add a new event task.

**Example of usage:**

`event task 3 /at 02/02/2009 0900`

**Expected outcome:**
```
Got it. I've added this task:
  [T][✘] task 3 (at: Feb 02, 2009 0900)
Now you have 3 tasks in the list.
```

### checking and updating taskList

#### `done [taskNum]`
Mark a task as done.

**Example of usage:**

`done 2`

**Expected outcome:**
```
Nice! I've marked this task as done:
  [T][✓] task 2 (by: Jan 01, 2009 0900)
```

#### `find [keyword]`

Find tasks even if the keyword matches the taskName only partially.

**Example of usage:**

`find as`

**Expected outcome:**
```
Here are the matching tasks in your list:
1. [T][✘] task 1
2. [T][✓] task 2 (by: Jan 01, 2009 0900)
3. [T][✘] task 3 (at: Feb 02, 2009 0900)
```

#### `list` 

List all tasks in `data/tasks.txt`.

**Example of usage:**

`list`

**Expected outcome:**
```
Here are the tasks in your list:
1. [T][✘] task 1
2. [T][✓] task 2 (by: Jan 01, 2009 0900)
3. [T][✘] task 3 (at: Feb 02, 2009 0900)
```


### `delete [taskNum]`

Delete a task in taskList.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
 [T][✘] task 1
Now you have 2 tasks in the list.
```
#### `bye`

Quit the program.

**Example of usage:**

`bye`

**Expected outcome:**

The program closes.
