public class Main {
    public static void main(String[] args){
        MCCannonModel mccm = new MCCannonModel();
        Vector2D target = new Vector2D(-135, 0);
        target.convertToRads();
        Vector2D out = Meth.targetInput(target, mccm);
        System.out.println(out.convertToDegrees());
        System.out.println(mccm.f(out.convertToRads()).convertToDegrees());
    }
}