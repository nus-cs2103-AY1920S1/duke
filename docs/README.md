# Duke User Guide

## Features 

### Task management

Duke is a productivity tool for managing your tasks. With flexible support for various types of tasks, Duke exposes an array of useful and robust tools to better manage your everyday agenda. Harness the power of Duke, and never again worry about missing out on important tasks in life.

### Automatic data persistence

All your activity is written automatically to your storage system after every action. Duke safely manages the persistence of your tasks, and you never have to worry about losing your data. 

## Usage

### `list` - List tasks

Lists all existing tasks.

Example of usage: 

`list`

Expected outcome:

    Here are the tasks in your list:
    1.[T][✗] Attend a very important meeting
    2.[D][✗] Wash laundry (by: 2/11/2019 2200)
    3.[E][✗] Justin Bieber concert (at: 15/1/2020 1700)

### `find` - Find within tasks

Searches for matching keywords among all existing tasks.

Example of usage: 

`find justin`

Expected outcome:

    Here are the matching tasks in your list:
    3.[E][✗] Justin Bieber concert (at: 15/1/2020 1700)

### `todo` - Create todo task

Creates a todo task.

Example of usage: 

`todo Attend a very important meeting`

Expected outcome:

    Got it. I've added this task:
      [T][✗] Attend a very important meeting
    Now you have 1 task in the list.

### `deadline` - Create deadline task

Creates a deadline task.

Example of usage: 

`deadline Wash laundry /by 2/11/2019 2200`

Expected outcome:

    Got it. I've added this task:
      [D][✗] Wash laundry (by: 2/11/2019 2200)
    Now you have 2 tasks in the list.

### `event` - Create event task

Creates an event task.

Example of usage: 

`event Justin Bieber concert /at 15/1/2020 1700`

Expected outcome:

    Got it. I've added this task:
      [E][✗] Justin Bieber concert (at: 15/1/2020 1700)
    Now you have 3 tasks in the list.

### `done` - Mark tasks as done

Marks tasks as done.

Example of usage: 

`done 1`

Expected outcome:

    Nice! I've marked this task as done:
    [T][✓] Attend a very important meeting

### `delete` - Delete existing tasks

Deletes an existing task.

Example of usage: 

`delete 3`

Expected outcome:

    Noted. I've removed this task:
      [E][✗] Justin Bieber concert (at: 15/1/2020 1700)
    Now you have 2 tasks in the list.

### `exit` - Exit Duke

Exits the Duke program.

Example of usage: 

`exit`