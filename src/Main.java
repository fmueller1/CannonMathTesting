
public class Main {
    public static void main(String[] args) {
        double delta = 0.0001;
        Vector2D inpVector = new Vector2D(  180, -180);
        Vector2D target = new Vector2D(10, 10);
        inpVector.convertToRads();
        target.convertToRads();

        Vector2D out = RREF.reducedRowEchelonForm(inpVector, target, delta);
    }
}