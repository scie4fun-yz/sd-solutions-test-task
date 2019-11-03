package com.sd.solutions.test.task.ops;

import com.sd.solutions.test.task.domain.BinarySquareMatrix;
import com.sd.solutions.test.task.domain.Matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelBinaryMatrixMultiplier implements MatrixMultiplier {

  @Override
  public Matrix multiply(Matrix left, Matrix right) {
    ExecutorService executor = Executors.newFixedThreadPool(MatrixMultiplier.NUMBER_OF_CORES_AVAILABLE);

    Matrix matrix = new BinarySquareMatrix(left.getRows());

    for (int i = 0; i < left.getRows(); i++) {
      for (int j = 0; j < right.getColumns(); j++) {
        ParallelBinaryMultiplier multiplyThread = new ParallelBinaryMultiplier(i, j, left, right, matrix);
        executor.execute(multiplyThread);
      }
    }
    executor.shutdown();

    return matrix;
  }

  public static class ParallelBinaryMultiplier extends Thread {
    private int row;
    private int column;
    private Matrix left;
    private Matrix right;
    private Matrix result;

    public ParallelBinaryMultiplier(int row, int column,
                                    Matrix left, Matrix right, Matrix result) {
      this.row = row;
      this.column = column;
      this.left = left;
      this.right = right;
      this.result = result;
    }

    public void run() {
      int value = 0;
      for (int i = 0; i < left.getColumns(); i++) {
        value = (value + (left.getCell(row, i) * right.getCell(i, column))) % 2;
      }
      result.setCell(row, column, value);
    }
  }
}
