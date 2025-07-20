package task.famous.advanced.task9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AirportTest {

    @Test
    void givenPlan_whenLanding_shouldSucceed() throws InterruptedException {
        Plane plane = new Plane(1);

        Airport airport = new Airport();

        airport.requestForLanding(plane);

        Assertions.assertEquals(plane, airport.getLandedPlanes().take());
    }


    @Test
    void givenPlans_whenLading_shouldNotLandOnSameLandingStrip() throws InterruptedException {
        Airport airport = new Airport();
        int amountOfPlans = 10;

        List<CompletableFuture<Void>> futures = IntStream.rangeClosed(1, amountOfPlans).mapToObj(Plane::new)
                .map(plane -> CompletableFuture.runAsync(() -> airport.requestForLanding(plane)))
                .toList();

        futures.forEach(CompletableFuture::join);

        TimeUnit.SECONDS.sleep(10);

        Assertions.assertEquals(amountOfPlans, airport.getLandedPlanes().size());
    }
}
