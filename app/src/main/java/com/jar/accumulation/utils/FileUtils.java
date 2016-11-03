/*
 *   Copyright (C) 2016 jar Corporation. All rights reserved.
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.jar.accumulation.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jar on 16-4-25-20:54
 * Email is jarhot@@163.com
 */
public class FileUtils {

    public static StringBuilder readFile(String filePath) {
        File file = new File(filePath);
        if (file == null || !file.isFile()) {
            return null;
        }
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (fileContent.length() != 0) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            reader.close();
            reader = null;
            line = null;
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static boolean writeFile(String filePath, InputStream stream,
                                    int byteSize) {
        OutputStream o = null;
        try {
            mkdirIfNotExists(filePath.substring(0,
                    filePath.lastIndexOf(File.separator) + 1));
            o = new FileOutputStream(filePath);
            byte data[] = new byte[byteSize];
            int length = -1;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean writeFile(String filePath, InputStream stream) {
        return writeFile(filePath, stream, 1024);
    }

    public static boolean writeFile(String filePath, String content,
                                    boolean append) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean mkdirIfNotExists(String path) {
        File file = new File(path);
        if (file.exists() && !file.isDirectory() || !file.exists()) {
            file.mkdirs();
            return true;
        }
        return false;
    }
}
