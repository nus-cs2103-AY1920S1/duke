# User Guide
Welcome to Duke, a star wars themed task manager to help you keep on top of  your tasks. Duke utilizes a chatbot
interface to make your experience more immersive. Take on the role as young Anakin Skywalker and get help from the
Jedi Master Yoda in completing your tasks. Be careful though, Darth Vader might appear if you have more than 10
uncompleted tasks.

## Getting started
1. Ensure that you have Java 11 or above installed on your computer.
2. Download the latest release of Duke.
3. Copy it into the folder of your choice and rename it to your liking.
4. Double click the file to start the app.
5. If step 4 does not work, navigate to the containing folder on your command line and type `java -jar [file name]`
6. The GUI should appear in a few seconds.
7. If you prefer to use a command line interface, follow step 5 but type `java -jar [file name] -c` instead.

## Features 

### Multiple user intefaces
This application supports a command line interface for those users who prefer not to use a GUI. To switch to using a
command line interface instead, simply navigate to Duke's containing folder in your command line and type 
`java -jar [file name] -c` to start the application.

### Add various tasks
The application hosts a wide range of tasks to suit your needs. 
1. To-dos - non-urgent tasks with no deadlines
2. Events - tasks that occur at a specfic time
3. Deadlines - urgent tasks that need to be completed by a specific time
4. Do-afters - tasks that can only be done after a specific time, like catching a soon to be released movie
5. Do-within - not-as-urgent tasks that can be done within a time frame.

### Natural language
This application allows you to key in your date and time arguments without having to follow a fixed format or with
flags. Type the task as how you would say it in your head. For example, "event meet John for dinner mon 1930" is a
valid command. Just remember to put your date time arguments at the back of your command. Experiment with what the
application can understand!

Some recognized  date and time patterns:

1. monday/mon
2. tuesday/tues/tue
3. wednesday/wed
4. thursday/thurs/thur/thu
5. friday/fri
6. saturday/sat
7. sunday/sun
8. today/tomorrow
9. [datetime] to [datetime]
10. [datetime] [datetime]
11. [date] [time]

For more specific date and times, use the format [dd/mm/yyyy] [hhmm] e.g. ```12/02/2020 0900```

### Undoing commands
The application lets you undo commands that you may have accidentally entered. Simply type ```undo``` and all the
changes made by the previous command will instantly be reverted. 

### Sorting tasks
The application lets you sort your tasks according to two criterion ```date``` and ```time```. Sort by date to see
which of your tasks are more urgent and sort by name to find specific tasks more easily. 


### Interactivity
Fully immerse yourself in the star wars theme of our application by chatting to the likes of Master Yoda and Darth 
Vader. Endure Master Yoda's nagging as your uncompleted tasks start to pile up but be careful not to stray to far to
the dark side.

### Other neat tricks
You can delete all your tasks at once by entering the command ```delete all``` instead of deleting them one by one. You
can also search for specific tasks using the ```find [parameter]``` command.

## Usage

### `help` - open guide

Opens an in-app guide describing all the available commands.

Example of usage: 

`help`

### `todo` - add todo

Adds a to-do your current list of tasks

Example of usage: 

`todo [description]`

`todo bake a cake`

### `deadline` - add deadline

Adds a deadline to your current list of tasks

Example of usage: 

`deadline [description] [date] [time] (optional)`

`deadline final project 02/11/2019 2359`

### `event` - add event

Adds an event to your current list of tasks

Example of usage: 

`event [description] [date] [time] (optional)`

`event movie fri`

### `after` - add do-after

Adds a do-after task to your current list of tasks

Example of usage: 

`after [description] [date] [time] (optional)`

`after visit pc fair thursday 0900`

### `within` - add do-within

Adds a do-within task to your current list of tasks

Example of usage: 

`within [description] [date] [time] (optional) to (optional) [date] [time] (optional)`

`within do laundry tues 1500 tues 1800`

### `list` - lists tasks

Lists all your tasks

Example of usage: 

`list`

### `done` - mark task as done

Marks a task as done based on the index  at which it was displayed

Example of usage: 

`done [index]`

`done 1`

### `delete` - delete task

Delete a task baed on the index at which it was displayed or delete all tasks

Example of usage: 

`delete [index]` `delete all`

### `sort` - sort tasks

Sort your tasks based on certain criteria

Example of usage: 

`sort name` `sort date`

### `undo` - undo

Reverts changes made by your previous command

Example of usage: 

`undo`

### `bye` - exit

Exits the application

Example of usage: 

`bye`
