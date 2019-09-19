# Duke User Guide
Duke is chatbot application that can help you keep track of yours tasks at hand. 
It is able to create, update, delete and search for various types of tasks such as todos, deadlines, and events.
Furthermore, you can update these tasksto be marked as done once you have finished them. 
The chatbot also automatically stores these tasks on exit 
and automatically loads them up again when you start it up the next time. Enjoy!

[![Ui.png](https://i.postimg.cc/g2MqdD6p/Ui.png)](https://postimg.cc/VJCbWXRZ)
 
## Features 
* Support for Todos, Events and Deadlines.
* Supports Create, Update and Delete functionalities for Tasks.
* Supports Mark-As-Done functionality for Tasks upon completion.
* Supports List functionality to display all Tasks.
* Supports Filter and Finding functionalities for Tasks.
* Auto-storing and loading data upon closing and opening Duke.

## Usage

`deadline <desciption> /by <date> <time>`

`event <description> /at <date> <time>`  

`todo <description>` 

Create new Tasks for Duke to keep track of. Duke will display the Task you have input and update you on the number of Tasks you currently have.

For Deadlines and Events, format `<date>` as  `dd/MM/yyyy` and `<time>` as `HHmm`

Example of usage: 

* Deadline return book /by 2/12/2019 1800
* Event birthday party /at 13/07/2019 1800
* Todo homework

- - -

`update <index> <command>`

Update details of an exisitng Task. Duke will display the new updated Tasks and show you the list of Tasks that you currently have .

For`<index>`, only valid, in-range integers are allowed. i.e you cannot input an index that is smaller than 1 or larger than the number of Tasks you currently have.

For `<command>`, follow format of Creating new Tasks. e.g. `update 1 deadline return book /by 2/12/2019 1800`

Example of usage: 

* update 2 Deadline return book /by 13/12/2019

----

`delete <index>`

Delete redundant Tasks. Duke will display the Task that is being removed and update you on the number of Tasks you currently have.

Example of usage: 

* delete 1
- - -

`done <index>`

Mark-As-Done Tasks upon completion. Duke will display the Task and tick the Task to indicate completion.

Example of usage: 

* done 1

- - -
`find <keyword>`

Find and filter Tasks. Duke will search through the current list of Tasks and 
display the list of Tasks that contain the input `keyword`.

Example of usage: 

* find deadline

- - -
`list`

List down all Tasks. Duke will display all Tasks that you currently have.

Example of usage: 

* list

- - - 
`Bye`

Exit Duke Application. Duke will save and store the Tasks that you currently have and load them up again upon the next start-up.

Example of usage: 

* bye

- - -
