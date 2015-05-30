package muwd.controller;

import muwd.model.InputMessage;
import muwd.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/messageinput")
    @SendTo("/topic/messageoutput")
    public OutputMessage message(InputMessage inputMessage) throws Exception {
        Thread.sleep(1000);
        return new OutputMessage("Hello, " + inputMessage.getContent() + "!");
    }

}
