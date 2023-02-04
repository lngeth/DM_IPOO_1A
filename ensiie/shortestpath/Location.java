package ensiie.shortestpath;

class Location {
  private String name;
  private double latitude;
  private double longitude;
  private Location[] neighbors;
  private double distance;
  private Location from;

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
    this.distance = Double.POSITIVE_INFINITY;
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

  public Location getFrom() {
    return from;
  }

  public void setFrom(Location from) {
    this.from = from;
  }

  public double distanceTo(Location to) {
    final int R = 6378;
    double lat1 = this.getLatitude();
    double long1 = this.getLongitude();
    double lat2 = to.getLatitude();
    double long2 = to.getLongitude();

    return R * (Math.PI/2 - Math.asin(Math.sin(lat2) * Math.sin(lat1) + Math.cos(long2 - long1) * Math.cos(lat2) * Math.cos(lat1)));
  }

  public void findPathTo(Location to) {
    // Initialization
    LocationSet set = new LocationSet();
    this.distance = 0;
    Location cur = this;
    cur.setFrom(null);

    // algo path finding
    while (cur != null && cur.getName() != to.getName()) {
      cur.proceedNode(set);
      cur = set.removeMin();
    }

    // print result
    if (cur == null) {
      System.out.println("Pas de chemin possible entre : " + this.name + " et " + to.getName());
    } else {
      System.out.println("Your trip from " + this.name + " to " + to.getName() + " in reverse order");
      
      // bad order print
      /*
      do {
        System.out.println("  " + cur.getName() + " at " + cur.getDistance());
      } while ((cur = cur.getFrom()) != null);
      */

      // good order print
      this.goodOrder(cur);
    }
  }

  public void proceedNode(LocationSet set) {
    for (int i = 0; i < this.neighbors.length; i++) {
      if (this.neighbors[i].getDistance() == Double.POSITIVE_INFINITY) { // non atteint
        set.add(this.neighbors[i]); // put atteint
      }

      double distanceToNeighbor = this.distance + distanceTo(this.neighbors[i]);
      if (distanceToNeighbor < this.neighbors[i].getDistance()) { // non atteint or lower distance
        this.neighbors[i].setDistance(distanceToNeighbor);
        this.neighbors[i].setFrom(this);
      }
    }
  }

  private void goodOrder(Location cur) {
    if (cur.getFrom() != null)
      goodOrder(cur.getFrom());
    System.out.println("  " + cur.getName() + " at " + cur.getDistance());
  }
}
