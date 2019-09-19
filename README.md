# Setting up

**Prerequisites**

* JDK 11
* Recommended: IntelliJ IDE
* Fork this repo to your GitHub account and clone the fork to your computer

**Importing the project into IntelliJ**

1. Open IntelliJ (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first).
1. Set up the correct JDK version.
   * Click `Configure` > `Structure for new Projects` (in older versions of Intellij:`Configure` > `Project Defaults` > `Project Structure`).
   * If JDK 11 is listed in the drop down, select it. If it is not, click `New...` and select the directory where you installed JDK 11.
   * Click `OK`.
1. Click `Import Project`.
1. Locate the project directory and click `OK`.
1. Select `Create project from existing sources` and click `Next`.
1. Rename the project if you want. Click `Next`.
1. Ensure that your src folder is checked. Keep clicking `Next`.
1. Click `Finish`.

# Tutorials 

Duke Increment | Tutorial
---------------|---------------
`A-Gradle` | [Gradle Tutorial](tutorials/gradleTutorial.md)
`A-TextUiTesting` | [Text UI Testing Tutorial](tutorials/textUiTestingTutorial.md)
`Level-10` | JavaFX tutorials:<br>→ [Part 1: Introduction to JavaFX][fx1]<br>→ [Part 2: Creating a GUI for Duke][fx2]<br>→ [Part 3: Interacting with the user][fx3]<br>→ [Part 4: Introduction to FXML][fx4]

[fx1]: <tutorials/javaFxTutorialPart1.md>
[fx2]: <tutorials/javaFxTutorialPart2.md>
[fx3]: <tutorials/javaFxTutorialPart3.md>
[fx4]: <tutorials/javaFxTutorialPart4.md>

# Feedback, Bug Reports

* If you have feedback or bug reports, please post in [se-edu/duke issue tracker](https://github.com/se-edu/duke/issues).
* We welcome pull requests too.

# User Guide

#### 1. Introduction

Duke is a **simple desktop app** designed for those who wish to be able to keep track of and manage their tasks. These tasks could entail deadlines, events, or just todos. 
It is mostly **suited for those who prefer to work with a command-line interface** while still having a **user-friendly GUI** for a chat-like simulation with the Duke chatbot. 
Sounds interesting? Get started now!

#### 2. Quick Start

* Fork this repo to your GitHub account and clone the fork to your computer.
* Source code can be viewed using an IDE (preferred: IntelliJ).
* Run the program by typing in `gradlew build` followed by `gradlew run` command in your terminal.
* The Duke application will open.
* Alternatively, you can download the jar file `v0.2 Duke ChatBot` from the releases page of this repo and run that file on your local computer.

#### 3. Features

Command Format |
---------------|
Words in `UPPER_CASE` are the parameters to be supplied by the user. Ex: In `deadline DESCRIPTION /by DATE_AND_TIME`, `DESCRIPTION` and `DATE_AND_TIME` are parameters which can be used as `deadline ma2202 hw /by 19/09/2019 1800`. |
Items in square brackets are optional. Ex: `deadline DESCRIPTION /by DATE_AND_TIME [/every FREQUENCY_IN_DAYS]` can be used as `deadline ma2202 hw /by 19/09/2019 1800 /every 7` or as `deadline ma2202 hw /by 19/09/2019 1800`.|
Parameters need to be in the order given. Ex: If the command specifies `DESCRIPTION /by DATE_AND_TIME`, something like `/by DATE_AND_TIME DESCRIPTION` would be invalid input. |

**3.1. Adding a todo: `todo`**

Adds a todo to the list of tasks
Format: `todo DESCRIPTION [/every FREQUENCY_IN_DAYS]`
* The `DESCRIPTION` can have spaces between it.

Example: 
* `todo buy groceries`
* `todo go to gym /every 7`

**3.2. Adding a deadline: `deadline`**

Adds a deadline to the list of tasks
Format: `deadline DESCRIPTION /by DATE_AND_TIME [/every FREQUENCY_IN_DAYS]`
* The `DESCRIPTION` can have spaces between it.
* The `DATE_AND_TIME` must be in the format `Date/Month/Year Time`, where `Time` is in 24-hour time format.

Example: 
* `deadline ma2202 hw /by 19/09/2019 1800`
* `deadline ma2202 hw /by 19/09/2019 1800 /every 7`

**3.3. Adding an event: `event`**

Adds an event to the list of tasks
Format: `event DESCRIPTION /at DATE_AND_TIME [/every FREQUENCY_IN_DAYS]`
* The `DESCRIPTION` can have spaces between it.
* The `DATE_AND_TIME` must be in the format `Date/Month/Year Time`, where `Time` is in 24-hour time format.

Example: 
* `event dine and dance /at 21/09/2019 2100`
* `event dine and dance /at 21/09/2019 2100 /every 14`

**3.4. Listing all tasks: `list`**

Shows list of all tasks in the task list
Format: `list`

**3.5. Marking a task as done: `done`**

Marks a task as done in the list of tasks
Format: `done INDEX`
* The `INDEX` refers to the index number shown when the list of tasks is displayed. The **index must be a positive integer and is bounded by the size of the list**.
* The task list will be updated with the task at the index marked as done.
* When a recurring task is marked done, the same task (with the new date if it is a deadline or event) is added to the task list automatically. It is up to the user to delete the older task marked done.

Example: 
* `done 2`

**3.5. Deleting a task: `delete`**

Deletes a task from the list of tasks
Format: `delete INDEX`
* The `INDEX` refers to the index number shown when the list of tasks is displayed. The **index must be a positive integer and is bounded by the size of the list**.
* The task list will be updated with the task at the index deleted and the rest of the tasks indices updated based on the size of the updated list.
* To delete a recurring task, the latest version of the task that is not yet marked as done should be deleted.

Example: 
* `delete 2`

**3.6. Locating tasks by keywords: `find`**

Displays the list of tasks that contain the keyword
Format: `find KEYWORD`
* The `KEYWORD` can have spaces between it.
* If the `KEYWORD` contains more than one word, a task will be displayed in the list of found tasks only if it contains the full `KEYWORD` with all its words in that sequence.

Example: 
* `find groceries`
* `find buy groceries`

**3.7. Exiting the bot: `bye`**

Exits the Duke application
Format: `bye`

#### 4. FAQ

**Q:** Does the application have support for recurring tasks?  
**A:** Yes it does for all types of tasks (todos, deadlines, and events). You can add these kind of tasks by adding `/every FREQUENCY_BY_DAY` after writing the task details. More information in the features list above.

**Q:** Will the tasks I add to the list be saved by the bot so that I can view it the next time I open the application?  
**A:** Yes! Tasks are stored to the memory so the next time you open your application, these tasks are loaded back to continue from!
 
#### 5. Command Summary

* **Add todo** `todo DESCRIPTION [/every FREQUENCY_IN_DAYS]`  
Ex: `todo go to gym /every 7`

* **Add deadline** `deadline DESCRIPTION /by DATE_AND_TIME [/every FREQUENCY_IN_DAYS]`   
Ex: `deadline ma2202 hw /by 19/09/2019 1800 /every 7`

* **Add event** `event DESCRIPTION /at DATE_AND_TIME [/every FREQUENCY_IN_DAYS]`   
Ex: `event dine and dance /at 21/09/2019 2100 /every 14`

* **List tasks** `list`

* **Mark done** `done INDEX`  
Ex: `done 2`

* **Delete task** `delete INDEX`  
Ex: `delete 2`

* **Find tasks** `find KEYWORD`  
Ex: `find groceries`

* **Exit application** `bye`
