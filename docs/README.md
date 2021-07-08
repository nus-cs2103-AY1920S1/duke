# User Guide

## Features 

### Task List
Maintains a list of todos, deadlines and events and allows create, read, delete and search functions.

### Trivia Questions
Maintains a list of trivia questions. The list can be modified and the questions can be asked and answered.

## Usage

### `todo <task>` - Adds a todo

Adds a task of type todo to the list

Example of usage: 

`todo paint picture`

Expected outcome:

```
Got it. I've added this task:
  [T][✘] paint picture
Now you have 3 tasks in the list.
```
### `deadline <task> /by <datetime>` - Adds a deadline

Adds a task of type deadline to the list

Example of usage: 

`deadline return book /by 2/12/2019 1800`

Expected outcome:

```
Got it. I've added this task:
  [D][✘] return book (by: 2/12/2019 1800)
Now you have 4 tasks in the list.
```
### `event <task> /at <datetime>` - Adds an event

Adds a task of type deadline to the list

Example of usage: 

`event project meeting /at 6/13/2019 1400 to 6/8/2019 1600`

Expected outcome:

```
Got it. I've added this task:
  [E][✘] project meeting (at: 6/8/2019 1800 to 6/8/2019 1600)
Now you have 5 tasks in the list.
```
### `list` - Shows task list

Shows task list

Example of usage: 

`list`

Expected outcome:

```
1.[T][✘] eat lunch with Sam
2.[T][✘] borrow book
3.[T][✘] paint picture
4.[D][✘] return book (by: 2/12/2019 1800)
5.[E][✘] project meeting (at: 6/8/2019 1800 to 6/8/2019 1600)
```
### `done <list number>` - Marks task as done

Marks the specified task as done

Example of usage: 

`done 3`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] paint picture
```
### `delete <list number>` - Deletes task

Removes the specified task

Example of usage: 

`delete 3`

Expected outcome:

```
Noted. I've removed this task:
[T][✓] paint picture
```
### `find <search term>` - finds all matching tasks

Searches list for all tasks containing the search term

Example of usage: 

`find book`

Expected outcome:

```
Here are the matching tasks in your list:
2.[T][✘] borrow book
4.[D][✘] return book (by: 2/12/2019 1800)
```
### `trivia` - asks a trivia question

Displays a trivia question and waits for user to enter the correct answer or `give up`

Example of usage: 

`trivia`

Expected outcome:

```
what is the meaning of life?
```
### `trivia new <question> / <answer>` - adds new trivia question

Adds new trivia question and answer to questions list

Example of usage: 

`trivia new what is a skeleton's favourite pizza? / pepper-bone-i`

Expected outcome:

```
cool! i've added this question to the list:
    Q: what is a skeleton's favourite pizza?
    A: pepper-bone-i
```
### `trivia list` - lists all trivia questions

Adds new trivia question and answer to questions list

Example of usage: 

`trivia list`

Expected outcome:

```
here are all the questions you've stored:
1.  Q: what is a skeleton's favourite pizza?
    A: pepper-bone-i
2.  Q: how does nasa throw a party?
    A: they planet
3.  Q: what is a skeleton's favourite pizza?
    A: pepper-bone-i
```
### `trivia delete <list number>` - deletes trivia question

Deletes the specified question

Example of usage: 

`trivia delete 3`

Expected outcome:

```
Noted. I've removed this question:
    Q: what is a skeleton's favourite pizza?
    A: pepper-bone-i
```
### `bye` - ends program

disables send button and text box, shows a close button. Also saves tasks and questions to file to be loaded on next start up.

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
