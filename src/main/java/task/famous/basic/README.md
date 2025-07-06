## **Basic Tasks**

### Order doesn't influence on difficulty 

1. **Print Numbers in Sequence Using Multiple Threads**
    - **Description**: Create three threads to print numbers sequentially (e.g., Thread-1 prints `1`, Thread-2 prints `2`, etc.).
    - **Estimated Time**: 1–2 hours

2. **Ping-Pong Task**
    - **Description**: Use two threads to alternately print "Ping" and "Pong".
    - **Estimated Time**: 1–2 hours

3. **Thread-safe Counter**
    - **Description**: Create a thread-safe counter.
    - **Estimated Time**: 1–2 hours

4. **Hello from Threads**
    - **Description**: Create five threads that each print a message like `"Hello from Thread-X"`, where X is the thread name or number.
    - **Estimated Time**: 15–30 minutes

5. **Sum of Elements with Threads**
    - **Description**: Divide an array into parts and compute the sum of each part using separate threads. Collect the total sum at the end.
    - **Estimated Time**: 1 hour

6. **Thread-safe Bank Account**
    - **Description**: Simulate deposit and withdrawal from a shared bank account using multiple threads. Prevent race conditions.
    - **Estimated Time**: 1–2 hours

7. **Odd-Even Printer**
    - **Description**: Use two threads to print odd and even numbers from 1 to 20 in correct sequence.
    - **Estimated Time**: 1–2 hours

8. **Interruptible Worker Thread**
    - **Description**: Create a worker thread that performs a loop and stops gracefully when interrupted.
    - **Estimated Time**: 30–60 minutes

9. **Thread Join Practice**
    - **Description**: Start multiple threads, ensure the main thread waits until they all finish using `join()`, and then print "All done".
    - **Estimated Time**: 30–60 minutes

10. **Simple Thread Pool with ExecutorService**
    - **Description**: Use a fixed thread pool to execute 10 simple tasks (like printing numbers). Ensure all tasks are completed before exit.
    - **Estimated Time**: 1 hour

11. **Basic Thread Creation**
   - **Description**: Create a thread that prints “Hello from thread” using `Thread` and a lambda expression.
   - **Estimated Time**: 5 minutes

12. **Multiple Threads Printing Names**
   - **Description**: Launch several threads that print their thread names using `Thread.currentThread().getName()`.
   - **Estimated Time**: 10 minutes

13. **Passing Parameters to Threads**
   - **Description**: Pass parameters to thread logic using lambda expressions or constructor injection.
   - **Estimated Time**: 10 minutes

14. **Thread Join and Execution Order**
   - **Description**: Start a thread and wait for its completion using `join()`. Observe behavior with and without joining.
   - **Estimated Time**: 15 minutes

15. **Array Sum in a Thread**
   - **Description**: Compute the sum of an integer array inside a thread and print the result.
   - **Estimated Time**: 20 minutes

16. **Parallel Factorials**
   - **Description**: Create several threads that compute factorials of different numbers and print results.
   - **Estimated Time**: 30 minutes

17. **Synchronized Shared Variable**
   - **Description**: Demonstrate safe access to a shared counter using `synchronized` methods or blocks.
   - **Estimated Time**: 30 minutes

18. **Race Condition Demonstration**
   - **Description**: Show a race condition by incrementing a shared variable in multiple threads without synchronization.
   - **Estimated Time**: 20 minutes

19. **Thread-Safe Increment with `synchronized`**
   - **Description**: Use `synchronized` or `ReentrantLock` to ensure thread-safe increments in a counter class.
   - **Estimated Time**: 30 minutes

20. **Explicit Locking with `ReentrantLock`**
   - **Description**: Protect shared access using `ReentrantLock` and apply `try-finally` to ensure correct lock release.
   - **Estimated Time**: 30 minutes

21. **Even and Odd Printing with Two Threads**
   - **Description**: Create two threads: one prints even numbers, another prints odd numbers in order up to N.
   - **Estimated Time**: 40 minutes

22. **Lambda Thread Entry**
   - **Description**: Use lambda expressions as thread bodies to demonstrate concise thread creation.
   - **Estimated Time**: 5 minutes

23. **Single vs Multithreaded Sum**
   - **Description**: Compare the time it takes to compute a sum of a large array using a single thread vs multiple threads.
   - **Estimated Time**: 45 minutes

24. **Concurrent Increment with Multiple Threads**
   - **Description**: Launch many threads that increment the same shared counter N times and print the final result.
   - **Estimated Time**: 30 minutes