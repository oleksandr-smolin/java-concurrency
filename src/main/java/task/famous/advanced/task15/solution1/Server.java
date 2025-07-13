package task.famous.advanced.task15.solution1;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Server implements Runnable {

  private final String serverName;
  private final BlockingQueue<Request> requests = new LinkedBlockingQueue<>();

  public Server(String serverName) {
    this.serverName = serverName;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Request request = requests.take();
        new Random().ints(100_000).sorted().forEach(num -> {});
        String response = "Request Processed by " + serverName + ". request id: " + request.getId() + " params: " + request.getParams();
//        System.out.println(response);
        request.setResponse(response);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void sendRequest(Request request) {
    requests.offer(request);
  }
}
