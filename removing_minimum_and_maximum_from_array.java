//You are given a 0-indexed array of distinct integers nums.

//There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.

//A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.

//Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.

class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int left = Math.max(minIndex, maxIndex) + 1;
        int right = n - Math.min(minIndex, maxIndex);
        int both = Math.min(minIndex, maxIndex) + 1
                 + n - Math.max(minIndex, maxIndex);

        return Math.min(left, Math.min(right, both));
    }
}



