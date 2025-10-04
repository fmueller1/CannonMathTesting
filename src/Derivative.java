public class Derivative{
    public static Vector2D takePartialDerivativeA(Differentiand d, Vector2D in, double da){
        double compA = (d.f(new Vector2D(in.x+da, in.y)).x - d.f(in).x);
        double compB = (d.f(new Vector2D(in.x+da, in.y)).y - d.f(in).y);
        if(compA != 0.0){
            compA /= da;
        }
        if(compB != 0.0){
            compB /= da;
        }

        return new Vector2D(compA, compB);
    }
    public static Vector2D takePartialDerivativeB(Differentiand d, Vector2D in, double db){
        double compA = (d.f(new Vector2D(in.x, in.y+db)).x - d.f(in).x);
        double compB = (d.f(new Vector2D(in.x, in.y+db)).y - d.f(in).y);
        if(compA != 0){
            compA /= db;
        }
        if(compB != 0){
            compB /= db;
        }

        return new Vector2D(compA, compB);
    }
}
