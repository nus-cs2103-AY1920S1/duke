# User Guide

## Features 

![duke image](./Ui.png)

**Duke** is a personal chat-bot assistant. So what can he do?

#### Remember your tasks
Duke is able to remember the different tasks you have input. These include tasks that you
have todo, or tasks with a deadline or a timing. He is able to list out all the tasks
you have available.

#### Finish a Task
Duke has the ability to track which tasks are finished or not. Simply tell him to
mark it as finished and he will remember that.

#### Delete a Task
If you are finished with a task and don't need to look at it anymore, Duke is able to
delete the task from his memory. Just give the command!

#### Find a Task
Sometimes your list of tasks just gets too large. Duke is able to find any task from
his memory. Just give him a search term and he will find it for you.

## Usage

> Note! `>>` indicates user input.

---
<br>

### `help`

Displays a helpful list of commands and formatting in-app.

```
>> help

Here are a list of commands you can try:

list        Show list of tasks
todo        [DESCRIPTION]
deadline    [DESCRIPTION] /by [TIME]
event       [DESCRIPTION] /at [TIME]
done        [NUMBER]
delete      [NUMBER]
find        [SEARCH STRING]

[TIME] :    [dd/mm/yyyy hh:mm]
```
---
<br>

### `todo [DESCRIPTION]`

Creates a new `todo` task, a task without a specified deadline or timing.

```
>> todo say hello!

Got it. I've added this task:
[T][0] say hello!
Now you have 1 tasks in the list
```

---
<br>

### `deadline [DESCRIPTION] /by [TIME]`

Creates a new `deadline` task, a task that has a specified deadline.

`[TIME]` is of the format `dd/mm/yyyy hh:mm` where `hh:mm` are in
**24-hr format**.

```
>> deadline write email to vendor /by 20/09/2019 23:59

Got it. I've added this task:
[D][0] write email to vendor (by: 20 Sep 2019 11:59 PM)
Now you have 2 tasks in the list
```

---
<br>

### `event [DESCRIPTION] /at [TIME]`

Creates a new `event` task, a task that has a timing of when the event will
occur.

`[TIME]` is of the format `dd/mm/yyyy hh:mm` where `hh:mm` are in
**24-hr format**.

```
>> event group presentation /at 17/09/2019 14:00

Got it. I've added this task:
[E][0] group presentation (at: 17 Sep 2019 02:00 PM)
Now you have 3 tasks in the list
```

---
<br>

### `list`

Shows the current list of tasks.

```
>> list

Here are the tasks in your list:
1. [T][0] say hello!
2. [D][0] write email to vendor (by: 20 Sep 2019 11:59 PM)
3. [E][0] group presentation (at: 17 Sep 2019 02:00 PM)
```

---
<br>

### `done [NUMBER]`

Finishes a task when it is completed.

`[NUMBER]` follows the task number shown by `list`.
Duke will know if you try to `done` a task that is not in the list.

```
>> done 1

Nice! I've marked this task as done:
[T][1] say hello!

>> list

Here are the tasks in your list:
1. [T][1] say hello!
2. [D][0] write email to vendor (by: 20 Sep 2019 11:59 PM)
3. [E][0] group presentation (at: 17 Sep 2019 02:00 PM)
```

---
<br>

### `delete [NUMBER]`

Deletes a task from the list.

`[NUMBER]` follows the task number shown by `list`. Duke
will know if you try to `delete` a task that is not in the list.

```
>> delete 1

Noted. I've removed this task:
[T][1] say hello!
You now have 2 tasks in the list.

>> list

Here are the tasks in your list:
1. [D][0] write email to vendor (by: 20 Sep 2019 11:59 PM)
2. [E][0] group presentation (at: 17 Sep 2019 02:00 PM)
```

---
<br>

### `find [SEARCH-TERM]`


Finds any task that matches the given search string.

The search string can have a name, date, etc.

```
>> find email

Here are the matching tasks in your list:
1. [D][0] write email to vendor (by: 20 Sep 2019 11:59 PM)

>> find Sep

Here are the matching tasks in your list:
1. [D][0] write email to vendor (by: 20 Sep 2019 11:59 PM)
2. [E][0] group presentation (at: 17 Sep 2019 02:00 PM)

>> find 17 Sep

Here are the matching tasks in your list:
2. [E][0] group presentation (at: 17 Sep 2019 02:00 PM)
```
