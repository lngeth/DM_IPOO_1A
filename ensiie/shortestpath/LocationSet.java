package ensiie.shortestpath;

class LocationSet {
  private Location[] locations;
  private int nbLocations;

  public LocationSet() {
    this.locations = null;
    this.nbLocations = 0;
  }
  
  public Location[] getLocations() {
    return locations;
  }

  public int getNbLocations() {
    return nbLocations;
  }

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
  
  @Override
  public String toString() {
    String res = "";
    for (int i = 0; i < this.nbLocations; i++) {
      res += this.locations[i].getName() + " ";
    }
    return res;
  }
}
