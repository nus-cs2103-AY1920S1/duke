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

# **DUKE**- User Guide

## Table of Content
1. Introduction
1. Getting Started
1. Features
1. FAQ

## 1. Introduction:
**DUKE** is a desktop application that allows an individual to **organise his/her daily tasks and expenses** efficiently. While it consists of a Graphical User Interface (GUI) that makes interaction with the user friendly and understandable, **DUKE** is **optimised** for users who prefer to work with a Command Line Interface (CLI) which allows for fast typing and interpretation of the user-given commands.

## 2. Getting Started:
1. Ensure that you have Java 11 or above on your computer
1. Download the latest release of duke.jar here
1. Copy and paste the file to the folder you would like to use as the home folder for your DUKE app
1. Double click the file to start the app. The GUI should appear within a few seconds
1. Type your command in the command box and press “Enter” on your keyboard or “Send” on the GUI to execute it
	e.g. Typing **Glossary** and pressing **Enter** will refer you to a list of all helpful commands
1. Refer to Section 3 **Features** for a list of all useful commands

## 3. Features:
### Command Format:
* The **first** word that you type into the command prompt will be the command
* Each command has its respective input format. Refer to our glossary for more details
* Ensure that the command format is correct before pressing **Enter** or clicking **Send**. This line of command will then prompt **DUKE** to respond accordingly
* Commands are **not** case-sensitive

### Commands: 
1. View all commands: 'glossary'
    * Format: glossary
    * Allows you to view a list of all useful commands
1. Add a task:
    * 'todo' 
    	* Format: [task details]
    	* Adds a todo-type task for you
    * 'deadline' 
    	* Format: [task details] /by [dd/mm/yyyy] [24hr time format]
 	* Adds a deadline-type task for you
    * 'event' 
	* Format: [task details] /at [dd/mm/yyyy] [start time(24hr time format)]-[end time(24hr time format)]
	* Adds an event-type task for you
1. Delete a task: 'delete'
    * Format: delete [n]
    * Deletes the nth task in the list
1. Mark a task as done: 'done'
    * Format: done [n]
    * Marks the nth task in the list as done
1. View all tasks: 'list'
    * Format: list
    * Lists all available tasks and their done status
1. Find a task: 'find'
    * Format: Find [keyword/phrase]
    * Lists all available tasks that contains the given keyword or phrase
1. Add an expense: 'spending'
    * Format: spending [category] [amount] [description]
    * Below is the list of all available categories:
	1. Food
        1. Transport
        1. Education
        1. Household
        1. Apparel
        1. Beauty
        1. Health
        1. Social
        1. Others
1. Delete an expense: 'delexp'
    * Format: delexp [category] [n]
    * Deletes the nth expense in the list from the given category
1. View all expenses: 'expenses'
    * Format: expenses
    * Lists all available expenses and their total amount

## 4. FAQs
**Q: Where can I find all the commands?**
  A: Type 'glossary' into the command prompt and click enter. This will print out a list of all useful commands for your reference.
**Q: How do I exit from the application?**
  A: Left-click the red-buttom marked X at the top right hand corner of the application window.
**Q: If I key in a wrong amount for my expense, how can i correct it?**
  A: As of Version 1.0.0, the only way to fo it would be to remove this expense and add it back again. We are working on providing you with an easier way to handle expenses and task editing in Version 2. Stay tuned.**
**Q: What is the difference between a task and an expense?**
  A: Our application provides you with both choices, where a task keeps track of your daily schedule and activities while an expense takes note of your daily spendings.





