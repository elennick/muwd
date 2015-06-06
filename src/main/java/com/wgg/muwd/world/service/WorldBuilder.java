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

    private Environment environment;

    private boolean worldLoaded;

    private final String defaultWorldFile = "worlds/default.world";

    public WorldBuilder() {
        worldLoaded = false;
    }

    //can specify param to load a specific world file when the server starts
    //ie: java -jar muwd.jar --world.file=your.world
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

        String worldFileLocation = environment.getProperty("world.file");
        if (StringUtils.isEmpty(worldFileLocation)) {
            worldFileLocation = defaultWorldFile;
        }

        loadWorld(worldFileLocation);

    }

    private void loadWorld(String worldFileLocation) {
        File worldFile = new File(worldFileLocation);
        if(!worldFile.exists()) {
            throw new RuntimeException("Could not load world file! " + worldFile.getAbsolutePath());
        }

        System.out.println("Loading file: " + worldFile.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        try {
            World world =  mapper.readValue(worldFile, World.class);
            System.out.println("world = " + world);
        } catch (IOException e) {
            e.printStackTrace();
        }

        worldLoaded = true;
    }
}
