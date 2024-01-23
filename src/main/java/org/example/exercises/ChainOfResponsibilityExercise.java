package org.example.exercises;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
  protected Game game;
  protected int baseAttack, baseDefense;

  public Creature(Game game, int baseAttack, int baseDefense)
  {
    this.game = game;
    this.baseAttack = baseAttack;
    this.baseDefense = baseDefense;
  }

  public abstract int getAttack();
  public abstract int getDefense();
  public abstract void query(Object source, StatQuery sq);
  public abstract boolean instanceOfThis(Object object);
}

class Paladin extends Creature {

  public Paladin(Game game) {
    super(game, 4, 4);
  }

  @Override
  public int getAttack() {
    StatQuery q = new StatQuery(Statistic.ATTACK);
    for (Creature c : game.creatures){
      c.query(this, q);
    }
    return q.result;
  }

  @Override
  public int getDefense() {
    StatQuery q = new StatQuery(Statistic.DEFENSE);
    for (Creature c : game.creatures){
      c.query(this, q);
    }
    return q.result;
  }

  @Override
  public void query(Object source, StatQuery sq) {
    if (!instanceOfThis(source)) {
      return;
    }
    if (source == this)
    {
      switch (sq.statistic)
      {
        case ATTACK:
          sq.result += baseAttack;
          break;
        case DEFENSE:
          sq.result += baseDefense;
          break;
      }
    }
  }

  @Override
  public boolean instanceOfThis(Object object) {
    return object instanceof Paladin;
  }
}

class Goblin extends Creature
{
  protected Goblin(Game game, int baseAttack, int baseDefense)
  {
    super(game, baseAttack, baseDefense);
  }

  public Goblin(Game game)
  {
    super(game, 1, 1);
  }

  @Override
  public int getAttack()
  {
    StatQuery q = new StatQuery(Statistic.ATTACK);
    for (Creature c : game.creatures)
      c.query(this, q);
    return q.result;
  }

  @Override
  public int getDefense()
  {
    StatQuery q = new StatQuery(Statistic.DEFENSE);
    for (Creature c : game.creatures)
      c.query(this, q);
    return q.result;
  }

  @Override
  public void query(Object source, StatQuery sq)
  {
    if (!instanceOfThis(source)) {
      return;
    }
    if (source == this)
    {
      switch (sq.statistic)
      {
        case ATTACK:
          sq.result += baseAttack;
          break;
        case DEFENSE:
          sq.result += baseDefense;
          break;
      }
    }
    else if (sq.statistic == Statistic.DEFENSE)
    {
      sq.result++;
    }
  }

  @Override
  public boolean instanceOfThis(Object object) {
    return object instanceof Goblin;
  }
}

class GoblinKing extends Goblin
{
  public GoblinKing(Game game)
  {
    super(game, 3, 3);
  }

  @Override
  public void query(Object source, StatQuery sq)
  {
    if (!instanceOfThis(source)) {
      return;
    }
    if (source != this && sq.statistic == Statistic.ATTACK)
    {
      sq.result++; // every goblin gets +1 attack
    }
    else super.query(source, sq);
  }
}

enum Statistic
{
  ATTACK, DEFENSE
}

class StatQuery
{
  public Statistic statistic;
  public int result;

  public StatQuery(Statistic statistic)
  {
    this.statistic = statistic;
  }
}

class Game
{
  public List<Creature> creatures = new ArrayList<>();
}


class ChainOfResponsibilityDemo {
  public static void main(String[] args){
    Game game = new Game();
    Creature goblin = new Goblin(game);
    game.creatures.add(goblin);

    Creature goblin2 = new Goblin(game);
    game.creatures.add(goblin2);

    Creature goblinKing = new GoblinKing(game);
    game.creatures.add(goblinKing);

    Creature paladin = new Paladin(game);
    game.creatures.add(paladin);

    System.out.println("Paladin");
    System.out.println(paladin.getAttack());
    System.out.println(paladin.getDefense());

    System.out.println("Goblin King");
    System.out.println(goblinKing.getAttack());
    System.out.println(goblinKing.getDefense());

    System.out.println("Goblin");
    System.out.println(goblin.getAttack());
    System.out.println(goblin.getDefense());

    System.out.println("Goblin 2");
    System.out.println(goblin2.getAttack());
    System.out.println(goblin2.getDefense());
  }
}
