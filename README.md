# ADS.ASGN4
Task 1 DFS.

Trace:
1. Start at A. Mark visited. Neighbors: C, B, D.
2. Go to C. Mark visited. Neighbors: A, B, D.
3. A is visited. Go to B. Mark visited. Neighbors: A, C, E, G.
4. A and C visited. Go to E. Mark visited. Neighbors: G, F, B.
5. Go to G. Mark visited. Neighbors: F, B.
6. Go to F. Mark visited. Neighbors: G, E.
7. All F's neighbors visited. Backtrack to G, then E, then B. B's neighbor G is visited. Backtrack to C.
8. C has unvisited neighbor D. Go to D. Mark visited. Neighbors: C, A.
9. All D's neighbors visited. Backtrack to C, then A. Done.

Task 2 BFS.

Trace:
1. Queue: [A]. Visited: {A}.
2. Dequeue A. Add unvisited neighbors. Queue: [C, B, D]. Visited: {A, C, B, D}.
3. Dequeue C. Neighbors (A, B, D) are visited or in queue.
4. Dequeue B. Add unvisited neighbors. Queue: [D, E, G]. Visited: {A, C, B, D, E, G}.
5. Dequeue D. Neighbors (C, A) are visited.
6. Dequeue E. Add unvisited neighbor F. Queue: [G, F]. Visited: {A, C, B, D, E, G, F}.
7. Dequeue G. Neighbors (F, B) are visited.
8. Dequeue F. Neighbors (G, E) are visited. Queue empty.

Task 4.

Trace:
1. Init distances: Edinburgh(0), others (∞).
2. From Edinburgh(0), check neighbors:
   Glasgow: 0 + 70 = 70.
   Stirling: 0 + 50 = 50.
   Perth: 0 + 100 = 100.
3. Pick min distance node: Stirling(50). Check neighbors:
   Glasgow: 50 + 50 = 100 (100 > 70, no update).
   Perth: 50 + 40 = 90 (90 < 100, update to 90).
4. Pick next min: Glasgow(70). Neighbors already have smaller distances.
5. Pick next min: Perth(90). Check neighbors:
   Dundee: 90 + 60 = 150 (update to 150).
6. Pick Dundee(150). Destination reached.

Shortest path: Edinburgh -> Stirling -> Perth -> Dundee
Total distance: 150
