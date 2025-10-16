
public class RREF {
    public static Vector2D reducedRowEchelonForm(Vector2D inpVector, Vector2D target, Differentiand Model, double delta){

        Vector2D differanceVector = target.subtract(inpVector);

        Vector2D partialDerivativeA = Derivative.takePartialDerivativeA(Model, inpVector, delta);
        Vector2D partialDerivativeB = Derivative.takePartialDerivativeB(Model, inpVector, delta);

        double yawWeight = calcYawWeight(partialDerivativeA, partialDerivativeB, differanceVector);
        double pitchWeight = calcPitchWeight(partialDerivativeA, partialDerivativeB, differanceVector);

        double weightedYaw = partialDerivativeA.x * yawWeight + partialDerivativeB.x * pitchWeight;
        double weightedPitch = partialDerivativeA.y * yawWeight + partialDerivativeB.y * pitchWeight;

        Vector2D correctionVector = new Vector2D(weightedYaw, weightedPitch);
        return inpVector.add(correctionVector);
    }

    static double calcYawWeight(Vector2D partialDerivativeA, Vector2D partialDerivativeB, Vector2D differanceVector){
        double yawWeightNumerator = (-partialDerivativeB.x * differanceVector.y + differanceVector.x * partialDerivativeB.y);
        double yawWeightDenominator = (partialDerivativeA.x * partialDerivativeB.y - partialDerivativeB.x * partialDerivativeA.y);
        if(yawWeightDenominator == 0){
            return 0;
        }
        return yawWeightNumerator/yawWeightDenominator;
    }

    static double calcPitchWeight(Vector2D partialDerivativeA, Vector2D partialDerivativeB, Vector2D differanceVector){
        double pitchWeightNumerator = (partialDerivativeA.x * differanceVector.y - differanceVector.x * partialDerivativeA.y);
        double pitchWeightDenominator = (partialDerivativeA.x * partialDerivativeB.y - partialDerivativeB.x * partialDerivativeA.y);
        if(pitchWeightDenominator == 0){
            return 0;
        }
        return pitchWeightNumerator/pitchWeightDenominator;
    }
}
