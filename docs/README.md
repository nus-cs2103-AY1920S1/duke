# Charlie User Guide

## Features 

1. Tracks tasks you have yet to complete
2. Search for task that are in the list
3. Label task as done
4. Delete tasks

## Usage
To update the chatbot, we use a set of keywords and descriptions (if applicable)

e.g. todo buy bread - creates a todo task with "buy bread" as the description

Certain keywords and their functions

1. Add tasks - change description and date accordingly
    - todo description - Lets Charlie know you have a todo task
    - deadline description "/by" date* - Lets Charlie know you have a deadline  
    - event description "/at" date* - Lets Charlie know you have an event on specified date
    
    *date has to be in the following format "dd/MM/yyyy hhmm"
   ```
    //Usage examples
    todo buy bread
    deadline return book /by 2/12/2019 1800
    event attend CS2103T lecture /at 2/10/2019 1800
    ```
2. Show list of tasks
    - list - Charlie will list the tasks you have added
    ```
    //Usage examples
    list
    //Shows 
    Here are the tasks in your list:
    1. [T][✗] Buy bread
    2. [D][✗] Return Book (by: 02 Dec 2019 06:00 PM)
    3. [E][✗] Attend CS2103T Lecture (at: 02 Oct 2019 06:00 PM)
   
    ```
3. Delete tasks
    - delete "number on tasklist"
    
### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
