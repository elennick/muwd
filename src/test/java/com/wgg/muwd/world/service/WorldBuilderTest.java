package com.wgg.muwd.world.service;

import com.wgg.muwd.MuwdApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.EnvironmentTestUtils;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MuwdApplication.class)
public class WorldBuilderTest {

    @Autowired
    private WorldBuilder worldBuilder;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test(expected = IllegalArgumentException.class)
    public void testLoadingWorldFileWithParam_Invalid() {
        String invalidFileName = "this-isnt-a-valid-file.world";
        String invalidParamString = WorldBuilder.WORLD_FILE_PARAM_KEY + "=" + invalidFileName;
        EnvironmentTestUtils.addEnvironment(environment, invalidParamString);

        worldBuilder.setEnvironment(environment);
    }

    @Test
    public void testLoadingWorldFileWithParam_Valid() {
        String validParamString = WorldBuilder.WORLD_FILE_PARAM_KEY + "=" + WorldBuilder.DEFAULT_WORLD_FILE;
        EnvironmentTestUtils.addEnvironment(environment, validParamString);

        worldBuilder.setEnvironment(environment);

        assertThat(worldBuilder.isWorldLoaded());
    }

    @Test
    public void testLoadingDefaultWorldFile() {
        worldBuilder.setEnvironment(environment);

        assertThat(worldBuilder.isWorldLoaded());
    }
}
