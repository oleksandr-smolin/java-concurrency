## **Advanced Tasks** (Estimated Time: 24–36 hours from 1 to 5)

### 1. Bank Transactions System
- **Objective**: Create a multithreaded application to simulate money transfers between accounts.
- **Requirements**:
  - **Account Management**:
    - Each account should have a unique ID and a balance.
    - Accounts should be stored in a thread-safe data structure.
  - **Transaction Handling**:
    - Threads simulate money transfers between accounts:
      - Deduct money from the sender’s account.
      - Add money to the receiver’s account.
    - Ensure transactions are atomic to avoid partial updates (e.g., deduction without corresponding addition).
  - **Concurrency Handling**:
    - Use thread-safe mechanisms to prevent race conditions during balance updates.
    - Avoid deadlocks when multiple threads attempt to lock multiple accounts simultaneously.
  - **Error Handling**:
    - Handle scenarios like insufficient funds or invalid account IDs gracefully.
    - Log transaction failures for debugging and audit purposes.
  - **Metrics**:
    - Track the total number of successful and failed transactions.
    - Optionally, record the time taken for each transaction.

---

### 2. **Task Scheduler**
- **Description**: Use `ScheduledExecutorService` to schedule tasks periodically.
- **Estimated Time**: 3–4 hours

---

### 3. Web Crawler
- **Objective**: Build a simple multithreaded web crawler that visits URLs concurrently.
- **Key Techniques**:
   - Use `ConcurrentHashMap` to track visited URLs and avoid duplicates.

---

### 4. Chat Application
- **Objective**: Simulate a chat server where multiple clients can send and receive messages.
- **Key Techniques**:
   - Use `ExecutorService` for thread management.
   - Use `BlockingQueue` for message handling between threads.

---

### 5. Stock Price Simulator
- **Objective**: Simulate live stock price updates and allow multiple users to subscribe to updates.
- **Requirements**:
  - **Stock Price Updates**:
    - Maintain a list of stocks with their current prices.
    - Periodically update stock prices in a background thread to simulate live market behavior.
    - Updates should be pushed to all subscribers in real-time.
  - **Subscriber Management**:
    - Support multiple subscribers who can receive stock price updates concurrently.
    - Allow subscribers to register and deregister dynamically.
    - Ensure thread-safe management of the subscriber list.
  - **Concurrency Handling**:
    - Use a thread-safe mechanism for broadcasting updates to all subscribers.
    - Ensure that updates are consistent across all subscribers without delays.
  - **Event Handling**:
    - Use a queue to buffer stock price updates, ensuring that no updates are lost even if subscribers are temporarily unavailable.
  - **Logging and Monitoring**:
    - Log all stock price updates and subscriber notifications.
    - Optionally, track the latency of notifications to subscribers.

---

### 6. Ride-Sharing System
- **Objective**: Simulate a ride-sharing app where passengers request rides and drivers accept them.
- **Key Techniques**:
   - Use `BlockingQueue` to match passengers and drivers.
   - Implement a timeout for unmatched requests.

---

### 7. Game Simulation: Snake and Ladder
- **Objective**: Simulate a multiplayer Snake and Ladder game where each player moves on a shared board.
- **Key Techniques**:
   - Use `ReentrantLock` to synchronize board updates.
   - Use `AtomicInteger` to represent player positions.

---

### 8. Order Processing System
- **Objective**: Simulate an e-commerce system where multiple threads process orders and update inventory.
- **Requirements**:
  - **Order Processing**:
    - Each thread should represent an order being processed.
    - Threads must deduct items from the inventory based on the order.
    - Orders may contain multiple items, each with varying quantities.
  - **Inventory Management**:
    - Maintain a central inventory data structure where:
      - Keys represent product IDs.
      - Values represent available quantities.
    - Ensure that inventory updates are thread-safe to avoid inconsistencies (e.g., overselling items).
  - **Concurrency Handling**:
    - Prevent simultaneous updates to the same product by multiple threads.
    - Handle cases where an order attempts to purchase more items than are available (e.g., reject the order or partially fulfill it).
  - **Logging and Metrics**:
    - Log successful and failed order processing attempts.
    - Track the remaining inventory after each order is processed.

---

### 9. Airport Simulator
- **Objective**: Simulate an airport where multiple planes land, take off, and park at gates.
- **Key Techniques**:
   - Use `Semaphore` to manage limited gates.
   - Implement a priority system for emergency landings.

---

### 10. Hotel Booking System
- **Objective**: Simulate a hotel booking system where multiple threads reserve rooms concurrently.
- **Key Techniques**:
   - Use `ReentrantLock` to synchronize room reservations.
   - Ensure thread safety while updating availability.

---

### 11. Inventory Management System
- **Objective**: Build a system to manage inventory with real-time updates.
- **Key Techniques**:
   - Use `ConcurrentHashMap` for inventory data.
   - Synchronize updates with `ReentrantLock`.

---

### 14. Train Reservation System
- **Objective**: Simulate a train reservation system with multiple passengers booking seats.
- **Key Techniques**:
   - Use `ReentrantLock` to synchronize seat allocations.
   - Handle overbooking scenarios gracefully.

---

### 15. Load Balancer System
- **Objective**: Create a multithreaded application to simulate a load balancer that distributes incoming requests to backend servers.
- **Requirements**:
  - **Request Handling**:
    - Simulate incoming requests using threads, with each request having a unique ID and payload.
    - Requests should be processed by backend servers via the load balancer.
  - **Backend Servers**:
    - Simulate a fixed number of backend servers (e.g., 5 servers).
    - Each server processes requests with a random delay (e.g., 50–500 ms).
    - Servers may occasionally fail to process a request, simulating downtime.
  - **Load Balancer Logic**:
    - Implement the following request distribution strategies:
      - **Round-Robin**: Assign requests sequentially to servers.
      - **Least Connections**: Assign requests to the server with the fewest active requests.
      - **Random**: Assign requests to servers randomly.
  - **Concurrency Handling**:
    - Use thread-safe mechanisms to distribute requests and manage server states (e.g., active connections).
    - Ensure no race conditions during request assignment.
  - **Error Handling**:
    - Handle scenarios where servers fail to process requests by retrying or logging failures.
  - **Metrics**:
    - Track and display:
      - Total number of requests processed.
      - Number of requests processed by each server.
      - Number of failed requests.
      - Average processing time per request.
- **Bonus**:
  - Add a **health check mechanism** to exclude failing servers temporarily.
  - Allow dynamic switching between load-balancing strategies at runtime.

### 16. Multithreaded File Downloader
- **Objective**: Simulate a file download system that downloads different parts of a file in parallel and combines them.
- **Key Techniques**:
  - Use `ExecutorService` to manage worker threads.
  - Divide file into byte ranges and simulate download with delays.
  - Merge partial results safely.

### 17. Thread-Safe Cache with Read/Write Lock
- **Objective**: Implement a simple cache using `Map` that supports concurrent reads and synchronized writes.
- **Key Techniques**:
  - Use `ReentrantReadWriteLock` for optimized access.
  - Support put/get operations from multiple threads.

### 18. Parallel Web Crawler
- **Objective**: Create a web crawler that explores URLs in parallel while avoiding re-visiting the same links.
- **Key Techniques**:
  - Use `ConcurrentHashMap` to track visited URLs.
  - Use thread pool for crawling tasks.

### 19. Thread-Safe Singleton
- **Objective**: Implement the Singleton pattern with safe lazy initialization.
- **Key Techniques**:
  - Use `synchronized` block or double-checked locking.
  - Ensure visibility with `volatile`.

### 20. Multithreaded Chat Server (Simulation)
- **Objective**: Simulate a chat room where multiple clients can send and receive messages concurrently.
- **Key Techniques**:
  - Use synchronized queues or `BlockingQueue`.
  - Each client handled by a separate thread.

### 21. Parallel Image Filter Application
- **Objective**: Apply an image processing filter (like Gaussian blur) on an image using multiple threads.
- **Key Techniques**:
  - Split image into regions.
  - Use threads to process regions in parallel and combine results.

### 22. Work-Stealing Thread Pool
- **Objective**: Simulate a thread pool with basic work-stealing behavior to balance load.
- **Key Techniques**:
  - Use `Deque` for each worker thread.
  - Implement logic for idle threads to steal tasks.

### 23. Task Scheduler with Priority Queue
- **Objective**: Implement a simple task scheduler that runs tasks based on their priority.
- **Key Techniques**:
  - Use `PriorityBlockingQueue` or custom comparator.
  - Worker threads poll and execute based on priority.

### 24. Concurrent Ticket Booking System
- **Objective**: Simulate a ticket booking system with limited tickets and multiple buyers.
- **Key Techniques**:
  - Use `ReentrantLock` to avoid overbooking.
  - Handle failure cases when no tickets are available.

### 25. Parallel Prime Number Finder
- **Objective**: Find all prime numbers in a given range using multiple threads.
- **Key Techniques**:
  - Divide range into subranges.
  - Use thread pool to process each subrange.
  - Collect and merge results.

### 26. Lock-Free Queue with Atomic Variables
- **Objective**: Implement a simple non-blocking queue using `AtomicReference`.
- **Key Techniques**:
  - Use CAS (compare-and-set) to update head/tail.
  - Avoid locks entirely.

### 27. Thread-Safe Observer Pattern
- **Objective**: Implement the observer pattern where observers can be added/removed concurrently.
- **Key Techniques**:
  - Use `CopyOnWriteArrayList` or synchronized blocks.
  - Notify observers safely from multiple threads.

### 28. Multithreaded Pipeline (Fetch -> Decode -> Process)
- **Objective**: Create a three-stage pipeline with each stage handled by its own thread.
- **Key Techniques**:
  - Use `BlockingQueue` between pipeline stages.
  - Ensure proper shutdown and flow control.

### 29. Performance Comparison: Thread vs CompletableFuture
- **Objective**: Compare performance and usability between raw `Thread` and `CompletableFuture`.
- **Key Techniques**:
  - Run same computation using both techniques.
  - Measure execution time and resource usage.

### 30. Deadlock Debugging Simulation
- **Objective**: Write a program that intentionally causes a deadlock and analyze it.
- **Key Techniques**:
  - Use `ThreadMXBean` or logging to detect deadlock.
  - Highlight importance of lock acquisition order.
