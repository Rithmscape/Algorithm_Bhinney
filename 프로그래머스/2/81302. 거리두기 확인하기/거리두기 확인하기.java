import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] ans = new int[5]; // 대기실이 총 5개
        
        for (int i = 0; i < 5; i++) {
            boolean isValid = true;
            
            for (int r = 0; r < 5 && isValid; r++) {
                for (int c = 0; c < 5 && isValid; c++) {
                    if (places[i][r].charAt(c) == 'P' && !bfs(r, c, places[i])) {
                        isValid = false;
                    }
                }
            }
            
            ans[i] = isValid ? 1 : 0;
        }

        return ans;
    }
    
    private boolean bfs(int x, int y, String[] place) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5 || (nx == x && ny == y))
                    continue;
                
                int d = Math.abs(nx - x) + Math.abs(ny - y);
                
                if(place[nx].charAt(ny) == 'P' && d <= 2) 
                    return false;
                if(place[nx].charAt(ny) == 'O' && d < 2)
                    queue.offer(new Node(nx, ny));
            }
        }
        
        return true;
    }
    
    private class Node {
        int x;
        int y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}