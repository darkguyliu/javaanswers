    public static int solve(List<List<Integer>> shots, List<List<Integer>> players) {
    // Write your code here
        int strengths = 0;
        shots.sort(Comparator.comparingInt(t -> t.get(0)));
        players.sort(Comparator.comparingInt(t -> t.get(0)));
        for (List<Integer> player : players) {
            Iterator<List<Integer>> iter = shots.iterator();
            while(iter.hasNext()){
                List<Integer> shot = iter.next();
                if(player.get(1)<shot.get(0))
                {
                    break;
                }
                else if(shot.get(1)<player.get(0)) 
                {
                    iter.remove();
                }
                else 
                {
                    strengths++;
                }
            }            
        }
        return strengths;    
    }
