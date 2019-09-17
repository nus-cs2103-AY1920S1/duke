# Duke User Guide
This is the Duke ChatBot created by De Xun as part of the CS2103T Individual Project.
<img src="./Ui.png" width="600" height="600"/>

## Features 
### GUI
Instead of giving commands via a simple CLI, all inputs and outputs are displayed on a GUI designed for a quality and smooth User Experience.

### Create, Read, Update, Delete (CRUD)
Duke is able to effectively perform CRUD actions based on pre-defined User Commands.

### Saving and Loading
User-Created entities are saved into a text file and loaded everytime Duke starts up. 

### Task List
Duke provides the functionality to save and record to-dos, events, and deadlines.

### Contact List
Duke provides the functionality to save the personal information of your contacts.

## Usage

### `list` - Displays list of all Tasks

Example of usage: 
`list`

Expected outcome:
```
Here are the tasks in your list:
1. [D][✓] return book (by: 10 DECEMBER 2019 0830)
2. [D][✓] project meeting (by: 7 JULY 2019 0815)
3. [T][✓] join sports club
4. [E][✗] Do CS2103T (20 AUGUST 2019 0000 - 18 NOVEMBER 2019 2359)
5. [D][✗] iP (by: 23 SEPTEMBER 2019 2359)
```

### `todo` - Creates a Task to be done

Example of usage: 

```
todo [taskDescription]
todo Write ReadME
```

Expected outcome:
```
Got it. I've added this task:
  [T][✗] Write Readme
Now you have 6 tasks in the list.
```

### `deadline` - Creates a Deadline Task with an End Date

Example of usage: 

```
deadline [deadlineDescription] /by DD/MM/YYYY HHMM
deadline Submit README /by 19/09/2019 2359
```

Expected outcome:
```
Got it. I've added this task:
  [D][✗] Submit ReadME (by: 19 SEPTEMBER 2019 2359)
Now you have 7 tasks in the list.
```

### `event` - Creates an Event with a Start and End Date

Example of usage: 
```
event [eventDescription] /at DD/MM/YYYY HHMM - DD/MM/YYYY HHMM
event Attend Talk /at 20/09/2019 1000 - 20/09/2019 1200
```

Expected outcome:
```
Got it. I've added this task:
  [E][✗] Attend Talk (20 SEPTEMBER 2019 1000 - 20 SEPTEMBER 2019 1200)
Now you have 8 tasks in the list.
```

### `done` - Marks item as completed in the Task List

Example of usage: 
```
done [taskNumber]
done 6
```

Expected outcome:
```
Nice! I've marked this task as done:
  [T][✓] Write Readme
```

### `delete` - Deletes item off the Task List

Example of usage: 
```
delete [taskNumber]
delete 6
```

Expected outcome:
```
Noted. I've removed this task: 
  [T][✓] Write Readme
Now you have 7 tasks in the list.
```

### `find` - Find via keyword a specific Task in the Task List

Example of usage: 
```
find [keyword]
find return
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [D][✓] return book (by: 10 DECEMBER 2019 0830)
```

### `contacts` - Show your list of contacts

Example of usage: 
```
contacts
```

Expected outcome:
```
Here is your list of contacts:
1. Name: LoremIpsum
Relationship: FakePerson
Contact Number: 94350294
Email: loremipsum@gmail.com

2. Name: DeXun
Relationship: Myself
Contact Number: -999
Email: chia.dexun97@gmail.com
```

### `addContact` - Find via keyword a specific Task in the Task List

Example of usage: 
```
addContact [Name], [Relationship], [Phone Number], [Email]
addContact Damith, Professor, 000, damith@u.nus.edu
```

Expected outcome:
```
Got it. I've added this contact:
Name: Damith
Relationship: Professor
Contact Number: 000
Email: damith@u.nus.edu
```

### `findContact` - Find a contact in the contact list

Example of usage: 
```
findContact [keyword]
findContact D
```

Expected outcome:
```
Here is your list of contacts matching your keyword:
1. Name: DeXun
Relationship: Myself
Contact Number: -999
Email: chia.dexun97@gmail.com

2. Name: Damith
Relationship: Professor
Contact Number: 000
Email: damith@u.nus.edu
```

### `deleteContact` - Delete a contact in the contact list

Example of usage: 
```
deleteContact [index number]
deleteContact 3
```

Expected outcome:
```
Noted. I've removed this contact: 
Name: Damith
Relationship: Professor
Contact Number: 000
Email: damith@u.nus.edu

Now you have 2 contacts in your contact list.
```

### `bye` - Ends and shutsdown the program

Example of usage: 

```
bye
```

Expected outcome: <i>Program Terminates and Closes</i>
