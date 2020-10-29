# SberStart Project in alpha
---
Title: alpha_project  
Author: Alexey Orkhoyan  
Date: 29.10.2020
---
### Sberstart alpha_project for SB
This program allows the user to monitor the directory for new files.

Available functionality
---
Implemented the ability to select a directory for monitoring new files.

Interaction types
---
To interact with the program, the user needs to use the console.

Using
---
At the beginning, the user is given the opportunity to select a directory for monitoring.
Appearance of new files is recorded in the log file.  
If new files appear in the directory, the program leaves an entry with the name, file 
extension type and date of its creation. If these files have extensions ".json" or ".xml",
the program leaves an entry about the number of lines in these files.  
If these files have other extensions, the program removes them.
The program also leaves an entry with the start time of handling the new file, and the time
spent on handling.

---
Note
---

The default directory for monitoring new files is called `demo`.  
The program can monitor the appearance of no more than 500 files at a time.  
Logging is done using `Log4j` and `slf4j`.


