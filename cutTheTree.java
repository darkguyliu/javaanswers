class Result {

    /*
     * Complete the 'cutTheTree' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY data
     *  2. 2D_INTEGER_ARRAY edges
     */

    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
    // Write your code here
        int len = data.size();
        List<Integer>[] graph = new LinkedList[len + 1];
        int[] visited = new int[len + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new LinkedList<Integer>();
        }
        for (List<Integer> edge : edges) {
            graph[edge.get(0)].add(edge.get(1));
            graph[edge.get(1)].add(edge.get(0));
        }
        TreeNode root = new TreeNode(1, data.get(0));
        visited[1] = 1;
        root.sum = buildChildren(root, graph, data, visited);
        return cut(root, root.sum, Integer.MAX_VALUE);    

    }
    private static int cut(TreeNode node, int sum, int lastMinDiff) {
        int minDiff = lastMinDiff;
        List<TreeNode> nodes = node.children;

        for (int i = 0; i < nodes.size(); i++) {
            int currentNodeSum = nodes.get(i).sum;
            int diff = sum - currentNodeSum * 2;
            if (diff > 0) {
                minDiff = Integer.min(minDiff, diff);
            }
        }

        for (int i = 0; i < nodes.size(); i++) {
            int currentNodeSum = nodes.get(i).sum;
            int diff = sum - currentNodeSum * 2;
            if (diff == 0) {
                return 0;
            }
            
            if (diff > 0) {
                minDiff = Integer.min(minDiff, diff);
            } else {
                return cut(nodes.get(i), sum, minDiff);
            }
        }

        return Integer.min(minDiff, Math.abs(sum - node.sum * 2));
    }
    private static int buildChildren(TreeNode node, List<Integer>[] graph, List<Integer> data, int[] visited) {
        int childrenSum = 0;
        for (int j = 0; j < graph[node.index].size(); j++) {
            int index = graph[node.index].get(j);
            if (visited[index] == 1) continue;

            TreeNode child = new TreeNode(index, data.get(index - 1));
            node.children.add(child);                
            visited[index] = 1;
            child.sum = buildChildren(child, graph, data, visited);
            childrenSum = childrenSum + child.sum;
        }
        return node.value + childrenSum;
    }
}

   


class TreeNode {
    int value;
    int index;
    int sum;
    List<TreeNode> children;
    
    public TreeNode(int index, int value) {
        this.index = index;
        this.value = value;
        this.children = new ArrayList();
        this.sum = value;
    }
} 
