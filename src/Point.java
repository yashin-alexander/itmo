public class Point {
    public String x;
    public String y;
    public String r;
    public String isInside;
    public String color;

    Point(String x, Double y, Double r, String Color, String isInside) {
        this.x = x;
        this.y = y.toString();
        this.r = r.toString();
        this.isInside = isInside;
        this.color = Color;
    }

    public String getX() {
        return this.x;
    }

    public String getIsInside() {
        return this.isInside;
    }

    public String getColor() {
        return this.color;
    }

    public String getR() {
        return this.r;
    }

    public String getY() {
        return this.y;
    }
}
