package math;

public class Point {

  private double x;
  private double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getY() {
    return y;
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public void setY(double y) {
    this.y = y;
  }

  public double getR() {
    return Math.sqrt(x * x + y * y);
  }

  public double getTheta() {
    return Math.atan2(y, x);
  }

  // Euclidean distance between this point and that point
  public double distanceTo(Point that) {
    double dx = this.x - that.x;
    double dy = this.y - that.y;
    return Math.sqrt(dx * dx + dy * dy);
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  public Point addPoint(Point that) {
    return new Point(this.x + that.x, this.y + that.y);
  }

  public Point subtractPoint(Point that) {
    return new Point(this.x - that.x, this.y - that.y);
  }
  public Point multiply(double c) {
    return new Point(c * x, c * y);
  }

}
