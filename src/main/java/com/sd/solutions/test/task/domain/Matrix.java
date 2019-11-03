package com.sd.solutions.test.task.domain;

import com.sd.solutions.test.task.ops.MatrixMultiplier;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Matrix {
  public static final int MATRIX_SIZE_LIMIT = 10_000;
  private final int rows;
  private final int columns;
  private final AtomicIntegerArray cells;

  public Matrix(int rows, int columns) {
    validateMatrixDimensions(rows, columns);
    this.rows = rows;
    this.columns = columns;
    this.cells = new AtomicIntegerArray(rows * columns);
  }

  public Matrix(Matrix other) {
    this(other.getRows(), other.getColumns());
    copyCells(other);
  }

  public Matrix(Matrix left, Matrix right, MatrixMultiplier matrixMultiplier) {
    this(matrixMultiplier.multiply(left, right));
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public int size() {
    return rows * columns;
  }

  public int getCell(int row, int column) {
    return cells.get(index(row, column));
  }

  public void setCell(int row, int column, int newCellValue) {
    this.cells.set(index(row, column), newCellValue);
  }

  private int index(int row, int column) {
    return column * this.columns + row;
  }

  private void copyCells(Matrix other) {
    for (int r = 0; r < other.getRows(); ++r) {
      for (int c = 0; c < other.getColumns(); ++c) {
        setCell(r, c, other.getCell(r, c));
      }
    }
  }

  private void validateMatrixDimensions(int rows, int columns) {
    if (rows * columns < 0 || rows * columns > MATRIX_SIZE_LIMIT) {
      throw new IllegalArgumentException("Matrix size should be in range of 0 ... 10 000");
    }
  }

  private boolean areCellsEqual(Matrix other) {
    boolean areEqualCells = true;
    for (int r = 0; r < rows; ++r) {
      for (int c = 0; c < columns; ++c) {
        areEqualCells = getCell(r, c) == other.getCell(r, c);
        if (!areEqualCells) {
          break;
        }
      }
    }

    return areEqualCells;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Matrix matrix = (Matrix) o;
    return rows == matrix.rows &&
            columns == matrix.columns &&
            areCellsEqual(matrix);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rows, columns, cells);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (int r = 0; r < rows; ++r) {
      builder.append("{ ");
      for (int c = 0; c < columns; ++c) {
        int cellValue = getCell(r, c);
        String delimiter = c < columns - 1 ? ", " : "";
        builder.append(cellValue).append(delimiter);
      }
      builder.append(" }");
      builder.append("\n");
    }

    return builder.toString();
  }
}
