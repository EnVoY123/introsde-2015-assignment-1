First of all,  this assignment is about XML, XSD, XPATH techologies. Also, it demonstrate marhalling and unmarhalling XML and JSON by using JAXB techologies.


###How to launch it###

Make sure that you are already installed ANT on your machine and then type on console the following instructions:

			ant execute.evaluation

Command "execute.evaluation" contains a different types of commands such as:

	"compile" - Compiling all the source files 
	"execute.PeopleID" - Executing search person with selected ID. In this case, ID = 10
	"execute.Weight" - Executing a search on people with weight. In this case, weight > 95
	"execute.HPReader" - Executing the Unmarhalling of people 
	"execute.HPWriter" - Executing the marshalling of people to people.xml file
	"execute.HPJson" - Marshalling to JSON format

###Project structure###

The root folder contains files ivy.xml and build.xml files which used in this project for dowloading all important libs and compiling all files.
You can see a src folder. This src-package contains all necessary java-files. The main classes are PeopleProfile, PeopleProfileJson, PeopleProfileReader and PeopleWriter.




