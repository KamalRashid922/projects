import time
class Node1:
    def __init__(self, state, parent, action, index, depth):
        self.state = state
        self.parent = parent
        self.action = action
        self.index = index
        self.depth = depth
class puzzle:
    def __init__(self, start, goal):
        self.start = start
        self.goal = goal
        for r in range(3):
            for c in range(3):
                if (start[r][c] == 0):
                    break
            if (start[r][c] == 0):
                break
        self.index = (r, c)
        self.path = []

    def print(self):
        while self.path:
            node = self.path.pop()
            print(node.action)
            for nd in node.state:
                print(nd)

    def equals(self, mat):
        newMat = []
        for i in range(3):
            row = []
            for j in range(3):
                row.append(mat[i][j])
            newMat.append(row)
        return newMat

    def expand(self, node, frontier, explored):
        (row, col) = node.index
        # move up
        if row > 0:
            hold = self.equals(node.state)
            hold[row][col] = hold[row - 1][col]
            hold[row - 1][col] = 0
            new = Node1(state=hold, parent=node, action="up", index=(row - 1, col), depth=node.depth + 1)
            inFrontier = False
            for i in range(len(frontier)):
                if (new.state == frontier[i].state):
                    inFrontier = True
                    break
            inExplored = False
            for i in range(len(explored)):
                if (new.state == explored[i]):
                    inExplored = True
                    break
            if not (inExplored or inFrontier):
                    frontier.append(new)
        # move right
        if col < 2:
            hold = self.equals(node.state)
            hold[row][col] = hold[row][col + 1]
            hold[row][col + 1] = 0
            new = Node1(state=hold, parent=node, action="right", index=(row, col + 1), depth=node.depth + 1)
            inFrontier = False
            for i in range(len(frontier)):
                if (new.state == frontier[i].state):
                    inFrontier = True
                    break
            inExplored = False
            for i in range(len(explored)):
                if (new.state == explored[i]):
                    inExplored = True
                    break
            if not (inExplored or inFrontier):
                frontier.append(new)
        # move down
        if row < 2:
            hold = self.equals(node.state)
            hold[row][col] = hold[row + 1][col]
            hold[row + 1][col] = 0
            new = Node1(state=hold, parent=node, action="down", index=(row + 1, col), depth=node.depth + 1)
            inFrontier = False
            for i in range(len(frontier)):
                if (new.state == frontier[i].state):
                    inFrontier = True
                    break
            inExplored = False
            for i in range(len(explored)):
                if (new.state == explored[i]):
                    inExplored = True
                    break
            if not (inExplored or inFrontier):
                frontier.append(new)
        # move left
        if col > 0:
            hold = self.equals(node.state)
            hold[row][col] = hold[row][col - 1]
            hold[row][col - 1] = 0
            new = Node1(state=hold, parent=node, action="left", index=(row, col - 1), depth=node.depth + 1)
            inFrontier = False
            for i in range(len(frontier)):
                if (new.state == frontier[i].state):
                    inFrontier = True
                    break
            inExplored = False
            for i in range(len(explored)):
                if (new.state == explored[i]):
                    inExplored = True
                    break
            if not (inExplored or inFrontier):
                frontier.append(new)
        return frontier

    def dfs(self):
        s = time.time_ns()
        explored = []
        max = 0
        root = Node1(state=self.start, parent=None, action="root", index=self.index, depth=1)
        stack = []
        stack.append(root)
        while stack:
            node = stack.pop()
            explored.append(node.state)
            if node.depth > max:
                max = node.depth
            # check if goal reached  ===> return
            if (node.state == self.goal):
                while node.parent is not None:
                    self.path.append(node)
                    node = node.parent
                self.path.append(node)
                e = time.time_ns()
                result = [len(self.path) - 1]
                result.append(explored)
                result.append(max)
                result.append(e - s)
                return result
            # max depth to search
            if node.depth >=50:
                continue
            # or expand the node(add children to the stack)
            stack = self.expand(node, stack, explored)
        while node.parent is not None:
            self.path.append(node)
            node = node.parent
        self.path.append(node)
        e = time.time_ns()
        result = [0]
        result.append(len(self.path) - 1)
        result.append(explored)
        result.append(max)
        result.append(e - s)
        return result

    def bfs(self):
        s = time.time_ns()
        explored = []
        root = Node1(state=self.start, parent=None, action="root", index=self.index, depth=1)
        queue = []
        queue.append(root)
        while queue:
            node = queue.pop(0)
            explored.append(node.state)
            # check if goal reached  ===> return
            if (node.state == self.goal):
                while node.parent is not None:
                    self.path.append(node)
                    node = node.parent
                self.path.append(node)
                e = time.time_ns()
                result = [len(self.path) - 1]
                result.append(explored)
                result.append(len(self.path) - 1)
                result.append(e - s)
                return result
            if node.depth >= 35:
                continue
            # or expand the node(add children to the queue)
            queue = self.expand(node, queue, explored)
        while node.parent is not None:
            self.path.append(node)
            node = node.parent
        self.path.append(node)
        e = time.time_ns()
        result = [0]
        result.append(len(self.path) - 1)
        result.append(explored)
        result.append(len(self.path) - 1)
        result.append(e - s)
        return result