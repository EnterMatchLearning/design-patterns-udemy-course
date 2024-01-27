package org.example.exercises;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

class Event<T> {
  private List<Consumer<T>> consumers = new ArrayList<>();

  public void subscribe(Consumer<T> consumer) {
    consumers.add(consumer);
  }

  public void invoke(T arg) {
    for (Consumer<T> consumer : consumers) consumer.accept(arg);
  }
}

class Game {
  public Event<Rat> ratEnters = new Event<>();
  public Event<Rat> ratDies = new Event<>();
  public Event<Rat> notifyRat = new Event<>();
}

class Rat implements Closeable {
  private Game game;
  public int attack = 1;

  public Rat(Game game) {
    this.game = game;
    game.ratEnters.subscribe(
        rat -> {
          if (rat != this) {
            ++attack;
            game.notifyRat.invoke(rat);
          }
        });
    game.notifyRat.subscribe(
        rat -> {
          if (rat == this) ++attack;
        });
    game.ratDies.subscribe(rat -> --attack);
    game.ratEnters.invoke(this);
  }

  @Override
  public void close() throws IOException {
    // rat dies ;(
    game.ratDies.invoke(this);
  }
}
