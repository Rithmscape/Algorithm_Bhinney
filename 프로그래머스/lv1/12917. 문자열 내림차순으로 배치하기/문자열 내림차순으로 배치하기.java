import java.util.stream.*;
import java.util.*;

class Solution {
    public String solution(String s) {
        
        return Arrays.stream(s.split("")).sorted(Comparator.reverseOrder())
    .collect(Collectors.joining());
    }
}