//You are given a 0-indexed array of positive integers nums and a positive integer limit.

//In one operation, you can choose any two indices i and j and swap nums[i] and nums[j] if |nums[i] - nums[j]| <= limit.

//Return the lexicographically smallest array that can be obtained by performing the operation any number of times.

//An array a is lexicographically smaller than an array b if in the first position where a and b differ, array a has an element that is less than the corresponding element in b. For example, the array [2,10,3] is lexicographically smaller than the array [10,2,3] because they differ at index 0 and 2 < 10.


class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int[] result = new int[n];

        int start = 0;

        while (start < n) {
            int end = start;

            while (end + 1 < n && arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                indices.add(arr[i][1]);
                values.add(arr[i][0]);
            }

            Collections.sort(indices);
            Collections.sort(values);

            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }

            start = end + 1;
        }

        return result;
    }
}
