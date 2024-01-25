package org.example.exercises;

import java.util.Iterator;

class Node<T> {
  public T value;
  public Node<T> left, right, parent;

  public Node(T value) {
    this.value = value;
  }

  public Node(T value, Node<T> left, Node<T> right) {
    this.value = value;
    this.left = left;
    this.right = right;

    left.parent = right.parent = this;
  }

  public Iterator<Node<T>> preOrder() {
    return new PreorderIterator<>(this);
  }
}

class PreorderIterator<T> implements Iterator<Node<T>> {

  private Node<T> current;
  private boolean yieldedStart;

  public PreorderIterator(Node<T> root) {
    current = root;
  }

  public boolean hasRightmostParentWithRightChild(Node<T> node) {
    if (node.parent == null) return false;
    else {
      return (node.parent.right != null && node.parent.right != node)
          || hasRightmostParentWithRightChild(node.parent);
    }
  }

  @Override
  public boolean hasNext() {
    return current.left != null || hasRightmostParentWithRightChild(current);
  }

  @Override
  public Node<T> next() {
    if (!yieldedStart) {
      yieldedStart = true;
      return current;
    }
    if (current.left != null) {
      current = current.left;
      return current;
    }
    Node<T> p = current.parent;
    while (p != null && (p.right == null || p.right == current)) {
      current = p;
      p = p.parent;
    }
    current = p.right; // p cannot be null here is hasNext was called first and it returned true
    return current;
  }
}

class IteratorExerciseDemo {
  public static void main(String[] args) {
    //    Node<Integer> root = new Node<>(1, new Node<>(2), new Node<>(3));
    //    Iterator<Node<Integer>> nodeIterator = root.preOrder();
    //    while (nodeIterator.hasNext()) {
    //      System.out.println((nodeIterator.next().value));
    //    }

    Node<Character> node =
        new Node<>('a', new Node<>('b', new Node<>('c'), new Node<>('d')), new Node<>('e'));
    StringBuilder sb = new StringBuilder();
    Iterator<Node<Character>> it = node.preOrder();
    while (it.hasNext()) {
      sb.append(it.next().value);
    }
    System.out.println(sb);
    //    assertEquals("abcde", sb.toString());
  }
}
