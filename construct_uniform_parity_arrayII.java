//You are given an array nums1 of n distinct integers.

//You want to construct another array nums2 of length n such that the elements in nums2 are either all odd or all even.

//For each index i, you must choose exactly one of the following (in any order):

//nums2[i] = nums1[i]​​​​​​​
//nums2[i] = nums1[i] - nums1[j], for an index j != i, such that nums1[i] - nums1[j] >= 1
//Return true if it is possible to construct such an array, otherwise return false.

class Solution {
    public boolean isPossible(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        boolean hasEven = false, hasOdd = false;

        for (int x : nums1) {
            if (x % 2 == 0) {
                hasEven = true;
            } else {
                hasOdd = true;
                minOdd = Math.min(minOdd, x);
            }
        }

        // Already all have the same parity
        if (!hasOdd || !hasEven)
            return true;

        // Every even number must be greater than some odd number
        for (int x : nums1) {
            if (x % 2 == 0 && x <= minOdd)
                return false;
        }

        return true;
    }
}
