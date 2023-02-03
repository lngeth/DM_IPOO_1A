package ensiie.shortestpath;

class LocationSet {
  private Location[] locations;
  private int nbLocations;

  public LocationSet() {
    this.locations = null;
    this.nbLocations = 0;
  }

  public void add(Location location) {
    if (this.locations == null) { // array not initialized
      this.locations = new Location[2];
    } else if (this.locations[this.nbLocations - 1] != null) { // array full
      int newSize = this.nbLocations + 2;
      Location[] newLocation = new Location[newSize];
      for (int i = 0; i < this.nbLocations; i++) {
        newLocation[i] = this.locations[i];
      }
      this.locations = newLocation;
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
    } else {
      for (int i = 1; i < this.nbLocations; i++) {
        Location actualLocation = this.locations[i];
        if (actualLocation.getDistance() < this.locations[i-1].getDistance())
          minLocationIndex = i;
      }
      minDistanceLocation = this.locations[minLocationIndex];
    }
    this.locations[minLocationIndex] = null;
    this.nbLocations--;
    return minDistanceLocation;
  }
}
