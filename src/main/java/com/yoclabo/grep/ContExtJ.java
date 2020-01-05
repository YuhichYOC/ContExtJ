/*
 *
 * ContExtJ.java
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

import com.yoclabo.filesystem.DirectoryEntityJ;
import com.yoclabo.text.FileEntityJ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContExtJ {

    private List<Condition> conditions;

    private String delimiter;

    private String enc;

    private List<Match> hit;

    public ContExtJ() {
        conditions = new ArrayList<>();
        delimiter = "\t";
        enc = "UTF-8";
        hit = new ArrayList<>();
    }

    public void setDelimiter(String arg) {
        delimiter = arg;
    }

    public void setEncoding(String arg) {
        enc = arg;
    }

    public List<Match> getHit() {
        return hit;
    }

    public void init(String arg, boolean startsWithTag) throws IOException {
        FileEntityJ f = new FileEntityJ(enc, arg);
        f.read();
        for (String l : f.getContent()) {
            conditions.add(fetchPatterns(split(l), startsWithTag));
        }
        f.clear();
    }

    private List<String> split(String arg) {
        List<String> ret = new ArrayList<>();
        String[] items = arg.split(delimiter);
        Collections.addAll(ret, items);
        return ret;
    }

    private Condition fetchPatterns(List<String> patterns, boolean startsWithTag) {
        Condition ret = new Condition();
        for (int i = 0; patterns.size() > i; ++i) {
            if (0 == i && startsWithTag) {
                ret.setTag(patterns.get(i));
                continue;
            }
            boolean n = false;
            if (patterns.get(i).startsWith("[N]")) {
                n = true;
            }
            if (patterns.get(i).startsWith("(-)")) {
                n = true;
            }
            if (n) {
                ret.add(patterns.get(i).substring(3), n);
            }
            else {
                ret.add(patterns.get(i), n);
            }
        }
        return ret;
    }

    public void scan(String path) throws IOException {
        DirectoryEntityJ d = new DirectoryEntityJ(enc, path);
        d.describe();
        scan(d);
    }

    private void scan(DirectoryEntityJ arg) throws IOException {
        for (FileEntityJ f : arg.getFiles()) {
            f.read();
            for (Condition c : conditions) {
                c.test(f);
            }
            for (Condition c : conditions) {
                hit.addAll(c.getHit());
                c.init();
            }
            f.clear();
        }
        for (DirectoryEntityJ subD : arg.getSubDirectories()) {
            scan(subD);
        }
    }
}
