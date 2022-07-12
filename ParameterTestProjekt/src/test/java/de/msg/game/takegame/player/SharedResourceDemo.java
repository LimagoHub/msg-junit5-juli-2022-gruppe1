package de.msg.game.takegame.player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;
import static org.junit.jupiter.api.parallel.ResourceAccessMode.*;

@Execution(CONCURRENT)
@ExtendWith(MockitoExtension.class)
class SharedResourcesDemo {
    @Test
    @ResourceLock(value = "system.properties", mode = READ)
    void customPropertyIsNotSetByDefault() {
        assertNull(System.getProperty("my.prop"));
    }

    @Test
    @ResourceLock(value = "system.properties", mode = READ_WRITE)
    void canSetCustomPropertyToFoo() {
        System.setProperty("my.prop", "foo");
        assertEquals("foo", System.getProperty("my.prop"));
    }

    @Test
    @ResourceLock(value = "system.properties", mode = READ_WRITE)
    void canSetCustomPropertyToBar() {
        System.setProperty("my.prop", "bar");
        assertEquals("bar", System.getProperty("my.prop"));
    }
}