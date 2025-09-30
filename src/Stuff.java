public class Stuff implements Differentiand{

    public Vector2D f(Vector2D in){
        return new Vector2D(in.x*in.y, in.x*Math.pow(in.y,2));
    }
}
