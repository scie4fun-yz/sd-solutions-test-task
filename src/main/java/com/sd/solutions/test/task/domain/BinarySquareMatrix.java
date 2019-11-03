package com.sd.solutions.test.task.domain;

public class BinarySquareMatrix extends Matrix {
  public BinarySquareMatrix(int side) {
    super(side, side);
  }

  public BinarySquareMatrix(Matrix other) {
    super(other);
  }
}
