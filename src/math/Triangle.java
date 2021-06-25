package math;

public class Triangle {
  private int xy, xz, yz; // A,B,C
  private Point x, y, z;

  public int getXy() {
    return xy;
  }

  public void setXy(int xy) {
    this.xy = xy;
  }

  public int getXz() {
    return xz;
  }

  public void setXz(int xz) {
    this.xz = xz;
  }

  public int getYz() {
    return yz;
  }

  public void setYz(int yz) {
    this.yz = yz;
  }

  public Point getX() {
    return x;
  }

  public void setX(Point x) {
    this.x = x;
  }

  public Point getY() {
    return y;
  }

  public void setY(Point y) {
    this.y = y;
  }

  public void setZ(Point z) {
    this.z = z;
  }

  public Triangle(int a, int b, int c) {
    this.xy = a;
    this.xz = b;
    this.yz = c;
    getVertices();
  }

  private void getVertices() {
    x = new Point(0, 0);
    y = new Point(xy, 0);
    z = getZ();
  }

  public double getAngleA() {
    return Math.acos((Math.pow(yz, 2) - Math.pow(xz, 2) - Math.pow(xy, 2)) / (-2 * xz * xy));
  }

  public Point getZ() {
    double theta = getAngleA();
    double x_cord = xz * Math.cos(theta);
    double y_cord = xz * Math.sin(theta);
    return new Point(x_cord, y_cord);
  }

  public Point getA(double xa) {
    Point point_xy = getVectorXY();
    Point unit_xa = getUnitVector(point_xy);
    return x.addPoint(unit_xa.multiply(xa));

  }

  private Point getVectorXY() {
    return y.subtractPoint(x);
  }

  private Point getUnitVector(Point point_xy) {
    return point_xy.multiply(1 / (double) xy);
  }

  private boolean isTriangle() {
    if (xy + yz < xz)
      return false;
    if (xy + xz < yz)
      return false;
    return (yz + xz > xy);
  }

  public static int getDistance(int xy, int xz, int yz, int xa) {
    Triangle t = new Triangle(xy, xz, yz);
    // if (!t.isTriangle()) {
    //   if (maxSide(xy, xz, yz) == xy) {
    //     return Math.abs(xa - xz);
    //   }
    //   if (maxSide(xy, xz, yz) == xz) {
    //     return Math.abs(xz - xa);
    //   }
    //   if (maxSide(xy, xz, yz) == yz) {
    //     return xa + xz;
    //   }
    // }
    Point point_a = t.getA(xa);
    Point new_distance_cord = t.getZ().subtractPoint(point_a);
    return (int) new_distance_cord.getR();
  }

  private static int maxSide(int x, int y, int z) {
    return Math.max(Math.max(x, y), z);
  }
}