package org.example.exercises;

class Person
{
  public int id;
  public String name;

  public Person(int id, String name)
  {
    this.id = id;
    this.name = name;
  }
}

class PersonFactory
{
  private static int index = 0;

  public Person createPerson(String name)
  {
    return new Person(index++, name);
  }
}