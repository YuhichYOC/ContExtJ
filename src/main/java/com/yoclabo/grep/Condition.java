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

import com.yoclabo.text.FileEntityJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Condition {

    List<SubCondition> subConditions;

    private String tag;

    private Map<List<SubCondition>, Boolean> hit;

    public Condition() {
        subConditions = new ArrayList<>();
        hit = new HashMap<>();
    }

    public void setTag(String arg) {
        tag = arg;
    }

    public Map<List<SubCondition>, Boolean> getHit() {
        return hit;
    }

    public void add(String p, boolean n) {
        subConditions.add((new SubCondition(p, n)));
    }

    public void test(FileEntityJ arg) {
        testFirstOnes(arg);
        for (List<SubCondition> h : hit.keySet()) {
            testOthers(arg, h);
        }
    }

    private void testFirstOnes(FileEntityJ arg) {
        for (int i = 0; arg.getRowCount() > i; ++i) {
            int start = 0;
            while (arg.getContent().get(i).length() >= start) {
                if (subConditions.get(0).test(arg.getContent().get(i), i, start)) {
                    List<SubCondition> add = new ArrayList<>();
                    for (SubCondition s : subConditions) {
                        add.add(s.fork());
                    }
                    hit.put(add, true);
                    start += subConditions.get(0).getLeft();
                }
                else {
                    start = arg.getContent().get(i).length();
                }
            }
        }
    }

    private void testOthers(FileEntityJ arg, List<SubCondition> h) {
        for (int i = h.get(0).getRow(); arg.getRowCount() > i; ++i) {
            int start = 0;
            if (i == h.get(0).getRow()) {
                start = h.get(0).getLeft();
            }
            while (arg.getContent().get(i).length() >= start) {
                for (int j = 1; h.size() > j; ++j) {
                    if (h.get(j).isHit()) {
                        continue;
                    }
                    if (h.get(j).test(arg.getContent().get(i), i, start)) {
                        start += h.get(j).getLeft();
                    }
                }
            }
            if (!consistencyInspect(h)) {
                hit.replace(h, false);
                return;
            }
            if (allHit(h)) {
                return;
            }
        }
        if (!allHit(h)) {
            hit.replace(h, false);
        }
    }

    private boolean consistencyInspect(List<SubCondition> h) {
        List<SubCondition> positives = new ArrayList<>();
        for (SubCondition item : h) {
            if (item.isNegative()) {
                continue;
            }
            positives.add(item);
        }
        int pc = positives.size();
        for (int i = 1; pc > i; ++i) {
            if (!positives.get(i - 1).isHit() && positives.get(i).isHit()) {
                return false;
            }
        }
        List<SubCondition> negatives = new ArrayList<>();
        for (SubCondition item : h) {
            if (item.isNegative()) {
                negatives.add(item);
            }
        }
        for (SubCondition negative : negatives) {
            if (negative.isHit()) {
                return false;
            }
        }
        return true;
    }

    private boolean allHit(List<SubCondition> h) {
        for (SubCondition c : h) {
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