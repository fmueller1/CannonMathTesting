
public class Meth {
    public static Vector2D reducedRowEchelonForm(Vector2D inpVector, Vector2D target, Differentiand Model, double delta){

        Vector2D differanceVector = target.subtract(inpVector);

        Vector2D partialDerivativeY = Derivative.takePartialDerivativeA(Model, inpVector, delta);
        Vector2D partialDerivativeP = Derivative.takePartialDerivativeB(Model, inpVector, delta);

        double yawWeight = calcYawWeight(partialDerivativeY, partialDerivativeP, differanceVector);
        double pitchWeight = calcPitchWeight(partialDerivativeY, partialDerivativeP, differanceVector);

        double weightedYaw = partialDerivativeY.x * yawWeight + partialDerivativeP.x * pitchWeight;
        double weightedPitch = partialDerivativeY.y * yawWeight + partialDerivativeP.y * pitchWeight;

        Vector2D correctionVector = new Vector2D(weightedYaw, weightedPitch);
        return inpVector.add(correctionVector);
    }

    static double calcYawWeight(Vector2D partialDerivativeY, Vector2D partialDerivativeP, Vector2D differanceVector){
        double yawWeightNumerator = (-partialDerivativeP.x * differanceVector.y + differanceVector.x * partialDerivativeP.y);
        double yawWeightDenominator = (partialDerivativeY.x * partialDerivativeP.y - partialDerivativeP.x * partialDerivativeY.y);
        if(yawWeightDenominator == 0){
            return 0;
        }
        return yawWeightNumerator/yawWeightDenominator;
    }

    static double calcPitchWeight(Vector2D partialDerivativeY, Vector2D partialDerivativeP, Vector2D differanceVector){
        double pitchWeightNumerator = (partialDerivativeY.x * differanceVector.y - differanceVector.x * partialDerivativeY.y);
        double pitchWeightDenominator = (partialDerivativeY.x * partialDerivativeP.y - partialDerivativeP.x * partialDerivativeY.y);
        if(pitchWeightDenominator == 0){
            return 0;
        }
        return pitchWeightNumerator/pitchWeightDenominator;
    }
}
