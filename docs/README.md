# User Guide

## Features 
OwlBook is an task manager that stores and manages the tasks that you are required to complete. OwlBook is great
for users who prefer using a Command Line Interface (CLI) while keeping some Graphical User Interface (GUI). It 
has a simple yet unique design that makes your experience using it smooth!

### Feature 1 
Adds a task to your task list.

## Usage

### `todo` or `deadline` or `event` - Adds a task to your task list.

Describe action and its outcome.

_____________
Command  |  Arguments | Description |

`todo` | `description` | adds a todo task to with `description` to your task list.
`deadline` | `description` /by `dd/mm/yyyy` | adds a deadline task with `description` and due date by `dd/mm/yyyy` to your task list.
`event` | `description` /at `dd/mm/yyyy` `time` | adds an event with `description` at `dd/mm/yyyy`, `time` in 24h clock representation to your task list.
_____________

Example of usage: 

`todo wash toilet`

Expected outcome:

   
    ____________________________________________________________
     Got it. I've added this task:
       [T][-] wash toilet
     Now you have 1 tasks in the list.

    ____________________________________________________________
    
### Feature 2 
Deletes a task to your task list.

## Usage

### `delete <tasknumber>` - Deletes a task with <tasknumber> in your task list.

Describe action and its outcome.

_____________
Command  |  Arguments | Description |

`delete` | `tasknumber` | deletes task with `tasknumber` in your task list.
_____________

Example of usage: 

`todo wash toilet`
`delete 1`

Expected outcome:

    ____________________________________________________________
     Noted. I've removed this task:
       [T][-] wash toilet
     Now you have 0 tasks in the list.
    ____________________________________________________________
    
### Feature 2 
Find tasks in your task list.

## Usage

### `find <taskname>` - Retrieves all tasks with names that contain `taskname`.

Describe action and its outcome.

_____________
Command  |  Arguments | Description |

`find` | `taskname` | Retrieves all tasks with names that contain `taskname`
_____________

Example of usage: 

`todo wash toilet`
`find wash`

Expected outcome:

     ____________________________________________________________
      Here are the matching tasks in your list:
      1.  [T][-] wash toilet
     ____________________________________________________________

### Feature 3 
View list and mark task as done

## Usage

### `list` - Displays your task list.

### `done` - Marks a task as done.

Describe action and its outcome.

_____________
Command  |  Arguments | Description |

`list` | none | Displays all of your tasks.

`done` | `tasknumber` | Marks task with `tasknumber` as done.
_____________

Example of usage: 

`todo wash toilet`
`done 1`

Expected outcome:

    ____________________________________________________________
     Nice! I've marked this task as done:
       [T][+] wash toilet
    ____________________________________________________________
 
 ### Feature 4 
 Help for users who are unsure of how to use the application.
 
 ## Usage
 
 ### `help` - Shows a table of commands and its parameters for our application.
 
 
 Describe action and its outcome.
 
 _____________
 Command  |  Arguments | Description |
 
 `help` | none | Displays help information.
 
 _____________
 
 ### Feature 5 
 Undo.
 
 ## Usage
 
 ### `undo` - Undo the previous command that made changes to your task list.
 
 Describe action and its outcome.
 
 _____________
 Command  |  Arguments | Description |
 
 `undo` | none | undo the previous command.

 _____________
 
 Example of usage: 
 
 `todo wash toilet`
 `undo`
 `list`
 
 Expected outcome:
 
    Got it! I've undo the previous command.
    ____________________________________________________________
    Here are the tasks in your list:

    ____________________________________________________________

 
 


