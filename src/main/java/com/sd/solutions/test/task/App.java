package com.sd.solutions.test.task;

import com.sd.solutions.test.task.domain.BinarySquareMatrix;
import com.sd.solutions.test.task.domain.Matrix;
import com.sd.solutions.test.task.ops.MatrixMultiplier;
import com.sd.solutions.test.task.ops.ParallelBinaryMatrixMultiplier;
import com.sd.solutions.test.task.ops.SequentialBinaryMatrixMultiplier;
import com.sd.solutions.test.task.utils.MatrixUtils;

import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Please, enter square matrix side length:");
      int squareMatrixSide = readSide(scanner.nextLine());

      Matrix left = new BinarySquareMatrix(squareMatrixSide);
      MatrixUtils.matrixRandomBinaryValuesGenerator(left);

      Matrix right = new BinarySquareMatrix(squareMatrixSide);
      MatrixUtils.matrixRandomBinaryValuesGenerator(right);

      Matrix sequentialProduct = MatrixMultiplier.multiplyWithPerformanceLog(left, right, new SequentialBinaryMatrixMultiplier());
      Matrix parallelProduct = MatrixMultiplier.multiplyWithPerformanceLog(left, right, new ParallelBinaryMatrixMultiplier());
      validateMultiplications(sequentialProduct, parallelProduct);

      if (squareMatrixSide <= 10) {
        System.out.println("\nTwo binary square matrix to be multiplied generated:");
        System.out.println(left);
        System.out.println(right);

        System.out.println("Product of matrices generated above is:");
        System.out.println(parallelProduct);
      } else {
        System.out.println(
                "\nNOTE: To be able to see matrices cells\n      " +
                        "square matrix side length should be not more than 10");
      }

      System.out.println("\nDo you want to try again? (Type \"NO\")");
      String answer = scanner.nextLine();
      if (answer.toUpperCase().equals("NO")) {
        break;
      }
    }

    scanner.close();
  }

  private static int readSide(String inputLine) {
    try {
      return Integer.parseInt(inputLine);
    } catch (NumberFormatException e) {
      throw new NumberFormatException("Wrong square matrix side length passed!");
    }
  }

  private static void validateMultiplications(Matrix sequentialProduct, Matrix parallelProduct) {
    if (!sequentialProduct.equals(parallelProduct)) {
      throw new RuntimeException("Sequential and Parallel products are not equal! Shame on you, developer! :)");
    }
  }
}
