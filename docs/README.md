# User Guide

## Features 

#### Command Format
* `<date>`: `DD/MM/YYYY`
* `<time>`: `HHMM` in 24-hour format
---
### Task Management

#### Listing all tasks : `list`

Shows a list of all tasks in the task list.

Format: `list`

#### Adding a task

##### Adding a todo : `todo`

Adds a todo to the task list.

Format: `todo <desc>`

Examples:
* `todo read book`
* `todo buy groceries`
* `todo practise piano`

##### Adding a deadline : `deadline`

Adds a deadline to the task list.

Format: `deadline <desc> /by <date> <time>`

Examples:
* `deadline complete report /by 20/10/2019 2359`
* `deadline submit reflection /by 5/11/2019 2359`
* `deadline learn french /by 28/9/2020 1200`

##### Adding an event : `event`

Adds an event to the task list.

Format: `event <desc> /at <date> <time>`

Examples:
* `event formal dinner /at 29/10/2019 1900`
* `event symphony concert /at 7/11/2019 2000`
* `event facebook hackathon /at 13/11/2019 1000`

#### Mark as done : `done`

Marks a task in the task list as done.

Format: `done <index>`

Examples:
* `done 1`
* `done 3`

#### Finding a task : `find`

Finds a task in the task list with the specified keyword.

Format: `find <desc>`

Examples:
* `find report`
* `find assignment`
* `find formal dinner`

#### Deleting a task : `delete`

Deletes the specified task from the task list.

Format: `delete <index>`

Examples:
* `delete 1`
* `delete 3`

---

### Expense Management

#### Listing all transactions : `summary`

Shows a compiled list of debts and loans.

Format: `summary`

#### Adding a transaction

##### Adding a debt : `owe`

Adds a debt to the list of transactions.

Format: `owe <name> <amount>`

Examples:
* `owe Adam 10`
* `owe Selina 15`
* `owe XiaoMing 12.50`

##### Adding a loan : `lend`

Adds a loan to the list of transactions.

Format: `lend <name> <amount>`

Examples:
* `lend Cynthia 9.20`
* `lend Natalie 18`
* `lend HuiLin 30`

#### Removing a transaction

Removes a transaction from the list of transactions.

##### Paying a debt : `pay`

Removes a partial / full debt from the list of transactions.

Format: `pay <name> <amount>`

Examples:
* `pay Adam 5`
* `pay Selina 14`
* `pay XiaoMing 9.50`

##### Settling a loan : `receive`

Settles a partial / full loan from the list of transactions.

Format: `receive <name> <amount>`

Examples:
* `receive Cynthia 5`
* `receive Natalie 8.30`
* `receive HuiLin 4`

---

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

Task and transaction data are saved in the hard disk automatically after exiting the program.

Remember to type `bye`!