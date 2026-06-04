public class Solution {
    public int countPalindromes(String s) {
        int n = s.length();
        long MOD = 1_000_000_007;
        long totalPalindromes = 0;

        long[] prefixCounts = new long[10];
        long[][] prefixPairs = new long[10][10];
        
        long[] suffixCounts = new long[10];
        long[][] suffixPairs = new long[10][10];

        for (int i = n - 1; i >= 0; i--) {
            int currentDigit = s.charAt(i) - '0';
            

            for (int objDigit = 0; objDigit < 10; objDigit++) {
                suffixPairs[currentDigit][objDigit] += suffixCounts[objDigit];
            }
            suffixCounts[currentDigit]++;
        }

        for (int i = 0; i < n; i++) {
            int centerDigit = s.charAt(i) - '0';

            suffixCounts[centerDigit]--;
            for (int objDigit = 0; objDigit < 10; objDigit++) {
                suffixPairs[centerDigit][objDigit] -= suffixCounts[objDigit];
            }


            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    long leftPairs = prefixPairs[x][y];
                    long rightPairs = suffixPairs[y][x]; 
                    
                    if (leftPairs > 0 && rightPairs > 0) {
                        long combinations = (leftPairs * rightPairs) % MOD;
                        totalPalindromes = (totalPalindromes + combinations) % MOD;
                    }
                }
            }

            for (int objDigit = 0; objDigit < 10; objDigit++) {
                prefixPairs[objDigit][centerDigit] += prefixCounts[objDigit];
            }
            prefixCounts[centerDigit]++;
        }

        return (int) totalPalindromes;
    }
}