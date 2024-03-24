import java.util.Scanner;

public class Night {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt(),m=sc.nextInt();
        if(n==1){
            System.out.println(1);
            return;
        }else if(n==2){
            int x=m/2;
            if(m%2==1 && m>1){
                x++;
            }
            if(x==0){
                System.out.println(x+1);
            }else if(x>4){
                System.out.println(4);
            }
            else{
                System.out.println(x);
            }

            return;
        }
        if(m>=7){
            System.out.println(m-2);
        }else{
            if(m>=4){
                System.out.println(4);
            }else{
                System.out.println(m);
            }
        }

    }
}
