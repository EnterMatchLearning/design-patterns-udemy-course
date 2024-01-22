package org.example.exercises;

interface Renderer {
  public String whatToRenderAs();
  public String renderTriangle();
  public String renderSquare();
}

class VectorRenderer implements Renderer {
  @Override
  public String whatToRenderAs() {
    return "lines";
  }

  @Override
  public String renderTriangle() {
    return "Drawing Triangle as lines";
  }

  @Override
  public String renderSquare() {
    return "Drawing Square as lines";
  }
}

class RasterRenderer implements Renderer {
  @Override
  public String whatToRenderAs() {
    return "pixels";
  }

  @Override
  public String renderTriangle() {
    return "Drawing Triangle as pixels";
  }

  @Override
  public String renderSquare() {
    return "Drawing Square as pixels";
  }
}

abstract class Shape {
  protected final Renderer renderer;
  public Shape(Renderer renderer) {
    this.renderer = renderer;
  }

  public abstract String getName();
}

class Triangle extends Shape {
  public Triangle(Renderer renderer) {
    super(renderer);
  }

  @Override
  public String getName() {
    return "Triangle";
  }

  @Override
  public String toString() {
    return renderer.renderTriangle();
  }
}

class Square extends Shape {
  public Square(Renderer renderer) {
    super(renderer);
  }

  @Override
  public String getName() {
    return "Square";
  }

  @Override
  public String toString() {
    return renderer.renderSquare();
  }
}

class Demo {
  public static void main(String[] args){
    Renderer vectorRenderer = new VectorRenderer();
    Renderer rasterRenderer = new RasterRenderer();
    Shape triangle = new Triangle(vectorRenderer);
    Shape triangle2 = new Triangle(rasterRenderer);
    Shape square = new Square(vectorRenderer);
    Shape square2 = new Square(rasterRenderer);

    System.out.println(triangle.toString());
    System.out.println(triangle2.toString());
    System.out.println(square.toString());
    System.out.println(square2.toString());
  }
}




// Not used anymore

//class VectorSquare extends Square {
//  @Override
//  public String toString() {
//    return String.format("Drawing %s as lines", getName());
//  }
//}
//
//class RasterSquare extends Square {
//  @Override
//  public String toString() {
//    return String.format("Drawing %s as pixels", getName());
//  }
//}

// imagine VectorTriangle and RasterTriangle are here too
