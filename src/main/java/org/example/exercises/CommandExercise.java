package org.example.exercises;

class Command {
  enum Action {
    DEPOSIT,
    WITHDRAW
  }

  public Action action;
  public int amount;
  public boolean success;

  public Command(Action action, int amount) {
    this.action = action;
    this.amount = amount;
  }

  public void call(Account account) {
    switch (action) {
      case DEPOSIT:
        success = account.deposit(amount);
        break;
      case WITHDRAW:
        success = account.withdraw(amount);
        break;
    }
  }

}

class Account {
  public int balance;

  public boolean deposit(int amount) {
    balance += amount;
    return true;
  }

  public boolean withdraw(int amount) {
    if (balance - amount < 0) {
      return false;
    }
    balance -= amount;
    return true;
  }

  public void process(Command c) {
    c.call(this);
  }
}
