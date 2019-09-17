# User Guide for Duke

## Features 

### Adding new tasks 
Duke supports three types of tasks:
* `todo` normal to-do tasks without a specified time. 
* `deadline` a task that needs to be done by a specified time. 
* `event` a task that will take place at a specified time. 

### Listing all existing tasks
You may ask Duke to list down all your existing tasks, both done and undone.

### Deleting an existing task
You may delete an existing task.

### Marking a task as done
You may complete a task and mark it as done.

### Assigning priority level to a task
Duke provides you with the option to mark the priority level of a specific task. There are three levels of priority: `LOW`, `MEDIUM` and `HIGH`. A task can have zero or one priority level associated. 

### Searching for tasks with keywords
You may search for all the tasks containing certain keywords.

### Bidding farewell to Duke
You may quit the application by simply typing `bye` or closing the window using your cursor.

## Usage

### `todo` - add a to-do task
Command: `todo [task description]`
Example: `todo go to school`
Expected outcome:
```
Got it. I've added this task:
[T][x] go to school
Now you have [number of tasks] tasks in the list.
```

### `deadline` - add a deadline task
Command: `deadline [task description] /by [dd/mm/yyyy hhmm]`
Example: `deadline essay /by 20/09/2019 2359`
Expected outcome:
```
Got it. I've added this task:
[D][x] essay (by: Fri, 20 Sep 2019 23:59:00 SGT)
Now you have [number of tasks] tasks in the list.
```

### `event` - add an event task
Command: `event [task description] /at [dd/mm/yyyy hhmm]`
Example: `event birthday celebration /at 15/08/2020 0001`
Expected outcome:
```
Got it. I've added this task:
[E][x] birthday celebration (at: Sat, 15 Aug 2020 00:01:00 SGT)
Now you have [number of tasks] tasks in the list.
```

### `list` - list down all existing tasks
This will show all the tasks with their index, task type, status (done/undone), task description, time (if any), priority level (if any).
Command: `list`
Expected outcome (example):
```
Here are the tasks in your list:
1.[T][x] go to school
2.[D][x] essay (by: Fri, 20 Sep 2019 23:59:00 SGT) [Priority: HIGH]
3.[E][x] birthday celebration (at: Sat, 15 Aug 2020 00:01:00 SGT)
```

### `delete` - delete an existing task
Command: `delete [index of the task]`
Example: `delete 5`
Expected outcome:
```
Noted. I've removed this task:
[E][x] skype meeting (at: Wed, 25 Sep 2019 13:00:00 SGT)
Now you have [number of tasks] tasks in the list.
```

### `done` - mark a task as done
Command: `done [index of the task]`
Example: `done 3`
Expected outcome:
```
Nice! I've marked this task as done:
[E][tick] birthday celebration (at: Sat, 15 Aug 2020 00:01:00 SGT)
```

### `priority` - assign a priority level to a task
You may assign a priority level to a task or change an existing priority level.
Command: `priority [index of the task] [priority level]`
Example: `priority 2 high`
Expected outcome:
```
The following task has been set to Priority HIGH
2. [D][x] essay (by: Fri, 20 Sep 2019 23:59:00 SGT) [Priority: HIGH]
```

### `find` - find tasks by keywords
Command: `find [keywords or phrases]`
Example: `find group project`
Expected outcome:
```
Here are the matching tasks in your list:
5.[T][x] project meeting [Priority: MEDIUM]
6.[T][tick] skype meeting
```

### `bye` - close the application
Command: `bye`
Expected outcome:
```
Bye. Hope to see you again soon!
```
Then the application will automatically close after 1 second.


## Contact 
If you have any queries regarding Duke, feel free to contact @LiuZechu.
