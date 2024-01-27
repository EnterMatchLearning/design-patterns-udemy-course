package org.example.exercises;

import java.util.Arrays;
import java.util.stream.Collectors;

class CombinationLock {
  public static final String LOCKED = "LOCKED";
  private int[] combination;
  public String status;

  public CombinationLock(int[] combination) {
    this.combination = combination;
    status = LOCKED;
  }

  public void enterDigit(int digit) {
    String stringDigit = String.valueOf(digit);

    if (status.equals(LOCKED)) {
      status = stringDigit;
      return;
    }

    String stringCombination = Arrays.stream(combination).mapToObj(Integer::toString).collect(Collectors.joining());

    if (status.length() + 1 == combination.length) {
      if (stringCombination.equals(status + stringDigit)) {
        status = "OPEN";
      } else {
        status = "ERROR";
      }
    } else if (!stringCombination.startsWith(status)) {
      status = "ERROR";
    } else {
      status += stringDigit;
    }
  }
}

class StateExerciseDemo {
  public static void main(String[] args){
    CombinationLock cl = new CombinationLock(new int[] {1, 2, 3, 4});
    System.out.println(cl.status);
    cl.enterDigit(1);
    System.out.println(cl.status);
    cl.enterDigit(2);
    System.out.println(cl.status);
    cl.enterDigit(3);
    System.out.println(cl.status);
    cl.enterDigit(4);
    System.out.println(cl.status);
  }
}



//class CombinationLock
//{
//  private int [] combination;
//
//  public String status;
//  private int digitsEntered = 0;
//  private boolean failed = false;
//
//  public CombinationLock(int[] combination)
//  {
//    this.combination = combination;
//    reset();
//  }
//
//  private void reset()
//  {
//    status = "LOCKED";
//    digitsEntered = 0;
//    failed = false;
//  }
//
//  public void enterDigit(int digit)
//  {
//    if (status.equals("LOCKED")) status = "";
//    status += digit;
//    if (combination[digitsEntered] != digit)
//    {
//      failed = true;
//    }
//    digitsEntered++;
//
//    if (digitsEntered == combination.length)
//      status = failed ? "ERROR" : "OPEN";
//  }
//}
//class CombinationLock
//{
//  private int [] combination;
//
//  public String status;
//  private int digitsEntered = 0;
//  private boolean failed = false;
//
//  public CombinationLock(int[] combination)
//  {
//    this.combination = combination;
//    reset();
//  }
//
//  private void reset()
//  {
//    status = "LOCKED";
//    digitsEntered = 0;
//    failed = false;
//  }
//
//  public void enterDigit(int digit)
//  {
//    if (status.equals("LOCKED")) status = "";
//    status += digit;
//    if (combination[digitsEntered] != digit)
//    {
//      failed = true;
//    }
//    digitsEntered++;
//
//    if (digitsEntered == combination.length)
//      status = failed ? "ERROR" : "OPEN";
//  }
//}