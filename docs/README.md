# User Guide

This is a feature guide on a simple chatbot that I built as part of CS2030's Individual Project. This is a chatbot which functions as a todolist, and it supports 3 types of tasks for you to manage. They are, in no specific order: Todos, Deadlines, Events. You can mark these events as done and deleted, list them etc as you would a normal todo list.

** Screenshot **
![Screenshot](Ui.png)


## Features

The features are as follows. There are a total of 8 commands that you can use to communicate.
They are:
1. bye
2. event
3. deadline
4. todo
5. list
6. find
7. delete
8. done

The todo list is then stored on disc so that it can subsequently be reloaded when you start talking to the chatbot again. Avoid changing the save location to avoid unintended bugs as this is not something which is expected by the chatbot developer.

## Bye
### Description
Enables you to say goodbye to the chatbot!
### Usage
To run this command, simply state:

```
bye
```

This will prompt an goodbye message from our chatbot as well.

## Event
### Description
The event command allows you to create a task that is associated with a time that it will take place.

### Usage
The event command expects 2 key bits of information, the name and the date

```
event <name> /at dd/mm/yy HHMM (in 24 hours time)
```

If the data is not in the order above, the chatbot will throw an error and it will not be added to the list. Else, you will receive a message telling you that the event has successfully been added to the list.


## Deadline
### Description
The deadline command allows you to create a task with a deadline to remind yourself.

### Usage
Like the event command, the deadline command expects 2 key bits of information, the name and the date

```
deadline <name> /by dd/mm/yy HHMM (in 24 hours time)
```

If the data is not in the order above, the chatbot will throw an error and it will not be added to the list. Else, you will receive a message telling you that the deadline has successfully been added to the list.

## Todo
### Description
The todo command is just a simple command that allows you to add a task to the todo list.
### Usage
```
todo <name>
```

If the input follows as above, you will receive a message saying that the todo task has been added to the list. Else, you will see an error message.
The event command allows you to create a task that is associated with a time that it will take place.

## List
### Description
Lists the tasks currently in your todo list
### Usage
```
list
```

If there is nothing in the list, then nothing will be shown.

## Find
### Description
The find command allows you to search for items in the list with names that match your selected keyword.
### Usage
```
find <keyword>
```

If theres a result, it will be shown, else the chatbot will tell you that 0 results have been found.

## Delete
### Description
The delete command allows you to delete items in the list. This deletion is done according to the task number as stated when you run `list`
### Usage
```
delete <index>
```

Make sure to pass an integer as the index.

If it works, the chatbot will inform you that the task has been deleted. Else, an error message will be thrown. 

## Done
### Description
The done command allows you to mark a certain task as completed. This marking is done according to the task number as stated when you run `list`, like deletion.
### Usage
```
done <index>
```

Like the delete command, make sure to pass an integer as the index. If the query is correct, the chatbot will inform you that the message has been marked as done.

