package lc.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tgu on 6/22/17.
 */
public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<>();
    }

}
