package com.wgg.muwd.world.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgg.muwd.world.World;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
public class WorldBuilder implements EnvironmentAware {

    public static final String DEFAULT_WORLD_FILE = "worlds/default.world";
    public static final String WORLD_FILE_PARAM_KEY = "world.file";
    private World world;

    //can specify param to load a specific world file when the server starts
    //ie: java -jar muwd.jar --world.file=your.world
    @Override
    public void setEnvironment(Environment environment) {
        File worldFile = getWorldFileFromEnvironmentProperty(environment);

        if (!worldFile.exists()) {
            throw new IllegalArgumentException("Could not load world file! " + worldFile.getAbsolutePath());
        }

        loadWorld(worldFile);
    }

    private File getWorldFileFromEnvironmentProperty(Environment environment) {
        String worldFileLocation = environment.getProperty(WORLD_FILE_PARAM_KEY);
        if (StringUtils.isEmpty(worldFileLocation)) {
            worldFileLocation = DEFAULT_WORLD_FILE;
        }
        return new File(worldFileLocation);
    }

    private void loadWorld(File worldFile) {
        System.out.println("Loading file: " + worldFile.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        try {
            world = mapper.readValue(worldFile, World.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getListOfEnabledCommands() {
        List<String> enabledCommands = new ArrayList<>();

        if (null != world) {
            enabledCommands = world.getEnabledCommands();
        }

        return enabledCommands;
    }

    public Optional<World> getCurrentlyLoadedWorld() {
        if (null == world) {
            return Optional.empty();
        } else {
            return Optional.of(world);
        }
    }

    public boolean isWorldLoaded() {
        return getCurrentlyLoadedWorld().isPresent();
    }
}
