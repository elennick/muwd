package com.wgg.muwd.util;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.command.service.CommandRegistry;

public final class TestUtil {

    private TestUtil() {
        //dont instantiate static utils class
    }

    public static Command getTestCommand(String commandValue, String response) {
        return new MockCommand(commandValue, response);
    }

    private static class MockCommand extends Command {

        private String commandValue;

        private String response;

        public MockCommand(String commandValue, String response) {
            this.commandValue = commandValue;
            this.response = response;
        }

        @Override
        public String getCommandValue() {
            return commandValue;
        }

        @Override
        public String getResponse(String[] input, CommandRegistry commandRegistry) {
            return response;
        }

        @Override
        public String getHelpText() {
            return "test help text";
        }
    }
}
