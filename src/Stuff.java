public class Stuff implements Differentiand{

    double distanceToSurface = 0.375;
    double windChargeExplosionRadius = 1;
    double windChargeExplosionPower = 1;
    double windChargeDetonationSurfaceOffset = 0.25;
    double initialTntXOffset = 0;
    double initialTntYOffset = 0.25;
    double initialTntZOffset = 0;
    double initialArrowXOffset = 0;
    double initialArrowYOffset = 0;
    double initialArrowZOffset = 0;
    double S = -1;
    double Pw = 1;
    double flipYaw = 0;

    public Vector2D f(Vector2D in){
        double Xi = distanceToSurface;
        double Yi = distanceToSurface * Math.abs(1.0/Math.cos(in.x)) * Math.tan(-in.y);
        double Zi = distanceToSurface * Math.tan(in.x);

        double M = (S+1)/2.0;

        double Xf1 = Xi + initialTntXOffset * S * windChargeDetonationSurfaceOffset;
        double Yf1 = Yi + initialArrowYOffset;
        double Zf1 = Zi + initialTntZOffset;

        double Df = Math.sqrt(Math.pow(Xf1, 2) + Math.pow(Yf1, 2) + Math.pow(Yf1, 2));

        double P = (windChargeExplosionRadius - Df)/Pw;

        double Xf2 = Xf1 + initialArrowXOffset/P;
        double Yf2 = Yf1 + initialArrowYOffset/P;
        double Zf2 = Zf1 + initialArrowZOffset/P;
        P = (windChargeExplosionRadius - Df) * Pw;


        double yawComp = 0;
        double pitchComp = 0;
        return new Vector2D(yawComp, pitchComp);
    }
}
