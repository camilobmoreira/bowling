package org.example.bowling.main;


import java.util.Map;

import org.example.bowling.utils.ArgumentsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {

        ArgumentsUtils.validateArgumentsPrintHelp(args);
        Map<String, String> arguments = ArgumentsUtils.convertArgumentsToMap(args);

    }
}
