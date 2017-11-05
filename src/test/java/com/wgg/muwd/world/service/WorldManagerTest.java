package com.wgg.muwd.world.service;

import com.wgg.muwd.MuwdApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MuwdApplication.class)
public class WorldManagerTest {

    @Autowired
    private WorldManager worldManager;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test(expected = IllegalArgumentException.class)
    public void testLoadingWorldFileWithParam_Invalid() {
        String invalidFileName = "this-isnt-a-valid-file.world";
        String invalidParamString = WorldManager.WORLD_FILE_PARAM_KEY + "=" + invalidFileName;
        EnvironmentTestUtils.addEnvironment(environment, invalidParamString);

        worldManager.setEnvironment(environment);
    }

    @Test
    public void testLoadingWorldFileWithParam_Valid() {
        String validParamString = WorldManager.WORLD_FILE_PARAM_KEY + "=" + WorldManager.DEFAULT_WORLD_FILE;
        EnvironmentTestUtils.addEnvironment(environment, validParamString);

        worldManager.setEnvironment(environment);

        assertThat(worldManager.isWorldLoaded());
    }

    @Test
    public void testLoadingDefaultWorldFile() {
        worldManager.setEnvironment(environment);

        assertThat(worldManager.isWorldLoaded());
    }
}
