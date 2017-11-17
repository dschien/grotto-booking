package ac.uk.bristol.cs.santa.grottobooking;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TestGrottoRoutePlanning {

    @Test
    void simple_test() {
        ArrayList<Grotto> grottos = new ArrayList<Grotto>();
        Grotto a = new Grotto("A", 3, 2);
        Grotto b = new Grotto("B", 2, 3);
        Grotto c = new Grotto("C", -5, 1);
        Grotto d = new Grotto("D", 2, -2);
        grottos.add(a);
        grottos.add(d);
        grottos.add(b);
        grottos.add(c);

        GrottoRoutePlanning tsp = new GrottoRoutePlanning();

        ArrayList<Grotto> result = tsp.computeOptimalTour(grottos, 1000.00);
        String[] res = new String[]{"A", "D", "C", "B", "A"};

        int idx = 0;
        for (Grotto p : result) Assert.assertTrue(p.getName().equals(res[idx++]));
    }
}
