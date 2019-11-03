package com.sd.solutions.test.task.utils;

import com.sd.solutions.test.task.domain.Matrix;

public class MatrixUtils {

  public static void matrixRandomBinaryValuesGenerator(final Matrix matrix) {
    for (int i = 0; i < matrix.getRows(); i++) {
      for (int j = 0; j < matrix.getColumns(); j++) {
        int randomInt = (int) (Math.random() * 2);
        matrix.setCell(i, j, randomInt);
      }
    }
  }
}
