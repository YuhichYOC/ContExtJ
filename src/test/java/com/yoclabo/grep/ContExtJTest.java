package com.yoclabo.grep;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class ContExtJTest {

    @Test
    public void test01() {
        String path = ContExtJTest.class.getClassLoader().getResource("./patterns2.txt").getPath();
        String target = ContExtJTest.class.getClassLoader().getResource("./test1").getPath();

        ContExtJ c = new ContExtJ();
        try {
            c.init(path, true);
            c.scan(target);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            Assert.fail();
        }
        List<Match> ret = c.getHit();

        Assert.assertEquals(6, ret.size());

        String F1 = "a1.txt";
        String F2 = "ab1.txt";
        String F3 = "ac1.txt";

        String F1L1 = "a1.txt";
        String F1L2 = "elit, sed do eiusmod tempor incididunt ut labore et";
        String F1L3 = "dolore magna aliqua. Ut enim ad minim veniam, quis";
        String F1L4 = "contains";
        String F1L5 = "abc";
        String F1L6 = "reprehenderit in voluptate velit esse cillum dolore";
        String F1L7 = "eu fugiat nulla pariatur. Excepteur sint occaecat";
        String F1L8 = "def";

        Assert.assertEquals(F1, (new File(ret.get(0).getPath())).getName());
        Assert.assertEquals(F1, (new File(ret.get(1).getPath())).getName());

        Assert.assertEquals(1, ret.get(0).getStart());
        Assert.assertEquals(7, ret.get(1).getStart());

        Assert.assertEquals(F1L1, ret.get(0).getHit().get(0));
        Assert.assertEquals(F1L2, ret.get(0).getHit().get(1));
        Assert.assertEquals(F1L3, ret.get(0).getHit().get(2));
        Assert.assertEquals(F1L4, ret.get(0).getHit().get(3));
        Assert.assertEquals(F1L5, ret.get(1).getHit().get(0));
        Assert.assertEquals(F1L6, ret.get(1).getHit().get(1));
        Assert.assertEquals(F1L7, ret.get(1).getHit().get(2));
        Assert.assertEquals(F1L8, ret.get(1).getHit().get(3));

        String F2L1 = "ab1.txt contains";
        String F2L2 = "fgh";
        String F2L3 = "reprehenderit in voluptate velit esse cillum dolore";
        String F2L4 = "ijk";

        Assert.assertEquals(F2, (new File(ret.get(2).getPath())).getName());
        Assert.assertEquals(F2, (new File(ret.get(3).getPath())).getName());

        Assert.assertEquals(3, ret.get(2).getStart());
        Assert.assertEquals(6, ret.get(3).getStart());

        Assert.assertEquals(F2L1, ret.get(2).getHit().get(0));
        Assert.assertEquals(F2L2, ret.get(3).getHit().get(0));
        Assert.assertEquals(F2L3, ret.get(3).getHit().get(1));
        Assert.assertEquals(F2L4, ret.get(3).getHit().get(2));

        String F3L1 = "dolore magna ac1.txt aliqua. Ut enim ad minim veniam, quis";
        String F3L2 = "nostrud exercitation ullamco laboris nisi ut aliquip";
        String F3L3 = "ex ea commodo klm consequat. Duis aute irure dolor in";
        String F3L4 = "reprehenderit in voluptate velit esse cillum dolore";
        String F3L5 = "eu fugiat contains nulla pariatur. Excepteur sint occaecat";
        String F3L6 = "ex ea commodo klm consequat. Duis aute irure dolor in";
        String F3L7 = "reprehenderit in voluptate velit esse cillum dolore";
        String F3L8 = "eu fugiat contains nulla pariatur. Excepteur sint occaecat";
        String F3L9 = "cupidatat non opq proident, sunt in culpa qui officia";

        Assert.assertEquals(F3, (new File(ret.get(4).getPath())).getName());
        Assert.assertEquals(F3, (new File(ret.get(5).getPath())).getName());

        Assert.assertEquals(2, ret.get(4).getStart());
        Assert.assertEquals(4, ret.get(5).getStart());

        Assert.assertEquals(F3L1, ret.get(4).getHit().get(0));
        Assert.assertEquals(F3L2, ret.get(4).getHit().get(1));
        Assert.assertEquals(F3L3, ret.get(4).getHit().get(2));
        Assert.assertEquals(F3L4, ret.get(4).getHit().get(3));
        Assert.assertEquals(F3L5, ret.get(4).getHit().get(4));
        Assert.assertEquals(F3L6, ret.get(5).getHit().get(0));
        Assert.assertEquals(F3L7, ret.get(5).getHit().get(1));
        Assert.assertEquals(F3L8, ret.get(5).getHit().get(2));
        Assert.assertEquals(F3L9, ret.get(5).getHit().get(3));
    }

    @Test
    public void test02() {
        String path = ContExtJTest.class.getClassLoader().getResource("./patterns2.txt").getPath();
        String target = ContExtJTest.class.getClassLoader().getResource("./test2").getPath();

        ContExtJ c = new ContExtJ();
        try {
            c.init(path, true);
            c.scan(target);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        List<Match> ret = c.getHit();

        Assert.assertEquals(6, ret.size());

        String F1 = "a1.txt";
        String F2 = "ab1.txt";
        String F3 = "ac1.txt";

        String F1L1 = "a1.txt";
        String F1L2 = "elit, sed do eiusmod tempor incididunt ut labore et";
        String F1L3 = "dolore magna aliqua. Ut enim ad minim veniam, quis";
        String F1L4 = "contains";
        String F1L5 = "abc";
        String F1L6 = "reprehenderit in voluptate velit esse cillum dolore";
        String F1L7 = "eu fugiat nulla pariatur. Excepteur sint occaecat";
        String F1L8 = "def";

        Assert.assertEquals(F1, (new File(ret.get(0).getPath())).getName());
        Assert.assertEquals(F1, (new File(ret.get(1).getPath())).getName());

        Assert.assertEquals(1, ret.get(0).getStart());
        Assert.assertEquals(7, ret.get(1).getStart());

        Assert.assertEquals(F1L1, ret.get(0).getHit().get(0));
        Assert.assertEquals(F1L2, ret.get(0).getHit().get(1));
        Assert.assertEquals(F1L3, ret.get(0).getHit().get(2));
        Assert.assertEquals(F1L4, ret.get(0).getHit().get(3));
        Assert.assertEquals(F1L5, ret.get(1).getHit().get(0));
        Assert.assertEquals(F1L6, ret.get(1).getHit().get(1));
        Assert.assertEquals(F1L7, ret.get(1).getHit().get(2));
        Assert.assertEquals(F1L8, ret.get(1).getHit().get(3));

        String F2L1 = "ab1.txt contains";
        String F2L2 = "fgh";
        String F2L3 = "reprehenderit in voluptate velit esse cillum dolore";
        String F2L4 = "ijk";

        Assert.assertEquals(F2, (new File(ret.get(2).getPath())).getName());
        Assert.assertEquals(F2, (new File(ret.get(3).getPath())).getName());

        Assert.assertEquals(3, ret.get(2).getStart());
        Assert.assertEquals(6, ret.get(3).getStart());

        Assert.assertEquals(F2L1, ret.get(2).getHit().get(0));
        Assert.assertEquals(F2L2, ret.get(3).getHit().get(0));
        Assert.assertEquals(F2L3, ret.get(3).getHit().get(1));
        Assert.assertEquals(F2L4, ret.get(3).getHit().get(2));

        String F3L1 = "dolore magna ac1.txt aliqua. Ut enim ad minim veniam, quis";
        String F3L2 = "nostrud exercitation ullamco laboris nisi ut aliquip";
        String F3L3 = "ex ea commodo klm consequat. Duis aute irure dolor in";
        String F3L4 = "reprehenderit in voluptate velit esse cillum dolore";
        String F3L5 = "eu fugiat contains nulla pariatur. Excepteur sint occaecat";
        String F3L6 = "ex ea commodo klm consequat. Duis aute irure dolor in";
        String F3L7 = "reprehenderit in voluptate velit esse cillum dolore";
        String F3L8 = "eu fugiat contains nulla pariatur. Excepteur sint occaecat";
        String F3L9 = "cupidatat non opq proident, sunt in culpa qui officia";

        Assert.assertEquals(F3, (new File(ret.get(4).getPath())).getName());
        Assert.assertEquals(F3, (new File(ret.get(5).getPath())).getName());

        Assert.assertEquals(2, ret.get(4).getStart());
        Assert.assertEquals(4, ret.get(5).getStart());

        Assert.assertEquals(F3L1, ret.get(4).getHit().get(0));
        Assert.assertEquals(F3L2, ret.get(4).getHit().get(1));
        Assert.assertEquals(F3L3, ret.get(4).getHit().get(2));
        Assert.assertEquals(F3L4, ret.get(4).getHit().get(3));
        Assert.assertEquals(F3L5, ret.get(4).getHit().get(4));
        Assert.assertEquals(F3L6, ret.get(5).getHit().get(0));
        Assert.assertEquals(F3L7, ret.get(5).getHit().get(1));
        Assert.assertEquals(F3L8, ret.get(5).getHit().get(2));
        Assert.assertEquals(F3L9, ret.get(5).getHit().get(3));
    }
}
