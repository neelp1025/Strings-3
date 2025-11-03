// Time Complexity : O(n) for processing one char at a time from string and doing the sum at the end
// Space Complexity : O(n) for storing the numbers in stack
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Since we have to apply BODMAS rule for parsing the string, we have to apply the division and multiplication operations whenever we see the sign for it.
 * Creating a stack where we keep track of all additions and subtractions using the last sign.
 * If the last sign was / or *, we can pop the last number, apply the division or multiplication operation with current number and last number in stack and put it back in the stack.
 * At the end, do a sum of all elements to get the final result.
 */
class Solution {
    public int calculate(String s) {
        Stack<Integer> st = new Stack<>();
        char lastSign = '+';
        int curr = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // number
            if (Character.isDigit(ch)) {
                curr = curr * 10 + (ch - '0');
            }
            //sign
            if (!Character.isDigit(ch) && !Character.isWhitespace(ch) || i == s.length() - 1) {
                if (lastSign == '-') {
                    st.push(-curr);
                } else if (lastSign == '+') {
                    st.push(curr);
                } else if (lastSign == '*') {
                    st.push(curr * st.pop());
                } else if (lastSign == '/') {
                    st.push(st.pop() / curr);
                }
                curr = 0;
                lastSign = ch;
            }
        }

        int res = 0;
        while (!st.isEmpty()) {
            res += st.pop();
        }

        return res;
    }
}