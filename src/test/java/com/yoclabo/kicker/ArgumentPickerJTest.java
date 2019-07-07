package com.yoclabo.kicker;

import org.junit.Assert;
import org.junit.Test;

public class ArgumentPickerJTest {

    @Test
    public void test01() {
        var argv = new String[]{"-", "-"};
        ArgumentPickerJ p = new ArgumentPickerJ(argv);
        try {
            p.run();
        }
        catch (IllegalArgumentException e) {
            System.out.println("Collect case");
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test02() {
        var argv = new String[]{"-d", "test", "abc", "-"};
        ArgumentPickerJ p = new ArgumentPickerJ(argv);
        try {
            p.run();
        }
        catch (IllegalArgumentException e) {
            System.out.println("Collect case");
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test03() {
        var patternFile = ArgumentPickerJTest.class.getClassLoader().getResource("./patterns2.txt").getPath();
        var target = ArgumentPickerJTest.class.getClassLoader().getResource("./test1").getPath();
        var argv = new String[]{"-d", "test01", "abc", "-e", "test02", "-p", patternFile, "-t", target, "-o", "test05"};
        ArgumentPickerJ p = new ArgumentPickerJ(argv);
        try {
            p.run();
        }
        catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals("test01", p.getDelimiter());
        Assert.assertEquals("test02", p.getEncoding());
        Assert.assertEquals(patternFile, p.getPatternFile());
        Assert.assertEquals(target, p.getTargetDirectory());
        Assert.assertEquals("test05", p.getOut());
        Assert.assertEquals(false, p.isWriteStdout());
    }

    @Test
    public void test04() {
        var target = ArgumentPickerJTest.class.getClassLoader().getResource("./test1").getPath();
        var argv = new String[]{"-d", "test01", "abc", "-e", "test02", "-p", "test03", "-t", target, "-o", "test05"};
        ArgumentPickerJ p = new ArgumentPickerJ(argv);
        try {
            p.run();
        }
        catch (IllegalArgumentException e) {
            System.out.println("Collect case");
        }
        catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void test05() {
        var patternFile = ArgumentPickerJTest.class.getClassLoader().getResource("./patterns2.txt").getPath();
        var argv = new String[]{"-d", "test01", "abc", "-e", "test02", "-p", patternFile, "-t", "test04", "-o", "test05"};
        ArgumentPickerJ p = new ArgumentPickerJ(argv);
        try {
            p.run();
        }
        catch (IllegalArgumentException e) {
            System.out.println("Collect case");
        }
        catch (Exception e) {
            Assert.fail();
        }
    }
}
