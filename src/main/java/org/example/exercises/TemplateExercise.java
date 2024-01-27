package org.example.exercises;

import java.util.Arrays;
import java.util.List;

class Creature {
  public int attack, health;
  public int fightHealth;

  public Creature(int attack, int health) {
    this.attack = attack;
    this.health = this.fightHealth = health;
  }
}

abstract class CardGame {
  public Creature[] creatures;

  public CardGame(Creature[] creatures) {
    this.creatures = creatures;
  }

  // returns -1 if no clear winner (both alive or both dead)
  public int combat(int creature1, int creature2) {
    Creature first = creatures[creature1];
    Creature second = creatures[creature2];
    hit(first, second);
    hit(second, first);
    // determine who won and return either creature1 or creature2
    if ((first.fightHealth <= 0 && second.fightHealth <= 0)
        || (first.fightHealth > 0 && second.fightHealth > 0)) {
      return -1;
    } else if (first.fightHealth <= 0) {
      return creature2;
    } else {
      return creature1;
    }
  }

  // attacker hits other creature
  protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame {
  public TemporaryCardDamageGame(Creature[] creatures) {
    super(creatures);
  }

  @Override
  protected void hit(Creature attacker, Creature other) {
    other.fightHealth = other.health - attacker.attack;
  }
}

class PermanentCardDamageGame extends CardGame {
  public PermanentCardDamageGame(Creature[] creatures) {
    super(creatures);
  }

  @Override
  protected void hit(Creature attacker, Creature other) {
    other.fightHealth -= attacker.attack;
  }
}

class TemplateExerciseDemo {
  public static void main(String[] args) {
    Creature[] creatures = new Creature[] {new Creature(1, 3), new Creature(2, 3)};
    //    PermanentCardDamageGame game = new PermanentCardDamageGame(creatures);
    TemporaryCardDamageGame game = new TemporaryCardDamageGame(creatures);
    System.out.println(game.combat(0, 1));
    System.out.println(game.combat(0, 1));
  }
}
