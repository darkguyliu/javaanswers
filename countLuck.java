    public static String countLuck(List<String> matrix, int k) {
    // Write your code here
        List<Integer> start = getStartIndex(matrix);
        Map<String,Integer> map = new HashMap<>();
        map.put("counter",0);
        isSolution(start.get(0), start.get(1), matrix, new HashSet<>(),map);
        return map.get("counter") == k ? "Impressed" : "Oops!";    

    }
    private static List<Integer> getStartIndex(List<String> matrix){
        for(int i = 0;i < matrix.size();i++)
            if(matrix.get(i).contains("M")) 
                return Arrays.asList(i,matrix.get(i).indexOf("M"));
        
        return null;
    }
    private static boolean isSolution(int i,int j, List<String> matrix,Set<String> visited,Map<String,Integer> map){
        if(visited.contains(i+","+j)) return false;
        visited.add(i+","+j);
        if(!isValidIndex(i, j, matrix)) return false;
        if(matrix.get(i).charAt(j)=='X') return false;
        if(matrix.get(i).charAt(j)=='*') return true;
        
        int count = 0;
        if(isCellValid(i - 1, j, matrix,visited)) count++;
        if(isCellValid(i, j - 1, matrix,visited)) count++;
        if(isCellValid(i + 1, j, matrix,visited)) count++;
        if(isCellValid(i, j + 1, matrix,visited)) count++;
        if(count > 1){
            map.put("counter", map.get("counter") + 1);
        }
        //Backtrack and add the counts in sub path (i.e. if the correct path)
        Map<String,Integer> localMap = new HashMap<>();
        localMap.put("counter",0);
        if(isSolution(i - 1, j, matrix, visited,localMap)){//top
            map.put("counter", map.get("counter") + localMap.get("counter"));
            return true;
        }else{
             localMap = new HashMap<>();
             localMap.put("counter",0);
             if(isSolution(i, j - 1, matrix, visited,localMap)){//left
                  map.put("counter", map.get("counter") + localMap.get("counter"));
                  return true;
             }
             else{
                localMap = new HashMap<>();
                localMap.put("counter",0);
                if(isSolution(i + 1, j, matrix, visited,localMap)){//bottom
                    map.put("counter", map.get("counter") + localMap.get("counter"));
                    return true;
                }else{
                    localMap = new HashMap<>();
                    localMap.put("counter",0);
                     if(isSolution(i, j + 1, matrix, visited,localMap)) {
                        map.put("counter", map.get("counter") + localMap.get("counter"));
                        return true;
                    }
                }
            }
        }
        return false;           
    }
    private static boolean isCellValid(int i, int j, List<String> matrix,Set<String> visited){
        if(visited.contains(i+","+j)) return false;
        if(!isValidIndex(i, j, matrix)) return false;
        return matrix.get(i).charAt(j) == '.' || matrix.get(i).charAt(j) == '*';
    }
    private static boolean isValidIndex(int i, int j,List<String> matrix){
        return i >= 0 && j >= 0 && i < matrix.size() && j < matrix.get(0).length();
    }    
