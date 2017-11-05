package com.wgg.muwd.util;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.websocket.Client;
import com.wgg.muwd.world.World;

import java.util.Optional;

public final class TestUtil {

    private TestUtil() {}

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
        public Optional<String> getResponse(String[] input, World world, Client client) {
            return Optional.of(response);
        }

        @Override
        public String getHelpText() {
            return "test help text";
        }
    }

}
