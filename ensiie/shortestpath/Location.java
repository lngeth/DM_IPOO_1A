package ensiie.shortestpath;

class Location {
  private String name;
  private double latitude;
  private double longitude;
  private Location[] neighbors;
  private double distance;

  /**
   * Initialization of an instance of Location with distance at Random between [1, 100]
   * @param name
   * @param latitude
   * @param longitude
   */
  public Location(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = (Math.PI * latitude) / 180;
    this.longitude = (Math.PI * longitude) / 180;
    this.neighbors = null;
    this.distance = Math.random() * 100;
  }
  
  public void display() {
    System.out.println(this.name + " (" + this.latitude + ", " + this.longitude + ")");
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public Location[] getNeighbors() {
    return neighbors;
  }

  public void setNeighbors(Location... neighbors) {
    this.neighbors = neighbors;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public double distanceTo(Location to) {
    final int R = 6378;
    double lat1 = this.getLatitude();
    double long1 = this.getLongitude();
    double lat2 = to.getLatitude();
    double long2 = to.getLongitude();

    return R * (Math.PI/2 - Math.asin(Math.sin(lat2) * Math.sin(lat1) + Math.cos(long2 - long1) * Math.cos(lat2) * Math.cos(lat1)));
  }
}
