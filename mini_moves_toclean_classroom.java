//You are given an m x n grid classroom where a student volunteer is tasked with cleaning up litter scattered around the room. Each cell in the grid is one of the following:

//'S': Starting position of the student
//'L': Litter that must be collected (once collected, the cell becomes empty)
//'R': Reset area that restores the student's energy to full capacity, regardless of their current energy level (can be used multiple times)
//'X': Obstacle the student cannot pass through
//'.': Empty space
//You are also given an integer energy, representing the student's maximum energy capacity. The student starts with this energy from the starting position 'S'.

//Each move to an adjacent cell (up, down, left, or right) costs 1 unit of energy. If the energy reaches 0, the student can only continue if they are on a reset area 'R', which resets the energy to its maximum capacity energy.

//Return the minimum number of moves required to collect all litter items, or -1 if it's impossible.

import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0, k = 0;
        int[][] id = new int[m][n];

        for (int[] row : id)
            Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == 'L') {
                    id[i][j] = k++;
                }
            }
        }

        int totalMasks = 1 << k;

        if (k == 0)
            return 0;

        int states = m * n * (energy + 1) * totalMasks;
        boolean[] visited = new boolean[states];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc, energy, 0, 0});

        int startIndex = (((sr * n + sc) * (energy + 1) + energy) * totalMasks);
        visited[startIndex] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int target = totalMasks - 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int e = cur[2];
            int mask = cur[3];
            int moves = cur[4];

            if (mask == target)
                return moves;

            if (e == 0 && classroom[r].charAt(c) != 'R')
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                char ch = classroom[nr].charAt(nc);

                if (ch == 'X')
                    continue;

                int ne = e - 1;
                int nmask = mask;

                if (ch == 'L') {
                    nmask |= 1 << id[nr][nc];
                }

                if (ch == 'R') {
                    ne = energy;
                }

                int index = (((nr * n + nc) * (energy + 1) + ne)
                             * totalMasks + nmask);

                if (!visited[index]) {
                    visited[index] = true;
                    q.offer(new int[]{nr, nc, ne, nmask, moves + 1});
                }
            }
        }

        return -1;
    }
}
