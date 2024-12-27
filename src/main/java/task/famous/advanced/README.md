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

