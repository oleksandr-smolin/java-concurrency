package task.famous.advanced.task9;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class LandingStrip {

    private final int id;
    private final Queue<Plane> landedPlanes;
    private final Semaphore semaphore = new Semaphore(3);
    private final BlockingQueue<LandingStrip> availableStrips;

    LandingStrip(int id, Queue<Plane> landedPlanes, BlockingQueue<LandingStrip> availableStrips) {
        this.id = id;
        this.landedPlanes = landedPlanes;
        this.availableStrips = availableStrips;
    }

    void landPlane(Plane plane) {
        if (!semaphore.tryAcquire()) {
            throw new IllegalStateException("Aircraft crash! Because more then on plain were on landing on a strip");
        }

        try {
            TimeUnit.MICROSECONDS.sleep(500);
            landedPlanes.offer(plane);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }

        try {
            availableStrips.put(this);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
