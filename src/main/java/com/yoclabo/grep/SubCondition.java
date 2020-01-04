/*
 *
 * SubCondition.java
 *
 * Copyright 2020 Yuichi Yoshii
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class SubCondition {

    private Pattern pattern;

    private boolean negative;

    private boolean isHit;

    private int row;

    private int left;

    private String hit;

    public SubCondition(String p, boolean n) {
        pattern = Pattern.compile(p);
        negative = n;
        isHit = false;
        row = 0;
        left = 0;
        hit = "";
    }

    public boolean isNegative() {
        return negative;
    }

    public boolean isHit() {
        return isHit;
    }

    public int getRow() {
        return row;
    }

    public int getLeft() {
        return left;
    }

    public String getHit() {
        return hit;
    }

    public void init() {
        isHit = false;
        row = 0;
        left = 0;
        hit = "";
    }

    public boolean test(String arg, int r, int start) {
        Matcher m = pattern.matcher(arg.substring(start));
        if (m.find()) {
            isHit = true;
            row = r;
            left = start + m.end();
            hit = arg;
            return true;
        }
        return false;
    }

    public SubCondition fork() {
        SubCondition ret = new SubCondition(pattern.toString(), negative);
        ret.isHit = isHit;
        ret.row = row;
        ret.left = left;
        ret.hit = hit;
        init();
        return ret;
    }

    @Override
    public String toString() {
        return pattern.toString();
    }
}
