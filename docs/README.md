# __User Guide__
_Duke is a command-line-based task manager._

## _Features_

### 1. Display help
Display the list of commands and their format.

### 2. Add a task
Add a task either of type Todo, Deadline or Event.
* __Todo__ 
  * format: todo <TASK_NAME>
  * Example: 
    * Usage: todo borrow_book     
    * Expected Outcome: 
```
Got it. I've added this task:
[T][-]borrow_book
1 tasks to go
```
   
* __Deadline__ 
  * format: deadline <TASK_NAME> /by <due date/time> 
    * due date/time must be written in the format (d/MM/yyyy) (HHmm).
  * Example: 
    * Usage: deadline return_book /by 31/9/2019 2359     
    * Expected Outcome: 
```
Got it. I've added this task:
[D][-]return_book (by: 30/9/2019 2359)
2 tasks to go
```
  
  
* __Event__ 
  * format: event <TASK_NAME> /at <event date> <start time - end time>
    * event date/time must be written in the format (d/MM/yyyy) (HHmm-HHmm)
  * Example: 
    * Usage: event CS2103_lecture /at 20/09/2019 1200-1400     
    * Expected Outcome:
```
Got it. I've added this task:
[E][-] CS2103_lecture (at: 20/09/2019 1200-1400)
3 tasks to go
```

### 3. Mark a task as done
Mark an existing task as complete. 
Incomplete tasks have a "-" status symbol left of their task name, while completed tasks have a "+" status symbol.

* format: done <index>
  * index indicates the position of the task on the list to mark as done.
* Example
  * done 1
* Expected Outcome: 
```
Nice!
I've marked this task as done:
[T][+]borrow_book
```

### 4. Delete a task
Delete an existing task from the task list.
* format: done <index>
  * index indicates the position of the task on the list to mark as done.
* Example:
  * done 1
* Expected Outcome: 
```
Ok. I've removed the task:
[T][+]borrow_book
2 tasks left
```

### 5. Delete all tasks
Delete every task from the task list and starting fresh.
* format: clear
* Example:
  * clear
* Expected Outcome: 
```
Got it. Starting fresh.
```

### 6. Find tasks using keywords
Find a task or certain tasks using given keyword(s).
* format: search <keyword>
* Example: search book
* Expected Outcome:
```
Here are the matching task(s) in your list:

1.[D][-]return_book (by: 30/9/2019 2359)
```

### 7. Display the task list
Display the full list of tasks.
* format: list
* Example: list
* Expected Outcome:
```
Nice and Light.
Here are the task(s) in your list:

1.[D][-]return_book (by: 30/9/2019 2359)
2.[E][-] CS2103_lecture (at: 20/09/2019 1200-1400)
```

### 8. Set task as recurssive.
Set a task to recur once every specified time period
* format: recur <index>
* Example: recur 2 days 2
*Expected Outcome:
```
Got it. Starting fresh.
```

### 9. Set recurssive task as non-recurssive.
Set a task to ocue only once at the specified time.
* format: revert <index>
* Example: revert 2
* Expected Outcome:
```
Got it.
Setting this event to non-recurring:
2.[E][-] CS2103_lecture (at: 20/09/2019 1200-1400)
```
### 10. exit
Application closes after displaying farewell message.
* format: bye
* Example: bye
* Expected Outcome:
```
See you around...
```
