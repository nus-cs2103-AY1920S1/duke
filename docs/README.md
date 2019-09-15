# Duke user guide

__Table of contents__

<a href="#1">1. General Information</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1.1">1.1 System overview</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#1.2">1.2 Organization of READ.me</a>
<br>
<br>
<a href="#2">2. Getting Started</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2.1">2.1 Installation of Duke</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#2.2">2.2 System commands</a>
<br>
<br>
<a href="#3">3. Special Instruction for Error Correction</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3.1">3.1 Exception handling</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#3.2">3.2 Help page</a>
<br>
<br>
<a href="#4">4. Feedback and Contact us</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#4.1">4.1 Contact us</a>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#4.2">4.2 Special thanks</a>

---
## <a id="1">1. General Information</a>
### <a id="1.1">1.1 System overview</a>
Welcome to Duke! Duke acts as your trusty online secretary where he helps you to keep track of all your key dates for all your important events. These tasks that you have can be classified into To Do, Events and Deadline tasks. There are also other features that Duke can do for your convenience. Get your very own Duke today!
<br>

### <a id="1.2">1.2 Organization of READ.me</a>
The user's manual consists of 4 sections: General Information, Getting Started, Special Instruction for Error Correction and Feedback and Contact us.
<br>

| Section   | Description    | 
|:-----------|------------|
| General Information    |Gives a broad overview of what Duke does and the <br> purpose for which it is intended.
| Getting started			 |Instructions to installing Duke and the commands <br> available for Duke.|	
| Special Instructions  |Highlights some of the possible issues users can <br> face while using Duke.| 
|Feedback / Contact|Provide information to contact the creator of Duke|   

<br>

---

## <a id="2">2. Getting started</a>
### <a id="2.1">2.1 Installation of Duke</a>
To get your very own of Duke, go to my GitHub [Duke](https://github.com/ngzhaoming/duke) repository. At that page, click the button `clone or download` and download the zip file. Once you have downloaded the zip file, extract the file and locate the `JAR` file for Duke. Assess the `JAR` file to startup Duke.
<br>
### <a id="2.2">2.2 System commands</a>
Here are the commands that are available for Duke.

| Basic Commands   | Advance key    | Description     |
|:-----------|:------------|:------------|
| list        |l|Shows the current list of task that the <br>user has while using Duke.
| todo [desc] |t [desc]| To Do task accompanied with the <br>description of the task. 
| deadline [desc] /by [time]       |d [desc] /by [time]|Deadline task accompanied with the <br>description of the task and also the <br>time when the task has to be completed.
| event [desc] /at [time]|e [desc] /at [time] | Event task accompanied with the <br>description of the task and also the <br>time when the task is occurring.
|find [keyword]|f [keyword]|Given a keyword from the user, Duke<br>will search and show tasks that matches<br>the keyword.
|done [index]|do [index] | Checks the specified task in the list.
|delete [index] | dd [index] | Deletes the specified task in the list.
|help| -| Provides a list of commands that Duke<br> supports for the user.
|morehelp| - |Links users to a help page for Duke.
|bye|bb|Save the current list and close Duke.

<br>

---

## <a id="3">3. Special Instruction for Error Correction</a>

### <a id="3.1">3.1 Exception Handling</a>
Here are some of the possible violations that users may encounter when using Duke:

* Number error exception: The specific index that the user give is not a valid number recognized by Java

* Index out of bound exception: The specific index that the user give is out of the range of the number of task in the task list.

* Duke exception: Duke exceptions are thrown when the user gives an invalid command which Duke does not recognize.

Should there be any other issues faced with Duke, feel free to contact the developer instead. Thank you!
<br>
### <a id="3.2">3.2 Help page</a>
Should there be any other issues with Duke, feel free to enter the `help` command to get a list of commands that Duke support. Also, typing `morehelp` will give a more detailed explanation on how to use Duke.
<br>
<br>

---

## <a id="4">4. Feedback and Contact us</a>
### <a id="4.1">4.1 Contact us</a>
Thank you for using Duke. Feel free to drop the developer a feedback in order to improve Duke further! I can be connected through the following channels:

1.  <a href="https://github.com/ngzhaoming">Github</a> 
2.  <a href="https://www.instagram.com/zhaoming_boiboi/">Instagram</a>
3.  <a href="www.linkedin.com/in/zhaoming-ng"> LinkedIn</a>

### <a id="4.2">4.2 Special thanks</a>


<marquee class="GeneratedMarquee" direction="right" scrollamount="5" behavior="alternate" fontFamily="comic sans">
	Thank you for using Duke! Hope that he is of good use to you! :)
</marquee>

<marquee class="GeneratedMarquee" direction="right" scrollamount="10" behavior="alternate" fontFamily="comic sans">
	Arigatou Gozaimasu! 🌚
</marquee>

```
 ________  ___  ___  ___  __    _______      
|\   ___ \|\  \|\  \|\  \|\  \ |\  ___ \     
\ \  \_|\ \ \  \\\  \ \  \/  /|\ \   __/|    
 \ \  \ \\ \ \  \\\  \ \   ___  \ \  \_|/__  
  \ \  \_\\ \ \  \\\  \ \  \\ \  \ \  \_|\ \ 
   \ \_______\ \_______\ \__\\ \__\ \_______\
    \|_______|\|_______|\|__| \|__|\|_______|                              
```

<style type="text/css" scoped>
.GeneratedMarquee {
font-size: 30;
font-family:'Comic Sans MS';
color:#FFFFFF;
}



