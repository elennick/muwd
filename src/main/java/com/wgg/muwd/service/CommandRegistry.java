package com.wgg.muwd.service;

import com.wgg.muwd.commands.Command;
import org.reflections.Reflections;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@Scope("singleton")
public class CommandRegistry {

    private Map<String, Command> registeredCommands;

    public CommandRegistry() {
        registeredCommands = new HashMap<>();

        try {
            initCommands();
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println("error initializing commands!");
            e.printStackTrace();
        }
    }

    public void registerCommand(Command command) {
        registeredCommands.put(command.getCommandValue(), command);
    }

    public Command getCommandByValue(String value) {
        return registeredCommands.get(value);
    }

    public Collection<Command> getAllCommands() {
        return registeredCommands.values();
    }

    private void initCommands() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("com.wgg.muwd.commands");
        Set<Class<? extends Command>> sourcesInPackage = reflections.getSubTypesOf(Command.class);
        for (Class<? extends Command> commandClass : sourcesInPackage) {
            Command command = commandClass.newInstance();
            registerCommand(command);
            System.out.println("registered command -> " + command.getCommandValue());
        }
    }
}
