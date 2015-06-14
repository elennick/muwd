package com.wgg.muwd.command.service;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandHandlerTest {

    @Mock
    private CommandRegistry mockCommandRegistry;

    @Autowired
    @InjectMocks
    private CommandHandler commandHandler;

    private String validCommandValue = "test-command";

    private String invalidCommandValue = "invalid-test-command";

    private String response = "test response";

    private Command command;

    @Before
    public void setup() {
        command = TestUtil.getTestCommand(validCommandValue, response);
        Optional<Command> commandOptional = Optional.of(command);

        when(mockCommandRegistry.getCommandByValue(validCommandValue))
                .thenReturn(commandOptional);

        when(mockCommandRegistry.getCommandByValue(invalidCommandValue))
                .thenReturn(Optional.empty());
    }

    @Test
    public void testValidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper(validCommandValue);
        ResponseWrapper responseWrapper = commandHandler.handleCommandInput(commandWrapper);

        assertThat(responseWrapper.getContent()).isEqualTo(response);
    }

    @Test
    public void testInvalidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper(invalidCommandValue);
        ResponseWrapper responseWrapper = commandHandler.handleCommandInput(commandWrapper);

        assertThat(responseWrapper.getContent()).isNotEqualTo(response).contains("Unrecognized command");
    }


}
