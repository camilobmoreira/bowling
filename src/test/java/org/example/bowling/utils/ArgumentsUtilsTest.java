package org.example.bowling.utils;


import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * Test class for {@link ArgumentsUtils}
 *
 * @author camilo
 */
@RunWith(JUnit4.class)
public class ArgumentsUtilsTest {

    @Test
    public void convertArgumentsToMapTest$simpleTestOneParameter() {
        String[] args = new String[] { "Example.txt" };
        Map<String, String> argumentsMap = ArgumentsUtils.convertArgumentsToMap(args);
        String[] argsExpected = new String[] { "Example.txt", "\t" };
        Assertions.assertAll(
                () -> Assertions.assertNotNull(argumentsMap),
                () -> Assertions.assertEquals(argsExpected.length, argumentsMap.size()),
                () -> Assertions.assertEquals(argsExpected[0], argumentsMap.get(ArgumentsUtils.FILE_NAME)),
                () -> Assertions.assertEquals(argsExpected[1], argumentsMap.get(ArgumentsUtils.VALUE_SEPARATOR))
        );
    }

    @Test
    public void convertArgumentsToMapTest$simpleTestOneParameterWithFlag() {
        String[] args = new String[] { "-f Example.txt" };
        Map<String, String> argumentsMap = ArgumentsUtils.convertArgumentsToMap(args);
        String[] argsExpected = new String[] { "Example.txt", "\t" };
        Assertions.assertAll(
                () -> Assertions.assertNotNull(argumentsMap),
                () -> Assertions.assertEquals(argsExpected.length, argumentsMap.size()),
                () -> Assertions.assertEquals(argsExpected[0], argumentsMap.get(ArgumentsUtils.FILE_NAME)),
                () -> Assertions.assertEquals(argsExpected[1], argumentsMap.get(ArgumentsUtils.VALUE_SEPARATOR))
        );
    }

    @Test
    public void convertArgumentsToMapTest$simpleTestTwoParametersWithFlag() {
        String[] args = new String[] { "--file Example.txt", "-s \t" };
        Map<String, String> argumentsMap = ArgumentsUtils.convertArgumentsToMap(args);
        String[] argsExpected = new String[] { "Example.txt", "\t" };
        Assertions.assertAll(
                () -> Assertions.assertNotNull(argumentsMap),
                () -> Assertions.assertEquals(argsExpected.length, argumentsMap.size()),
                () -> Assertions.assertEquals(argsExpected[0], argumentsMap.get(ArgumentsUtils.FILE_NAME)),
                () -> Assertions.assertEquals(argsExpected[1], argumentsMap.get(ArgumentsUtils.VALUE_SEPARATOR))
        );
    }

    @Test
    public void convertArgumentsToMapTest$simpleTestExampleParametersWithFlag() {
        String[] args = new String[] { "-e" };
        Map<String, String> argumentsMap = ArgumentsUtils.convertArgumentsToMap(args);
        String[] argsExpected = new String[] { "Example.txt", "\t" };
        Assertions.assertAll(
                () -> Assertions.assertNotNull(argumentsMap),
                () -> Assertions.assertEquals(argsExpected.length, argumentsMap.size()),
                () -> Assertions.assertEquals(argsExpected[0], argumentsMap.get(ArgumentsUtils.FILE_NAME)),
                () -> Assertions.assertEquals(argsExpected[1], argumentsMap.get(ArgumentsUtils.VALUE_SEPARATOR))
        );
    }

}
