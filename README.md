![eBayK Logo](https://www.ebay-kleinanzeigen.de/static/img/common/logo/logo-ebayk-402x80.png)
# SQL Coding Challenge

Congratulations! You made it to the eBay Kleinanzeigen remote SQL Coding Challenge, where we want to see your hands-on coding skills.


## Steps

1. Check installation prerequisites: JDK version >=11, Maven latest version, your preferred IDE/editor. To see that everything basic is working, execute `mvn verify`, it should show you a `SUCCESS` build.
2. Read carefully through "Requirements" and "Out-of-scope" to focus on the right things.
3. After finishing coding, please add your comments and remarks inside this `README.md` file, see end of file.
4. Send back the result as .zip file to the person who sent it originally to you. Please do within **3 hours** after you received the task. No need to be hyper-punctual (e.g. 10 minutes earlier or sooner won't make a difference).

Coding guidance: Combine "clean, object-oriented, tested code" with "working software".


##  Requirements

* Structure and design [`Table.java`](/src/main/java/exercise/Table.java)
* Read and parse data from [`purchases.csv`](/src/main/resources/purchases.csv) and [`users.csv`](/src/main/resources/users.csv) and initialize to [`Table.java`](/src/main/java/exercise/Table.java)
* [`Table.java`](/src/main/java/exercise/Table.java) should be generic, column-names are inside .csv files and parsing should be based on this source.
* Implement an ORDER BY DESC in [`Sql.java#orderByDesc`](/src/main/java/exercise/Sql.java) ([SQL reference]( http://www.w3schools.com/sql/sql_orderby.asp))
* Implement an INNER JOIN in [`Sql.java#join`](/src/main/java/exercise/Sql.java) ([SQL reference]( https://www.w3schools.com/sql/sql_join.asp))


## Out-of-scope

* NO "real" persistence necessary (do not integrate MySQL, HSQLDB or JPA). Use your own internal storage model (see also [`Table.java`](/src/main/java/exercise/Table.java)).
* NO need to implement any SQL parser or anything, Java code interface is sufficient.
* NO need to be Maven expert or adapt build, project harness and build is setup already.
* NO Javadoc necessary. Tests and self explaining code are sufficient.


## Your comments / remarks

What would you do if you had more time? Which trade-offs did you take?

I would like to create Generic classes for Row() and Column instead of having a String List to hold rows. 
Generic Table Class which returns T class(for example for users.csv, a User class with given fields).
I did not spend time for outer joins, i assume inner join covers our case.
No test if number of column is same for each row when inserting.
I did not focus on time/space complexity at all, it can be enhanced.


Have fun!
