public class PPAP16120 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(br.readLine());
        StringBuilder tmp = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            tmp.append(sb.charAt(i));
            while (tmp.length() >= 4) {
                if (tmp.substring(tmp.length() - 4).toString().equals("PPAP")) {
                    tmp.delete(tmp.length() - 3, tmp.length());
                } else {
                    break;
                }
            }
        }
        if (tmp.toString().equals("P")) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}
