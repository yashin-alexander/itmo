//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Point {
    public String x;
    public String y;
    public String r;
    public String isInside;

    Point(String x, Double y, Double r, String isInside) {
        this.x = x;
        this.y = y.toString();
        this.r = r.toString();
        this.isInside = isInside;
    }

    public String getX() {
        return this.x;
    }

    public String getIsInside() {
        return this.isInside;
    }

    public String getR() {
        return this.r;
    }

    public String getY() {
        return this.y;
    }
}
