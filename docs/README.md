# Duke - User Guide

Duke is a user application serving as a task manager. Add different kinds of tasks, mark them as done, delete them, and stay on the ball with all your work!

<div style="text-align: center">
    <img src="Ui.png" alt="App Screneshot" height="600px" width="400px" />
</div>

## Features 

1. ### Adding tasks 
    Duke allows the addition of different kind of tasks, namely todo, event and deadline.

2. ### Deleting tasks
    Tasks can be deleted from the task list.

3. ### Updating tasks
    Details of the task can also be updated in the task list.

4. ### Listing tasks
    Duke can display a list of all tasks, along with the relevant types and dates.

5. ### Marking tasks as done
    Tasks can be marked as done in the task list.

6. ### Finding tasks
    Tasks can be filtered by certain keywords.

## Usage

### `todo <description>` - Adds a todo task with the relevant description.

#### Example of usage: 

    todo borrow book

#### Expected outcome:

    Got it: I've added this task: 
    [T][X] borrow book 
    Now you have 1 task in the list 

### `event <description> /at yyyy-MM-dd HH:mm` - Adds an event with the relevant description and date.

#### Example of usage: 

    event read book /at 2019-12-31 23:59

#### Expected outcome:

    Got it: I've added this task:
    [E][X] borrow book (at: 2019-12-31 23:59)
    Now you have 2 task(s) in the list

### `deadline <description> /by yyyy-MM-dd HH:mm` - Adds a deadline with the relevant description and date.

#### Example of usage: 

    deadline return book /by 2019-12-31 23:59

#### Expected outcome:

    Got it: I've added this task:
    [D][X] borrow book (by: 2019-12-31 23:59)
    Now you have 3 task(s) in the list

### `find <keyword>` - Finds any task with contains the relevant keyword in its description.

#### Example of usage:

    find borrow

#### Expected outcome:

    Here are the tasks in your list:
    1. [T][X] borrow book

### `update <task_number>` - Uppdates task at the provided number in the task list.

#### Example of usage:

    >> update 1

    Okay. Updating this task:
    [T][X] borrow book

    Please use one of these options to update:
    1. name <new_name>
    2. date <new_date>
    3. both <new_name> <new_date>

    >> name HAHAHAHA

    Okay. Here's the updated task:
    [T][X] HAHAHAHA

### `delete <task_number>` - Deletes task at the provided number in the task list.

#### Example of usage:

    delete 1

#### Expected outcome:

    Noted. I've removed this task:
    1. [T][X] borrow book
    Now you have 2 task(s) in the list

### `done <task_number>` - Marks task at the provided number in the list as done.

#### Example of usage:

    done 1

#### Expected outcome:

    Nice!. I've marked this task as done:
    1. [T][v] borrow book

### `list` - Lists all tasks in the list.

#### Example of usage:

    list

#### Expected outcome:

    Here are the tasks in your list:
    1. [T][X] borrow book
    2. [E][X] read book (at: <date>)
    3. [D][X] return book (by: <date>)

### `bye` - Exits the program.

#### Example of usage:

    bye

