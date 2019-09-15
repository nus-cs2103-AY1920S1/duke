# User Guide

## Features 

### Task Adding
Allows the addition of tasks to a list that will be stored on your local list for persistent usage.

## Usage

### `todo taskname` - todo task
Adds a standard task item to the todo list.
### `deadline taskname /by dd/mm/yyyy hhmm` - deadline task
Adds a deadline task to the todo list.
### `event eventname /by dd/mm/yyyy hhmm` - event task
Adds a event task to the todo list.

Example of usage: 

```
todo CS2105 Assignment 1
```

Expected outcome:

```
Got it. I've added this task:
[T} ✘ CS2105 Assignment 1
Now you have X tasks in your list.
```


### Listing tasks
List all existing tasks in the list. 

## Usage

### `list` - List all tasks

Example of usage: 

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1. [T} ✘ CS2105 Assignment 1
2. [E] ✘ Mid Autumn Festival /at 14/09/2019 1900
```


### Marking Task as Done
Mark a task as done in the list.

## Usage

### `done n` - Marks the n-th task in the list as done

Example of usage: 

```
done 1
```

Expected outcome:

```
Nice! I've marked this task as done:
[T} ✓ CS2105 Assignment 1
```


### Listing tasks
List all existing tasks in the list. 

## Usage

### `list` - List all tasks

Example of usage: 

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1. [T} ✘ CS2105 Assignment 1
2. [E] ✘ Mid Autumn Festival /at 14/09/2019 1900
```


### Help Menu
Shows all available commands Duke is capable of.

## Usage

### `help` - List all commands and the argument format

Example of usage: 

```
help
```

Expected outcome:

```
List of commands:

bye:
Exits from the program

list:
List all existing tasks

help:
Prints the list of commands

done n:
Marks the n-th task on the list as done

delete n:
Deletes the n-th task on the list

todo taskName :
Adds a new Todo task with the given "taskName".

event taskName /at DD/MM/YYYY HHmm :
Adds a new Event task with the deadline in the given format.

deadline taskName /by DD/MM/YYYY HHmm :
Adds a new Deadline task with the deadline in the given format.

undo:
Undoes the most recent action.

find keyword :
Returns a list of task with names containing the "keyword".

sort category r:
Sorts and returns the list of tasks. Category can be one of "name", "deadline", "type", "status".
Optional argument "r" sorts list in reverse order.
```







# About Duke

Duke is a simple chatbot that helps you track your todo list. Relying on JavaFX GUI, it provides an 
interactive and intuitive user experience that makes the monitoring of task status convenient and 
easy to handle.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 
See deployment for notes on how to deploy the project on a live system.

### Prerequisites

JDK 11 required. Go to Environment Variables and set JAVA_HOME variable to the address where JDK software is located.

## Deployment

Simply download the Jar file and run it for instant usage.

## Built With

* [Gradle](https://gradle.org/) - Dependency Management

## Authors

* **Gary Lim** - *Initial work* - [Jeffry Lum](https://github.com/j-lum)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.


## Acknowledgments

* A big thank you to Jeffry Lum our most respected and honourable TA who helped prepare the entire Duke package 
for our learning. It was such a life-changing and eye-opening experience. I truly can't imagine a world without 
Jeffry Lum.