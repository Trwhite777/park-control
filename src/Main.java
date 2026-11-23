public class Main {

    static void main(String[] args) {
         numeroMayor(67,62,13);
    }
    public static void numeroMayor(int a,int b,int c) {
        if (a>b && a>c) {
            System.out.println(a);
        } else if (b>a && b>c) {
            System.out.println(b);
        } else {
            System.out.println(c);
        }
    }

}
