
public class Main {
    public static void main(String[] args) {
        double delta = 0.0001;
        Vector2D inpVector = new Vector2D(0, 0);
        Vector2D target = new Vector2D(90, 90);
        inpVector.convertToRads();
        target.convertToRads();
        double yaw = Meth.calcYawCorrection(new Vector2D(-1,3), new Vector2D(1,2), new Vector2D(1,10));
        double pitch = Meth.calcPitchCorrection(new Vector2D(-1,3), new Vector2D(1,2), new Vector2D(1,10));
        System.out.println(new Vector2D(yaw, pitch));
    }
}