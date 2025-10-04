
public class Main {
    public static void main(String[] args) {
        double delta = 0.0001;
        Vector2D inpVector = new Vector2D(  1, 1);
        Vector2D target = new Vector2D(10, 10);
        inpVector.convertToRads();
        target.convertToRads();

        Vector2D out = REF(inpVector, target, delta);
        System.out.println(out);
    }
    public static Vector2D REF(Vector2D inpVector, Vector2D target, double delta){

        Vector2D differanceVector = target.subtract(inpVector);

        Vector2D deltaInpVecA = Derivative.takePartialDerivativeA(new Stuff(), inpVector, delta);
        Vector2D deltaInpVecB = Derivative.takePartialDerivativeB(new Stuff(), inpVector, delta);

        double yawWeight = (-deltaInpVecB.x * differanceVector.y + differanceVector.x * deltaInpVecB.y);
        double yawDivisor = (deltaInpVecA.x * deltaInpVecB.y - deltaInpVecB.x * deltaInpVecA.y);
        if(yawDivisor == 0){
            yawWeight = 0;
        } else {
            yawWeight /= yawDivisor;
        }
        double pitchWeight = (deltaInpVecA.x * differanceVector.y - differanceVector.x * deltaInpVecA.y);
        double pitchDivisor = (deltaInpVecA.x * deltaInpVecB.y - deltaInpVecB.x * deltaInpVecA.y);
        if(pitchDivisor == 0){
            pitchWeight = 0;
        } else {
            pitchWeight /= pitchDivisor;
        }

        double weightedYaw = deltaInpVecA.x * yawWeight + deltaInpVecB.x * pitchWeight;
        double weightedPitch = deltaInpVecA.y * yawWeight + deltaInpVecB.y * pitchWeight;

        Vector2D correctionVector = new Vector2D(weightedYaw, weightedPitch);
        return inpVector.add(correctionVector);
    }
}