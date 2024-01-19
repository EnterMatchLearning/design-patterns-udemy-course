package org.example.exercises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Field {
  private final String name;
  private final String type;

  public Field(String name, String type) {
    this.name = name;
    this.type = type;
  }

  @Override
  public String toString() {
    return String.format("public %s %s;", type, name);
  }
}

class ClassCode {
  String className;
  List<Field> fields = new ArrayList<>();
  private final int indentSize = 2;
  private final String newLine = System.lineSeparator();

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("public class %s", className))
        .append(newLine)
        .append("{")
        .append(newLine);
    fields.forEach(
        field -> {
          //          sb.append(" ".repeat(indentSize)).append(field.toString()).append(newLine);
          sb.append(String.join("", Collections.nCopies(indentSize, " ")))
              .append(field.toString())
              .append(newLine);
        });
    sb.append("}");
    return sb.toString();
  }
}

class CodeBuilder {
  private final ClassCode classCode = new ClassCode();

  public CodeBuilder(String className) {
    classCode.className = className;
  }

  public CodeBuilder addField(String name, String type) {
    Field field = new Field(name, type);
    classCode.fields.add(field);
    return this;
  }

  @Override
  public String toString() {
    return classCode.toString();
  }
}

class UseBuilder {
  public static void main(String[] args) {
    CodeBuilder codeBuilder = new CodeBuilder("Person");
    codeBuilder.addField("name", "String").addField("age", "int");
    System.out.println(codeBuilder);
  }
}
