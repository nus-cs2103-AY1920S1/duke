# User Guide


## Features 
* Quickly adding various tasks into the task list
    * Tasks including Todo, Deadline, and Event
* Easily managing existing tasks
    * Marking a task as done
    * Deleting a task
* Nicely viewing all tasks
* Smartly searching for keyword-contained tasks

## Usage

####Add a todo task

>   `todo [task_description]`
>   * A todo task will be added to the user's task list. Duke will respond with successful adding message.
>   * Example of usage: `todo buy kiwi`

####Add a deadline task

>   `deadline [description] /by [date/month/year hh:mm]`
>   * A deadline task will be added to the user's task list. Duke will respond with successful adding message.
>   * Example of usage: `deadline buy kiwi /by 31/05/2020 12:00`
   
####Add a event task

>    `event [description] /at [date/month/year hh:mm-hh:mm]`
>    * An event task will be added to the user's task list. Duke will respond with successful adding message. 
>    * Example of usage: `event eat kiwi /at 31/05/2020 12:00-13:00`
   
####Mark a task done

>    `done [task_number]`
>    * Mark an incomplete task as done. Duke will inform the user the change of status of that task.
>    * Example of usage: `done 1`
    
####Delete a task

>    `delete [task_number]`
>    * Delete a task from the task list. Duke will inform the user that task is deleted.
>    * Example of usage: `delete 1`

####List all tasks

>    `list`
>    * Duke will show the user all the tasks in his task list.
>    * Example of usage: `list`
    
####Find tasks with a keyword

>    `find [keyword]`
>    * Duke will show the user the filtered tasks which contain the keyword.
>    * Example of usage: `find buy`
    
####Exit the application

>    `bye`
>    * Duke will say goodbye to the user and exit the application.
>    * Example of usage: `bye`

   