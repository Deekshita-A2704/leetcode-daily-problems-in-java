//You are given two strings s and target, each of length n, consisting of lowercase English letters.

//Return the lexicographically smallest string that is both a palindromic permutation of s and strictly greater than target. If no such permutation exists, return an empty string.


class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] half = new int[26];

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
        }

        int halfLen = n / 2;
        String targetHalf = target.substring(0, halfLen);

        int[] temp = half.clone();
        boolean possible = true;

        for (int i = 0; i < halfLen; i++) {
            int x = targetHalf.charAt(i) - 'a';

            if (temp[x] == 0) {
                possible = false;
                break;
            }

            temp[x]--;
        }

        if (possible) {
            StringBuilder left = new StringBuilder(targetHalf);
            StringBuilder answer = new StringBuilder();

            answer.append(left);

            if (n % 2 == 1) {
                answer.append(middle);
            }

            answer.append(new StringBuilder(left).reverse());

            if (answer.toString().compareTo(target) > 0) {
                return answer.toString();
            }
        }

        for (int pos = halfLen - 1; pos >= 0; pos--) {

            int[] remaining = half.clone();
            boolean ok = true;

            for (int i = 0; i < pos; i++) {
                int x = targetHalf.charAt(i) - 'a';

                if (remaining[x] == 0) {
                    ok = false;
                    break;
                }

                remaining[x]--;
            }

            if (!ok) {
                continue;
            }

            int targetChar = targetHalf.charAt(pos) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    remaining[c]--;

                    StringBuilder left = new StringBuilder();
                    left.append(targetHalf.substring(0, pos));
                    left.append((char) ('a' + c));

                    for (int j = 0; j < 26; j++) {
                        while (remaining[j] > 0) {
                            left.append((char) ('a' + j));
                            remaining[j]--;
                        }
                    }

                    StringBuilder answer = new StringBuilder();
                    answer.append(left);

                    if (n % 2 == 1) {
                        answer.append(middle);
                    }

                    answer.append(new StringBuilder(left).reverse());

                    return answer.toString();
                }
            }
        }

        return "";
    }
}
