package com.wgg.muwd.commands;

import com.wgg.muwd.service.CommandRegistry;

import java.util.Collections;
import java.util.List;

abstract public class Command {

    abstract public String getCommandValue();

    public List<String> getAliases() {
        return Collections.emptyList();
    }

    abstract public String getResponse(String[] input, CommandRegistry commandRegistry);

    abstract public String getHelpText();

    @Override
    public String toString() {
        return getCommandValue();
    }
}
