# Duke User Guide

![Screenshot](Ui.png)

## Format of this guide

User input is prefixed with `>`.

Required parts are surrounded by angle brackets `<>`.

Optional parts are surrounded by square brackets `[]`.

These brackets are not part of the actual typed commands.

## Features

### Tasks, deadlines, events and loans

You can manage tasks, deadlines, events and loans all in one app!

### Simple command-line interface

Perfect for those who prefer no-frills programs.

## Usage

### `todo` &mdash; Add todo task

Add a task you need to do.

```
> todo <description>
```

### `deadline` &mdash; Add deadline

Add a deadline you need to meet.

```
> deadline <description> [/by <time: d/m/yyyy hh:mm>]
```

### `event` &mdash; Add event

Add an event you need to attend.

```
> event <description> [/at <time: d/m/yyyy hh:mm>]
```

### `loanin` &mdash; Add loan in

Add a loan you made from someone.

```
> loanin <amount> <description> [/by <time: d/m/yyyy hh:mm>]
```

### `loanout` &mdash; Add loan out

Add a loan you made to someone.

```
> loanout <amount> <description> [/by <time: d/m/yyyy hh:mm>]
```

### `list` &mdash; List tasks

List tasks.

```
> list
```

### `find` &mdash; Filter tasks

Search for tasks whose description contains a given keyword.

```
> find <keyword>
```

### `done` &mdash; Mark tasks as done

Mark a task as done. The task number is as shown by `list`.

```
> done <task number>
```

### `delete` &mdash; Delete tasks

Delete a task. The task number is as shown by `list`.

```
> delete <task number>
```

### `bye` &mdash; Exit

Exit Duke.

```
> bye
```
