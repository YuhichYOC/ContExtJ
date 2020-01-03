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

public class Condition {

    List<SubCondition> subConditions;
    private String tag;

    private List<SubCondition> hit;

    public Condition() {
        subConditions = new ArrayList<>();
        hit = new ArrayList<>();
    }

    public void setTag(String arg) {
        tag = arg;
    }

    public List<SubCondition> getHit() {
        return hit;
    }

    public void add(String p, boolean n) {
        subConditions.add((new SubCondition(p, n)));
    }

    public void test(String arg, int rix) {
        if (0 > rix) {
            throw new IllegalArgumentException("Argument 'rix' must be bigger than zero. 'rix' is Row IndeX of file that testing.");
        }
        int testStart = 0;
        if (!match.started() && subConditions.get(0).test(arg)) {
            match.setTag(tag);
            match.start(rix);
            testStart += subConditions.get(0).getLeft();
        }
        if (match.started()) {
            match.add(arg);
            int rcc = subConditions.size();
            for (int i = 1; rcc > i; ++i) {
                if (subConditions.get(i).isHit()) {
                    continue;
                }
                if (subConditions.get(i).test(arg.substring(testStart))) {
                    testStart += subConditions.get(i).getLeft();
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
        List<SubCondition> p = new ArrayList<>();
        for (SubCondition item : subConditions) {
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
        List<SubCondition> n = new ArrayList<>();
        for (SubCondition item : subConditions) {
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
        for (SubCondition c : subConditions) {
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
        for (SubCondition c : subConditions) {
            c.init();
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; subConditions.size() > i; ++i) {
            if (0 < i) {
                ret.append(" ## ");
            }
            if (subConditions.get(i).isNegative()) {
                ret.append("[N]");
            }
            ret.append(subConditions.get(i).toString());
        }
        return ret.toString();
    }

}
