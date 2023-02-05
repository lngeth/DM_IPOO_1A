package ensiie.shortestpath;

class LocationSet {
  private Location[] locations;
  private int nbLocations;

  /**
   * Default constructor
   */
  public LocationSet() {
    this.locations = null;
    this.nbLocations = 0;
  }
  
  /**
   * Getter of list of reference of instance of Location
   * @return the reference of a list of Location
   */
  public Location[] getLocations() {
    return locations;
  }

  /**
   * Getter of the number of Location inside the list
   * @return Int number
   */
  public int getNbLocations() {
    return nbLocations;
  }

  /**
   * Add a Location inside the list of Location
   * @param location the reference of instance of Location
   */
  public void add(Location location) {
    if (this.locations == null) { // array not initialized
      this.locations = new Location[2];
    } else {
      int lastElementIndex = this.nbLocations;
      if (this.nbLocations == 0)
        lastElementIndex = 0;
      else
        lastElementIndex = this.nbLocations - 1;

      if (this.locations[lastElementIndex] != null) { // array full
        int newSize = this.nbLocations + 2;
        Location[] newLocation = new Location[newSize];
        for (int i = 0; i < this.nbLocations; i++) {
          newLocation[i] = this.locations[i];
        }
        this.locations = newLocation;
      }
    }

    this.locations[this.nbLocations] = location;
    this.nbLocations++;
  }

  /**
   * Remove from list the Location with lowest distance and return it
   * @return the Location with minimum distance
   */
  public Location removeMin() {
    Location minDistanceLocation = null;
    
    if (this.nbLocations == 0) {
      return minDistanceLocation;
    }

    int minLocationIndex = 0;
    if (this.nbLocations == 1) {
      minDistanceLocation = this.locations[minLocationIndex];
      this.locations[minLocationIndex] = null;
    } else {
      for (int i = 1; i < this.nbLocations; i++) {
        if (this.locations[i].getDistance() < this.locations[i-1].getDistance())
          minLocationIndex = i;
      }
      minDistanceLocation = this.locations[minLocationIndex];
      this.locations[minLocationIndex] = null;
      // shift element
      for (int i = minLocationIndex; i < this.nbLocations-1; i++) {
        this.locations[i] = this.locations[i+1];
      }
    }
    this.nbLocations--;

    return minDistanceLocation;
  }
  
  /**
   * toString method that prints out content of the list
   */
  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < this.nbLocations; i++) {
      res += this.locations[i].getName() + " ";
    }
    return res;
  }
}
