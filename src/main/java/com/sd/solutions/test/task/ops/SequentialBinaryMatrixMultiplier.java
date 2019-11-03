package com.sd.solutions.test.task.ops;

import com.sd.solutions.test.task.domain.BinarySquareMatrix;
import com.sd.solutions.test.task.domain.Matrix;

public class SequentialBinaryMatrixMultiplier implements MatrixMultiplier {

  @Override
  public Matrix multiply(Matrix left, Matrix right) {
    Matrix matrix = new BinarySquareMatrix(left.getRows());

    for (int r = 0; r < left.getRows(); r++) {
      for (int c = 0; c < right.getColumns(); c++) {
        int value = 0;
        for (int i = 0; i < left.getColumns(); i++) {
          value = (value + (left.getCell(r, i) * right.getCell(i, c))) % 2;
        }
        matrix.setCell(r, c, value);
      }
    }

    return matrix;
  }
}
