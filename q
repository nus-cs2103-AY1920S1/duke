[33mcommit 9e49a6612f20a66bb599b513255ed53bac55adc3[m[33m ([m[1;36mHEAD -> [m[1;32mmaster[m[33m)[m
Author: Gary Lim <garylyp@hotmail.com>
Date:   Tue Aug 20 01:16:35 2019 +0800

    Text UI Testing
    
    Changes made in this commit:
    1) Provided support to compare actual output against expected output

[33mcommit cb10b39a44ac01309b3940ae4b12235337aa6fd0[m[33m ([m[1;33mtag: Level-4[m[33m, [m[1;31morigin/master[m[33m, [m[1;31morigin/HEAD[m[33m)[m
Author: Gary Lim <garylyp@hotmail.com>
Date:   Sun Aug 18 01:41:58 2019 +0800

    Level 4. ToDos, Events, Deadlines (repost)
    
    Changes made in this commit:
    1) Introduced abstract Task class and implemented various subclasses.
    
    Task are now distinct objects instead of just a String name as in the
    third level.

[33mcommit f39baad6936b5ea60ad6cd61f9928b42382d506d[m
Author: Gary Lim <garylyp@hotmail.com>
Date:   Sun Aug 18 01:20:30 2019 +0800

    Level 4 ToDos, Events, Deadlines
    
    Changes made in this commit:
    1) Created the abstract Task class and various subclasses of Task.
    
    Tasks are now distinct objects instead of just Strings. Each task
    displays slightly different wordings on print.

[33mcommit 13683aec80839e6b70723626fea4d4c8537d4af2[m[33m ([m[1;33mtag: Level-3[m[33m)[m
Author: Gary Lim <garylyp@hotmail.com>
Date:   Sat Aug 17 02:03:19 2019 +0800

    Level 3. Mark as Done
    
    Changes made in this commit:
    1) Implemented feature to mark Task as done.

[33mcommit 3689ff4e1e212e414dfe44dfa4d24e267042e49a[m[33m ([m[1;33mtag: Level-2[m[33m)[m
Author: Gary Lim <garylyp@hotmail.com>
Date:   Sat Aug 17 01:23:38 2019 +0800

    Level 2. Add, List
    
    Changes made in this commit:
    1) Created a list to add and monitor input items.

[33mcommit 08ce8a42152b25e08a049e1217aa3b06347f2438[m[33m ([m[1;33mtag: Level-1[m[33m)[m
Author: Gary Lim <garylyp@hotmail.com>
Date:   Sat Aug 17 01:09:42 2019 +0800

    Level 1. Greet, Echo, Exit
    
    Changes made in this commit:
    1) Initialize the basic I/O features of the programme.

[33mcommit 999fa98979ab5be8233b7838451650d8345bb520[m
Merge: d2ffa00 8d6d6f4
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 15:18:12 2019 +0800

    Merge pull request #7 from j-lum/tutorial-fix
    
    Address issues #5 and #6

[33mcommit 8d6d6f4b95dd028ee97ba3df4d36b38ffc92d343[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 14:05:46 2019 +0800

    Add images to JavaFX tutorial 3

[33mcommit b6df1a32827a0d318ba241a10bfded88101daca3[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Thu Aug 15 13:59:56 2019 +0800

    Fix typo referring to `HelloWorld`

[33mcommit d2ffa00dbe45016b1c087a9a26e2d5be1d595ee4[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 14 15:35:46 2019 +0800

    Update Gradle tutorial to reflect new checkstyle config

[33mcommit f20d61019a2ea5ce166440b47e9a37a8bd373bb0[m
Merge: 5c47c23 bad66fc
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Wed Aug 14 15:30:37 2019 +0800

    Merge pull request #3 from j-lum/inline-javafx-tutorials
    
    Inline JavaFX tutorials

[33mcommit bad66fc459bc2173e26be31598e04c4315df7933[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Aug 12 14:24:27 2019 +0800

    Adapt JavaFX tutorial for Duke
    
    Rather than to have distinct tutorials building up to a Duke-like
    application, let's merge the tutorials into Duke's tutorials
    to enhance cohesiveness in the course material.
    
    We also merge the gradle/gradleless tutorials into one by providing a
    universal entry point to JavaFX in the style of AddressBook
    applications.
    
    Fix header levels in JavaFX Tutorial 1.
    
    Change code samples to use Duke.
    
    Add hints on required import statements.
    
    Add location hints to code snippets to help students find where to copy
    and paste them.
    
    Remove nitpicks to make the development process smoother.
    
    Fix usage of `Collections` to `FXCollections`.
    
    Replace image for JavaFX tutorial 3.
    
    Specify location to place images.
    
    Replace a screenshot that referred to the outdated package structure.
    
    Remove reference to DukeStub.

[33mcommit 5c47c238cef0369657cea1f3023dcc053aabe733[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 20:38:08 2019 +0800

    Specify checkstyle version
    
    Not specifying the checkstyle version causes it to fail with a cryptic
    error.

[33mcommit a2150c17028761c775b44368ede448060a7ce44e[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 20:10:10 2019 +0800

    textUiTestingTutorial.md: mention updating java/javac commands

[33mcommit 2d06f90400a17fd0ad95c0a68edf4917d12ee7ff[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 19:54:03 2019 +0800

    textUiTestingTutorial.md: add missing <br>

[33mcommit 5c491d3de4822b43f1db4c3bf806f81eae0c64a0[m
Author: damithc <damith@gmail.com>
Date:   Tue Aug 6 19:50:20 2019 +0800

    Add a tutorial on text UI testing

[33mcommit 53c04603712fd4132acd73091ffa37e29b7c0e70[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:15:04 2019 +0800

    Update list of contributors

[33mcommit 21af6a13f93c15964b20eb0ead90ef50ef70a42b[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Tue Aug 6 15:06:11 2019 +0800

    Fix typo in link to checkstyle config files

[33mcommit 8fe8afd9397509e450f821bc1f5524a3519ab488[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:29:40 2019 +0800

    README: use numbered list for steps

[33mcommit f20bff25cfb935b211b47aa3bcb5e690bbe614c0[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:26:04 2019 +0800

    Tweak tutorial text

[33mcommit 6a7120cbb6568134aef5d1d35e9596e4862acae4[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:08:53 2019 +0800

    Move Duke.java out of the package

[33mcommit 6bb6b9f6c525f5343fdc6dd3a43a086a11e9708e[m
Author: damithc <damith@gmail.com>
Date:   Mon Aug 5 19:08:08 2019 +0800

    .gitignore: add *.iml

[33mcommit 946f3bb509aa0710d45d820855ea3aa6cf096071[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Jul 29 17:14:46 2019 +0800

    Import base branch for students to start off
    
    As student may not have learnt about branching, let's include a copy of
    the base branch into the main repository.

[33mcommit 245013d3ac98862fe7c5f52fee9a7b5c389fb7ab[m
Author: Jeffry Lum <22460123+j-lum@users.noreply.github.com>
Date:   Mon Jul 29 17:14:46 2019 +0800

    Initial commit
