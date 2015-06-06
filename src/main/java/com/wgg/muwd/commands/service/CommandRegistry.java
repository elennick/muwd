package com.wgg.muwd.commands.service;

import com.wgg.muwd.commands.Command;
import org.reflections.Reflections;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
public class CommandRegistry {

    private Map<String, Command> registeredCommands;

    public CommandRegistry() {
        loadCommands();
    }

    public void registerCommand(Command command) {
        registeredCommands.put(command.getCommandValue(), command);
        for (String alias : command.getAliases()) {
            registeredCommands.put(alias, command);
        }
    }

    public Command getCommandByValue(String value) {
        return registeredCommands.get(value);
    }

    public List<Command> getAllCommands() {
        Collection<Command> allCommands = registeredCommands.values();
        List<Command> allCommandsWithoutDuplicates =
                allCommands.parallelStream().distinct().collect(Collectors.toList());
        return allCommandsWithoutDuplicates;
    }

    private void loadCommands() {
        initCommandMap();
        Set<Class<? extends Command>> allCommandClasses = getAllCommandClasses();
        registerAllCommands(allCommandClasses);
    }

    private void initCommandMap() {
        if(null == registeredCommands) {
            registeredCommands = new HashMap<>();
        } else {
            registeredCommands.clear();
        }
    }

    private Set<Class<? extends Command>> getAllCommandClasses() {
        Reflections reflections = new Reflections("com.wgg.muwd.commands");
        Set<Class<? extends Command>> sourcesInPackage = reflections.getSubTypesOf(Command.class);
        return sourcesInPackage;
    }

    private void registerAllCommands(Set<Class<? extends Command>> allCommandClasses) {
        for (Class<? extends Command> commandClass : allCommandClasses) {
            Command command = null;
            try {
                command = commandClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                System.out.println("error initializing command -> " + commandClass.getName());
                e.printStackTrace();
            }

            registerCommand(command);
            System.out.println("registered command -> " + command.getCommandValue());
        }
    }
}
