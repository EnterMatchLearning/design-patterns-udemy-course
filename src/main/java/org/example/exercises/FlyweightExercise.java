package org.example.exercises;

import java.util.*;

class Sentence {
  private final String[] words;
  private final Map<Integer, WordToken> wordTokens = new HashMap<>();

  public Sentence(String plainText) {
    words = plainText.split(" ");
  }

  public WordToken getWord(int index) {
    WordToken wordToken = new WordToken();
    wordTokens.put(index, wordToken);
    return wordToken;
  }

  @Override
  public String toString() {
    List<String> wordsToPrint = new ArrayList<>();
    for (int i = 0; i < words.length; i++){
      String word = words[i];
      if (wordTokens.containsKey(i) && wordTokens.get(i).capitalize){
        word = word.toUpperCase();
      }
      wordsToPrint.add(word);
    }
    return String.join(" ", wordsToPrint);
  }

  static class WordToken {
    public boolean capitalize;
  }
}

class FlyweightDemo {
  public static void main(String[] args){
    Sentence sentence = new Sentence("hello world");
    sentence.getWord(1).capitalize = true;
    System.out.println(sentence);
  }
}
