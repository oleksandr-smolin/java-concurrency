# **Concurrency Programming Practice Tasks**

This README outlines a structured list of concurrency programming tasks to help you master multithreading, parallelism, and synchronization. Tasks are grouped into difficulty levels, and each section has its own numbering.

---


# Java Concurrency Tools

| **Tool/Utility**              | **Description**                                                                                                  |
|-------------------------------|------------------------------------------------------------------------------------------------------------------|
| **`Thread`**                  | The basic unit of concurrent execution in Java.                                                                  |
| **`Runnable`**                | A functional interface for defining tasks to run on a `Thread`.                                                  |
| **`Callable`**                | Similar to `Runnable` but allows returning a result and throwing exceptions.                                     |
| **`Future`**                  | Represents the result of an asynchronous computation.                                                            |
| **`CompletableFuture`**       | Extends `Future`, enabling chaining and combining of asynchronous tasks.                                         |
| **`ExecutorService`**         | Manages and controls thread pools for executing tasks.                                                           |
| **`ForkJoinPool`**            | Optimized pool for dividing tasks into smaller subtasks (e.g., parallel streams).                                |
| **`Parallel Streams`**        | Provides easy parallelism for collections using `ForkJoinPool`.                                                  |
| **`Locks` (`ReentrantLock`)** | Advanced synchronization primitives for fine-grained control of critical sections.                               |
| **`ReadWriteLock`**           | Provides separate locks for read and write access to shared resources.                                           |
| **`Semaphore`**               | Controls access to a limited number of permits for shared resources.                                             |
| **`CountDownLatch`**          | Waits for a set number of threads to complete before proceeding.                                                 |
| **`CyclicBarrier`**           | Synchronizes threads at a common barrier point, reusable after threads pass the barrier.                         |
| **`Phaser`**                  | A more flexible version of `CyclicBarrier` for coordinating multiple phases of execution.                        |
| **`Atomic Classes`**          | (`AtomicInteger`, `AtomicBoolean`, `AtomicReference`, etc.) for lock-free thread-safe operations on primitives.   |
| **`Volatile`**                | A lightweight synchronization mechanism for variables to ensure visibility across threads.                       |
| **`Synchronized`**            | A keyword to make methods or blocks thread-safe by acquiring intrinsic locks.                                    |
| **`Thread-Safe Collections`** | Built-in collections like `ConcurrentHashMap`, `CopyOnWriteArrayList`, and `BlockingQueue` for concurrent access. |

# Java Collections for Multithreading

| **Collection**                 | **Description**                                                                                               |
|--------------------------------|---------------------------------------------------------------------------------------------------------------|
| **`ConcurrentHashMap`**        | A thread-safe implementation of `HashMap` that supports high concurrency with segment-based locking.          |
| **`CopyOnWriteArrayList`**     | A thread-safe implementation of `ArrayList` that creates a new copy of the list on every modification.        |
| **`CopyOnWriteArraySet`**      | A thread-safe implementation of `Set` backed by a `CopyOnWriteArrayList`.                                      |
| **`BlockingQueue`**            | An interface for thread-safe queues that block on insertion or retrieval if the queue is full or empty.        |
| **`LinkedBlockingQueue`**      | A thread-safe queue implemented as a linked list, optionally bounded.                                          |
| **`ArrayBlockingQueue`**       | A thread-safe, bounded queue implemented as a fixed-size array.                                                |
| **`PriorityBlockingQueue`**    | A thread-safe priority queue based on natural ordering or a comparator.                                        |
| **`DelayQueue`**               | A thread-safe queue that holds elements until they are eligible for processing based on a delay.               |
| **`SynchronousQueue`**         | A thread-safe queue where each insertion waits for a corresponding removal (and vice versa).                   |
| **`Deque` (`ConcurrentLinkedDeque`)** | A thread-safe double-ended queue for concurrent access.                                                          |
| **`ConcurrentLinkedQueue`**    | A thread-safe, unbounded queue implemented as a non-blocking linked list.                                       |
| **`TreeMap` (with `synchronizedMap`)**| A sorted map wrapped with `Collections.synchronizedMap` for thread safety.                                   |
| **`ThreadLocalRandom`**        | A thread-safe random number generator optimized for multithreaded environments.                                |
| **`Atomic Classes`**           | Classes like `AtomicInteger`, `AtomicLong`, and `AtomicReference` for lock-free thread-safe operations.         |
| **`Collections.synchronizedMap`** | A wrapper to make a regular map thread-safe by synchronizing access.                                          |
| **`Collections.synchronizedList`** | A wrapper to make a regular list thread-safe by synchronizing access.                                         |


# ⚠️ Common Concurrency Issues in Java (and How to Avoid Them)

Understanding what can go wrong is the first step to writing safe, efficient multithreaded code.

---

## 1. 🧨 Race Conditions

- **What**: Multiple threads access shared data simultaneously, and at least one modifies it.
- **Symptoms**: Intermittent bugs, incorrect results.
- **Example**:
  ```java
  counter++; // Not atomic!
  ```
- **Prevention**:
    - Use `synchronized` or `ReentrantLock`
    - Use atomic classes like `AtomicInteger`
    - Prefer immutable data

---

## 2. 🧮 Atomicity Violations

- **What**: A set of operations that must execute together are interleaved.
- **Example**:
  ```java
  if (!map.containsKey(key)) {
      map.put(key, value); // Unsafe!
  }
  ```
- **Prevention**:
    - Use atomic methods like `putIfAbsent`
    - Synchronize compound actions

---

## 3. 🧵 Deadlocks

- **What**: Two or more threads wait forever for each other's locks.
- **Example**:
    - Thread A locks `L1`, waits for `L2`
    - Thread B locks `L2`, waits for `L1`
- **Prevention**:
    - Acquire locks in a consistent global order
    - Use `tryLock()` with timeout
    - Prefer higher-level concurrency tools (`ExecutorService`)

---

## 4. ⏳ Livelocks

- **What**: Threads keep responding to each other without making progress.
- **Analogy**: Two people stepping side-to-side in a hallway forever.
- **Prevention**:
    - Use backoff strategies
    - Add randomness to retries

---

## 5. 🕳️ Starvation

- **What**: A thread never gets CPU time or access to resources.
- **Causes**:
    - Lower-priority threads being ignored
    - Poor locking or scheduling strategy
- **Prevention**:
    - Use fair locks (`ReentrantLock(true)`)
    - Use thread pools with fair task distribution

---

## 6. 🐛 Thread Interference

- **What**: Threads corrupt shared data by unsynchronized access.
- **Example**:
  ```java
  sharedList.add(item); // Not thread-safe with ArrayList
  ```
- **Prevention**:
    - Use thread-safe collections (`CopyOnWriteArrayList`)
    - Use proper synchronization

---

## 7. 🔁 Incorrect Synchronization

- **What**: Lack of or improper synchronization leads to visibility or ordering issues.
- **Example**:
  ```java
  boolean flag = true;
  // Thread 1 sets flag = false
  // Thread 2 may not see the change without `volatile`
  ```
- **Prevention**:
    - Use `volatile` for visibility
    - Use `synchronized` blocks or concurrency primitives

---

## 8. ⚠️ Memory Visibility Issues

- **What**: One thread updates a variable, but others don’t see the change.
- **Cause**: CPU cache or compiler reordering.
- **Prevention**:
    - Use `volatile` or proper locking

---

## 9. 🧼 Improper Use of Thread Pools

- **Issues**:
    - Too many threads → memory waste, high context switching
    - Too few threads → low throughput
    - Not shutting down → resource leaks
- **Prevention**:
    - Use `Executors.newFixedThreadPool(...)`
    - Always shut down with `executor.shutdown()`

---

## 10. 🔄 Instruction Reordering

- **What**: JVM or CPU may reorder instructions for optimization.
- **Consequence**: Code executes out of expected order across threads.
- **Prevention**:
    - Use synchronization or `volatile` to create memory barriers

---

## 11. ❌ Blocking on Non-blocking Threads

- **What**: Blocking calls inside non-blocking or async code.
- **Examples**:
    - `Thread.sleep()`, I/O in `CompletableFuture` chains
- **Prevention**:
    - Offload to dedicated thread pools
    - Use reactive patterns properly

---

## 12. 🧟 Forgotten Thread Termination

- **What**: Threads keep running in the background after main thread exits.
- **Causes**:
    - Infinite loops
    - Non-daemon threads
- **Prevention**:
    - Use daemon threads where appropriate
    - Design with graceful shutdown

---

## 13. 🧱 Shared Mutable State

- **Rule**: Shared state across threads leads to complexity and bugs.
- **Prevention**:
    - Use immutable data structures
    - Use thread-local storage
    - Synchronize shared resources

---

## 14. 📉 Lock Contention and Performance Bottlenecks

- **What**: Too many threads compete for the same lock.
- **Symptoms**: Sluggish performance, increased CPU usage.
- **Prevention**:
    - Minimize lock scope
    - Use lock-free data structures
    - Use finer-grained or striped locks


