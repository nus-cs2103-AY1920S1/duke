# User Guide for Duke

## Features 

### One-Stop Companion
With Duke as your companion, you can practically live your life without
a ~~girlfriend~~ phone. If you need to keep track of all your todo, events,
and deadlines, just ask Duke for help. You can ask Duke to add tasks, remove
tasks, list tasks, and even filter tasks by keywords!

He is always there for you :kissing_heart:

### Dark mode
What's more to say? It's dark mode :new_moon_with_face:

_Inspired by [Solarized](https://ethanschoonover.com/solarized/)._

![Screenshot of Duke](Ui.png)



## Usage

### `todo` - Add todo

_Add a todo._

Example of usage: 

`todo <description>`

`t <description>`

Expected outcome:

```
Got it. I've added this task:
[T][✗] Buy coke (by: 13/09/2019 1550)
Now you have 1 tasks in the list.
```

---

### `event` - Add event

_Add an event._

Example of usage: 

`event <description> /at <d/M/yyyy HHmm>`

`e <description> /at <d/M/yyyy HHmm>`

Expected outcome:

```
Got it. I've added this task:
[E][✗] CS2103T lecture (by: 13/09/2019 1600)
Now you have 2 tasks in the list.
```

---

### `deadline` - Add deadline

_Add a deadline._

Example of usage: 

`deadline <description> /by <d/M/yyyy HHmm>`

`d <description> /by <d/M/yyyy HHmm>`

Expected outcome:

```
Got it. I've added this task:
[D][✗] CS2103T assignment (by: 22/09/2019 2359)
Now you have 3 tasks in the list.
```

---

### `list` - List all tasks

_Show a list of all tasks._

Example of usage: 

`list`

`ls`

Expected outcome:

```
[T][✗] Buy coke (by: 13/09/2019 1550)
[E][✗] CS2103T lecture (by: 13/09/2019 1600)
[D][✗] CS2103T assignment (by: 22/09/2019 2359)
```

---

### `find` - Find tasks with keyword

_Find all tasks that contains a particular keyword._

Example of usage: 

`find <keyword>`

Expected outcome:

```
[E][✗] CS2103T lecture (by: 13/09/2019 1600)
[D][✗] CS2103T assignment (by: 22/09/2019 2359)
```

---

### `done` - Mark task as done

_Mark a specific task as done._

Example of usage: 

`done <id>`

Expected outcome:

```
Nice! I've marked this task as done:
[D][✓] CS2103T assignment (by: 22/09/2019 2359)
```

---

### `delete` - Delete a task

_Delete a specific task._

Example of usage: 

`delete <id>`

`rm <id>`

Expected outcome:

```
Noted! I've removed this task:
[D][✓] CS2103T assignment (by: 22/09/2019 2359)
Now you have 2 tasks in your list.
```