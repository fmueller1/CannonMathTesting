public class MCWorldModel implements Differentiand{

    int cannonOrientation = 1;
    double surfaceDistance = 0.375; // x distance from surface
    double windChargeRange = 1.1; //
    double totalWindChargePower = 1; // wind-charge power
    double explosionOffsetFromSurface = -0.25; //
    double initialTNTXOffset = 0;
    double initialTNTYOffset = 0.25;
    double initialTNTZOffset = 0;
    double initialArrowXOffset = 0;
    double initialArrowYOffset = 0;
    double initialArrowZOffset = 0;

    public Vector2D f(Vector2D in) {

        double relativeYaw = rotateAxis(in.x, cannonOrientation);
        double directionOfCollisionSurface = getDirectionOfCollisionSurface(relativeYaw);

        double XWindChargeImpact = directionOfCollisionSurface* surfaceDistance;
        double YWindChargeImpact = surfaceDistance * Math.abs(1.0/Math.cos(relativeYaw))*Math.tan(-in.y);
        double ZWindChargeImpact = surfaceDistance * Math.tan(relativeYaw);

        double Xf1 = XWindChargeImpact + initialTNTXOffset +directionOfCollisionSurface* explosionOffsetFromSurface;
        double Yf1 = YWindChargeImpact + initialTNTYOffset;
        double Zf1 = ZWindChargeImpact + initialTNTZOffset;

        double windChargeTntDistanceThingy = pythagoreanTheorem(Xf1, Yf1, Zf1);

        double effectiveWindChargePower = (windChargeRange - windChargeTntDistanceThingy)* totalWindChargePower; // effective wind-charge power

        double Xf2 = (effectiveWindChargePower*Xf1+initialArrowXOffset)/effectiveWindChargePower;
        double Yf2 = (effectiveWindChargePower*Yf1+initialArrowYOffset)/effectiveWindChargePower;
        double Zf2 = (effectiveWindChargePower*Zf1+initialArrowZOffset)/effectiveWindChargePower;

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

    double getDirectionOfCollisionSurface(double yaw){
        if (yaw > 0){
            return -1;
        }
        return 1;
    }
}
