package com.wgg.muwd.command.service;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.util.TestUtil;
import com.wgg.muwd.world.World;
import com.wgg.muwd.world.service.WorldBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommandRegistryTest {

    @Mock
    private WorldBuilder mockWorldBuilder;

    @Autowired
    @InjectMocks
    private CommandRegistry commandRegistry;

    private World world;

    private List<String> enabledCommandsForWorld = Arrays.asList("help", "look");

    @Before
    public void setup() {
        world = new World();
        world.setEnabledCommands(enabledCommandsForWorld);

        when(mockWorldBuilder.getCurrentlyLoadedWorld())
                .thenReturn(Optional.of(world));
    }

    @Ignore
    @Test
    public void testGettingAllEnabledCommandsForAWorld() {
        List<Command> allCommandsForWorld = commandRegistry.getAllCommandsForWorld(world);

        assertThat(allCommandsForWorld)
                .extractingResultOf("getCommandValue")
                .containsOnly("help", "look");
    }

    @Ignore
    @Test
    public void testRegisteringCommands() {
        final String commandValue = "test-command";
        final String commandResponseText = "response text here";
        Command commandToRegister = TestUtil.getTestCommand(
                commandValue,
                commandResponseText);

        commandRegistry.registerCommand(commandToRegister);
        Optional<Command> commandGottenOptional = commandRegistry.getCommandByValue(commandValue);

        assertThat(commandGottenOptional).isPresent();
        assertThat(commandGottenOptional.get())
                .isEqualToComparingOnlyGivenFields(commandToRegister, "getCommandValue", "getResponse");
    }
}
