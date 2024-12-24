package com.study;


/**
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 * 5. 最长回文子串
 *
 * @author zzh
 * @date 2024/9/26 18:02
 */
public class Solution5 {
    // 结果正确，但是提交会超时
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        String palindrome = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String newStr = s.substring(i, j);
                boolean isPalindrome = true;
                // 比较头和尾是否相同（判断是否回文数）
                for (int k = 0; k < newStr.length() / 2; k++) {
                    if (newStr.charAt(k) != newStr.charAt(newStr.length() - k - 1)) {
                        isPalindrome = false;
                        break;
                    }
                }
                if (isPalindrome) {
                    palindrome = newStr.length() >= palindrome.length() ? newStr : palindrome;
                }
            }
        }
        return palindrome;
    }

    public static void main(String[] args) {
        Solution5 longestPalindrome = new Solution5();
        String s = "gphyvqruxjmwhonjjrgumxjhfyupajxbjgthzdvrdqmdouuukeaxhjumkmmhdglqrrohydrmbvtuwstgkobyzjjtdtjroqpyusfsbjlusekghtfbdctvgmqzeybnwzlhdnhwzptgkzmujfldoiejmvxnorvbiubfflygrkedyirienybosqzrkbpcfidvkkafftgzwrcitqizelhfsruwmtrgaocjcyxdkovtdennrkmxwpdsxpxuarhgusizmwakrmhdwcgvfljhzcskclgrvvbrkesojyhofwqiwhiupujmkcvlywjtmbncurxxmpdskupyvvweuhbsnanzfioirecfxvmgcpwrpmbhmkdtckhvbxnsbcifhqwjjczfokovpqyjmbywtpaqcfjowxnmtirdsfeujyogbzjnjcmqyzciwjqxxgrxblvqbutqittroqadqlsdzihngpfpjovbkpeveidjpfjktavvwurqrgqdomiibfgqxwybcyovysydxyyymmiuwovnevzsjisdwgkcbsookbarezbhnwyqthcvzyodbcwjptvigcphawzxouixhbpezzirbhvomqhxkfdbokblqmrhhioyqubpyqhjrnwhjxsrodtblqxkhezubprqftrqcyrzwywqrgockioqdmzuqjkpmsyohtlcnesbgzqhkalwixfcgyeqdzhnnlzawrdgskurcxfbekbspupbduxqxjeczpmdvssikbivjhinaopbabrmvscthvoqqbkgekcgyrelxkwoawpbrcbszelnxlyikbulgmlwyffurimlfxurjsbzgddxbgqpcdsuutfiivjbyqzhprdqhahpgenjkbiukurvdwapuewrbehczrtswubthodv";
        String res = longestPalindrome.longestPalindrome(s);
        System.out.println("res = " + res);
    }
}
