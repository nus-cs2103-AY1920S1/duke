# User Guide
Duke is ~~an anime girl on your computer~~ a simple chatbot that helps you track your tasks and whether you've completed them.

## Getting Duke
You can get Duke on your computer by downloading the latest .jar file under releases.<br><br>
As Duke was developed on JDK 11, and as such it's recommended that you have Java SE 11 or later installed on your computer before running Duke.<br><br>
Alternatively, you can clone the repo. Then from the root folder, execute the command:
```
gradlew run
```

## Features
### Duke keeps track of a list of tasks for you
You can add and delete tasks from a list.

### Duke allows you to differentiate between types of tasks
Each task can be classified as a todo, deadline or event task.

### Duke can search through your list
Duke can sieve through your list to return only the relevant tasks.

### Duke is able to maintain a separate list of notes
Duke can remember any tiny bit of trivia you tell her.

## Usage

### `todo` - Creates a new todo task in memory
Duke will create a new Todo Task and store it within its memory.

Example usage:
```
todo eat borgar
```

Expected outcome:
```
Gotcha! I've added a new task:
  [T][✘] eat borgar
You've got 1 task(s) on your list.
```

![todo example](https://raw.github.com/Chenggeng97/duke/master/docs/todo.png)

### `deadline` - Creates a new deadline task in memory
Duke will create a new Deadline Task and store it within its memory.<br>
You may specify either a date or a time, in any order. This must be pre-fixed by a `/by` subcommand.

Example usage:
```
deadline eat borgar /by 10/10/2020
deadline eat borgar /by 10/10/2020 2359
deadline eat borgar /by 2359 10/10/2020
```

Expected outcome:
```
Gotcha! I've added a new task:
  [D][✘] eat borgar (by 10/10/2020)
You've got 1 task(s) on your list.

Gotcha! I've added a new task:
  [D][✘] eat borgar (by 10/10/2020 2359)
You've got 2 task(s) on your list.

Gotcha! I've added a new task:
  [D][✘] eat borgar (by 10/10/2020 2359)
You've got 3 task(s) on your list.
```

![deadline example 1](https://raw.github.com/Chenggeng97/duke/master/docs/deadline1.png)
![deadline example 2](https://raw.github.com/Chenggeng97/duke/master/docs/deadline2.png)
![deadline example 3](https://raw.github.com/Chenggeng97/duke/master/docs/deadline3.png)

### `event` - Creates an new event task in memory
Duke will create a new Event Task and store it within its memory.<br>
The input dates should be pre-fixed by an `at`.<br>
You may enter up to two pairs of date-times. The two dates should be split by a `to`.

Example usage:
```
event eat borgar /at 16/9/19
event eat borgar /at 16/9/19 to 16/9/20
event eat borgar /at 16/9/19 0000 to 16/9/20 2359
```

Expected outcome:
```
Gotcha! I've added a new task:
  [E][✘] eat borgar (at 16/09/2012)
You've got 1 task(s) on your list.

Gotcha! I've added a new task:
  [E][✘] eat borgar (at 16/09/2012 to 16/09/2013)
You've got 2 task(s) on your list.

Gotcha! I've added a new task:
  [E][✘] eat borgar (at 16/09/2019 0000 to 16/09/2020 2359)
You've got 3 task(s) on your list.
```

![event example 1](https://raw.github.com/Chenggeng97/duke/master/docs/event1.png)
![event example 2](https://raw.github.com/Chenggeng97/duke/master/docs/event2.png)
![event example 3](https://raw.github.com/Chenggeng97/duke/master/docs/event3.png)

### `list` - Pulls up the entire list of tasks
Duke will return the entire list.<br>
In this example, 3 tasks were already stored in Duke's internal list.

Example usage:
```
list
```

Expected outcome:
```
1.[T][✘] eat borgar
2.[D][✘] eat borgar (by 2359)
3.[E][✘] beat orgar (at 17/09/2019 1359)
```

![list example](https://raw.github.com/Chenggeng97/duke/master/docs/list.png)

### `find` - Pulls up only the items that match the keyword
Duke will return only the parts of the list that match the keyword.

Example usage:
```
find beat
```

Expected outcome:
```
Scan complete! I've got your tasks right here!
3.[E][✘] beat orgar (at 17/09/2019 1359)
```

![find example](https://raw.github.com/Chenggeng97/duke/master/docs/find.png)

### `done` - Marks an item on the list as done
Duke will mark the Task a the specified index as done.

Example usage:
```
done 3
```

Expected outcome:
```
Good job! I've marked this task as done:
  [E][✓] beat orgar (at 17/09/2019 1359)
```

![done example](https://raw.github.com/Chenggeng97/duke/master/docs/done.png)

### `delete` - Delete a task from your list
Duke will delete a Task.

Example usage:
```
delete 3
```

Expected outcome:
```
No problem! I've deleted the task:
  [E][✓] beat orgar (at 17/09/2019 1359)
You've got 2 task(s) on your list.
```

![delete example](https://raw.github.com/Chenggeng97/duke/master/docs/delete.png)

### `nuke` - Deletes all tasks from your list
Duke will delete all Tasks.

Example usage:
```
nuke
```

Expected outcome:
```
Boom! I've deleted all your tasks!
```

![nuke example](https://raw.github.com/Chenggeng97/duke/master/docs/nuke.png)

### `note` - Creates a new note in memory
Duke will store a new note in its memory.

Example usage:
```
note McBorgar > borgarking
```

Expected outcome:
```
Gotcha! I've added a new note:
  McBorgar > borgarking
You've got 1 note(s) on your list.
```

![note example](https://raw.github.com/Chenggeng97/duke/master/docs/note.png)

### `notelist` - Pulls up the entire list of notes
Duke will return the entire list.

Example usage:
```
notelist
```

Expected outcome:
```
1. McBorgar > borgarking
```

![notelist example](https://raw.github.com/Chenggeng97/duke/master/docs/notelist.png)

### `deletenote` - Deletes a note in memory
Duke will delete the note at the specified index.

Example usage:
```
deletenote 1
```

Expected outcome:
```
No problem! I've deleted the note:
  McBorgar > borgarking
You've got 0 notes on your list.
```

![deletenote example](https://raw.github.com/Chenggeng97/duke/master/docs/deletenote.png)

### `nukenote` - Deletes all the notes
Duke will delete all notes within the list.

Example usage:
```
nukenote
```

Expected outcome:
```
Boom! I've deleted all your notes!
```

![nukenote example](https://raw.github.com/Chenggeng97/duke/master/docs/nukenote.png)

### `bye` - shuts down the program
Duke will shut down, after saying goodbye.

Example usage:
```
bye
```

![bye example](https://raw.github.com/Chenggeng97/duke/master/docs/bye.png)

Expected outcome:
```
Alright, see you again!
Good luck, and do your best!
```

## Authors
- Damith C. Rajapakse - _Module Coordinator_
- Cheng Geng - _Developer of Duke_

## Built With
- [Gradle](https://gradle.org/): for dependency management

## Acknowledgements
- People on StackExchange who asked the same questions as me on 5 years ago
- People on StackExchange who answered those questions 5 years ago
- Japan, for creating anime
- Philip II of Spain, whose decisions to go to war against the English led eventually to the creation of anime
- And you!
