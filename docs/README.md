# User Guide

## Features 

### Feature 1: Display Help
Shows list of functions and purpose.

Usage:

`help`

Expected outcome:

`list of functions and purpose`

### Feature 2: Display List
List items that are on the todo list.

Usage:

`list`

Expected outcome:

`Here are the tasks in your list: ...`

### Feature 3: Adding items
Add different types of items to be cleared (e.g. todo, event, deadline).

Usage:

`todo {description}`
`event {description} /at {date}`
`deadline {description} /by {date}`

Expected outcome:

Item is added to list.

### Feature 4: Item deletion
Delete items by index

Usage:

`delete {index}`

Expected outcome:

Item is removed from list.

Alternatively, you can also use `clear` to delete all items from the list.

### Feature 5: Mark item as done
Mark items by index

Usage:

`done {index}`

Expected outcome:

Item is marked as done.

### Feature 6: Saving
All items are automatically saved.

### Feature 7: Autocorrected commands
A list of possible commands will appear. Use the up and down arrow keys to navigate and press enter or right arrow key to autofill the command into the textbox.