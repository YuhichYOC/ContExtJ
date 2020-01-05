package com.yoclabo.text;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class FileEntityJTest {

    @Test
    public void test01() {
        try {
            String enc = "UTF-8";
            String path = Objects.requireNonNull(FileEntityJTest.class.getClassLoader().getResource("FileEntityJTest01.txt")).getPath();

            String N = "FileEntityJTest01.txt";
            int rowCount = 5;
            String L1 = "This";
            String L2 = "is";
            String L3 = "file";
            String L4 = "for";
            String L5 = "test01";

            FileEntityJ f = new FileEntityJ(enc, path);
            f.read();
            Assert.assertEquals(N, f.getName());
            Assert.assertEquals(rowCount, f.getRowCount());
            Assert.assertEquals(L1, f.getContent().get(0));
            Assert.assertEquals(L2, f.getContent().get(1));
            Assert.assertEquals(L3, f.getContent().get(2));
            Assert.assertEquals(L4, f.getContent().get(3));
            Assert.assertEquals(L5, f.getContent().get(4));
            f.clear();
            Assert.assertEquals(0, f.getContent().size());
            Assert.assertEquals(0, f.getRowCount());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
    }
}