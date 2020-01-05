package com.yoclabo.filesystem;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class DirectoryEntityJTest {

    @Test
    public void test01() {
        try {
            String enc = "UTF-8";
            String path = Objects.requireNonNull(DirectoryEntityJTest.class.getClassLoader().getResource("DirectoryEntityJTest01/a")).getPath();

            String CHILDF1 = "a1.txt";
            String CHILDD1 = "ab";
            String CHILDD2 = "ac";

            String CHILDF1_1 = "ad1.txt";
            String CHILDF1_2 = "ab2.txt";
            String CHILDF1_3 = "ab1.txt";
            String CHILDF2_1 = "ad1.txt";
            String CHILDF2_2 = "ac1.txt";

            DirectoryEntityJ d = new DirectoryEntityJ(enc, path);
            d.describe();
            Assert.assertEquals(CHILDF1, d.getFiles().get(0).getName());
            Assert.assertEquals(CHILDD1, d.getSubDirectories().get(0).getName());
            Assert.assertEquals(CHILDD2, d.getSubDirectories().get(1).getName());

            Assert.assertEquals(CHILDF1_1, d.getSubDirectories().get(0).getFiles().get(0).getName());
            Assert.assertEquals(CHILDF1_2, d.getSubDirectories().get(0).getFiles().get(1).getName());
            Assert.assertEquals(CHILDF1_3, d.getSubDirectories().get(0).getFiles().get(2).getName());
            Assert.assertEquals(CHILDF2_1, d.getSubDirectories().get(1).getFiles().get(0).getName());
            Assert.assertEquals(CHILDF2_2, d.getSubDirectories().get(1).getFiles().get(1).getName());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}