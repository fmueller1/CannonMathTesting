
public class Main {
    public static void main(String[] args) {
        double delta = 0.0001;
        Vector2D inpVector = new Vector2D(  -96, 36);
        Vector2D target = new Vector2D(10, 10);
        inpVector.convertToRads();
        target.convertToRads();

        System.out.println(new Stuff().f(inpVector));
//        System.out.println(RREF.reducedRowEchelonForm(inpVector, target, delta));
    }
}