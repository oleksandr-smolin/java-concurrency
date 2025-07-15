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

### 📅 16. Booking System (Hotel / Meeting Room / Train Seats)

* **Objective**: Develop a system to manage bookings, ensuring no resource conflicts or double-bookings occur.
* **Requirements**:

  * Implement objects to represent resources (`Room`, `TrainSeat`) and time slots.
  * Allow concurrent booking requests.
  * Utilize locks or thread-safe maps to avoid booking conflicts.
  * Clearly handle edge cases, such as simultaneous booking requests.
* **Key Techniques**:

  * Concurrency management
  * Object-oriented design
  * Thread-safe data structures

### 🧾 17. Rate Limiter

* **Objective**: Implement algorithms to control request rates, preventing service overload.
* **Requirements**:

  * Develop Token Bucket and Leaky Bucket algorithms.
  * Implement Sliding Window counters.
  * Use in-memory caching.
  * Ensure thread-safe data handling.
* **Key Techniques**:

  * Token Bucket / Leaky Bucket algorithms
  * Sliding window algorithm
  * Thread-safe caching

### 🧺 18. Thread-safe Queue or Bounded Buffer

* **Objective**: Create a robust producer-consumer system with concurrency controls.
* **Requirements**:

  * Utilize `BlockingQueue` to manage resource sharing.
  * Implement `wait()` and `notify()` mechanisms.
  * Apply semaphores for concurrency control.
* **Key Techniques**:

  * Producer-consumer pattern
  * Blocking mechanisms
  * Semaphores

### 📮 19. Messaging System (Pub/Sub)

* **Objective**: Build an asynchronous messaging system following the observer pattern.
* **Requirements**:

  * Allow message publishing and subscription.
  * Ensure decoupling of message publishers and subscribers.
  * Coordinate threads effectively to deliver messages asynchronously.
* **Key Techniques**:

  * Thread coordination
  * Observer pattern
  * Decoupling components

### 🧾 20. Order Matching Engine (Stock Exchange)

* **Objective**: Simulate a trading system to match buy and sell orders efficiently.
* **Requirements**:

  * Implement priority queues to manage order execution.
  * Develop algorithms to match buy and sell orders.
  * Ensure thread-safe execution under concurrent conditions.
* **Key Techniques**:

  * Priority queues
  * Matching algorithms
  * Thread safety

### 🧾 21. In-Memory Key-Value Store

* **Objective**: Develop a performant and reliable in-memory key-value store.
* **Requirements**:

  * Implement TTL (time-to-live) expiration for keys.
  * Use `ConcurrentHashMap` for thread-safe operations.
  * Schedule tasks to purge expired keys regularly.
* **Key Techniques**:

  * Concurrent data structures
  * Scheduled tasks
  * Data expiration logic

### 🔍 22. Search Autocomplete

* **Objective**: Create an efficient search autocomplete feature supporting asynchronous updates.
* **Requirements**:

  * Implement trie or prefix tree structures for rapid search suggestions.
  * Develop ranking logic based on query frequency or relevancy.
  * Ensure thread-safe reads and updates.
* **Key Techniques**:

  * Trie data structures
  * Ranking algorithms
  * Concurrent data handling

### 🧮 23. Analytics Counter (Event Counter by Time Window)

* **Objective**: Implement real-time event counting used in analytics and dashboards.
* **Requirements**:

  * Use sliding window algorithms to count events.
  * Maintain rolling counters using atomic variables or ring buffers.
  * Ensure accurate counts under high concurrency.
* **Key Techniques**:

  * Sliding window counting
  * Atomic operations
  * Concurrent data structures

### 📊 24. Leaderboard System

* **Objective**: Design a leaderboard capable of efficiently sorting, ranking, and updating scores concurrently.
* **Requirements**:

  * Use priority queues to maintain ranking.
  * Manage concurrent updates and queries efficiently.
  * Handle real-time data changes gracefully.
* **Key Techniques**:

  * Priority queue management
  * Concurrency handling
  * Efficient sorting and updates

### 🔐 25. User Authentication System (simplified)

* **Objective**: Build a basic user authentication service with secure session management.
* **Requirements**:

  * Implement stateless token-based authentication (JWT).
  * Support user registration, login, and session management.
  * Validate input securely and handle errors gracefully.
* **Key Techniques**:

  * Stateless JWT tokens
  * RESTful API design
  * Security best practices
