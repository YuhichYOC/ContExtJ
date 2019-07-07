/*
 *
 * DirectoryEntityJ.java
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
package com.yoclabo.filesystem;

import com.yoclabo.text.FileEntityJ;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryEntityJ {

    private final String enc;

    private final String path;

    private final String name;

    private List<DirectoryEntityJ> subDirectories;

    private List<FileEntityJ> files;

    public DirectoryEntityJ(String e, String p) {
        enc = e;
        path = p;
        name = (new File(p)).getName();
        subDirectories = new ArrayList<>();
        files = new ArrayList<>();
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public List<DirectoryEntityJ> getSubDirectories() {
        return subDirectories;
    }

    public List<FileEntityJ> getFiles() {
        return files;
    }

    public void describe() {
        File self = new File(path);
        for (File d : self.listFiles()) {
            if (d.isFile()) {
                continue;
            }
            addDirectory(describe(d));
        }
        for (File f : self.listFiles()) {
            if (f.isDirectory()) {
                continue;
            }
            FileEntityJ add = new FileEntityJ(enc, f.getAbsolutePath());
            addFile(add);
        }
    }

    private DirectoryEntityJ describe(File arg) {
        DirectoryEntityJ ret = new DirectoryEntityJ(enc, arg.getAbsolutePath());
        for (File d : arg.listFiles()) {
            if (d.isFile()) {
                continue;
            }
            ret.addDirectory(describe(d));
        }
        for (File f : arg.listFiles()) {
            if (f.isDirectory()) {
                continue;
            }
            FileEntityJ add = new FileEntityJ(enc, f.getAbsolutePath());
            ret.addFile(add);
        }
        return ret;
    }

    private void addDirectory(DirectoryEntityJ arg) {
        subDirectories.add(arg);
    }

    private void addFile(FileEntityJ arg) {
        files.add(arg);
    }
}