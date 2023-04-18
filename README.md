# Toll_Calculator
This Java program, TollCal, calculates the cost of a trip between two interchanges based on a given JSON file of interchange information.

The program requires the following dependencies:

json-simple-1.1.jar
junit.jar

The program requires the following files:

interchanges.json: A JSON file containing interchange information

To run the program, execute the main method in the TollCal class. It takes two arguments: the starting interchange and the ending interchange.

Example usage:
TollCal.costOfTrip("QEW", "Highway 400");
This will output the distance and cost of a trip between the "QEW" and "Highway 400" interchanges.

The program will return "Invalid From and To Interchanges. Please recheck given interchanges" if either the starting interchange or ending interchange is not found in the JSON file.

If there is an error loading the JSON file, the program will return "Null Exception Caught".

Note: The program assumes that the JSON file is named "interchanges.json" and is located in the same directory as the TollCal.java file. If your JSON file has a different name or is located in a different directory, update the FileReader constructor in the loadInterchanges method.


Running the Tests
TollCal includes a set of JUnit tests that can be run to ensure that the program is working correctly. To run the tests, execute the TestTollCal class located in the mypackage package.

You can run the tests from an IDE such as Eclipse or IntelliJ IDEA by right-clicking on the TestTollCal class and selecting "Run as JUnit test".
