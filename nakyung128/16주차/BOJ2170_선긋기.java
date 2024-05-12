import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ2170_선긋기 {
    static class Line implements Comparable<Line> {
        int x;
        int y;

        Line(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Line o) {
            if (this.x != o.x) {
                return this.x - o.x;
            } else {
                return this.y - o.y;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayList<Line> lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lines.add(new Line(x, y));
        }

        lines.sort(null);

        ArrayList<Line> answer = new ArrayList<>();
        answer.add(lines.get(0));

        int cnt = 0;
        for (int i = 1; i < lines.size(); i++) {
            if (lines.get(i).x <= answer.get(cnt).y && lines.get(i).y > answer.get(cnt).y) {
                answer.get(cnt).y = lines.get(i).y;
            } else if (lines.get(i).x > answer.get(cnt).y) {
                answer.add(lines.get(i));
                cnt++;
            }
        }

        int distance = 0;
        for (Line line : answer) {
            distance += Math.abs(line.y - line.x);
        }

        System.out.println(distance);
    }
}
