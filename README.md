# Duke: Task Manager
**Duke** is created for simple and easy **management of tasks**, using text commands in the command line. It supports various tasks like **DoAfters, Deadlines, Events** and **ToDos** and tracks them by displaying a neat overview of all tasks in a Graphical User Interface (GUI).

![User Interface](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/userInterface.png)

## User Guide

1. Ensure you have the latest version of Java
2. Download the latest version of Duke. ([Click here](https://github.com/jeunhoe/duke/releases/download/v1.0/duke-1.0.jar))
3. Double click the file to open it.
4. Duke should pop up in a new window.
5. Use the commands supported by Duke, which are listed [here](https://jeunhoe.github.io/duke/#features)!

## Features

### Adding Tasks
You can add tasks to Duke so Duke can help you manage them! Such tasks include **ToDos**, **Deadlines**, **DoAfters** and
 **Events**.

### **ToDo**
Adds a **ToDo** task to Duke.

Usage

`todo NAME_OF_TODO`

Adds **ToDo** to Duke.

Example of usage:

`todo Meet Rachel`

Expected Outcome:

![Task ToDo](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/taskToDo.png)

### **Deadline**
Adds a **Deadline** task to Duke.

Usage

`deadline NAME_OF_DEADLINE /by DATE_TIME`

Add **Deadline** with *DATE_TIME* (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`deadline CS2106 Lab 2 /by 25/09/2019 1500`

Expected Outcome:

![Task Deadline](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/taskDeadline.png)

### **DoAfter**
Adds a **DoAfter** task to Duke.

Usage

`doafter NAME_OF_DOAFTER /after DATE_TIME`

Add **DoAfter** task with *DATE_TIME* (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`doafter Feed Bruno /after 26/09/2019 1800`

Example Outcome:

![Task DoAfter](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/taskDoAfter.png)

### **Event**
Adds an **Event** task to Duke.

Usage

`event NAME_OF_EVENT /at DATE_TIME`

Add **Event** with *DATE_TIME* (format DD/MM/YYYY HH/MM) to Duke.

Example of usage:

`event CS2105 Midterms /at 07/10/2019 1400`

Example Outcome:

![Task Event](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/taskEvent.png)

### Managing Tasks
Apart from adding tasks to Duke, Duke allows you to manage these tasks by running some commands on them. These include:
1. Marking a task as done
2. Deleting a task
3. Finding task
4. Listing all tasks

### **Done**
Marks a task as **done** in Duke.

**"Done"** tasks will be displayed with a [+]

Usage

`done INDEX_OF_TASK`

Marks a task at index *INDEX_OF_TASK* as **done** in Duke.

Example of usage:

`done 1`

Expected Outcome:

![Done Command](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/doneCommand.png)

### **Delete**
**Deletes** a task from Duke.

Usage

`delete INDEX_OF_TASK`

**Deletes** task at *INDEX_OF_TASK* in Duke.

Example of usage:

`delete 1`

Expected Outcome:

![Delete Command](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/deleteCommand.png)

### **Find**
**Finds** a task in Duke.

Usage

`find STRING`

**Finds** all occurrences of *STRING* in tasks of Duke and prints them out.

Example of usage:

`find Bruno`

Expected Outcome:

![Find Command](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/findCommand.png)

### **List**
**Prints** out all tasks in Duke.

Usage

`list`

**Prints** out all tasks in Duke.

Example of usage:

`list`

Expected Outcome:

![List Command](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/listCommand.png)

### Exiting Duke
When you're all done with Duke, remember to save your data!

### **Bye**
Saves all current data and **exits** Duke.

*Usage*

`bye`

Saves all current data and **exits** Duke.

Example of usage:

`bye`

Expected Outcome:

![Bye Command](https://raw.githubusercontent.com/jeunhoe/duke/master/docs/images/byeCommand.png)