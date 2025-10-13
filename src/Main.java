
public class Main {
    public static void main(String[] args) {
        double delta = 0.0001;
        Vector2D inpVector = new Vector2D(  180, -180);
        Vector2D target = new Vector2D(10, 10);
        inpVector.convertToRads();
        target.convertToRads();

        Vector2D out = reducedRowEchelonForm(inpVector, target, delta);
    }
    public static Vector2D reducedRowEchelonForm(Vector2D inpVector, Vector2D target, double delta){

        Vector2D differanceVector = target.subtract(inpVector);

        Vector2D partialDerivativeA = Derivative.takePartialDerivativeA(new Stuff(), inpVector, delta);
        Vector2D partialDerivativeB = Derivative.takePartialDerivativeB(new Stuff(), inpVector, delta);

        double yawWeight = calcYawWeight(partialDerivativeA, partialDerivativeB, differanceVector);
        double pitchWeight = calcPitchWeight(partialDerivativeA, partialDerivativeB, differanceVector);

        double weightedYaw = partialDerivativeA.x * yawWeight + partialDerivativeB.x * pitchWeight;
        double weightedPitch = partialDerivativeA.y * yawWeight + partialDerivativeB.y * pitchWeight;

        Vector2D correctionVector = new Vector2D(weightedYaw, weightedPitch);
        return inpVector.add(correctionVector);
    }

    public static double calcYawWeight(Vector2D partialDerivativeA, Vector2D partialDerivativeB, Vector2D differanceVector){
        double yawWeightNumerator = calcYawWeightNumerator(partialDerivativeB, differanceVector);
        double yawWeightDenominator = calcYawWeightDenominator(partialDerivativeA, partialDerivativeB);
        if(yawWeightDenominator == 0){
            return 0;
        }
        return yawWeightNumerator/yawWeightDenominator;
    }

    public static double calcYawWeightNumerator(Vector2D partialDerivativeB, Vector2D differanceVector){
        return (-partialDerivativeB.x * differanceVector.y + differanceVector.x * partialDerivativeB.y);
    }

    public static double calcYawWeightDenominator(Vector2D partialDerivativeA, Vector2D partialDerivativeB){
        return (partialDerivativeA.x * partialDerivativeB.y - partialDerivativeB.x * partialDerivativeA.y);
    }

    public static double calcPitchWeight(Vector2D partialDerivativeA, Vector2D partialDerivativeB, Vector2D differanceVector){
        double pitchWeightNumerator = calcPitchWeightNumerator(partialDerivativeA, differanceVector);
        double pitchWeightDenominator = calcPitchWeightDenominator(partialDerivativeA, partialDerivativeB);
        if(pitchWeightDenominator == 0){
            return 0;
        }
        return pitchWeightNumerator/pitchWeightDenominator;
    }

    public static double calcPitchWeightNumerator(Vector2D partialDerivativeA, Vector2D differanceVector){
        return (partialDerivativeA.x * differanceVector.y - differanceVector.x * partialDerivativeA.y);
    }

    public static double calcPitchWeightDenominator(Vector2D partialDerivativeA, Vector2D partialDerivativeB){
        return (partialDerivativeA.x * partialDerivativeB.y - partialDerivativeB.x * partialDerivativeA.y);
    }
}