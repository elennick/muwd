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

@Service
@Scope("singleton")
public class WorldBuilder implements EnvironmentAware {

    private boolean worldLoaded = false;

    public static final String DEFAULT_WORLD_FILE = "worlds/default.world";

    public static final String WORLD_FILE_PARAM_KEY = "world.file";

    //can specify param to load a specific world file when the server starts
    //ie: java -jar muwd.jar --world.file=your.world
    @Override
    public void setEnvironment(Environment environment) {
        File worldFile = getWorldFileFromEnvironmentProperty(environment);

        if(!worldFile.exists()) {
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
            World world =  mapper.readValue(worldFile, World.class);
            System.out.println("world = " + world);
            worldLoaded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isWorldLoaded() {
        return worldLoaded;
    }
}
