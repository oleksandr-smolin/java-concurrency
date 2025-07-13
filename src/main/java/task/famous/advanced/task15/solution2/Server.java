package task.famous.advanced.task15.solution2;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

class Server {

    private final int id;

    private static final Map<String, String> PAGES = Map.of("/home", "HomePage.html");

    Server(int id) {
        this.id = id;
    }

    Response processRequest(Request request) {
        ThreadLocalRandom.current().ints(500_000).forEach(__ -> {}); // add some work for thread, don't make its life to easy :)
        String pageName = PAGES.get(request.path());

        if (pageName == null) {
            throw new IllegalArgumentException("Page not found");
        }

        return new Response(id, pageName);
    }
}
