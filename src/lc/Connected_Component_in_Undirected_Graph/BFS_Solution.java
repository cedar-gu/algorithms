package lc.Connected_Component_in_Undirected_Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import lc.graph.UndirectedGraphNode;

/**
 * Created by tgu on 6/22/17.
 */
public class BFS_Solution {
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
                bfs(node, visited, cc);
                Collections.sort(cc);
                result.add(cc);
            }
        }
        return result;
    }

    private void bfs(UndirectedGraphNode node, Set<UndirectedGraphNode> visited, List<Integer> cc) {

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        visited.add(node);
        cc.add(node.label);

        while (!queue.isEmpty()) {
            UndirectedGraphNode curr = queue.poll();

            for (UndirectedGraphNode neighbor : curr.neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    cc.add(neighbor.label);
                }
            }
        }

    }
}
