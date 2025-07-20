package task.famous.advanced.task9;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Airport {

    private final BlockingQueue<Plane> waitsForLandingPlans = new LinkedBlockingQueue<>();
    private final BlockingQueue<Plane> landedPlanes = new LinkedBlockingQueue<>();

    Airport () {
        BlockingQueue<LandingStrip> availableStrips = new LinkedBlockingQueue<>();
        List<LandingStrip> stripList = createLandingStrips(availableStrips);
        availableStrips.addAll(stripList);

        AirTrafficController airTrafficController = new AirTrafficController(waitsForLandingPlans, availableStrips);
        new Thread(airTrafficController).start();
    }

    private List<LandingStrip> createLandingStrips(BlockingQueue<LandingStrip> availableStrips) {
        return List.of(new LandingStrip(1, landedPlanes, availableStrips),
                new LandingStrip(2, landedPlanes, availableStrips),
                new LandingStrip(3, landedPlanes, availableStrips));
    }

    void requestForLanding(Plane plane) {
        try {
            waitsForLandingPlans.put(plane);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BlockingQueue<Plane> getLandedPlanes() {
        return landedPlanes;
    }
}
