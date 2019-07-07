/*
 *
 * FileEntityJ.java
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
package com.yoclabo.text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileEntityJ {

    private final String enc;

    private final String path;

    private final String name;

    private List<String> content;

    private int rowCount;

    public FileEntityJ(String e, String p) {
        enc = e;
        path = p;
        name = (new File(p)).getName();
        content = new ArrayList<>();
        rowCount = 0;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public List<String> getContent() {
        return content;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void read() throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), enc));
        String l = "";
        while (null != (l = r.readLine())) {
            content.add(l);
            ++rowCount;
        }
        r.close();
    }

    public void clear() {
        content.clear();
        rowCount = 0;
    }
}