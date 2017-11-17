package ac.uk.bristol.cs.santa.grottobooking;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Travelling Salesman Implementation to calculate shortest tour around grottos.
 * Liberally helped to from here: http://www.brendanweinstein.me/2012/03/13/c-to-java-computing-optimal-tour-between-islands/
 */
public class GrottoRoutePlanning {
    private static final Logger logger = LoggerFactory.getLogger(GrottoRoutePlanning.class);

    private class OptimalDistance {
        double dist;
    }

    /* Uses Pythagorean theorem to find the distance between two sets of coordinates */
    public double distance(Grotto one, Grotto two) {
        double a = Math.abs((two.getLatitude() - one.getLatitude()));
        double b = Math.abs((two.getLongitude() - one.getLongitude()));
        double distance = Math.sqrt((a * a) + (b * b));
        return distance;
    }

    /* Calculate the distance of a tour */
    public double computeTourCost(ArrayList<Grotto> tour) {
        double cost = 0.0;
        for (int i = 0; i < tour.size() - 1; i++) {
            cost += distance(tour.get(i), tour.get(i + 1));
        }
        return cost;
    }

    /* Copies one ArrayList over another */
    public void copyArrayListOverAnother(ArrayList overwriteThis, ArrayList copyThis) {
        overwriteThis.clear();
        for (int i = 0; i < copyThis.size(); i++) {
            overwriteThis.add(copyThis.get(i));
        }
    }


    /* Parent method to the recursive computeOptimalTour method */
    ArrayList<Grotto> computeOptimalTour(ArrayList<Grotto> islands, double maxDistance) {
        OptimalDistance optimalDistance = new OptimalDistance();
        optimalDistance.dist = Double.MAX_VALUE;
        int initialSize = islands.size();
        ArrayList<Grotto> workingTour = new ArrayList<>(initialSize);
        ArrayList<Grotto> optimalTour = new ArrayList<>(initialSize);
        computeOptimalTour(workingTour, islands, maxDistance, optimalTour, optimalDistance);
        logger.debug("optimal distance: " + optimalDistance.dist);
        return optimalTour;
    }

    /* Recursive Breadth-First Search Method */
    void computeOptimalTour(ArrayList<Grotto> tourSoFar, ArrayList<Grotto> remaining, double maxDistance, ArrayList optimalTour, OptimalDistance optimalDistance) {

        if (remaining.isEmpty()) {
            tourSoFar.add(tourSoFar.get(0));
            double totalCost = computeTourCost(tourSoFar);
            if (totalCost < optimalDistance.dist) {
                copyArrayListOverAnother(optimalTour, tourSoFar);
                optimalDistance.dist = totalCost;
            }
            tourSoFar.remove(tourSoFar.size() - 1);
            return;
        }

        for (int i = 0; i < remaining.size(); i++) {
            Grotto next = remaining.get(i);
            remaining.remove(i);
            tourSoFar.add(next);
            computeOptimalTour(tourSoFar, remaining, maxDistance, optimalTour, optimalDistance);
            tourSoFar.remove(tourSoFar.size() - 1);
            remaining.add(i, next);
        }
    }
}