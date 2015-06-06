package com.wgg.muwd.builder.service;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Scope("singleton")
public class WorldBuilder implements EnvironmentAware {

    private Environment environment;

    private boolean worldLoaded;

    private final String defaultWorldFile = "worlds/default.world";

    public WorldBuilder() {
        worldLoaded = false;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;

        String worldFile = environment.getProperty("world.file");
        if (!StringUtils.isEmpty(worldFile)) {
            worldFile = defaultWorldFile;
        }

        loadWorld(worldFile);

    }

    private void loadWorld(String worldFile) {
        System.out.println("worldFile = " + worldFile);

        worldLoaded = true;
    }
}
