package com.github.stantonk;

import com.netflix.governator.annotations.Configuration;

public class OtherConfig {
    @Configuration("some.thing.enabled")
    public boolean someThingEnabled = false;

    @Configuration("some.thing.timeout")
    public int someThingTimeout = 10;

    @Override
    public String toString() {
        return "OtherConfig{" +
                "someThingEnabled=" + someThingEnabled +
                ", someThingTimeout=" + someThingTimeout +
                '}';
    }
}
