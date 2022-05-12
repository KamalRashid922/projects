import numpy as np
import sys
from Puzzle import puzzle
from A_StarMD import A_StarMD
from A_StarED import A_StarED
# initialState = [[6, 4, 7], [8, 5, 0], [3, 2, 1]]
initialState = np.array([[3, 1, 2], [4, 7, 5], [6, 8, 0]])
# initialState = [[8, 6, 7], [2, 5, 4], [3, 0, 1]]
# initialState = [[2, 5, 4], [3, 0, 8], [1, 6, 7]]
goalState = [[0, 1, 2], [3, 4, 5], [6, 7, 8]]
initial1D = np.array(initialState).flatten()
initialInversionCount = 0
goal1D = np.array(goalState).flatten()
goalInversionCount = 0
for i in range(9):
    for j in range(i, 9):
        if initial1D[j] != 0 and initial1D[i] != 0 and (initial1D[i] > initial1D[j]):
            initialInversionCount += 1
for i in range(9):
    for j in range(i, 9):
        if goal1D[j] != 0 and goal1D[i] != 0 and (goal1D[i] > goal1D[j]):
            goalInversionCount += 1
if initialInversionCount % 2 != goalInversionCount % 2:
    print("unsolvable!!")
    sys.exit()
#"""
print("DFS")
dfs = puzzle(initialState, goalState)
out = dfs.dfs()
i = 0
if out[0] == 0:
    print("failed!!")
    print("")
    i = 1
costOfPath = out[i]
nodesExpanded = out[i + 1]
searchDepth = out[i + 2]
runTime = out[i + 3]
print("path to goal: ")
dfs.print()
print("cost of path: ", costOfPath)
print("nodes expanded: ", len(nodesExpanded))
print("search depth: ", searchDepth)
print("running time: ", runTime / (10**9), "s")
print("")
print("BFS")
bfs = puzzle(initialState, goalState)
out = bfs.bfs()
i = 0
if out[0] == 0:
    print("failed!!")
    print("")
    i = 1
costOfPath = out[i]
nodesExpanded = out[i + 1]
searchDepth = out[i + 2]
runTime = out[i + 3]
print("path to goal: ")
bfs.print()
print("cost of path: ", costOfPath)
print("nodes expanded: ", len(nodesExpanded))
print("search depth: ", searchDepth)
print("running time: ", runTime / (10**9), "s")
print("")
#"""
#"""
A = A_StarED()
print("A* using Euclidean Distance")
print("")
goal = A.search(initialState, goalState)
i = 0
if goal[0] == 0:
    print("failed!!")
    print("")
    i = 1
pathToGoal = goal[i]
costOfPath = goal[i + 1]
nodesExpanded = goal[i + 2]
searchDepth = goal[i + 3]
runTime = goal[i + 4]
print("path to goal:")
while pathToGoal:
    ptg = pathToGoal.pop()
    print(ptg[0])
    print(ptg[1])
    print(ptg[2])
    print("")
print("")
print("cost of path = ", costOfPath)
print("")
print("there are", len(nodesExpanded), "nodes expanded:")
#"""
"""
for ne in nodesExpanded:
    print(ne[0])
    print(ne[1])
    print(ne[2])
    print("")
"""
#"""
print("")
print("search depth = ", searchDepth)
print("")
print("running time = ", runTime / (10**9), "s")
print("")
#"""
#"""
A = A_StarMD()
print("A* using Manhattan Distance")
print("")
goal = A.search(initialState, goalState)
i = 0
if goal[0] == 0:
    print("failed!!")
    print("")
    i = 1
pathToGoal = goal[i]
costOfPath = goal[i + 1]
nodesExpanded = goal[i + 2]
searchDepth = goal[i + 3]
runTime = goal[i + 4]
print("path to goal:")
while pathToGoal:
    ptg = pathToGoal.pop()
    print(ptg[0])
    print(ptg[1])
    print(ptg[2])
    print("")
print("")
print("cost of path = ", costOfPath)
print("")
print("there are", len(nodesExpanded), "nodes expanded:")
#"""
"""
for ne in nodesExpanded:
    print(ne[0])
    print(ne[1])
    print(ne[2])
    print("")
"""
#"""
print("")
print("search depth = ", searchDepth)
print("")
print("running time = ", runTime / (10**9), "s")
print("")
#"""