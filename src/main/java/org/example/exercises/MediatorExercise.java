package org.example.exercises;

import java.util.ArrayList;
import java.util.List;

class Participant {
  Mediator mediator;
  int value = 0;
  public Participant(Mediator mediator) {
    this.mediator = mediator;
    mediator.subscribe(this);
  }

  public void say(int n) {
    mediator.broadcast(n, this);
  }
}

class Mediator {
  List<Participant> participants = new ArrayList<>();

  public void subscribe(Participant participant) {
    participants.add(participant);
  }

  public void broadcast(int number, Participant participant) {
    participants.stream().filter(p -> p != participant).forEach(p -> p.value += number);
  }
}

class MediatorDemo {
  public static void main(String[] args){
    Mediator mediator = new Mediator();
    Participant p1 = new Participant(mediator);
    Participant p2 = new Participant(mediator);
    System.out.println(p1.value);
    System.out.println(p2.value);
    p1.say(3);
    p2.say(2);
    System.out.println(" ");
    System.out.println(p1.value);
    System.out.println(p2.value);
  }
}
