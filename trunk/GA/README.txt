You must have Java 6 (SE at least) installed in your system and all environment variables set.
Be sure you have HOME variable set to Java's bin folder in order to execute the application.


The application has two approaches:

1) Main Execution (original idea). This tries to solve the problem using one individual per circuit.
2) Divide&Conquer Execution (alternative idea). This tries to solve the problem using one individual per output bit.

Actually, the application solves the problem faster with the second approach, so it's main class is default.

To run the application you must type (second approach, faster):

java -jar Binary2BCDConverterGA.jar

To run the application with the first approach you have to unpack the .jar provided and execute with 'java' command 
the following jala file: edu/itba/ia/tp1/main/Main.java, specifying all classpaths.

By now, these are the options to run the application. In a future, we are going to merge both solutions into the same
GUI, letting the user decide which approach wants to run.

Thank you.

Martín Heras (mheras@gmail.com)
Jorge Goldman (shorshi@gmail.com)
Fransisco Siviero (fsiviero@gmail.com)
