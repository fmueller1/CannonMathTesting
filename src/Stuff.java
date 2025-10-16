public class Stuff implements Differentiand{

    int cannonOrientation = 1;
    double surfaceDistance = 0.375; // x distance from surface
    double windChargeRange = 1.1; //
    double totalWindChargePower = 1; // wind-charge power
    double Os = -0.25; //
    double initialTNTXOffset = 0;
    double initialTNTYOffset = 0.25;
    double initialTNTZOffset = 0;
    double initialArrowXOffset = 0;
    double initialArrowYOffset = 0;
    double initialArrowZOffset = 0;

    public Vector2D f(Vector2D in) {

        double relativeYaw = rotateAxis(in.x, cannonOrientation);
        double directionOfCollisionSurface = getDirrectionOfCollisionSurface(relativeYaw);

        double Xi = directionOfCollisionSurface* surfaceDistance;
        double Yi = surfaceDistance * Math.abs(1.0/Math.cos(relativeYaw))*Math.tan(-in.y);
        double Zi = surfaceDistance * Math.tan(relativeYaw);

        double Xf1 = Xi + initialTNTXOffset +directionOfCollisionSurface*Os;
        double Yf1 = Yi + initialTNTYOffset;
        double Zf1 = Zi + initialTNTZOffset;

        double Df = pythagoreanTheorem(Xf1, Yf1, Zf1);

        double P = (windChargeRange -Df)* totalWindChargePower; // effective wind-charge power

        double Xf2=Xf1+ initialArrowXOffset /P;
        double Yf2=Yf1+ initialArrowYOffset /P;
        double Zf2=Zf1+ initialArrowYOffset /P;

        System.out.println(Xf2);
        System.out.println(Yf2);
        System.out.println(Zf2);

        double yawComp = 0;
        double pitchComp = 0;
        return new Vector2D(yawComp, pitchComp);
    }

    double pythagoreanTheorem(double a, double b, double c){
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
    }

    double rotateAxis(double val, int rot){
        return val + Math.PI/2 * rot;
    }

    double getDirrectionOfCollisionSurface(double yaw){
        if (yaw > 0){
            return -1;
        }
        return 1;
    }
}
