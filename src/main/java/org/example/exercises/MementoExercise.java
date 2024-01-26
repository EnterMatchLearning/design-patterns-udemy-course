package org.example.exercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token {
  public int value = 0;

  public Token(int value) {
    this.value = value;
  }


  @Override
  public String toString() {
    return "" + value;
  }
}

class Memento {
  public List<Token> tokens = new ArrayList<>();

  public Memento(List<Token> tokens) {
    this.tokens = new ArrayList<>(tokens);
//    this.tokens = tokens.stream().collect(Collectors.toList()); // this also works
  }
}

class TokenMachine {
  public List<Token> tokens = new ArrayList<>();

  public Memento addToken(int value) {
    tokens.add(new Token(value));
    return new Memento(tokens);
  }

  public Memento addToken(Token token) {
    addToken(token.value);
    return new Memento(tokens);
  }

  public void revert(Memento m) {
    this.tokens = m.tokens;
  }
}

class MementoExerciseDemo {
  public static void main(String[] args) {
    TokenMachine tokenMachine = new TokenMachine();
    Memento m1 = tokenMachine.addToken(1);
    Memento m2 = tokenMachine.addToken(2);
    Memento m3 = tokenMachine.addToken(3);
    Memento m4 = tokenMachine.addToken(new Token(4));
    Memento m5 = tokenMachine.addToken(new Token(5));
    Memento m6 = tokenMachine.addToken(new Token(6));

    System.out.println(tokenMachine.tokens);

    tokenMachine.revert(m3);

    System.out.println(tokenMachine.tokens);

//    TokenMachine tm = new TokenMachine();
//    System.out.println("Made a token with value 111 and kept a reference");
//    Token token = new Token(111);
//    System.out.println("Added this token to the list");
//    tm.addToken(token);
//    Memento m = tm.addToken(222);
//    System.out.println("Changed this token's value to 333 :)");
//    token.value = 333;
//    tm.revert(m);
//
//    System.out.println(tm.tokens.size());
//    System.out.println(tm.tokens.get(0).value);

//    assertEquals(
//            "At this point, token machine should have exactly two tokens, "
//                    + "but you got " + tm.tokens.size(),
//            2, tm.tokens.size()
//    );
//
//    assertEquals("You got the token value wrong here. " +
//                    "Hint: did you init the memento by value?",
//            111, tm.tokens.get(0).value);
  }
}
