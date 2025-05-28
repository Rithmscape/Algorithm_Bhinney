import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long ans = 0;
        
        for (int i = 1; i <= r2; i++) {
            long min = (long) Math.ceil(Math.sqrt(1.0 * r1 * r1 - 1.0 * i * i));
            long max = (long) Math.floor(Math.sqrt(1.0 * r2 * r2 - 1.0 * i * i));
            
            ans += (max - min + 1);
        }

        return ans * 4;
    }
}