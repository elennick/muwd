package com.wgg.muwd.command.service;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.util.TestUtil;
import com.wgg.muwd.world.World;
import com.wgg.muwd.world.service.WorldBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandHandlerTest {

    @Mock
    private CommandRegistry mockCommandRegistry;

    @Mock
    private WorldBuilder mockWorldBuilder;

    @Autowired
    @InjectMocks
    private CommandHandler commandHandler;

    private String validCommandValue = "test-command";

    private String invalidCommandValue = "invalid-test-command";

    private String mockResponse = "test response";

    private Command command;

    @Before
    public void setup() {
        World world = new World();
        world.setName("test world");

        command = TestUtil.getTestCommand(validCommandValue, mockResponse);
        Optional<Command> commandOptional = Optional.of(command);

        when(mockCommandRegistry.getCommandByValue(validCommandValue))
                .thenReturn(commandOptional);

        when(mockCommandRegistry.getCommandByValue(invalidCommandValue))
                .thenReturn(Optional.empty());

        when(mockWorldBuilder.getListOfEnabledCommands())
                .thenReturn(Arrays.asList(validCommandValue));

        when(mockWorldBuilder.getCurrentlyLoadedWorld())
                .thenReturn(Optional.of(world));
    }

    @Test
    public void testValidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper(validCommandValue);
        Optional<String> actualResponse = commandHandler.handleCommandInput(commandWrapper);

        assertThat(actualResponse).isPresent();
        assertThat(actualResponse.get()).isEqualTo(mockResponse);
    }

    @Test
    public void testInvalidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper(invalidCommandValue);
        Optional<String> actualResponse = commandHandler.handleCommandInput(commandWrapper);

        assertThat(actualResponse).isPresent();
        assertThat(actualResponse.get()).isNotEqualTo(mockResponse).contains("Unrecognized command");
    }

}
