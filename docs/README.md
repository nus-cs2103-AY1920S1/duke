# Charlie User Guide

## Features 

1. Adds tasks to Charlie
    - There are mainly 3 types of tasks
        1. Todo task - tasks you need to do
        2. Deadline task - tasks that has a deadline
        3. Event task - event on a certain date you need to attend
1. Tracks tasks you have yet to complete
2. Search for task that are in the list
3. Label task as done
4. Delete tasks

## Usage
To update the chatbot, we use a set of keywords and descriptions (if applicable)

e.g. todo buy bread - creates a todo task with "buy bread" as the description

###Keywords and their functions

1. Add tasks - adds tasks to charlie
    - `todo` description - Lets Charlie know you have a todo task
    - `deadline` description `/by` date* - Lets Charlie know you have a deadline  
    - `event` description `/at` date* - Lets Charlie know you have an event on specified date
    
    *date has to be in the following format "dd/MM/yyyy hhmm"
    ```
    //Example Usage
    todo Buy Bread
    deadline Return Book /by 2/12/2019 1800
    event Attend CS2103T Lecture /at 2/10/2019 1800
    ```
   
2. Show list of tasks
    - `list` - Charlie will list the tasks you have added
    
    ```
    //Example Usage
    list
    
    //Expected Outcome
    Here are the tasks in your list:
    1. [T][✗] Buy bread
    2. [D][✗] Return Book (by: 02 Dec 2019 06:00 PM)
    3. [E][✗] Attend CS2103T Lecture (at: 02 Oct 2019 06:00 PM)  
    ```
3. Delete tasks
    - `delete` number-as-shown-on-list
    
    ```
    //Example Usage
    delete 3
    
    //Expected Outcome
    Noted. I've removed this task:
        [E][✗] Attend CS2103T Lecture (at: 02 Oct 2019 06:00 PM)
    Now you have 2 tasks in the list
    ```
   
4. Find keywords in tasks
    - `find` word-in-task
    
    ```
    //Example Usage
    find Buy
   
    //Expected Outcome
    Here are the tasks in your list:
    1. [T][✗] Buy bread
    ```

Do let me know if you have any feedback.
