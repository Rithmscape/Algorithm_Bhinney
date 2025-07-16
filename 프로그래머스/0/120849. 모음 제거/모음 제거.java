class Solution {
    public String solution(String my_string) {
        String[] arr = {"a", "e", "i", "o", "u"};
        
        for (String a : arr) {
            my_string = my_string.replaceAll(a, "");
        }
        
        return my_string;
    }
}