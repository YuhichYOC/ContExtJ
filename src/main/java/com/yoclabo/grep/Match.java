/*
 *
 * Match.java
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

public class Match {

    private String tag;

    private List<String> hit;

    private int start;

    private String path;

    private String pattern;

    public Match() {
        hit = new ArrayList<>();
        start = -1;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String arg) {
        tag = arg;
    }

    public List<String> getHit() {
        return hit;
    }

    public int getStart() {
        return start;
    }

    public boolean started() {
        return (-1 < start);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String arg) {
        path = arg;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String arg) {
        pattern = arg;
    }

    public void start(int arg) {
        start = arg;
    }

    public void add(String arg) {
        hit.add(arg);
    }

    public void init() {
        hit.clear();
        start = -1;
    }

    public String oneLine() {
        String ret = "";
        for (String l : hit) {
            ret += l;
        }
        return ret;
    }
}
