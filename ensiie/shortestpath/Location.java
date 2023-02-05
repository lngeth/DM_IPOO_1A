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
  
  /**
   * toString like method, output the name & coordinates (latitude & longitude) of a Location
   */
  public void display() {
    System.out.println(this.name + " (" + this.latitude + ", " + this.longitude + ")");
  }

  /**
   * Getter of Location's name
   * @return the name of Location
   */
  public String getName() {
    return name;
  }

  /**
   * Setter of Location's name
   * @param name the Location's String name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Getter of Location's latitude
   * @return the latitude in Double
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Setter of Location's latitude
   * @param latitude the Location's latitude
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Getter of Location's longitude
   * @return the longitude in Double
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Setter of Location's longitude
   * @param longitude the Location's longitude in Double
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * Getter of Location's list of neighbors
   * @return a list of reference to instance of Location
   */
  public Location[] getNeighbors() {
    return neighbors;
  }

  /**
   * Setter of list neighbors
   * @param neighbors Several Location's reference seperated by a comma
   */
  public void setNeighbors(Location... neighbors) {
    this.neighbors = neighbors;
  }

  /**
   * Getter of Location's distance
   * @return Location's distance in Double
   */
  public double getDistance() {
    return distance;
  }

  /**
   * Setter of Location's distance
   * @param distance the Distance in Double
   */
  public void setDistance(double distance) {
    this.distance = distance;
  }

  /**
   * Getter of the last Location in the pathfinding
   * @return reference of instance of Location
   */
  public Location getFrom() {
    return from;
  }

  /**
   * Setter of last Location in the pathfinding
   * @param from reference of instance of Location
   */
  public void setFrom(Location from) {
    this.from = from;
  }

  /**
   * Calculate the distance between this Location to the Location specified in parameter
   * @param to the reference of instance of destination Location
   * @return a Double distance
   */
  public double distanceTo(Location to) {
    final int R = 6378;
    double lat1 = this.getLatitude();
    double long1 = this.getLongitude();
    double lat2 = to.getLatitude();
    double long2 = to.getLongitude();

    return R * (Math.PI/2 - Math.asin(Math.sin(lat2) * Math.sin(lat1) + Math.cos(long2 - long1) * Math.cos(lat2) * Math.cos(lat1)));
  }

  /**
   * Pathfinding algorithm that prints out the shortest path between this Location and the one
   * specified in the parameter
   * @param to the reference of the instance of the destination Location
   */
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
    this.reinit();
  }

  /**
   * Logical formula of pathfinding to find the shortest path
   * @param set the reference of instance of LocationSet
   */
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

  /**
   * Private recursive method used by pathfinding algo to print out the result in the right order (from to to)
   * @param cur the reference of instance of current Location
   */
  private void goodOrder(Location cur) {
    if (cur.getFrom() != null)
      goodOrder(cur.getFrom());
    System.out.println("  " + cur.getName() + " at " + cur.getDistance());
  }

  /**
   * Recursive function that initialize back the distance to Infinity
   */
  public void reinit() {
    this.distance = Double.POSITIVE_INFINITY;
    for (int i = 0; i < this.neighbors.length; i++) {
      if (this.neighbors[i].getDistance() != Double.POSITIVE_INFINITY)
        this.neighbors[i].reinit();
    }
  }
}
