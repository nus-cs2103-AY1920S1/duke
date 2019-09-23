# User Guide


## Features 
Duke is a simple and friendly Task Manager for any user who prefers the CLI. Read below to see how you can get Duke to help you better manage your daily tasks! ^-^
### Add Tasks
Duke classifies your tasks into Deadline, Event and Todo.

### Find Tasks
Duke helps you find the task containing a keyword or key phrase.

### Update Tasks
For the respective types of task, you can choose to make changes.

### List Tasks
Duke shows you an overview of all the tasks you have to complete with one simple command.
A task is printed in the format:
`[Task Type(D/E/T)][Done Status(Y/N)] <Task Description> (Time and Date)`

## Usage

### `Deadline` - Adds a Deadline to the Task List

Deadline command format:
`Deadline <Deadline Description> /by DD/MM/YYYY HHMM`

>Example of Usage:`deadline Complete CS1234 Assignment /by 23/9/2019 2359`
>
>Expected outcome: `[D][N] Complete CS1234 /by Mon, 23 Sep 2019 11:59PM`


### `Event` - Adds an Event to the Task List

Event command format:
`Event <Event Description> /at DD/MM/YYYY HHMM`

>Example of Usage: `event Food Festival /at 16/7/2019 1730`
>
>Expected outcome: `[E][N] Food Festival /at Tue, 16 Jul 2019, 05:30PM`

### `Todo` - Adds a Todo to the Task List

Todo command format:
`Todo <Todo Desription>`

>Example of usage: `todo Buy Toothpaste`
>
>Expected outcome: `[T][N] Buy Toothpaste`


### `List` - Lists All Items on the Task List

>Example of usage: 
>`list`
>
>Expected outcome:\
>`Here are the items on your list:`\
>`1. [D][N] Complete CS1234 /by Mon, 23 Sep 2019 11:59PM`\
>`2. [E][N] Food Festival /at Tue, 16 Jul 2019, 05:30PM`\
>`3. [T][N] Buy Toothpaste`

### `Delete` - Deletes Task
Delete command format:
`delete <TaskNo>`

>Example of usage: `delete 2`
>
>Expected outcome: \
>`Noted. I've removed this task:`\
>`2. [E][N] Food Festival /at Tue, 16 Jul 2019, 05:30PM`\
>`Now you have 2 tasks(s) in the list.`

### `Done` - Marks Task as Done 
When a task is not done, the task is marked with `[N]`.
When a task is done, it is marked with `[Y]`.

Done command format:
`done <TaskNo>`

>Example of usage: `done 3`
>
>Expected outcome: \
>`Nice! I've marked this task as done:`\
>`3. [T][Y] Buy Toothbrush`


### `Find` - Finds Task in Task List with Matching Keyword/Phrase

Find command format:
`Find <Keyword/Key Phrase>`

>Example of usage: 
>`find Food`
>
>Expected outcome:\
>`Here are the matching tasks in your list:`\
>`1. [E][N] Food Festival /at Tue, 16 Jul 2019, 05:30PM`

### `Update` - Updates Task Details

Update command format:
`update | <TaskNo> | <UpdateType> | <UpdateInfo> ` 

To update task description, enter:
`update | <TaskNo> | desc | <NewDescription>`

> Example of usage: 
>`update | 3 | desc | Buy Toothbrush`
>
>Expected outcome:\
>`Got it. Your task is now updated as:`\
>`3. [T][N] Buy Toothbrush` 

To update task date*, enter:
`update | <TaskNo> | date | <NewDate in DD/MM/YYYY format>`

>Example of usage: 
>`update | 2 | date | 15/7/2019`
>
>Expected outcome:\
>`Got it. Your task is now updated as:`\
>`2. [E][N] Food Festival /at Mon, 15 Jul 2019, 05:30PM`

To update task time*, enter:
`update | <TaskNo> | time | <NewTime in HHMM format>`

Example of usage: 
`update | 1 | time | 0900`

Expected outcome:\
`Got it. Your task is now updated as:`\
`1. [D][N] Complete CS1234 /by Mon, 23 Sep 2019 09:00AM`

*Only for Deadline and Event items.

### `Help` - Lists Command Format
If you forget the commands when using the application, you can always type `help` to refer to the formats for the above commands.

