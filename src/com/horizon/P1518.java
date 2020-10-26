package com.horizon;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class P1518 {
    public static void main(String[] args) throws IOException {
        char[][] map = new char[10][10];
        Set<Status> statusSet = new HashSet<>();
        Player farmer = null, cow = null;
        int timeCnt = 0;

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                char temp = (char) System.in.read();
                if (temp == '.')
                    map[x][y] = '.';
                else if (temp == '*')
                    map[x][y] = '*';
                else if (temp == 'F')
                    map[x][y] = 'F';
                else if (temp == 'C')
                    map[x][y] = 'C';
                else {
                    y--;
                    continue;
                }

                if (map[x][y] == 'F') {
                    farmer = new Player(true, x, y);
                } else if (map[x][y] == 'C') {
                    cow = new Player(false, x, y);
                }
            }
        }

        while (timeCnt < 10000000) {

            if (farmer.x == cow.x && farmer.y == cow.y)
                break;
            else {

                Status currStat = new Status(farmer, cow);
                /*
                if (statusSet.contains(currStat)) {
                    System.out.println("0");
                    //return;
                } else {
                    statusSet.add(currStat);
                }
                 */
            }

            /*
            System.out.println("### " + timeCnt);
            System.out.println("farmer: " + farmer.x + " " + farmer.y + " " + farmer.dir);
            System.out.println("cow: " + cow.x + " " + cow.y + " " + cow.dir);
            printArray(map, 10);
            */

            if (farmer.isToTurn(map)) {
                farmer.changeDir();
            } else {
                map[farmer.x][farmer.y] = '.';
                farmer.move();
                map[farmer.x][farmer.y] = 'F';
            }

            if (cow.isToTurn(map)) {
                cow.changeDir();
            } else {
                map[cow.x][cow.y] = '.';
                cow.move();
                map[cow.x][cow.y] = 'C';
            }
            timeCnt++;
        }
        System.out.println((timeCnt == 10000000)?0:timeCnt);
    }

    private static class Status {
        Player a, b;

        Status(Player a, Player b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Status)) return false;
            Status status = (Status) o;
            return a.equals(status.a) &&
                    b.equals(status.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    private static class Player {
        boolean type;//true Farmer
        int x, y, dir = 1;
        //dir: 1↑ 2→ 3↓ 4←

        Player(boolean type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Player)) return false;
            Player player = (Player) o;
            return x == player.x &&
                    y == player.y && dir == player.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        void changeDir() {
            if (this.dir == 4)
                this.dir = 1;
            else
                this.dir++;
        }

        boolean isToTurn(char[][] map) {
            switch (this.dir) {
                case 1:
                    return x - 1 < 0 || map[x - 1][y] == '*';

                case 2:
                    return y + 1 >= 10 || map[x][y + 1] == '*';

                case 3:
                    if (x + 1 < 10 && map[x + 1][y] != '*')
                        return false;
                    return true;

                case 4:
                    return y - 1 < 0 || map[x][y - 1] == '*';
            }
            return false;
        }

        void move() {
            switch (dir) {
                case 1 :
                    x--;
                    return;
                case 2 :
                    y++;
                    return;
                case 3 :
                    x++;
                    return;
                case 4 :
                    y--;
                    return;
            }
        }
    }

    private static void printArray(char[][] a, int n) {
        System.out.println("##########");
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                System.out.print(a[x][y]);
            }
            System.out.println();
        }
        System.out.println("##########\n");
    }
}
