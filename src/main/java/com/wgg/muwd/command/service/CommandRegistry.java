package com.wgg.muwd.command.service;

import com.wgg.muwd.command.Command;
import com.wgg.muwd.world.World;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommandRegistry implements ApplicationContextAware {

    private Map<String, Command> registeredCommands;

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
        loadCommands();
    }

    private void loadCommands() {
        initCommandMap();
        registerAllCommands();
    }

    private void initCommandMap() {
        if (null == registeredCommands) {
            registeredCommands = new HashMap<>();
        } else {
            registeredCommands.clear();
        }
    }

    private void registerAllCommands() {
        Map<String, Command> commands = ctx.getBeansOfType(Command.class);
        for (Command command : commands.values()) {
            registerCommand(command);
            System.out.println("registered command -> " + command.getCommandValue());
        }
    }

    public void registerCommand(Command command) {
        registeredCommands.put(command.getCommandValue(), command);
        for (String alias : command.getAliases()) {
            registeredCommands.put(alias, command);
        }
    }

    public Optional<Command> getCommandByValue(String value) {
        Command command = registeredCommands.get(value);
        return Optional.ofNullable(command);
    }

    public List<Command> getAllCommands() {
        Collection<Command> allCommands = registeredCommands.values();
        List<Command> allCommandsWithoutDuplicates =
                allCommands.parallelStream().distinct().collect(Collectors.toList());

        return allCommandsWithoutDuplicates;
    }

    public List<Command> getAllCommandsForWorld(World world) {
        List<Command> commands = getAllCommands();
        List<String> enabledCommands = world.getEnabledCommands();

        return commands.stream()
                .filter(command -> enabledCommands.contains(command.getCommandValue()))
                .collect(Collectors.toList());
    }

    public boolean isCommandValidForWorld(String command, World world) {
        List<Command> allCommandsForWorld = getAllCommandsForWorld(world);
        List<String> allCommandValuesForWorld = allCommandsForWorld.stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        return allCommandValuesForWorld.contains(command);
    }

    public List<String> getAllCommandValues() {
        return registeredCommands.values().stream().map(Object::toString).collect(Collectors.toList());
    }
}
