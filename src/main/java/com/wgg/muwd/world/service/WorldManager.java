package com.wgg.muwd.world.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wgg.muwd.client.ClientRegistry;
import com.wgg.muwd.client.NonPlayerCharacter;
import com.wgg.muwd.client.PlayerCharacter;
import com.wgg.muwd.util.WebsocketUtil;
import com.wgg.muwd.world.Room;
import com.wgg.muwd.world.World;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WorldManager implements EnvironmentAware {

    static final String DEFAULT_WORLD_FILE = "worlds/default.world";
    static final String WORLD_FILE_PARAM_KEY = "world.file";
    private final ClientRegistry clientRegistry;
    private final WebsocketUtil websocketUtil;
    private World world;
    private Random random = new Random();

    @Autowired
    public WorldManager(ClientRegistry clientRegistry, WebsocketUtil websocketUtil) {
        this.clientRegistry = clientRegistry;
        this.websocketUtil = websocketUtil;
    }

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
        log.info("Loading world file: {}", worldFile.getAbsolutePath());

        ObjectMapper mapper = new ObjectMapper();
        try {
            world = mapper.readValue(worldFile, World.class);

            for (NonPlayerCharacter npc : world.getNpcs()) {
                clientRegistry.putNpc(npc);
            }

            startNpcActing();
        } catch (IOException e) {
            log.error("Failed to load a world!", e);
            throw new RuntimeException("Failed to load a world!");
        }
    }

    public Optional<Room> getRoomById(Long id) {
        return world.getRoomById(id);
    }

    public Room getCurrentRoom(PlayerCharacter client) {
        return getRoomById(client.getCurrentRoom()).get();
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

    boolean isWorldLoaded() {
        return getCurrentlyLoadedWorld().isPresent();
    }

    private void startNpcActing() {
        List<NonPlayerCharacter> npcs = clientRegistry.getAllNpcs();
        new Thread(() -> {
            while (true) {
                try {
                    int delay = 30 + random.nextInt(90);
                    TimeUnit.SECONDS.sleep(delay);

                    for (NonPlayerCharacter npc : npcs) {
                        Long roomId = npc.getCurrentRoom();
                        List<String> dialogue = npc.getDialogue();
                        String randomDialogueLine = dialogue.get(random.nextInt(dialogue.size()));
                        String message = npc.getName() + " says \"" + randomDialogueLine + "\"";
                        websocketUtil.sendToAllInRoom(roomId, message);
                    }
                } catch (InterruptedException e) {
                    log.error("Thread interrupted", e);
                }
            }
        }).start();
    }

    public String getMotd() {
        return world.getMotd();
    }
}
