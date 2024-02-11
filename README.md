#### Project Setup:

1. In the persistent.xml file, replace the placeholders with the credetials for DB.
2. Run the code in an editor or export the code as a JAR. 
3. As a JAR, it can then be deployed on Docker or EC2 machine.
4. Run the main App.java file and make it the executable when creating JAR.
5. When it runs it will open a command prompt starting with >>>
6. Following that, give commands as specified in the document link: https://www.google.com/url?q=https%3A%2F%2Fdocs.google.com%2Fdocument%2Fd%2F1DKYH6GZ863Mk2DgdGwlgf6HZyySrT9nHVulLO2qBLbs%2Fedit%3Fusp%3Dsharing%26urp%3Dgmail_link&source=gmail&ust=1707737357793000&usg=AOvVaw2qz52SYHhfz6W0aK7z_y57
7. If the session gets interrupted or on exit. Don't worry, you can continue where you left off.
8. To reset the parkinglot use reset command on the prompt(no arguments required).

#### Requirements:

1. Jdk 11
2. PostGreSql

#### Description:

Following is the description of the commands:

create_parking_lot n: Here n is the number of slots in parking lot. This will create a parking lot if no other parking lot exits in DB.
park vehicle_id color: Here vehicle_id and color are both strings, this command when a parking lot exists will park the carin the nearest slot(least slot number). 
leave slot_number: Here, slot_number is the slot number which will be vacated if the parking lot exists.
status: Here, a table with all the parked cars will be printed
registration_numbers_for_cars_with_colour color: This will print the registration id(vehicle_id) of all the vehicles with the color of String color
reset: This will purge all the data of parking lot and will reset it so you can create another parking lot. This query also exits the terminal so you have to restart the program


All the commands return INVALID QUERY when called out of sequence.
