package engine;

public class Route {

  private String from;
  private String to;
  private int distance;

  public String getFrom() {
    return this.from;
  }

  public String getTo() {
    return this.to;
  }

  public int getDistance() {
    return this.distance;
  }

  public Route(String from, String to, int distance) {
    this.from = from;
    this.to = to;
    this.distance = distance;
  }

  public boolean containsCity(String firstCity, String secondCity) {
    return from.equals(firstCity) && to.contains(secondCity) || from.equals(secondCity) && to.contains(firstCity);
  }

}
