package lc.Connected_Component_in_Undirected_Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import lc.graph.UndirectedGraphNode;

/**
 * Definition for Undirected graph. class UndirectedGraphNode { int label; ArrayList<UndirectedGraphNode> neighbors;
 * UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); } };
 */
public class DFS_Stack_Solution {
    /**
     * @param nodes a array of Undirected graph node
     * @return a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // Write your code here
        List<List<Integer>> result = new ArrayList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        for (UndirectedGraphNode node : nodes) {
            if (!visited.contains(node)) {
                List<Integer> cc = new ArrayList<Integer>();
                dfs(node, visited, cc);
                Collections.sort(cc);
                result.add(cc);
            }
        }
        return result;
    }

    private void dfs(UndirectedGraphNode node, Set<UndirectedGraphNode> visited, List<Integer> cc) {

        Stack<UndirectedGraphNode> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            UndirectedGraphNode curr = stack.pop();

            if (visited.contains(curr)) {
                continue;
            }

            cc.add(curr.label);
            visited.add(curr);

            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }
    }
}

