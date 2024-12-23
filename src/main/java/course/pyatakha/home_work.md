course: https://www.udemy.com/course/java-development-for-beginners-learnit/

homework: https://docs.google.com/document/d/1iUGVwnOAitglW_MNpQSZjvZ2FyzE_T1m6k7q8eoBsug/edit?tab=t.0

# Multithreading Tasks in Java

## **Task 1: Create and Start Threads**
Create and start a thread that prints its name for around 5 seconds (one time per second). Run the thread in the following ways:

1. **By creating a separate class that implements the `Runnable` interface.**
2. **By extending the `Thread` class.**
3. **By passing a lambda function to the constructor of the `Thread` class.**
4. **By passing a method reference to the constructor of the `Thread` class.**

---

## **Task 2: Nested Class `Spam`**
### **Description:**
Create a nested class `Spam` that receives:
- An array of time intervals in milliseconds.
- A connected array of messages.

### **Requirements:**
1. For each time interval, print the corresponding message, then sleep for the specified interval.
2. The `Spam` object should stop when the `Enter` key has been pressed.
    - Simulate pressing the `Enter` key by passing the `'\n'` character to the program's input stream.
3. During the demo, simulate pressing the `Enter` key after 5 seconds.

---

## **Task 3: Counters Comparison**
### **Description:**
1. Create a class that implements the `Runnable` interface and declares two separate counters (`int` type).
2. Multiple threads will:
    - Compare the state of the two counters.
    - Print the comparison result to the console.
    - Increment the first counter.
    - Sleep for 10 milliseconds.
    - Increment the second counter.

### **Execution:**
- Run this program and interrupt it after 3 seconds.
- Compare the results of execution and console output for:
    - **Synchronized critical sections.**
    - **Non-synchronized critical sections.**

---

## **Task 4: Finding the Maximum in a Multi-dimensional Array**
### **Steps:**
1. Create a method to generate a multi-dimensional array (`M x N`).
    - For the demo, generate a 4x100 array.
2. Find the maximum integer using multiple threads with a **1 millisecond delay** per comparison.
    - This delay simulates the comparison of large objects.
3. Find the maximum integer using a single thread with the same delay.
4. Measure execution times for:
    - Multi-threaded solution.
    - Single-threaded solution.
5. Verify that the multi-threaded solution is faster.

### **Requirements:**
- Solve the task in two different ways:
    1. Using **Future** objects.
    2. Using a **CountDownLatch** synchronizer.

---

## **Task 5: Readers and Writers**
### **Types:**
- **Reader**: Reads messages from a buffer.
- **Writer**: Writes messages to a buffer.

### **Buffer:**
- Can be a `String`, `StringBuilder`, or `StringBuffer`.
- Size is **5 random characters**.

### **Requirements:**
1. **Writer**:
    - Writes messages to the buffer.
    - Waits when the buffer is full until readers have read all messages.
2. **Reader**:
    - Reads messages from the buffer.
    - Waits when the buffer is empty until notified by the writer.
3. **Console Output Example**:


