/*
 *
 * Condition.java
 *
 * Copyright 2019 Yuichi Yoshii
 *     吉井雄一 @ 吉井産業  you.65535.kir@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.yoclabo.grep;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Condition {

    List<RowCondition> rowConditions;
    private String tag;
    private Match match;

    private List<Match> hit;

    public Condition() {
        rowConditions = new ArrayList<>();
        match = new Match();
        hit = new ArrayList<>();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String arg) {
        tag = arg;
    }

    public List<Match> getHit() {
        return hit;
    }

    public void add(String p, boolean n) {
        rowConditions.add((new RowCondition(p, n)));
    }

    public void test(String arg, int rix) {
        if (0 > rix) {
            throw new IllegalArgumentException("Argument 'rix' must be bigger than zero. 'rix' is Row IndeX of file that testing.");
        }
        int testStart = 0;
        if (!match.started() && rowConditions.get(0).test(arg)) {
            match.setTag(tag);
            match.start(rix);
            testStart += rowConditions.get(0).getLeft();
        }
        if (match.started()) {
            match.add(arg);
            int rcc = rowConditions.size();
            for (int i = 1; rcc > i; ++i) {
                if (rowConditions.get(i).isHit()) {
                    continue;
                }
                if (rowConditions.get(i).test(arg.substring(testStart))) {
                    testStart += rowConditions.get(i).getLeft();
                }
            }
        }
        if (!ConsistencyInspection()) {
            initConditions();
            match.init();
        }
        if (allHit()) {
            initConditions();
            hit.add(match);
            match = new Match();
        }
    }

    private boolean ConsistencyInspection() {
        List<RowCondition> p = new ArrayList<>();
        for (RowCondition item : rowConditions) {
            if (item.isNegative()) {
                continue;
            }
            p.add(item);
        }
        int prcc = p.size();
        for (int i = 1; prcc > i; ++i) {
            if (!p.get(i - 1).isHit() && p.get(i).isHit()) {
                return false;
            }
        }
        List<RowCondition> n = new ArrayList<>();
        for (RowCondition item : rowConditions) {
            if (item.isNegative()) {
                n.add(item);
            }
        }
        int nrcc = n.size();
        for (int j = 0; nrcc > j; ++j) {
            if (n.get(j).isHit()) {
                return false;
            }
        }
        return true;
    }

    private boolean allHit() {
        for (RowCondition c : rowConditions) {
            if (c.isNegative()) {
                continue;
            }
            if (!c.isHit()) {
                return false;
            }
        }
        return true;
    }

    public void init() {
        initConditions();
        match.init();
        hit.clear();
    }

    private void initConditions() {
        for (RowCondition c : rowConditions) {
            c.init();
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; rowConditions.size() > i; ++i) {
            if (0 < i) {
                ret.append(" ## ");
            }
            if (rowConditions.get(i).isNegative()) {
                ret.append("[N]");
            }
            ret.append(rowConditions.get(i).toString());
        }
        return ret.toString();
    }

    private class RowCondition {

        private String pattern;

        private boolean negative;

        private boolean isHit;

        private int left;

        public RowCondition(String p, boolean n) {
            pattern = p;
            negative = n;
            isHit = false;
            left = 0;
        }

        public boolean isNegative() {
            return negative;
        }

        public boolean isHit() {
            return isHit;
        }

        public int getLeft() {
            return left;
        }

        public void init() {
            isHit = false;
            left = 0;
        }

        public boolean test(String arg) {
            Matcher m = Pattern.compile(pattern).matcher(arg);
            if (m.find()) {
                isHit = true;
                left = m.end();
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return pattern;
        }
    }
}
