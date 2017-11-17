package ac.uk.bristol.cs.santa.grottobooking;

import ac.uk.bristol.cs.santa.grottobooking.route.Location;
import ac.uk.bristol.cs.santa.grottobooking.route.LocationRoutePlanning;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TestLocationRoutePlanning {

    @Test
    void simple_test() {
        ArrayList<Location> locations = new ArrayList<Location>();
        Location a = new Location("A", 3D, 2D);
        Location b = new Location("B", 2D, 3D);
        Location c = new Location("C", -5D, 1D);
        Location d = new Location("D", 2D, -2D);
        locations.add(a);
        locations.add(d);
        locations.add(b);
        locations.add(c);

        LocationRoutePlanning tsp = new LocationRoutePlanning();

        ArrayList<Location> result = tsp.computeOptimalTour(locations, 1000.00);
        String[] res = new String[]{"A", "D", "C", "B", "A"};

        int idx = 0;
        for (Location p : result) Assert.assertTrue(p.getName().equals(res[idx++]));
    }
}
