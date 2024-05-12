import java.util.*;
import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        int n;
        int weight;

        Node(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            if (o.n == this.n) {
                return o.weight - this.weight;
            }
            return o.n - this.n;
        }
    }
    
    static ArrayList<Integer> list = new ArrayList<>();
    static int[] sales = { 90, 80, 70, 60 };
    static PriorityQueue<Node>pq = new PriorityQueue<>();
    
    
    
    public int[] solution(int[][] users, int[] emoticons) {
        int len = emoticons.length;
        dfs(0,len,users,emoticons);
        Node data = pq.poll();
        int[] result = {data.n,data.weight};
        return result;
    }
    
    public static void dfs(int dep,int len,int[][] users,int[] emoticons) {
        if (dep == len) {
            buy(len,users,emoticons);
            return;
        }
        for (int i = 0; i < 4; i++) {
            list.add(sales[i]);
            dfs(dep + 1,len,users,emoticons);
            list.remove(list.size() - 1);
        }
    }
    
    public static void buy(int len,int[][] users,int[] emoticons) {
        int a=0,weight=0;
        int[] lastemo = new int[len];
        int[] result = new int[users.length];
        for(int i=0;i<len;i++){
            lastemo[i] = emoticons[i] * list.get(i)/100;
        }
        for(int i=0;i<users.length;i++){
            for(int j=0;j<len;j++){
                if(users[i][0] <= (100-list.get(j))){
                    result[i] += lastemo[j];
                } 
            }
            if(users[i][1] > result[i]){
                weight+=result[i];
            }else{
                a++;
            }   
        }
        pq.offer(new Node(a, weight));
    }
    
    
}
