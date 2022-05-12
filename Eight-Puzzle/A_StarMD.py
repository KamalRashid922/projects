import heapq
import math
from Node import Node
import time
class A_StarMD:
    def Manhattan_Distance(self, currentState, theGoalState):
        theH = 0
        for ci in range(3):
            for cj in range(3):
                if (currentState[ci][cj] == 0):
                    continue
                for gi in range(3):
                    for gj in range(3):
                        if (currentState[ci][cj] == theGoalState[gi][gj]):
                            theH = theH + abs(ci - gi) + abs(cj - gj)
        return theH
    def equals(self, mat):
        newMat = []
        for i in range(3):
            row = []
            for j in range(3):
                row.append(mat[i][j])
            newMat.append(row)
        return newMat
    def getChildren(self, root, theGoalState):
        theChildren = []
        for i in range(3):
            for j in range(3):
                if (root.state[i][j] == 0):
                    if (i > 0):
                        child = Node()
                        child.parent = root
                        child.g = root.g + 1
                        child.state = self.equals(root.state)
                        child.state[i][j] = child.state[i - 1][j]
                        child.state[i - 1][j] = 0
                        child.h = self.Manhattan_Distance(child.state, theGoalState)
                        child.f = child.g + child.h
                        theChildren.append(child)
                    if (j > 0):
                        child = Node()
                        child.parent = root
                        child.g = root.g + 1
                        child.state = self.equals(root.state)
                        child.state[i][j] = child.state[i][j - 1]
                        child.state[i][j - 1] = 0
                        child.h = self.Manhattan_Distance(child.state, theGoalState)
                        child.f = child.g + child.h
                        theChildren.append(child)
                    if (i < 2):
                        child = Node()
                        child.parent = root
                        child.g = root.g + 1
                        child.state = self.equals(root.state)
                        child.state[i][j] = child.state[i + 1][j]
                        child.state[i + 1][j] = 0
                        child.h = self.Manhattan_Distance(child.state, theGoalState)
                        child.f = child.g + child.h
                        theChildren.append(child)
                    if (j < 2):
                        child = Node()
                        child.parent = root
                        child.g = root.g + 1
                        child.state = self.equals(root.state)
                        child.state[i][j] = child.state[i][j + 1]
                        child.state[i][j + 1] = 0
                        child.h = self.Manhattan_Distance(child.state, theGoalState)
                        child.f = child.g + child.h
                        theChildren.append(child)
                    break
            if (root.state[i][j] == 0):
                break
        return theChildren
    def getPath(self, node):
        path = [node.state]
        while node.parent != 0:
            node = node.parent
            path.append(node.state)
        return path
    def search(self, initialState, goalState):
        now = time.time_ns()
        maxG = 0
        firstNode = Node()
        firstNode.parent = 0
        firstNode.state = initialState
        firstNode.h = self.Manhattan_Distance(initialState, goalState)
        firstNode.f = firstNode.h
        frontier = []
        heapq.heappush(frontier, (firstNode.f,0 , firstNode))
        explored = []
        while frontier:
            #print("frontier:")
            fronts = heapq.nsmallest(len(frontier),frontier)
            """
            for front in fronts:
                print(front[2].state[0])
                print(front[2].state[1])
                print(front[2].state[2])
                print("f = ", front[0], " order = ", front[1])
            print("")
            """
            item = heapq.heappop(frontier)
            """
            print("node expanded:")
            print(item[2].state[0])
            print(item[2].state[1])
            print(item[2].state[2])
            print("")
            """
            if (item[2].g > maxG):
                maxG = item[2].g
            explored.append(item[2].state)
            if (goalState == item[2].state):
                f = item[2].f
                goal = [self.getPath(item[2])]
                goal.append(f)
                goal.append(explored)
                goal.append(maxG)
                then = time.time_ns()
                goal.append(then - now)
                return goal
            if (item[2].g == 31):
                continue
            children = self.getChildren(item[2], goalState)
            # print("children accepted:")
            for i in range(len(children)):
                inExplored = False
                inFrontier = False
                childAccepted = False
                for j in range(len(explored)):
                    if (explored[j] == children[i].state):
                        inExplored = True
                        break
                for j in range(len(frontier)):
                    if (frontier[j][2].state == children[i].state):
                        inFrontier = True
                        break
                if (not (inFrontier or inExplored)):
                    """
                    print(children[i].state[0])
                    print(children[i].state[1])
                    print(children[i].state[2])
                    print("g = ", children[i].g, ", h = ", children[i].h, ", f = ", children[i].f)
                    """
                    heapq.heappush(frontier, (children[i].f, 0, children[i]))
                    childAccepted = True
                elif (inFrontier):
                    """
                    print(children[i].state[0])
                    print(children[i].state[1])
                    print(children[i].state[2])
                    print("g = ", children[i].g, ", h = ", children[i].h, ", f = ", children[i].f)
                    """
                    if (frontier[j][0] > children[i].f):
                        childAccepted = True
                        temp = list(frontier[j])
                        temp[0] = children[i].f
                        temp[1] = 0
                        temp[2] = children[i]
                        frontier[j] = tuple(temp)
                if (childAccepted):
                    for x in range(len(frontier)):
                        if (frontier[x][0] == children[i].f):
                            temp = list(frontier[x])
                            temp[1] = frontier[x][1] + 1
                            frontier[x] = tuple(temp)
            # print("")
        then = time.time_ns()
        goal = [0]
        f = item[2].f
        goal.append(self.getPath(item[2]))
        goal.append(f)
        goal.append(explored)
        goal.append(maxG)
        then = time.time_ns()
        goal.append(then - now)
        return goal