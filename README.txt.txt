This app is currently generating queries to insert into neiist database.

It is generating emails from portuguese names with the following structure:  <first name>.<last name>@tecnico.ulisboa.pt
The ist id is a random number between 000000 and 999999 (e.g. ist418290)


The entities, organizations, degrees and campi, are all from the .txt files in the src/Names folder. If you want to add degrees or
organizations, just edit those files.


To execute this app

[Commands to compile]
	cd DB_RandomDataGenerator/src
	javac -cp ./Tables/*:./Names/*:./ *java

[To execute]
	java Main <Number of Data to generate>