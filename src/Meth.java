
public class Meth {

    final static double acceptableError = 0.1;

    public static Vector2D targetInput(Vector2D target, Differentiand Model, double delta){
        long iterations = 100000;
        Vector2D out = target;
        for(int i = 0; i < iterations; i++){
            Vector2D error = target.subtract(Model.f(out));
            if(error.getMagnitude() < acceptableError){
                return out;
            }
            out = aproxTargetInput(out, target, Model);
        }
        return out;
    }


    public static Vector2D aproxTargetInput(Vector2D inpVector, Vector2D target, Differentiand Model){

        Vector2D differanceVector = target.subtract(Model.f(inpVector));

        Vector2D partialDerivativeY = Derivative.takePartialDerivativeA(Model, inpVector);
        Vector2D partialDerivativeP = Derivative.takePartialDerivativeB(Model, inpVector);

        double yawCorrection = calcYawCorrection(partialDerivativeY, partialDerivativeP, differanceVector);
        double pitchCorrection = calcPitchCorrection(partialDerivativeY, partialDerivativeP, differanceVector);

        Vector2D correctionVector = new Vector2D(yawCorrection, pitchCorrection);
        return inpVector.add(correctionVector);
    }

    public static double calcYawCorrection(Vector2D partialDerivativeY, Vector2D partialDerivativeP, Vector2D differanceVector){
        double yawWeightNumerator = (-partialDerivativeP.x * differanceVector.y + differanceVector.x * partialDerivativeP.y);
        double yawWeightDenominator = (partialDerivativeY.x * partialDerivativeP.y - partialDerivativeP.x * partialDerivativeY.y);
        if(yawWeightDenominator == 0){
            return 0;
        }
        return yawWeightNumerator/yawWeightDenominator;
    }

    public static double calcPitchCorrection(Vector2D partialDerivativeY, Vector2D partialDerivativeP, Vector2D differanceVector){
        double pitchWeightNumerator = (partialDerivativeY.x * differanceVector.y - differanceVector.x * partialDerivativeY.y);
        double pitchWeightDenominator = (partialDerivativeY.x * partialDerivativeP.y - partialDerivativeP.x * partialDerivativeY.y);
        if(pitchWeightDenominator == 0){
            return 0;
        }
        return pitchWeightNumerator/pitchWeightDenominator;
    }
}
