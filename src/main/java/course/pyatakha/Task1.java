package course.pyatakha;

public class Task1 {

  public static void main(String ... args) {

    Thread threadBasedOnRunnableObject = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Hi, I'm " + Thread.currentThread().getName());
      }
    });
    threadBasedOnRunnableObject.setName("threadBasedOnRunnableObject");
    threadBasedOnRunnableObject.start();

    Thread threadBasedOnLambda = new Thread(() -> System.out.println("Hi, I'm " + Thread.currentThread().getName()));
    threadBasedOnLambda.setName("threadBasedOnLambda");
    threadBasedOnLambda.start();

    Thread threadBasedOnMethodReference = new Thread(Task1::printWhoAmI);
    threadBasedOnMethodReference.setName("threadBasedOnMethodReference");
    threadBasedOnMethodReference.start();

    CustomThread threadBasedOnCustomThread = new CustomThread();
    threadBasedOnCustomThread.setName("threadBasedOnCustomThread");
    threadBasedOnCustomThread.start();


    System.out.println("Hi, I'm " + Thread.currentThread().getName());

  }

  private static void printWhoAmI() {
    System.out.println("Hi, I'm " + Thread.currentThread().getName());
  }

  static class CustomThread extends Thread {
    @Override
    public void run() {
      System.out.println("Hi, I'm " + Thread.currentThread().getName());
    }
  }

}
