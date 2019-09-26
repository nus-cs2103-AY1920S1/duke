# Currents - User Guide 

<img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/currents.jpg" width="291" height="71">

### Plan your personal timetable!

## Features of Currents

#### Todo: `todo`
Allows you to track tasks that needs to be done and adds it to a list. 

#### Deadline: `deadline`
Reminds you of your upcoming deadlines and adds it to a list. 

#### Event: `event`
Remind you of your upcoming events and adds it to a list. 

#### Expenses: `expenses`
Allows you to record your expenses.

#### Delete: `delete`
Allows you to delete finished tasks or wrong tasks that was inputted.

#### Done: `done`
Allows you to tick off todo/event/deadline tasks that have been completed.

#### Find: `find`
Prints out matching words that you have inputted from the list.

#### List: `list`
Prints out the entire list.

#### Help: `help`
Prints out all the commands that is available in the application.

#### Bye: `bye`
Closes the application.

## Usage of Currents


Command | Example | Expected Outcome | Description
---------------|---------------|---------------|---------------
`todo <task>` | todo go gym | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/todo.jpg" width="291" height="71"> | Add a new to do task.
`event <description> /at <dd/MM/yyyy> <hhmm>` | event party /at 20/06/2021 1700 | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/event.jpg" width="291" height="71"> | Add a new event task.
`deadline <description> /by <dd/MM/yyyy> <hhmm>` | deadline essay /by 30/05/2023 2359 | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/deadline.jpg" width="291" height="71"> | Add a new deadline task.
`done <taskNumber>` | done 1 | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/done.jpg" width="291" height="71"> | Ticks off a todo/event/deadline task.
`delete <taskNumber>` | delete 1 | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/delete.jpg" width="291" height="71"> | Deletes a tasks from the list
`find <keyword>` | find essay | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/find.jpg" width="291" height="71"> | Find the words that match with your input and prints out corresponding tasks.
`expenses $<amount> /on <description>` | expenses $45.20 /on steak | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/expenses.jpg" width="291" height="71"> | Adds an expense to the list.
`list` | list | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/list.jpg" width="291" height="71"> | Prints out everything that you have added to your list.
`help` | help | <img src = "https://github.com/caesarpjz/duke/blob/master/docs/images/help.jpg" width="291" height="71"> | Prints out all the command that you can use in the application.