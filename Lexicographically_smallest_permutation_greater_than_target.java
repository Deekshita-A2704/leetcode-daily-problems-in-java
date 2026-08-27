//You are given two strings s and target, both having length n, consisting of lowercase English letters.

//Return the lexicographically smallest permutation of s that is strictly greater than target. If no permutation of s is lexicographically strictly greater than target, return an empty string.

//A string a is lexicographically strictly greater than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.


class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();

        for(int i = n - 1; i >= 0;i--) {
            int[] freq = new int[26];

            for(char c : s.toCharArray()) 
                freq[c - 'a']++;

                boolean possible = true;
                for(int j = 0; j < i; j++) {
                    int x = target.charAt(j) - 'a';

                    if(freq[x] == 0){
                        possible = false;
                        break;
                    }

                    freq[x]--;
                }

                if(!possible)
                    continue;

                int x = target.charAt(i) - 'a';
                
                for(int c = x + 1; c < 26; c++) {
                    if(freq[c] > 0) {
                        StringBuilder ans = new StringBuilder(target.substring(0,i));
                        ans.append((char)('a' + c));
                        freq[c]--;

                        for(int j = 0; j < 26;j++) {
                            while(freq[j] > 0) {
                                ans.append((char)('a' + j));
                                freq[j]--;
                            }
                        }
                        return ans.toString();

                    }
                }
        
        

        }

        return "";

    }

}


//Problem level - medium
        

        
