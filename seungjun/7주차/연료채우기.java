package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import javafx.scene.layout.Priority;

public class 연료채우기 {


    static class Node implements Comparable<Node> {
        int end;
        int full;

        Node(int end, int full) {
            this.end = end;
            this.full = full;
        }

        @Override
        public int compareTo(Node obj) {
            return this.end - obj.end;
        }
    }

    static ArrayList<Node> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new Node(a, b));
        }
        st = new StringTokenizer(br.readLine());
        int fi = Integer.parseInt(st.nextToken());
        int myf = Integer.parseInt(st.nextToken());

        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // for (int i = 0; i < list.size(); i++) {
        // if(list.get(i))
        // }

    }

}
