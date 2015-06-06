package com.wgg.muwd.service;

import com.wgg.muwd.MuwdApplication;
import com.wgg.muwd.commands.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MuwdApplication.class)
public class CommandHandlerTest {

    @Mock
    private CommandRegistry mockCommandRegistry;

    @Autowired
    @InjectMocks
    private CommandHandler commandHandler;

    private MockCommand command;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        command = new MockCommand();
        when(mockCommandRegistry.getCommandByValue("test-command"))
                .thenReturn(command);
    }

    @Test
    public void testValidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper("test-command");
        ResponseWrapper responseWrapper = commandHandler.handleCommandInput(commandWrapper);

        assertThat(responseWrapper.getContent()).isEqualTo("test response");
    }

    @Test
    public void testInvalidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper("invalid-test-command");
        ResponseWrapper responseWrapper = commandHandler.handleCommandInput(commandWrapper);

        assertThat(responseWrapper.getContent()).isNotEqualTo("test response").contains("Unrecognized command");
    }

    private static class MockCommand extends Command {
        @Override
        public String getCommandValue() {
            return "test-command";
        }

        @Override
        public String getResponse(String[] input, CommandRegistry commandRegistry) {
            return "test response";
        }

        @Override
        public String getHelpText() {
            return "test help text";
        }
    }
}
