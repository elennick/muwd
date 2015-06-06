package com.wgg.muwd.service;

import com.wgg.muwd.MuwdApplication;
import com.wgg.muwd.commands.Command;
import com.wgg.muwd.controller.model.CommandWrapper;
import com.wgg.muwd.controller.model.ResponseWrapper;
import com.wgg.muwd.util.TestUtil;
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

    private String commandValue = "test-command";

    private String response = "test response";

    private Command command;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        command = TestUtil.getTestCommand(commandValue, response);
        when(mockCommandRegistry.getCommandByValue(commandValue))
                .thenReturn(command);
    }

    @Test
    public void testValidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper(commandValue);
        ResponseWrapper responseWrapper = commandHandler.handleCommandInput(commandWrapper);

        assertThat(responseWrapper.getContent()).isEqualTo(response);
    }

    @Test
    public void testInvalidCommand() {
        CommandWrapper commandWrapper = new CommandWrapper("invalid-test-command");
        ResponseWrapper responseWrapper = commandHandler.handleCommandInput(commandWrapper);

        assertThat(responseWrapper.getContent()).isNotEqualTo(response).contains("Unrecognized command");
    }


}
