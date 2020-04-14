Firmstep Interview Test Submission - Sam O'Floinn
***
Choice of tools
- HTML and PHP are popular, versatile programming languages that suit the needs of many web developers. Since I am experienced in these, they were a natural fit for the core of this program. The PHP functionality of a self-generating page, plus its conditional checking to avoid errors with the database, proved useful when it came to testing.
- I also chose to use MySQL to handle the queue format. While it would have been possible to store the queue data in some session variables, I decided such would not have been a good format for this application - generally, important data needs a database. Hence, a MySQL database table accompanies this project. Note that the MySQL came with Wampserver.
- On the above two notes, the file also requires a separate "dbh.php" page be included, which manages connecting to the database. This is done for security.
- I chose the Bootstrap framework to provide CSS that made the site more presentable. While it was not a framework I had any prior practice with, I found it to be easy to learn and apply, using its classes for multiple HTML functions quickly. Furthermore, given Bootstrap's widespread use, I decided learning it would be a valuable tool for future projects.
***
MySQL  database format
- Requires a MySQL database with a database table named "firm", with the columns "id" (int(6)", "service" (varchar 20), "customer" (varchar 20), "name" (varchar 30), "time" (varchar 30).
