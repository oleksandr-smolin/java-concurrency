package task.famous.advanced.task15;

public class Request {

  private final long id;
  private final String params;

  private String response;

  public long getId() {
    return id;
  }

  public String getParams() {
    return params;
  }

  public synchronized void setResponse(String response) {
    this.response = response;
    notify();
  }

  public synchronized String getResponse() throws InterruptedException {
    while (response == null) {
      wait();
    }
    return response;
  }

  public Request(long id, String params) {
    this.id = id;
    this.params = params;
  }

  @Override
  public String toString() {
    return "Request{" +
        "id=" + id +
        ", payload='" + params + '\'' +
        ", response='" + response + '\'' +
        '}';
  }
}
