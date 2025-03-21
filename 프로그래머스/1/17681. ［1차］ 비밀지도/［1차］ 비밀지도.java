class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] ans = new String[n];
        
        for (int i = 0; i < n; i++) {
            String str1 = intToBinary(arr1[i], n);
            String str2 = intToBinary(arr2[i], n);
            
            ans[i] = binaryToMap(addArr1Arr2(str1, str2));
        }
            
        return ans;
    }
    
    // arr1과 arr2를 합하기
    private String addArr1Arr2(String str1, String str2) {
        String res = "";
        for(int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == '0' && str2.charAt(i) == '0')
                res += "0";
            else 
                res += "1";
        }
        
        return res;
    }
    
    // 2진수를 #과 공백으로 바꾸기
    private String binaryToMap(String str) {
        str = str.replaceAll("1", "#");
        str = str.replaceAll("0", " ");
        
        return str;
    }
    
    // 2진수 변환 메서드
    private String intToBinary(int num, int n) {
        String str = Integer.toBinaryString(num);
        
        // n보다 길이가 작을 경우 "0" 추가해주기
        while (str.length() < n)
            str = "0" + str;
        
        return str;
    }
}