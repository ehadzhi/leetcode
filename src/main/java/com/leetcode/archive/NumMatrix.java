package com.leetcode.archive;

// https://leetcode.com/problems/range-sum-query-2d-immutable/

//Given a 2D matrix matrix, handle multiple queries of the following type:
//
// Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1)
// and lower right corner (row2, col2).
// Implement the NumMatrix class:
//
// NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
// int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of
// matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
// You must design an algorithm where sumRegion works on O(1) time complexity.
public class NumMatrix {
    private int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                matrix[j][i] += matrix[j - 1][i];
            }
        }
    }

    public int sumRegion(int x, int y, int x1, int y1) {
        int result = matrix[x1][y1];
        if (x > 0 && y > 0) {
            result += matrix[x - 1][y - 1];
        }
        if (y > 0) {
            result -= matrix[x1][y - 1];
        }
        if (x > 0) {
            result -= matrix[x - 1][y1];
        }
        return result;
    }
}
