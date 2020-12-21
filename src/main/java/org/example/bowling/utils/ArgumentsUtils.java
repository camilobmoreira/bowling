package org.example.bowling.utils;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class used to help dealing with command line arguments
 *
 * @author camilo
 */
public class ArgumentsUtils {

    private static List<String> HELP_REGEX = Arrays.asList("-h", "--help");
    private static List<String> EXAMPLE_REGEX = Arrays.asList("-e", "--example");
    private static List<String> FILE_NAME_REGEX = Arrays.asList("-f", "--file");
    private static List<String> VALUE_SEPARATOR_REGEX = Arrays.asList("-s", "--separator");

    public static String EXAMPLE_FILE_NAME = "Example.txt";
    public static String DEFAULT_SEPARATOR = "\t";

    public static String FILE_NAME = "fileName";
    public static String VALUE_SEPARATOR = "valueSeparator";

    /**
     * Validates provided arguments. Prints help dialog if needed, containing all the options available.
     * @param args argumnets to be validated
     */
    public static void validateArgumentsPrintHelp(String[] args) {
        if (args.length == 0 || HELP_REGEX.contains(args[0])) {
            printHelpMe();
        }
    }

    /**
     * Prints help dialog
     */
    private static void printHelpMe() {
        System.out.println();
        System.out.println("Usage: ");
        System.out.println("\tmvn clean compile exec:java -Dexec.arguments=\"-f\\ Example.txt, -s \\t\"");//fixme
        System.out.println("\tSpaces and special characters should be espaped with \"\\\" as in the above example.");
        System.out.println("\tArguments should be separated by a comma.");
        System.out.println();
        System.out.println("\tIf you are specifying just the file name or path, you can omit the -f flag as below:");
        System.out.println("\tmvn clean compile exec:java -Dexec.arguments=\"Example.txt\"");
        System.out.println();
        System.out.println("Options");
        System.out.println("\t-h, --help \t\t \t\t\t prints this message");
        System.out.println("\t-e, --example \t\t \t\t\t runs an example file");
        System.out.println("\t-f, --file \t\t <FILE_NAME_OR_PATH> \t specify the name or path of the file");
        System.out.println("\t-s, --separator \t [<VALUE_SEPARATOR>] \t specify the value separator. Optional, default: tab");
        System.out.println();
    }

    /**
     * Parses provided arguments and converts it to a map.
     * If just one arguments is provided, it assumes it's the {@link ArgumentsUtils#FILE_NAME} and then adds to the map
     * the default value of {@link ArgumentsUtils#VALUE_SEPARATOR}
     * @param args arguments to be converted to a map
     * @return map of arguments
     */
    public static Map<String, String> convertArgumentsToMap(String[] args) {
        Map<String, String> arguments = new HashMap<>();
        if (args.length == 1 && !EXAMPLE_REGEX.contains(args[0])) {
            arguments.put(FILE_NAME, args[0].contains(" ") ? args[0].split(" ")[1] : args[0]);
            arguments.put(VALUE_SEPARATOR, DEFAULT_SEPARATOR);
            return arguments;
        }
        Arrays.stream(args).forEach(arg -> {
            String[] split = arg.split(" ");
            String part1 = split[0];
            if (FILE_NAME_REGEX.contains(part1.toLowerCase())) {
                arguments.put(FILE_NAME, split[1]);
            } else if (VALUE_SEPARATOR_REGEX.contains(part1.toLowerCase())) {
                arguments.put(VALUE_SEPARATOR, split[1]);
            } else if (EXAMPLE_REGEX.contains(part1.toLowerCase())) {
                arguments.put(FILE_NAME, EXAMPLE_FILE_NAME);
                arguments.put(VALUE_SEPARATOR, DEFAULT_SEPARATOR);
            }
        });
        return arguments;
    }

}
