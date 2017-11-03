package com.wgg.muwd.util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class NamePicker {

    private final List<String> names = Arrays.asList("Evan", "Josh", "Natalie", "Molly");
    private Random random = new Random();

    public String getRandomName() {
        int randomArrayPosition = randomInt(0, names.size());
        return names.get(randomArrayPosition);
    }

    private int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
