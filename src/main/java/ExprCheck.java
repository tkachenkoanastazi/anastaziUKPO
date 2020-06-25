public class ExprCheck {
    public boolean check (String expression)
    {
        int a[]=new int[expression.length()];
        a[0]=expression.indexOf("\\");
        return a;
    }
}
