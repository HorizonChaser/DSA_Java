package com.horizon;

import java.util.Scanner;

public class P4924 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m, n, temp = 1;
        m = in.nextInt();
        n = in.nextInt();
        int[][] ans = new int[n][n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                ans[x][y] = (int) Math.pow(temp, 2);
                temp++;
            }
        }

        printArray(ans, n);
    }

    private static int[][] rotate(int[][] ro, int x, int y, int r, boolean dir) {
        int nn = 2 * r + 1;
        int[][] ret = new int[nn][nn];

        if (dir) {
            for (int yy = 0; yy < nn; yy++)
                for (int xx = nn - 1; xx >= 0; xx--) {
                    ret[xx][yy] = ro[y+yy][xx-nn+1];
                }
        }
        return ret;
    }

    private static void printArray(int[][] a, int n) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (y != n - 1)
                    System.out.print(a[x][y] + " ");
                else
                    System.out.print(a[x][y]);
            }
            System.out.println();
        }
    }
}
