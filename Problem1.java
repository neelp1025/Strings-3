// Time Complexity : O(12) since we are processing a triplet at a time and we are doing 3 operations at each level
// Space Complexity : O(3) for recursive stack when processing a triplet
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * First saving the constants like numbers below twenty and ten, twenty etc since they are special cases. Also saving hundred, million and billion since we are processing each triplet at a time and adding the suffix.
 * For each triplet, we are calculating the string format of the number and adding the necessary suffix based on the position of the current triplet.
 */
class Solution {
    String[] below20 = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty"};
    String[] tens = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] suffix = new String[]{"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
        if (num == 0)
            return "Zero";

        int idx = 0;
        String res = "";
        while (num != 0) {
            int triplet = num % 1000;
            // if current triplet is 0, nothing needs to be added
            if (triplet != 0)
                res = helper(triplet).trim() + " " + suffix[idx] + " " + res;

            num = num / 1000;
            idx++;
        }

        return res.trim();
    }

    private String helper(int num) {
        // base case for all numbers below 20 since they are unique
        if (num < 20) {
            return below20[num];
        }
        // if number is 67, get sixty first and then get seven
        else if (num < 100) {
            return tens[num / 10] + " " + below20[num % 10];
        }
        // if number is 567, set five hundred and call the function to get the string for 67
        else {
            return below20[num / 100] + " Hundred " + helper(num % 100);
        }
    }
}