package com.horizon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1563 {
    public static void main(String[] args) {
        int n, m, currPos = 0;
        Scanner in = new Scanner(System.in);
        List<People> peopleList = new ArrayList<>();
        n = in.nextInt();
        m = in.nextInt();

        for (int i = 0; i < n; i++) {
            peopleList.add(new People(in.nextInt(), in.nextLine().replace(" ", "")));
        }

        for (int i = 0; i < m; i++) {
            int direction = in.nextInt(), x = in.nextInt();
            boolean lr = false;//false --

            if ((direction == 0 && peopleList.get(currPos).isFacingInward == 0)) {
                lr = false;
            }
            if ((direction == 0 && peopleList.get(currPos).isFacingInward == 1)) {
                lr = true;
            }
            if ((direction == 1 && peopleList.get(currPos).isFacingInward == 0)) {
                lr = true;
            }
            if ((direction == 1 && peopleList.get(currPos).isFacingInward == 1)) {
                lr = false;
            }

            if (lr) {
                currPos = (currPos + x) % n;
            } else {
                currPos = (currPos - x < 0)?(currPos+n-x):(currPos-x);
            }
            //System.out.println("#" + currPos);
        }
        System.out.println(peopleList.get(currPos).name);
    }

    private static class People {
        int isFacingInward;
        String name;

        People(int isFacingInward, String name) {
            this.isFacingInward = isFacingInward;
            this.name = name;
        }
    }
}
