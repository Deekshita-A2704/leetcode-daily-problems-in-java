 //You are given an integer array nums of length n and an integer k.

//For each index i, define its instability score as max(nums[0..i]) - min(nums[i..n - 1]).

//In other words:

//max(nums[0..i]) is the largest value among the elements from index 0 to index i.
//min(nums[i..n - 1]) is the smallest value among the elements from index i to index n - 1.
//An index i is called stable if its instability score is less than or equal to k.

//Return the smallest stable index. If no such index exists, return -1.

class Solution {
    public int stableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        int prefixMax = nums[0];

        for (int i = 0; i < n; i++) {
            prefixMax = Math.max(prefixMax, nums[i]);

            if (prefixMax - suffixMin[i] <= k) {
                return i;
            }
        }

        return -1;
    }
}
