## Bowling Score Board

This shell application's intent is to read and parse a text file containing the results for
one or more players bowling 10 frames each, according to these guidelines:

* Each line represents a player and a chance with the subsequent number of pins knocked down.
* An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).

By default, the values are tab-separated, but it can be any other character as long as
specified as a parameter when executing this app.

An example of file can be found [here](./Example.txt) or in the excerpt below:

```
Jeff	10
John	3
John	7
Jeff	7
Jeff	3
John	6
John	3
```

---

### How to run:

To run, fist you need to compile it by typeing the following in the terminal:
```
mvn clean compile
```

And then:
```
mvn exec:java -Dexec.arguments="Exemple.txt"
```

If no arguments are provided, or if the `-h` or `--help` flags are provided, the help text
is gonna be printed, as below:

```
Usage:
	mvn exec:java -Dexec.arguments="-file\ Example.txt, -s\ \t"
	Spaces and special characters should be espaped with "\" as in the above example.
	Arguments should be separated by a comma.

	If you are specifying just the file name or path, you can omit the -f flag as below:
	mvn exec:java -Dexec.arguments="Example.txt"

Options
	-h, --help 		 			 prints this message
	-e, --example 		 			 runs an example file
	-f, --file 		 <FILE_NAME_OR_PATH> 	 specify the name or path of the file
	-s, --separator 	 [<VALUE_SEPARATOR>] 	 specify the value separator. Optional, default: tab
```

As shown in the help text, to specify the file name or path, you can use `-f` or `--file`, like this:
```
mvn exec:java -Dexec.arguments="-f Exemple.txt"
```

If you want to specify a different character as a value separator, you can use `-s` or
`--separator`.  
Note that any special character, such as spaces, commas, tabs or any other, needs to be espaced
with a backslash `\ \`. For example, to read and parse a CSV file, you can do like this:
```
mvn exec:java -Dexec.arguments="-f\ Exemple.csv,-s\ \,"
```

There's also an option to run the app with an example provided file using `-e` or `--example`,
like this:
```
mvn exec:java -Dexec.arguments="--example"
```

---

### Other docs

A PDF file with the complete documentation for this chalenge can be found [here](./java-challenge-bowling.pdf)
