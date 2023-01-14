import java.util.Locale;

public class pracquestion {

    public static void main(String[] args) {
    int[] a = {0, 0, -5, 30212};
    int[] b = {-10, 40, -3, 9};
        System.out.println(sumofTwo(a,b, -8));
    }

    public static boolean sumofTwo(int[] a, int[] b, int v){
        int needed_value = 0;
        for (int i=0; i<a.length; i++) {
            needed_value = v - a[i];
        }
        System.out.println(needed_value);
            for (int j = 0; j < b.length; j++) {
                if (b[j] == needed_value) {
                    return true;
                }
            }
            return false;
        }

        public void nothing(){
            String s = "A man, a plan, a canal: Panama";
            for(int i = 0; i < s.length(); i++){
                s.toLowerCase();
            }

        }


}
