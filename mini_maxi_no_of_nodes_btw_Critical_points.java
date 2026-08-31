//A critical point in a linked list is defined as either a local maxima or a local minima.

//A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.

//A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.

//Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.

//Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, return [-1, -1].

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1, prev = -1;
        int min = Integer.MAX_VALUE;
        int pos = 1;

        ListNode p = head;
        ListNode c = head.next;

        while (c != null && c.next != null) {
            if ((c.val > p.val && c.val > c.next.val) ||
                (c.val < p.val && c.val < c.next.val)) {

                if (first == -1) {
                    first = pos;
                } else {
                    min = Math.min(min, pos - prev);
                }

                prev = pos;
            }

            p = c;
            c = c.next;
            pos++;
        }

        if (first == -1 || first == prev)
            return new int[]{-1, -1};

        return new int[]{min, prev - first};
    }
}
