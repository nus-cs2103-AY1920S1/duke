# Duke ChatBot User Guide
Duke is a chatbot that allows users to create and edit their own todo list, via Command-line interface.

## Features 
1.  Keep track of tasks
2.  Add task
3.  Mark task as done
4. Delete task
5. List all tasks

### Keep track of tasks 
The 2 lines at the top of duke.txt file will show the  *number of completed tasks* and *number of uncompleted tasks*:
    Number of tasks completed: ...
    Number of tasks not completed: ...

The status of task can also be viewed in the window using **list** command

### Adding tasks
Adds a new tasks to todo list, doing so will:
* Save new task under duke.txt file and for the next time the chatbot opens.
* New task will appear when the todo list is shown using **list** command.
 
 Task type | Command
 ------------ | -------------
 Todo | **todo** + (description)
 Deadline |  **deadline** + (description)  **/by** + (date in DD/MM/YYY HHMM format)
  Event |  **Event** + (description) +  **/at**  + (date in DD/MM/YYY HHMM format)
  
 ### Mark task as done
  Marks the task as completed. Doing so will :
  * Change the status icon of task when the todo list is shown using **list** command. 
  *  Update the task in duke.txt (from 1 to 0).
  
  Command: **done**  + (index number of task to be marked as done)
  
  ### Delete task
  Deletes the task from todo list by index. Doing so will :
  * Remove task when the todo list is shown using **list** command. 
  *  Remove the task from duke.txt.
  * Update the tasks statistics respectively.
  
  Command: **delete**  + (index number of task to be deleted)
  
  ### List all tasks
  Lists all tasks from todo list, with index. Doing so will list tasks in the following format:
  * **Number of tasks completed: X
       Number of tasks not completed: Y
       Here are the tasks in your list:**
    
    **1.[**(Task type shown)**][** (status icon) **]** (description) **(by/at:** (date in DAY MONTH DATE HH:MM:SS format) **SGT 2019)**
    
    Task status | Status icon
    ------------ | -------------
    Completed | **v**
    Not completed | **x**
    
    Type |  Task type shown
    ------------ | -------------
    Deadline | **D**
    Event | **E**
    Todo | **T**
  
   Command: **list**
 



