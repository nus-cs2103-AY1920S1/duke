# User Guide


## Features 
-------------------------------------------------------------------------------------------
### Add Tasks
This allows you to add various tasks such as to-dos, deadlines, and events.
This is to help you keep track of when and what to do.

### List Tasks
This lets you see the tasks on your list at a glance.

### Update Tasks
You can mark tasks as done when you have completed them, and keep track of the status of completion in your task list.

### Delete Tasks
This deletes your task when you want to remove it from the list.

### Find Tasks
You can find tasks from your list suing a keyword.

### Help
This will show you what commands can be used.


## Usage
-------------------------------------------------------------------------------------------
### `todo` - To add a to-do task to the list.

1. Provide the item description after the todo command.

E.g. `todo buy milk and cereal`

Expected outcome:
> ಥ◡ಥ Got it. I've added this task:
> [T][✘] buy milk and cereal
> Now you have 1 tasks in the list

### `event` - To add an event to the list.

Provide the event description, followed by date and time in the format `/at dd/MM/yyyy 0000` (in 24hr).

E.g. `event wedding anniversary dinner /at 30/09/2019 1800`

Expected outcome:
> ಥ◡ಥ Got it. I've added this task:
> [E][✘] wedding anniversary dinner (at: 30-9-2019 18:00)
> Now you have 4 tasks in the list.

### `deadline` - To add a task with deadline to the list.

Provide the deadline description, followed by date and time in the format `/by dd/MM/yyyy 0000` (in 24hr).

E.g. `deadline CS3230 assignment /by 01/10/2019 1159`

Expected outcome:
> ಥ◡ಥ Got it. I've added this task:
> [D][✘] CS3230 assignment (by: 01-10-2019 11:59)
> Now you have 5 tasks in the list.

### `list` - To list down the items on the list.

E.g. `list`

Expected outcome:
> (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ Here are the task(s) in your list:
> 1. [T][✘] buy milk and cereal
> 2. [E][✘] wedding anniversary dinner (at: 30-9-2019 18:00)
> 3. [D][✘] CS3230 assignment (by: 01-10-2019 11:59)

### `done` - To mark a task as done.

Provide the task number of the particular task you have completed and it will be checked to mean it has been completed.

E.g. `done 1`

Expected outcome:
> ' ▽ '  )ﾉ Nice! I've marked this task as done:
> [✓] buy milk and cereal

### `delete` - To delete an item on the list.

Provide the task number of the item you want to delete and it will be removed from the list.

E.g. `delete 1`

Expected outcome:
> (¬‿¬) Noted. I've removed this task:
> [T][✓] buy milk and cereal
> Now you have 2 tasks in the list.

### `find` - To see item(s) containing the keyword from the list.

Provide the keyword after the find command.

E.g. `find assignment`

Expected outcome:
> இ~இ Here are the matching task(s) in your list:
> 1. [D][✘] CS3230 assignment (by: 01-10-2019 11:59)

### `help` - To obtain a list of commands that can be used on Smart Baby.

E.g. `help`

Expected outcome:
> Come, lemme teachu some baby talk (●´ω｀●):
> todo - to add a todo task to the list.
> event - to add an event with date and time to the list.
> deadline - to add a task with deadline to the list.
> list - to see the items on the list.
> done - to mark a completed task as done.
> delete - to remove a task from the list.
> find - to see item(s) containing the keyword from the list.
> bye - to end the session.

### `bye` - To end the session with Smart Baby.

E.g. `bye`

Expected outcome:
> Zzz...sleeping time! ≖‿≖
