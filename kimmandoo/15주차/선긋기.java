import java.util.*;
import java.io.*;

class line implements Comparable<line> {
    int start;
    int end;

    public line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(line o) {
        return Integer.compare(this.start, o.start);
    }
}

class 선긋기 {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        List<line> lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lines.add(new line(start, end));
        }

        Collections.sort(lines); // 선분들을 시작점을 기준으로 정렬 -> 앞에서부터 쭉 겹치는게 있는지 확인하면서 나아간다
        // 이러면 끊긴경우는 당연히 분리됨

        int sum = 0;
        int prevEnd = -1000000001;

        for (line line : lines) {
            if (line.start > prevEnd) { // 현재 선분이 이전 선분과 겹치지 않으면
                sum += (line.end - line.start); // 겹치지 않는 부분의 길이를 더함
            } else if (line.end > prevEnd) { // 현재 선분이 이전 선분과 겹칠 경우
                sum += (line.end - prevEnd); // 겹치는 부분의 길이를 더함
            }
            prevEnd = Math.max(prevEnd, line.end); // 새롭게 이어진 선분의 끝점 배정
        }

        System.out.print(sum);
    }
}