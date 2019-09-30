# ERA - Easily Remember All[![Build Status](https://travis-ci.com/Cary-Xx/duke.svg?branch=master)](https://travis-ci.com/Cary-Xx/duke)

ERA is a Java application specifically designed for students or those who want to track their tasks. ERA is designed for users who prefer to use CLI but equipped with GUI as well.
## 1.How to start
1.Ensure that you that Java11 installed.(Run `java -version` on your terminal)
2.Download the jar here:  
3.Copy the jar file in a base folder where you can save and load your tasks from taskList.  
4.Double-click the jar or run `java -jar ERA-0.1.0.jar` incase the former doesn't work.  
5.The GUI will appear. Play with it!  
6.Type your command and press `Enter` and the command will be executed. Full list of possible commands can be found below.
## 2.Features 

### 2.1 Add a task: `todo`  
Format: `todo TASK_A`
Example: `todo borrow book`

### 2.2 Add a task: `deadline`
Format: `deadline TASK_A /by SOME_DATE`  
`SOME_DATE` can be in the format of `dd/MM/YYYY Hmm` or `dd/MM/yyyy` or `d/MM/yyyy Hmm` or `d/MM/yyyy` or simply e.g `Monday, Sunday`  
Example: `deadline return book /by 01/10/2019 900`, `deadline return book /by Sunday`

### 2.3 Add a task: `event`
Format: `event TASK_A /at SOME_DATE`
`SOME_DATE` can be in the format of `dd/MM/YYYY Hmm` or `dd/MM/yyyy` or `d/MM/yyyy Hmm` or `d/MM/yyyy` or simply e.g `Monday, Sunday`   
Example: `event sports meeting /at 01/09/2019 1800`, `event sports meeting /at Sunday`

### 2.4 List all the tasks in the taskList: `list`
Format: `list`

### 2.5 Delete a task in the taskList: `delete`
Format: `delete SOME_INDEX`  
`SOME_INDEX`corresponds to the index of a certain task displayed in your taskList.
Example: `delete 2`

### 2.6 Find a keyword in the taskList: `find`
Format: `find SOME_KEYWORD`
`SOME_KEYWORD` will be searched in the descriptions of all the tasks and return a list  
Example: `find book`

### 2.7 Mark a task as done: `done`
Format: `done SOME_INDEX`
`SOME_INDEX` corresponds to the index of a certain task displayed in your taskList.  
Example: `done 2`

### 2.8 Check duplicates in the taskList: `check`
Format: `check`
All the duplicates will be displayed.

### 2.9 Exit the application: `bye`
Format: `bye`
The program will be terminated and exit.

### 2.10 Saving and loading tasks
The data of your taskList will be saved and it will automatically loaded when you open the application.

## 3.Feedback, Bug Reports
* If you have feedback or bug reports, please post in [Cary-Xx/duke issue tracker](https://github.com/Cary-Xx/duke/issues).
* Pull requests are welcome too.
