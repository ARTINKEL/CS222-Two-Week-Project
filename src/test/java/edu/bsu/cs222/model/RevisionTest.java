package edu.bsu.cs222.model;

import org.junit.Assert;
import org.junit.Test;

public class RevisionTest {

    @Test
    public void testGetUsername() {
        Revision revisionTest = new Revision("FrescoBot", "timestamp");
        Assert.assertEquals("FrescoBot", revisionTest.getUsername());
    }

    @Test
    public void testGetTimestamp() {
        Revision revisionTest = new Revision("FrescoBot", "timestamp");
        Assert.assertEquals("timestamp", revisionTest.getTimestamp());
    }
}
