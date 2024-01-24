package org.example.exercises;

import java.util.HashMap;
import java.util.Map;

class ExpressionProcessor {
  public Map<Character, Integer> variables = new HashMap<>();

  enum NextOp {
    NOTHING,
    PLUS,
    MINUS
  }

  public Integer parseInt(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException ex) {
      return null;
    }
  }

  public int calculate(String expression) {
    NextOp nextOp = NextOp.NOTHING;
    String[] parts = expression.split("(?<=[+-])");
    int result = 0;
    for (String part : parts) {
      int value;
      String[] elements = part.split("[+-]");
      Integer first = parseInt(elements[0]);
      if (first != null) {
        value = first;
      } else if (elements[0].length() == 1 && variables.containsKey(elements[0].charAt(0))) {
        value = variables.get(elements[0].charAt(0));
      } else {
        return 0;
      }
      switch (nextOp) {
        case NOTHING:
          result = value;
          break;
        case PLUS:
          result += value;
          break;
        case MINUS:
          result -= value;
          break;
      }

      if (part.endsWith("+")) nextOp = NextOp.PLUS;
      else if (part.endsWith("-")) nextOp = NextOp.MINUS;
    }
    return result;
  }
}

class InterpreterDemo {
  public static void main(String[] args){
    ExpressionProcessor processor = new ExpressionProcessor();
    processor.variables.put('x', 5);
    System.out.println(processor.calculate("1+2+x"));
  }
}
