package task.famous.advanced.task9;

import java.util.concurrent.BlockingQueue;

public class AirTrafficController implements Runnable {

    private final BlockingQueue<Plane> waitsForLandingPlans;
    private final BlockingQueue<LandingStrip> availableStrips;


    public AirTrafficController(BlockingQueue<Plane> waitsForLandingPlans, BlockingQueue<LandingStrip> availableStrips) {
        this.waitsForLandingPlans = waitsForLandingPlans;
        this.availableStrips = availableStrips;
    }

    @Override
    public void run() {
        while (true) {
            Plane plane;
            try {
                plane = waitsForLandingPlans.take();
                availableStrips.take().landPlane(plane);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
