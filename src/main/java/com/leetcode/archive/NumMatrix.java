package com.leetcode.archive;

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
