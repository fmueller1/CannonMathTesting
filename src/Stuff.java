public class Stuff implements Differentiand{

    double distanceToSurface = 0.375;
    double windChargeExplosionRadius = 1;
    double windChargeExplosionPower = 1;
    double windChargeDetonationSurfaceOffset = -0.25;
    double initialTntXOffset = 0;
    double initialTntYOffset = 0.25;
    double initialTntZOffset = 0;
    double initialArrowXOffset = 0;
    double initialArrowYOffset = 0;
    double initialArrowZOffset = 0;
    double flipYaw = 1;
    double Pw = 1;
    double correctionCoef = 0;

    public Vector2D f(Vector2D in){

        double Xi = flipYaw * distanceToSurface;
        double Yi = distanceToSurface * Math.abs(1.0/Math.cos(in.x)) * Math.tan(-in.y);
        double Zi = distanceToSurface * Math.tan(in.x);

        double Xf1 = Xi + initialTntXOffset + flipYaw * windChargeDetonationSurfaceOffset;
        double Yf1 = Yi + initialTntYOffset;
        double Zf1 = Zi + initialTntZOffset;

        double Df = Math.sqrt(Math.pow(Xf1, 2) + Math.pow(Yf1, 2) + Math.pow(Yf1, 2));

        double P = (windChargeExplosionRadius - Df)/Pw;

        double Xf2 = Xf1 + initialArrowXOffset/P;
        double Yf2 = Yf1 + initialArrowYOffset/P;
        double Zf2 = Zf1 + initialArrowZOffset/P;
        P = (windChargeExplosionRadius - Df) * Pw;

        Vector2D tempVec = new Vector2D(Zf2, Xf2);
        if(tempVec.getQuadrant() == 2){
            correctionCoef = 1;
        }
        else if(tempVec.getQuadrant() == 3){
            correctionCoef = -1;
        } else{
            correctionCoef = 0;
        }

        double yawComp = clamp(Math.atan(-Xf2 / Zf2) + flipYaw);
        double pitchComp = clamp(-Math.atan(Yf2/Xf2) + Math.PI * correctionCoef);
        Vector2D outVector = new Vector2D(yawComp, pitchComp);
        outVector.convertToDegrees();
        return outVector;
    }

    double clamp(double x){
        return x % (2*Math.PI) - Math.PI;
    }
}
