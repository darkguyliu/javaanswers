
    public static int crabGraphs(int n, int t, List<List<Integer>> graph) {
    // Write your code here
        int[][]mat = new int[n+1][n+1];
        for(int i=0;i<graph.size();i++) {
        mat[graph.get(i).get(0)][graph.get(i).get(1)] = 1;
        mat[graph.get(i).get(1)][graph.get(i).get(0)] = 1;
        }
        System.out.println("Mat--");
        for(int i=0;i<mat.length;i++) {
            System.out.println();
            for(int j=0;j< mat[i].length;j++) {
                System.out.print(mat[i][j] + " ");
            }
        }


        int[][]flowg = new int[2*n+2+1][2*n+2+1];
        int[]degree = new int[2*n+2+1];
        int source = 0;
        int sink = 2*n+2;
        for(int i=1;i<mat.length;i++) {
            for(int j=1;j<mat[i].length;j++) {
                if(mat[i][j]==1) {
                    flowg[2*i][2*j+1] = 1;
                    flowg[2*j][2*i+1] = 1;
                    degree[2*i]++;
                }
            }
        }
        System.out.println("\nflow--");
        for(int i=0;i<flowg.length;i++) {
            System.out.println();
            for(int j=0;j< flowg[i].length;j++) {
                System.out.print(flowg[i][j] + " ");
            }
        }

        System.out.println("\nDegree:");
        for(int i=0;i<degree.length;i++) {
            System.out.print(degree[i]);
        }

        for(int i=1;(2*i)<(2*n+2);i++) {
            flowg[source][2*i] = Math.min(degree[2*i], t);
        }
        for(int j=1;(2*j+1)<(2*n+2);j++) {
            flowg[2*j+1][sink] = 1;
        }
        System.out.println("\nflow--");
        for(int i=0;i<flowg.length;i++) {
            System.out.println();
            for(int j=0;j< flowg[i].length;j++) {
                System.out.print(flowg[i][j] + " ");
            }
        }

        return maxFlow(flowg, source, sink);    

    }



    static int maxFlow(int capacity[][], int source, int sink){

        //declare and initialize residual capacity as total avaiable capacity initially.
        int residualCapacity[][] = new int[capacity.length][capacity[0].length];
        for (int i = 0; i < capacity.length; i++) {
            for (int j = 0; j < capacity[0].length; j++) {
                residualCapacity[i][j] = capacity[i][j];
            }
        }

        //this is parent map for storing BFS parent
        Map<Integer,Integer> parent = new HashMap<>();

        //stores all the augmented paths
        List<List<Integer>> augmentedPaths = new ArrayList<>();

        //max flow we can get in this network
        int maxFlow = 0;

        //see if augmented path can be found from source to sink.
        while(BFS(residualCapacity, parent, source, sink)){
            List<Integer> augmentedPath = new ArrayList<>();
            int flow = Integer.MAX_VALUE;
            //find minimum residual capacity in augmented path
            //also add vertices to augmented path list
            int v = sink;
            while(v != source){
                augmentedPath.add(v);
                int u = parent.get(v);
                if (flow > residualCapacity[u][v]) {
                    flow = residualCapacity[u][v];
                }
                v = u;
            }
            augmentedPath.add(source);
            Collections.reverse(augmentedPath);
            augmentedPaths.add(augmentedPath);

            //add min capacity to max flow
            maxFlow += flow;

            //decrease residual capacity by min capacity from u to v in augmented path
            // and increase residual capacity by min capacity from v to u
            v = sink;
            while(v != source){
                int u = parent.get(v);
                residualCapacity[u][v] -= flow;
                residualCapacity[v][u] += flow;
                v = u;
            }
        }
        // printAugmentedPaths(augmentedPaths);
        return maxFlow;
    }
    static boolean BFS(int[][] residualCapacity, Map<Integer,Integer> parent,
            int source, int sink){
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited.add(source);
        boolean foundAugmentedPath = false;
        //see if we can find augmented path from source to sink
        while(!queue.isEmpty()){
            int u = queue.poll();
            for(int v = 0; v < residualCapacity.length; v++){
                //explore the vertex only if it is not visited and its residual capacity is
                //greater than 0
                if(!visited.contains(v) &&  residualCapacity[u][v] > 0){
                    //add in parent map saying v got explored by u
                    parent.put(v, u);
                    //add v to visited
                    visited.add(v);
                    //add v to queue for BFS
                    queue.add(v);
                    //if sink is found then augmented path is found
                    if ( v == sink) {
                        foundAugmentedPath = true;
                        break;
                    }
                }
            }
        }
        //returns if augmented path is found from source to sink or not
        return foundAugmentedPath;
    }
