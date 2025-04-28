import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
       input();
       solution();
    }

    private static int n;
    private static long[][] edge;

    private static void solution() {
       long sum = 0;
       for (int i = 0; i < n; i++)
          sum += edge[i][0] * edge[i + 1][1] - edge[i + 1][0] * edge[i][1];
       double area = Math.abs(sum) / 2.0;
       System.out.printf("%.1f", area);
    }

    private static void input() throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       edge = new long[n + 1][2];
       for (int i = 0; i < n; i++)
          edge[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

       edge[n][0] = edge[0][0];
       edge[n][1] = edge[0][1]; // 수정된 부분
       br.close();
    }
}