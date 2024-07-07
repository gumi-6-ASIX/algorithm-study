import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] tic;
        while (true) {
            int x = 0;
            int o = 0;
            int xtic = 0;
            int otic = 0;
            tic = new char[9];
            String str = br.readLine();
            if (str.equals("end")) {
                return;
            }
            tic = str.toCharArray();
            for (int i = 0; i < 9; i++) {
                if (tic[i] == 'O') {
                    o++;
                } else if (tic[i] == 'X') {
                    x++;
                }
            }
            if (o > x) {
                System.out.println("invalid");
            } else {
                for (int i = 1; i <= 7; i += 3) {
                    if (tic[i] == tic[i - 1] && tic[i] == tic[i + 1]) {
                        if (tic[i] == 'X') {
                            xtic++;
                        } else if (tic[i] == 'O') {
                            otic++;
                        }
                    } else {
                        continue;
                    }
                }
                for (int i = 3; i < 6; i++) {
                    if (tic[i] == tic[i - 3] && tic[i] == tic[i + 3]) {
                        if (tic[i] == 'X') {
                            xtic++;
                        } else if (tic[i] == 'O') {
                            otic++;
                        }
                    } else {
                        continue;
                    }
                }
                if (tic[4] == tic[2] && tic[4] == tic[6]) {
                    if (tic[4] == 'X') {
                        xtic++;
                    } else if (tic[4] == 'O') {
                        otic++;
                    }
                } else if (tic[4] == tic[0] && tic[4] == tic[8]) {
                    if (tic[4] == 'X') {
                        xtic++;
                    } else if (tic[4] == 'O') {
                        otic++;
                    }
                }
                if (x - 1 == o || x == o) {
                    if (x - 1 == o) {
                        if (xtic == 1 && otic == 0) {
                            System.out.println("valid");
                        } else if (xtic == 2 && x == 5) {
                            System.out.println("valid");
                        } else if (x == 5 & xtic == 0 && otic == 0) {
                            System.out.println("valid");
                        } else {
                            System.out.println("invalid");
                        }
                    } else {
                        if (otic == 1 && xtic == 0) {
                            System.out.println("valid");
                        } else {
                            System.out.println("invalid");
                        }
                    }
                } else {
                    System.out.println("invalid");
                }
            }
        }
    }
}
