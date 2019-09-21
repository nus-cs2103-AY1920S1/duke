# User Guide
> Duke is a chatbot that helps us to keep track of our tasks on hand. :simple_smile:

## Task Overview 
> There are 3 types of tasks offered by Duke.
### `Todo`  
* Tasks without any date/time attached to it.
* _eg. buy food_
### `Deadline`  
* Tasks that need to be done before a specific date/time.
* _eg. assignment by 29/12/2019 2359_
### `Event`  
* Tasks that start at a specific time.
* _eg. anniversary at 31/12/2019 0000_

## Usage

### `Add` 
* Adds a task into our database. 

    Example of usage: 

    `todo buy food`
    
    `deadline assignment /by 29/12/2019 2359`
    
    `event anniversary /at 31/12/2019 0000`

    Expected outcome:
    ```
    Got it. I've added this task:
      [T][✘] buy food
    Now you have 1 tasks in the list.
    ```
    ```
    Got it. I've added this task:
      [D][✘] assignment (by: 29th Sep 2019, 11.59pm) 
    Now you have 2 tasks in the list.
    ```
    ```
    Got it. I've added this task:
      [E][✘] anniversary (at: 31st Dec 2019, 12.00am)
    Now you have 3 tasks in the list.
    ```
  
### `Delete` 
* Delete a task from our database using the task number. 

  Example of usage: 

  `delete 1`

  Expected outcome:
  ```
  Noted. I've removed this task:
    [T][✘] buy food
  Now you have 2 tasks in the list.
  ```

### `Done` 
* Marks a task from our database as done using the task number. 

  Example of usage: 

  `done 1`

  Expected outcome:
  ```
  Nice! I've marked this task as done:
    [D][✔] assignment (by: 29th Sep 2019, 11.59pm) 
  ```

### `List` 
* List all the tasks in our database. 

  Example of usage: 

  `list`

  Expected outcome:
  ```
  Here are the tasks in your list:
    1. [D][✔] assignment (by: 29th Sep 2019, 11.59pm) 
    2. [E][✘] anniversary (at: 31st Dec 2019, 12.00am)
  ```
  
### `Find` 
* Finds all the tasks containing the keyword in our database. 

  Example of usage: 

  `find ann`

  Expected outcome:
  ```
  Here are the matching tasks:
    2. [E][✘] anniversary (at: 31st Dec 2019, 12.00am)
  ```

### `Bye` 
* Exit Duke. 

  Example of usage: 

  `bye`

  Expected outcome: Program closes.

  
