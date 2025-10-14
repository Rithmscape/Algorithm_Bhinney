import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        new Solution().solution();
    }

    private static class Solution {
        private String str, target;
        private char[] result;
        private int idx;

        void solution() throws IOException {
            input();
            solve();
            output();
        }

        private void solve() {
            result = new char[str.length()];
            idx = 0;
            int targetLen = target.length();
            char lastChar = target.charAt(targetLen - 1);

            for (char ch : str.toCharArray()) {
                result[idx++] = ch;

                // idx가 targetLen 이상이고, 마지막 문자가 일치할 때만 확인
                if (idx >= targetLen && result[idx - 1] == lastChar) {
                    if (matches(targetLen)) {
                        idx -= targetLen; // target만큼 제거
                    }
                }
            }
        }

        private boolean matches(int len) {
            for (int i = 0; i < len; i++) {
                if (result[idx - len + i] != target.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        private void input() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            str = br.readLine();
            target = br.readLine();
            br.close();
        }

        private void output() {
            if (idx == 0) {
                System.out.println("FRULA");
            } else {
                System.out.println(new String(result, 0, idx));
            }
        }
    }
}