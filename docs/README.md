# User Guide

![Screenshot of Duke GUI](Ui.png)

## Features 

### Manage ToDos, Events & Deadlines
- Duke lets you add tasks to your list by using the [`todo`](#todo---add-a-todo), [`deadline`](#deadline---add-a-deadline) or [`event`](#event---add-an-event) commands.
- You can also view your list of tasks by using the [`list`](#list---see-your-tasks) command.
- When you're done with a task, check it off by using the [`done`](#done---mark-a-task-as-done) command.
- If you've made a mistake when entering a task or just want to keep your list short, use the [`delete`](#delete---delete-a-task) command to remove tasks off your list.

### Auto Save
Every time you add or remove a task, Duke automatically saves your list. No need for you to press `Control+S` every few seconds!

### Flexible Search
Duke lets you search through your tasks with as few or as many criteria as you want by using the [`find`](#find---find-a-task) command. See [how to specify the criteria here](#find---find-a-task).

### Fully Usable with Just a Keyboard
Once Duke starts, you can just start entering commands and when you're done with Duke, just close it by entering the [`bye`](#bye---exits-duke) command. All done without using a mouse.

## Usage

### `list` - See your tasks

`list` shows your current ToDos, Events & Deadlines, whether they're done, the description and if applicable, when it's due or when it'll happen.

Example of usage: 

`list`

Expected outcome:

    Here are the tasks in your list:
    1.[T][✓] read book
    2.[D][✘] do homework (by: 30/9/2019 0000)

### `todo` - Add a ToDo

Add a ToDo to your list of tasks.

Example of usage: 

`todo join sports club`

Expected outcome:

    Got it. I've added this task: 
      [T][✘] join sports club
    Now you have 3 tasks in the list.

### `deadline` - Add a Deadline

Add a Deadline to your list of tasks.

Example of usage: 

`deadline return book /by 29/9/2019 0000`

Expected outcome:

    Got it. I've added this task: 
      [D][✘] return book (by: 29/9/2019 0000)
    Now you have 4 tasks in the list.

### `event` - Add an Event

Add an Event to your list of tasks.

Example of usage: 

`event project meeting /at 23/9/2019 1400`

Expected outcome:

    Got it. I've added this task: 
      [E][✘] project meeting (by: 23/9/2019 1400)
    Now you have 5 tasks in the list.

### `done` - Mark a task as done

Marks a task as done, select the task by using the number as shown when using the [`list`](#list---see-your-tasks) command.

Example of usage: 
   
   Let's assume we've finished the "do homework" task, since it's task number two according to [`list`](#list---see-your-tasks), we'll enter the command as such:
   `done 2`

Expected outcome:

    Nice! I've marked this task as done:
      [D][✓] do homework (by: 30/9/2019 0000)
    
### `delete` - Delete a task

Deletes a task from your list. Select the task by using the number as shown when using the [`list`](#list---see-your-tasks) command.

Example of usage: 
   
   Let's assume we want to remove the "do homework" task, since it's task number two according to [`list`](#list---see-your-tasks), we'll enter the command as such:
   `delete 2`

Expected outcome:

    Noted. I've removed this task:
      [D][✓] do homework (by: 30/9/2019 0000)
    Now you have 4 tasks in the list.

### `find` - Find a task

Finds a task from your list based on any number of criteria, listed below. You can use multiple criteria in any order by just adding the desired subcommand to the `find` keyword.

| Criteria subcommand                                 | What it does                                                           | Example(s)                                                       |
|-----------------------------------------------------|------------------------------------------------------------------------|------------------------------------------------------------------|
| <code>/isdone (yes&#124;no)</code>                  | Finds tasks for whether is is done or not                              | `find /isdone yes` or `find /isdone no`                          |
| <code>/type (todo&#124;event&#124;deadline)</code>  | Finds tasks by the specific given type                                 | `find /type todo` or `find /type event` or `find /type deadline` |
| `/before {datetime}`                                | Finds Deadline and Event tasks that are before the given date/datetime | `find /before 30/9/2019` or `find /before 23/9/2019 1600`        |
| `/on {datetime}`                                    | Finds Deadline and Event tasks that are on the given date/datetime     | `find /on 23/9/2019` or `find /on 23/9/2019 1400`                |
| `/after {datetime}`                                 | Finds Deadline and Event tasks that are after the given date/datetime  | `find /after 22/9/2019` or `find /after 23/9/2019 1100`          |
| Any other words                                     | Finds tasks with a description that contains the given words           | `find sports`                                                    |


Example of usage: 
   
   Let's assume we want to find a task that matches all of the following criteria, we'll build the `find` command step by step:

| Step | Criteria                    | Subcommand to add | Subcommand so far                                   |
|------|-----------------------------|-------------------|-----------------------------------------------------|
| 1    | Must be an Event            | `/type event`     | `find /type event`                                  |
| 2    | Must not be done yet        | `/isdone no`      | `find /type event /isdone no`                       |
| 3    | Occurs on 23 Sept 2019      | `/on 23/9/2019`   | `find /type event /isdone no /on 23/9/2019`         |
| 4    | Contains the word "meeting" | `meeting`         | `find /type event /isdone no /on 23/9/2019 meeting` |

At the end of it, we'll enter the command `find /type event /isdone no /on 23/9/2019 meeting`.

Expected outcome:

	Here are the matching tasks in your list:
	1.[E][✘] project meeting (at: 23/9/2019 1400)
    
### `bye` - Exits Duke

Exits Duke and the Duke window should close.

Example of usage: 

`bye`

Expected outcome:

The Duke window closes.
