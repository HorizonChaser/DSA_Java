package com.horizon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class P1042 {

    public static void main(String[] args) throws IOException {
        char currChar = (char) System.in.read();
        int winCntA = 0, loseCntA = 0, winCntB = 0, loseCntB = 0;
        List<Score> elevenType = new ArrayList<>();
        List<Score> twentyOneType = new ArrayList<>();

        while (currChar != 'E') {

            if (currChar == 'W') {
                winCntA++;
                winCntB++;
            }
            if (currChar == 'L') {
                loseCntA++;
                loseCntB++;
            }

            if ((winCntA >= 11 || loseCntA >= 11) && Math.abs(winCntA - loseCntA) >= 2) {

                elevenType.add(new Score(winCntA, loseCntA));
                winCntA = loseCntA = 0;

            }

            if ((winCntB >= 21 || loseCntB >= 21) && Math.abs(winCntB - loseCntB) >= 2) {

                twentyOneType.add(new Score(winCntB, loseCntB));
                winCntB = loseCntB = 0;

            }

            currChar = (char) System.in.read();
        }

        elevenType.add(new Score(winCntA, loseCntA));
        twentyOneType.add(new Score(winCntB, loseCntB));

        printArray(elevenType);
        System.out.println();
        printArray(twentyOneType);
    }

    private static int countChar(String s, char c) {
        int ret = 0, l = s.length();
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == c)
                ret++;
        }
        return ret;
    }

    private static void printArray(List<Score> s) {
        for (Score ss : s) {
            System.out.println(ss.a + ":" + ss.b);
        }
    }

    private static class Score {
        int a, b;

        Score(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
