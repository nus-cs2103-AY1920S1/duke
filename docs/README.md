# Duke v0.2.0 User Guide

## Duke's Amazing Features 

### Task Management
* Duke can store and manage tasks by categorizing the tasks into 3 types;
    * `Todo` is a type of task which you do not need a specific time limit.
    * `Deadline` is a type of task where you need to complete by a specific time.
    * `Event` is a type of task which is happening in the future at a specific time.
* Duke allows the user to display the list of all tasks that the user have inserted inside the program.
It both temporarily stores the task list in the program while running, and also updating the task list in a text file.
* Duke will also load the tasks from the text file which contains all the saved tasks in the beginning of the program.

### CLI (Command Line Interface) Structure
* Duke handles all commands by a formatted sentence structure. Following are the legal commands that are available in Duke.
    1. **Inserting** a task: `[task_type] [task_description] /[time_regex] [dd/MM/YY HH:MM]`
    1. **Displaying** the list of tasks `list`
    1. **Marking** the task as 'Done' `done [task_list_index]`
    1. **Deleting** a specific task from the task list. `delete [task_list_index]`
    1. **Searching** a specific task(s) from the task list by providing single or multiple keyword(s).
        `find [task_key_word]`
    1. **Updating** a certain task from the task list by providing a new task description, time and task list index.
    `update [task_list_index] [new_task_description] [new_time_specified]` (User must at least specify either new task description
    or new time).

## Usage

### `Add task` - Inserting a task(Todo, Event, Deadline) into the task list

`[task_type] [task_description] /[time_regex] [dd/MM/YY HH:MM]`

Example of usage: 
`Todo Buy bread`, `Event project meeting /at 18/09/19 12:00`, `deadline submit report /by 19/09/19 11:59`

Expected outcome:

![event_outcome](/docs/event.PNG)
