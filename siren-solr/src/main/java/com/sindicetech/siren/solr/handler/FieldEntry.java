/**
 * Copyright (c) 2014, Sindice Limited. All Rights Reserved.
 *
 * This file is part of the SIREn project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sindicetech.siren.solr.handler;

import java.util.Deque;
import java.util.Iterator;

/**
 * A holder object for keeping a field generated by {@link com.sindicetech.siren.solr.handler.JsonReader}.
 */
public class FieldEntry {

  private final String path;
  private final Object value;

  public FieldEntry(Deque<String> path, Object value) {
    this.path = toFieldName(path);
    this.value = value;
  }

  public FieldEntry(String fieldname, Object value) {
    this.path = fieldname;
    this.value = value;
  }

  public Object getValue() {
    return value;
  }

  public String getPath() {
    return path;
  }

  public String toString() {
    return String.format("<%s, %s>", path, value.toString());
  }

  /**
   * Generates the field name.
   *
   * @return the generated field name.
   */
  private static final String toFieldName(Deque<String> path) {
    if (path.size() == 1) {
      return path.peek();
    }
    else {
      StringBuilder builder = new StringBuilder();
      Iterator<String> it = path.descendingIterator();
      while (it.hasNext()) {
        builder.append(it.next());
        builder.append('.');
      }
      return builder.deleteCharAt(builder.length() - 1).toString();
    }
  }

  public String getDatatype() {
    return value.getClass().getSimpleName();
  }

}
