public class Vector2D {
    public double x;
    public double y;

    public Vector2D(){
        x = 0;
        y = 0;
    }
    public Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public double getAngle(){
        return Math.atan2(y, x);
    }
    public double getMagnitude(){
        return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
    }
    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }
    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }
    public double dotProduct(Vector2D other){
        return this.x * other.x + this.y * other.y;
    }
    public void convertToRads(){
        x = x * Math.PI / 180.0;
        y = y * Math.PI / 180.0;
    }
    public void convertToDegrees(){
        x = x * 180.0 / Math.PI;
        y = y * 180.0 / Math.PI;
    }
    public int getQuadrant(){
        if (x >= 0){
            if(y >= 0){
                return 1;
            } else {
                return 4;
            }
        } else{
            if(y >= 0){
                return 2;
            } else {
                return 3;
            }
        }
    }
    public void overflowClamp(double min, double max){
        x = (x-min) % (max-min) + min;
        y = (y-min) % (max-min) + min;
    }
    public Vector3D sphericalToCartesian(){
        double m = this.getMagnitude();
        double cartiseanX = m * Math.sin(x) * Math.cos(y);
        double cartiseanY = m * Math.sin(x) * Math.sin(y);
        double cartiseanZ = m * Math.cos(y);
        return new Vector3D(cartiseanX,cartiseanY,cartiseanZ);

    }
}
