
public class Main {
    public static void main(String[] args){
        MCCannonModel mccm = new MCCannonModel();
        Vector2D vec = new Vector2D(-90, 0);
        vec.convertToRads();
        System.out.println(Meth.targetInput(vec, mccm));
    }
}