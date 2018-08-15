package util.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimplePropertiesFactoryTest {

    private SimplePropertiesFactory factory;

    @Before
    public void init() {
        factory = new SimplePropertiesFactory();
    }

    @Test
    public void getProperties() {
        Assert.assertNotNull(factory);
    }

    @Test
    public void getPropertiesValue() {
        Assert.assertEquals(factory.getPropertiesValue("BUCKET"), "test");
    }
}