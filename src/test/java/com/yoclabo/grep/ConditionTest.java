package com.yoclabo.grep;

import com.yoclabo.text.FileEntityJ;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ConditionTest {

    @Test
    public void test01() {
        String P1 = "a1\\.txt";
        String P2 = "contains";

        Condition c = new Condition();
        c.add(P1, false);
        c.add(P2, false);
        String enc = "UTF-8";
        String path = ConditionTest.class.getClassLoader().getResource("./test1/a1.txt").getPath();
        FileEntityJ f = new FileEntityJ(enc, path);
        try {
            f.read();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; f.getRowCount() > i; ++i) {
            c.test(f.getContent().get(i), i);
        }
        f.clear();
        List<Match> ret = c.getHit();
        Assert.assertEquals(1, ret.size());
        Assert.assertEquals(4, ret.get(0).getHit().size());
        Assert.assertEquals(1, ret.get(0).getStart());

        String L1 = "a1.txt";
        String L2 = "elit, sed do eiusmod tempor incididunt ut labore et";
        String L3 = "dolore magna aliqua. Ut enim ad minim veniam, quis";
        String L4 = "contains";
        Assert.assertEquals(L1, ret.get(0).getHit().get(0));
        Assert.assertEquals(L2, ret.get(0).getHit().get(1));
        Assert.assertEquals(L3, ret.get(0).getHit().get(2));
        Assert.assertEquals(L4, ret.get(0).getHit().get(3));
    }

    @Test
    public void test02() {
        String P1 = "ac1\\.txt";
        String P2 = "contains";

        Condition c = new Condition();
        c.add(P1, false);
        c.add(P2, false);
        String enc = "UTF-8";
        String path = ConditionTest.class.getClassLoader().getResource("./test1/ac/ac1.txt").getPath();
        FileEntityJ f = new FileEntityJ(enc, path);
        try {
            f.read();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; f.getRowCount() > i; ++i) {
            c.test(f.getContent().get(i), i);
        }
        f.clear();
        List<Match> ret = c.getHit();
        Assert.assertEquals(1, ret.size());
        Assert.assertEquals(5, ret.get(0).getHit().size());
        Assert.assertEquals(2, ret.get(0).getStart());

        String L1 = "dolore magna ac1.txt aliqua. Ut enim ad minim veniam, quis";
        String L2 = "nostrud exercitation ullamco laboris nisi ut aliquip";
        String L3 = "ex ea commodo klm consequat. Duis aute irure dolor in";
        String L4 = "reprehenderit in voluptate velit esse cillum dolore";
        String L5 = "eu fugiat contains nulla pariatur. Excepteur sint occaecat";
        Assert.assertEquals(L1, ret.get(0).getHit().get(0));
        Assert.assertEquals(L2, ret.get(0).getHit().get(1));
        Assert.assertEquals(L3, ret.get(0).getHit().get(2));
        Assert.assertEquals(L4, ret.get(0).getHit().get(3));
        Assert.assertEquals(L5, ret.get(0).getHit().get(4));
    }

    @Test
    public void test03() {
        String P1 = "ConditionTest::TestMethod3 start";
        String P2 = "contains";
        String P3 = "ConditionTest::TestMethod3 end";

        Condition c = new Condition();
        c.add(P1, false);
        c.add(P2, false);
        c.add(P3, false);
        String enc = "UTF-8";
        String path = ConditionTest.class.getClassLoader().getResource("./test1/a2.txt").getPath();
        FileEntityJ f = new FileEntityJ(enc, path);
        try {
            f.read();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; f.getRowCount() > i; ++i) {
            c.test(f.getContent().get(i), i);
        }
        f.clear();
        List<Match> ret = c.getHit();
        Assert.assertEquals(1, ret.size());
        Assert.assertEquals(7, ret.get(0).getHit().size());
        Assert.assertEquals(1, ret.get(0).getStart());

        String L1 = "ConditionTest::TestMethod3 start";
        String L2 = "elit, sed do eiusmod tempor incididunt ut labore et";
        String L3 = "dolore magna aliqua. Ut enim ad minim veniam, quis";
        String L4 = "contains";
        String L5 = "nostrud exercitation ullamco laboris nisi ut aliquip";
        String L6 = "ex ea commodo consequat. Duis aute irure dolor in";
        String L7 = "ConditionTest::TestMethod3 end";

        Assert.assertEquals(L1, ret.get(0).getHit().get(0));
        Assert.assertEquals(L2, ret.get(0).getHit().get(1));
        Assert.assertEquals(L3, ret.get(0).getHit().get(2));
        Assert.assertEquals(L4, ret.get(0).getHit().get(3));
        Assert.assertEquals(L5, ret.get(0).getHit().get(4));
        Assert.assertEquals(L6, ret.get(0).getHit().get(5));
        Assert.assertEquals(L7, ret.get(0).getHit().get(6));
    }

    @Test
    public void test04() {
        String P1 = "ConditionTest::TestMethod4 start";
        String P2 = "contains";
        String P3 = "ConditionTest::TestMethod4 end";

        Condition c = new Condition();
        c.add(P1, false);
        c.add(P2, false);
        c.add(P3, false);
        String enc = "UTF-8";
        String path = ConditionTest.class.getClassLoader().getResource("./test1/a3.txt").getPath();
        FileEntityJ f = new FileEntityJ(enc, path);
        try {
            f.read();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; f.getRowCount() > i; ++i) {
            c.test(f.getContent().get(i), i);
        }
        f.clear();
        List<Match> ret = c.getHit();
        Assert.assertEquals(0, ret.size());
    }

    @Test
    public void test05() {
        String P1 = "##TABLES##";
        String P2 = "##";
        String P3 = "##";
        String TEST1 = "TEST ROW1";
        String TEST2 = "TEST ##TABLES## ROW2";
        String TEST3 = "TEST ROW3";
        String TEST4 = "## TEST ROW4";
        String TEST5 = "TEST ROW 5##";

        Condition c = new Condition();
        c.add(P1, false);
        c.add(P2, false);
        c.add(P3, false);
        c.test(TEST1, 0);
        c.test(TEST2, 1);
        c.test(TEST3, 2);
        c.test(TEST4, 3);
        c.test(TEST5, 4);

        List<Match> ret = c.getHit();
        Assert.assertEquals(1, ret.size());
        Assert.assertEquals(4, ret.get(0).getHit().size());
    }

    @Test
    public void test06() {
        String P1 = "CREATE";
        String P2 = "(OR REPLACE)*";
        String P3 = "TRIGGER";
        String P4 = "Tr_AfterIns_Accounts";
        String P5 = "(BEFORE|AFTER)";
        String P6 = "(UPDATE|INSERT|DELETE)";
        String P7 = "ON";
        String P8 = "(SELECT|UPDATE|INSERT|DELETE)";
        String P9 = "Accounts";
        String TEST1 = "CREATE OR REPLACE TRIGGER Tr_AfterIns_Accounts ";
        String TEST2 = "AFTER INSERT ON Accounts ";

        Condition c = new Condition();
        c.add(P1, false);
        c.add(P2, false);
        c.add(P3, false);
        c.add(P4, false);
        c.add(P5, false);
        c.add(P6, false);
        c.add(P7, false);
        c.add(P8, true);
        c.add(P9, false);
        c.test(TEST1, 0);
        c.test(TEST2, 1);

        List<Match> ret = c.getHit();
        Assert.assertEquals(1, ret.size());
        Assert.assertEquals(2, ret.get(0).getHit().size());
    }
}
