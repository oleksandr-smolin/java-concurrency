| Approach                    | Speed | Complexity | Safe? | Scalable? | Notes                       |
| --------------------------- | ----- | ---------- | ----- | --------- | --------------------------- |
| `synchronized`              | 🟢    | 🟢         | ✅     | 🟡        | Simple and safe             |
| `ReentrantLock`             | 🟢    | 🟡         | ✅     | ✅         | Best general-purpose        |
| `StampedLock`               | 🟢🟢  | 🟡🟡       | ✅     | ✅         | Ideal for read-heavy        |
| Actor model                 | 🟡    | 🔴         | ✅     | ✅         | Architecturally clean       |
| DB + optimistic locking     | 🔴    | 🟡         | ✅     | 🟡        | Durable, retry logic needed |
| BlockingQueue worker thread | 🟡    | 🟢         | ✅     | 🔴        | Best for low-throughput     |
| AtomicLong balance          | 🟢🟢  | 🟡         | ⚠️    | ✅         | Only safe for trivial logic |
| Lock registry (striping)    | 🟢    | 🟡         | ✅     | ✅         | Good for generic systems    |
