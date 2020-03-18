Requirement of this TODO project:


1. Use Spring 4.3.10.RELEASE, Hibernate, maven, tomcat 8.5 or up, mysql 5.6 or up, and docker at last. 
2. Open a GitHub account or use your existing one, whichever you prefer, upload and update your code
    to the repo and share the repo with me. GUI is not needed, just need restful API and tests 
    verifying functionalities.

It fulfills basic todo features:
1. Post a to-do item(entity creation); change content in the to-do item; delete a to-do item
2. Read the item/items and query to-do items by status.(entity query). "status" is another object.
3. Assign a to-do item a status ("done", "in progress", "unable to fulfill", can be arbitrary status, 
(DB M-N relationship)), basically the CRUD of status.
4. It has two kinds of user roles, to-do submitter (can do all operation), to-do reader (can do only read todo item).
5. It can have multiple users, each user can have one or two roles. (DB many-to-many relationship).

Need to complete Restful API design.

Include tests, for example one test verifies creation of to-do item. One test verifies changing content,
one test verifies different combinations of operation.  Include tests that you think can verify the functionalities of the to-do.
Code coverage needs to be above 90%.
 
Data should be persisted using Hibernate and MySQL 5.6.x
Firstly attempt to run application without using docker. The use docker to setup two containers,
database and built WAR app running in a tomcat.

In the mean time, FortiPortal project can be accessed using https://dops-gerrit.fortinet-us.com/

I will provide wiki about how to use wiki later.

In the process of developing this TODO project, please closely discuss with me if you encounter any
problem. 

Should you need knowledge of spring framework and hibernate, I would like to suggest you following the Udemy course:
https://www.udemy.com/course/spring-hibernate-tutorial/