# User Guide for Rori

**Rori** is a Task Bot with unique personality that will records tasks for you. It is capable of storing tasks as a todo, deadline or event. This includes completion, deletion and finding of any task of your given choice.

## Usage

You can start up **Rori** by running up Rori.jar file, or subsequently you could clone the repo and run on the root folder using:
```
gradlew run
```

### Prerequisite

You would require JRE or JDK set up on your local machine to start up **Rori**.

## Features 

There are several features which **Rori** is capable of. That includes:

### Storing of Tasks

**Rori** is capable of storing your information into a .sav file located on root folder of the .jar file, or root folder folder of project itself. This information can be retrieved even if you close the program halfway through using it.

### Showing Tasks

**Rori** can display the list of task you have - both completed and incompleted tasks.

### Completing Tasks

**Rori** can complete a given task of your choice. It will be represented with the `X` symbol if it is uncompleted and `O` symbol once completed.

### Deleteing Tasks

**Rori** can delete a given task of your choice from the list.

### Finding Tasks

**Rori** can help you search for a task with a given keyword.

### In-App guide

**Rori** has a help system which provides you information on the specific commands you have to use.

### Gets angry when you make an error

**Rori** has a distinct personality when you give her something she doesn't understand.

## Commands:

Here you can find the list of commands you can type in the program.

### `todo` - Displays list of Tasks

`todo` adds an incomplete to-do to your list of tasks. It requires an additional argument after the word `todo`, e.g. `todo myTask`

Example of Usage:

`todo Buy a new fan`

Expected outcome:

```
Got it. I've added this task:
  [T][X] Buy a new fan
Now you have 1 tasks in the list.
```

### `deadline` - Displays list of Tasks

`deadline` adds an incomplete to-do to your list of tasks. It requires an two additional arguments after the word `deadline`, e.g. `deadline myTask /by 11/11/2011 11:11`.

Example of Usage:

```
deadline Finish English Assignments /by 11/11/2011 11:11
deadline Return my books /by 01/01/2019
deadline Find my phone /by 01:01
```

Expected outcome:

```
Got it. I've added this task:
  [D][X] Finish English Assignments (by: 11/11/2011 11:11)
Now you have 2 tasks in the list.

Got it. I've added this task:
  [D][X] Return my books (by: 01/01/2019)
Now you have 3 tasks in the list.

Got it. I've added this task:
  [D][X] Find my phone (by: 01:01)
Now you have 4 tasks in the list.
```

### `event` - Displays list of Tasks

`event` adds an incomplete to-do to your list of tasks. It requires an two additional arguments after the word `event`, e.g. `event myTask /at 11/11/2011 11:11`.

Example of Usage:

```
event Attend Lecture /at 11/11/2011 11:11
event Attend Tutorial /at 01/01/2019
event Attend Secret Society Meeting /at 01:01
```

Expected outcome:

```
Got it. I've added this task:
  [E][X] Attend Lecture (at: 11/11/2011 11:11)
Now you have 5 tasks in the list.

Got it. I've added this task:
  [E][X] Attend Tutorial (at: 01/01/2019)
Now you have 6 tasks in the list.

Got it. I've added this task:
  [E][X] Attend Secret Society Meeting (at: 01:01)
Now you have 7 tasks in the list.
```

### `list` - Display list of Tasks

`list` displays the list of your tasks.

Example of Usage:

```
list
```

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] Buy a new fan
2. [D][X] Finish English Assignments (by: 11/11/2011 11:11)
3. [D][X] Return my books (by: 01/01/2019)
4. [D][X] Find my phone (by: 01:01)
5. [E][X] Attend Lecture (at: 11/11/2011 11:11)
6. [E][X] Attend Tutorial (at: 01/01/2019)
7. [E][X] Attend Secret Society Meeting (at 01:01)
```

### `done` - Completes a Task

`done` marks a task of the given number as complete. It requries an additional argumenet after the word `done` e.g. `done myNumber` where myNumber is any number as long as it exists in the list.

Example of Usage:

```
done 2
```

Expected outcome:

```
Nice. I've marked this task as done!
  [D][O] Finish English Assignments (by: 11/11/2011 11:11)
```

### `delete` - Deletes a Task

`delete` removes a task of the given number from the list. It require an additional argument after the word `delete` e.g. `delete myNumber` where myNumber is any number in the list.

Example of usage:

```
delete 4
```

Expected outcoem:

```
Noted. I've removed this task:
  [D][X] Find my phone (by 01:01)
Now you have 6 task in the list.
```

### `find` -  Finds a Task

`Find` finds a task with the given keyword from the list. It requires an ddition arguemnet e.g. `find myKeyword`, where myKeyword is any keyword of any task in the list.

Example of usage:

```
find Attend
```

Expected outcome:

```
Finding for `Attend` now...
1. [E][X] Attend Lecture (at: 11/11/2011 11:11)
2. [E][X] Attend Tutorial (at: 01/01/2019)
3. [E][X] Attend Secret Society Meeting (at 01:01)
```

### `bye` - Exits the program

`bye` closes the application immediately.

### `help` - Provide an in-app user guide

`help` provides a user guide. It can either take in no argument or an addition one consisting of a command e.g. `help myCommand`.

Example of usage:

```
help
help todo
```

Expected outcome:

```
Hello this is Rori's help page.
There are 8 main commands excluding help and tutorial
1. list
2. todo
3. deadline
4. event
5. done
6. delete
7. find
8. bye
Type `help myCommand` to see the different features for each command!
And if you ever need an example, type `tutorial` for one!
```

```
'todo myTask' Keeps track of a to-do.
```

### `tutorial` - Provide an example into the current save file

`tutorial` is a feature that automatically happens when you run the program for the first time. It ask the user whether they want the tutorial and if so, provides 3 examples into the current task list. It also can be used any time.

Example outcome - Assuming user enters for first time:
```
It seems it is your first time here.
Would you like a tutorial for this?
Type 'Yes' or 'No'.
```

Example output:

```
yes
list
```

Expected outcome:

```
I've added a temporary save file first.
Try typing 'list' to get started.
And if you ever need help, type 'help'!
```

```
Here are the tasks in your list:
1. [T][X] This is a Todo.
2. [D][X] This is a Deadline. (by: 12/09/2019 10:10)
3. [E][X] This is an Event. (at: 12/09/2019) 
```

## Authors

* **Timothy Yu Zhiwen** - *Developer of Rori*








