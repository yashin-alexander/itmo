package templates;

/**
 * Created by alexander on 30.04.17.
 */
public class CheckResult {
    private double x;
    private double y;
    private double r;

    private boolean result;

    public CheckResult( double x, double y, double r, boolean result ) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public void setResult( boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

}