# User Guide

### What is Shuyuan's Task Manager(STM)?
**STM** is a task manager. This desktop application offers people 
functionalities to create and different types of tasks with specified time and related people;
add and view tasks lists; mark and delete tasks. This application use command line interface; 
this means that you operate the application mainly by typing commands into the text box.

This is what STM looks like:
<br/>![Figure1](UG_GUI.PNG)
<br>*Figure 1. The graphical user interface for STM.*

## Getting started
 You can type your command into text box and view STM's responses in the pane.
 You can also view the conversation history by scrolling up or down.
## Features 
This application offers the following features:
* Add tasks
* Delete tasks
* List tasks
* Mark tasks
* Find tasks

## Commands
Tasks have `event`,`deadline`,`todo` types. 
Each task contains description, time(only for `deadline` and `event`) and 
people.

Each person consists of name, title/relationship and contact number. 
A person can be represented by `name(title)123456`. 
If title or contact number are unknown, they can be omitted. 
Thus, these commands are also applicable: `James`,`James(boss)`,`James()123`. 
### Add tasks
You can use `event`,`deadline`,`todo` commands to add different types of tasks.

#### Add event task
1. Type `event|task description|at some time|person1|person2|...`.
Persons can be omitted. e.g. `event|work|Friday`
2. Now the event task is added into the list and STM returns its information as well as the total number of tasks in the list.
<br/>![Figure2](event_result.PNG)
<br>*Figure 2. Result of event command.*

#### Add deadline task
1. Type `deadline|task description|by some time|person1|person2|...`.
Persons can be omitted. e.g. `deadline|release Duke|Sunday|Shuyuan(student)666`
2. Now the deadline task is added into the list and STM returns its information as well as the total number of tasks in the list.
<br/>![Figure3](deadline_result.PNG)
<br>*Figure 3. Result of deadline command.*

#### Add todo task
1. Type `todo|task description|person1|person2|...`.
Persons can be omitted. e.g. `todo|study|Poorme()123`
2. Now the todo task is added into the list and STM returns its information as well as the total number of tasks in the list.
<br/>![Figure4](todo_result.PNG)
<br>*Figure 4. Result of todo command.*

### Delete tasks
1. Type in `delete|<number of task you want to delete>`. e.g. `delete|5`
2. Now STM shows the deleted task information.
<br/>![Figure5](delete_result.PNG)
<br>*Figure 5. Result of delete command.*

### List tasks
`list` command lists out all the current tasks.
1. Type `list` in text box.
2. You can view the tasks lists and all the task details now.
<br/>![Figure6](list_result.PNG)
<br>*Figure 6. Result of list command.*
 
### Mark tasks
`done` command updates a task status from `-`(not done) to `+`(done).
1. Type in `done|<number of task you want to delete>`. e.g. `done|5`
2. Now STM shows the updated task information.
<br/>![Figure7](done_result.PNG)
<br>*Figure 7. Result of done command.*

### Find tasks
`find` command searches the string in task descriptions.
1. Type in `find|<string you want to search>`. e.g. `find|sleep`
2. Now STM returns all the tasks whose descriptions contain string "sleep".
<br/>![Figure8](find_result.PNG)
<br>*Figure 8. Result of find command.*

### Save history and exit application
`bye` command saves and updates your task list into `.txt` file.
<br/>![Figure9](bye_result.PNG)
<br>*Figure 9. Result of bye command.*

## FAQ
Q: What if I typed some invalid commands?
<br>A: No worries, STM gives you warnings and reminds you where the commands are wrong. 

