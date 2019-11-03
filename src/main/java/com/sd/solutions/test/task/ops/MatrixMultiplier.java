package com.sd.solutions.test.task.ops;


import com.sd.solutions.test.task.domain.Matrix;

import java.text.MessageFormat;

@FunctionalInterface
public interface MatrixMultiplier {

  int NUMBER_OF_CORES_AVAILABLE = Runtime.getRuntime().availableProcessors();

  static Matrix multiplyWithPerformanceLog(Matrix left, Matrix right, MatrixMultiplier matrixMultiplier) {
    long start = System.currentTimeMillis();

    Matrix result = matrixMultiplier.multiply(left, right);

    long end = System.currentTimeMillis();

    System.out.println(MessageFormat.format("{0} matrix multiplication complete in {1} ms",
            matrixMultiplier instanceof ParallelBinaryMatrixMultiplier ? "parallel" : "sequential",
            end - start));

    return result;
  }

  Matrix multiply(Matrix left, Matrix right);
}
