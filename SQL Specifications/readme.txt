# FOR USER_INFO TABLE
In NetBeans, create a database called UserDB, with a schema called APP and a basic table called USER_INFO.
Right click the table on the Services tab and click Recreate Table.
Import the USER_INFO file.

# FOR APPLICANT TABLE (NEW)
In MySQL Workbench, ceate a schema called 'file'.
Go to the Server tab > Data Import, choose Import from Self-Contained File.
Verify that the settings are correct then import.
You will most likely need to change the URLs of resume_filepath for testing purposes.

# FOR APPLICANT TABLE (OLD)
Run the TXT code in MySQL Workbench.
You may need to create your own dummy data.

# !!!!!!!!!!!!!! NOTE BEFORE STARTING UP THE PROGRAM !!!!!!!!!!!!!!
Before launching/cleaning & building/deploying the program,
please go to web.xml and change the url, username, and password values to match your database specicfications, AS WELL AS the associated '_sql' versions.
Append ?useSSL=false to your personal MySQL url to make it work.
