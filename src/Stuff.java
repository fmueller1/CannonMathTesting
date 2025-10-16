public class Stuff implements Differentiand{

    int cannonOrientation = 1;
    double Dsx = 0.375; // x distance from surface
    double Rw = 1.1; //
    double Pw = 1; // wind-charge power
    double Os = -0.25; //
    double Oix = 0;
    double Oiy = 0.25;
    double Oiz = 0;
    double Xa = 0;
    double Ya = 0;
    double Za = 0;

    public Vector2D f(Vector2D in) {

        double rotatedYaw = rotateAxis(in.x, cannonOrientation);
        double directionOfCollisionSurface = -Math.abs(Math.sin(rotateAxis(in.x, cannonOrientation -1)))/Math.sin(rotateAxis(in.x, cannonOrientation -1));

        double Xi = directionOfCollisionSurface*Dsx;
        double Yi = Dsx * Math.abs(1.0/Math.cos(rotatedYaw))*Math.tan(-in.y);
        double Zi = Dsx * Math.tan(rotatedYaw);

        double Xf1 = Xi+Oix+directionOfCollisionSurface*Os;
        double Yf1 = Yi+Oiy;
        double Zf1 = Zi+Oiz;

        double Df = pythagoreanTheorem(Xf1, Yf1, Zf1);

        double P = (Rw-Df)*Pw; // effective wind-charge power

        double Xf2=Xf1+Xa/P;
        double Yf2=Yf1+Ya/P;
        double Zf2=Zf1+Ya/P;

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
}
