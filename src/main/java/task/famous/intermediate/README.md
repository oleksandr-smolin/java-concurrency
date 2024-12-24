## Intermediate Tasks (Estimated Time: 20–40 hours from 1 to 7)

### 1. Producer-Consumer Problem

- **Description**: Implement a bounded buffer where producers generate data and consumers process it.
- **Estimated Time**: 3–4 hours

---

### 2. Dining Philosophers Problem

- **Description**: Solve the classic synchronization problem using `Semaphore`.
- **Estimated Time**: 3–4 hours

#### Problem Overview

- You have five philosophers sitting at a circular table.
- Between each pair of philosophers is one chopstick (or fork), making a total of five chopsticks.
    - Each philosopher alternates between **thinking** and **eating**:
        - **Thinking**: A philosopher does not need any chopsticks and spends a random amount of time thinking.
        - **Eating**: A philosopher needs two chopsticks (the one on their left and the one on their right) and spends a
          random amount of time eating.
    - **Duration**:
        - Each philosopher spends a random time (e.g., 1 to 3 seconds) on both thinking and eating.
    - **Frequency**:
        - Philosophers continuously alternate between thinking and eating in a loop.
    - **Challenges**:
        - No two philosophers eat simultaneously using the same chopstick.
        - No philosopher starves (i.e., waits indefinitely to eat).

---

### 3. Matrix Multiplication

- **Description**: Use multiple threads to compute matrix multiplication in parallel.
- **Estimated Time**: 3–4 hours

---

### 4. Parallel Search

- **Description**: Search for an element in a large array using multiple threads.
- **Estimated Time**: 2–3 hours

---

### 5. Word Count

- **Description**: Count word occurrences in a large text file using threads.
- **Estimated Time**: 3–4 hours

---

### 6. ForkJoinPool: Recursive Sum

- **Scenario**: Compute the sum of a large array (e.g., 1 billion integers) by dividing it into smaller parts and
  solving recursively.
- **Why Use**: Efficiently uses CPU cores for large data processing with `ForkJoinPool`.
- **Requirements**:
    1. Generate an array of 1 billion integers.
    2. Implement a `RecursiveSumTask` class that extends `RecursiveTask<Long>`.
    3. If the chunk size is below a threshold (e.g., 100,000), compute the sum directly.
    4. Otherwise, split the task into two parts, execute with `fork()` and combine with `join()`.
    5. Use `ForkJoinPool` to execute the task and return the result.

---

### 11. CountDownLatch: Race Simulation

- **Scenario**: Simulate a race where all racers start simultaneously and report their finish times.
- **Why Use**: Ensures all racers begin at the same time and allows the main thread to wait for all to finish.

#### **Requirements**:

1. **Start Signal**:

- Use a `CountDownLatch` initialized to `1` to ensure all racer threads start racing simultaneously when the latch count
  reaches zero.

2. **Finish Signal**:

- Use another `CountDownLatch` initialized to the number of racers (e.g., `5`) to wait for all racers to finish the race
  before proceeding.

3. **Racer Threads**:

- Each racer thread simulates racing by introducing a random delay (to represent varying speeds).
- Threads record and report their finish times when they cross the finish line.

4. **Main Thread**:

- The main thread:
    - Releases the `startSignal` to begin the race.
    - Waits on the `finishSignal` for all racers to complete.
    - Consolidates results and announces the winner.

#### **Detailed Implementation**:

1. Prepare `CountDownLatch` for both starting and finishing signals.
2. Use threads to represent racers, each waiting on the start signal and racing with random delays.
3. Record individual racer times for result tracking.
4. Announce the winner based on the fastest time after all racers finish.

---

### 8. CyclicBarrier: Team Task

- **Scenario**: Coordinate multiple threads (e.g., teams) working on a project with multiple synchronized stages.
- **Why Use**: Ensures all threads reach a common checkpoint before proceeding.
- **Requirements**:
    1. Create 5 threads representing team members.
    2. Define 3 project stages (e.g., design, development, testing).
    3. Use `CyclicBarrier` to synchronize threads at the end of each stage.
    4. Add a barrier action to log progress after all threads reach the checkpoint.

---

### 9. Phaser: Multi-Phase Computation

- **Scenario**: Coordinate tasks with multiple sequential phases (e.g., data collection, processing, and visualization)
  where participation varies by phase.
- **Why Use**: Dynamically adds/removes threads while ensuring synchronization across phases.
- **Requirements**:
    1. Create 3 phases: data collection, analysis, and visualization.
    2. Use 3 threads for the first phase.
    3. Add 2 threads for the second phase dynamically.
    4. Remove 1 thread before the third phase.
    5. Use `arriveAndAwaitAdvance` to synchronize threads at each phase.

---

### 10. ForkJoinPool: Parallel Sorting

- **Scenario**: Sort a large dataset (e.g., 10 million numbers) using a parallel merge sort.
- **Why Use**: Accelerates sorting by splitting data into smaller chunks and processing in parallel.
- **Requirements**:
    1. Create an array of 10 million integers.
    2. Implement a `MergeSortTask` class that extends `RecursiveTask<int[]>`.
    3. Divide the array into smaller chunks recursively and sort each chunk.
    4. Merge sorted chunks using a `merge()` function.
    5. Use `ForkJoinPool` to execute the sorting.

---

### 11. CyclicBarrier: File Merge

- **Scenario**: Process chunks of a large file in parallel and synchronize results in stages.
- **Why Use**: Ensures all threads complete their work before merging results iteratively.
- **Requirements**:
    1. Split a file (e.g., 1 GB) into 10 chunks.
    2. Each thread processes its chunk (e.g., compresses it).
    3. Use `CyclicBarrier` to synchronize threads after each stage.
    4. Merge processed chunks after the barrier.

---

### 12. Phaser: Dynamic Party Registration

- **Scenario**: A multiplayer game where players dynamically join and leave during different stages (e.g., setup,
  gameplay, summary).
- **Why Use**: Manages threads that dynamically join/leave phases, ensuring synchronization.
- **Requirements**:
    1. Create 3 phases: setup, gameplay, and summary.
    2. Start with 4 players (threads).
    3. Add 2 new players during the gameplay phase.
    4. Allow 1 player to leave before the summary phase.
    5. Use `arriveAndAwaitAdvance` for synchronization.

