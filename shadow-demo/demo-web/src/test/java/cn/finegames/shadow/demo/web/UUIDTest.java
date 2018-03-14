package cn.finegames.shadow.demo.web;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

/**
 * TODO
 *
 * @author wang zhaohui
 * @since 1.0.0
 */
public class UUIDTest {

    @Test
    public void testUUID() {
        Assert.assertNotEquals(UUID.randomUUID().toString(), UUID.randomUUID().toString());
    }
}
