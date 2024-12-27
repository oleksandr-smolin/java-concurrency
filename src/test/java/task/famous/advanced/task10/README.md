
What we need to test:

#### Worker
1. One Thread process booking object and save in DataStore
2. ~~Many threads doesn't have to have race condition~~
   We don't need to check it because we specified blocking collection explicitly  
3. Check that shared resource has expected state after processing

#### HttpHandler
1. One Thread put Booking object to Queue
2. ~~Many Threads put objects in Queue without race condition~~
   We don't need to check it because we specified blocking collection explicitly
3. Thread doesn't wait till object will be processed, it has to return some response in scope of 2 seconds
4. All exception cases
5. Shared resources have expected state after processing
