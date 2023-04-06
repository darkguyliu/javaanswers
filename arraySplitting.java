    public static int arraySplitting(List<Integer> arr) throws RuntimeException{
        int[] ps = new int[arr.size()];
        ps[0] = arr.get(0);
        for(int i=1;i<ps.length;i++){
            ps[i] = ps[i-1]+arr.get(i);
        }
        return fun(0,ps.length-1,ps);

    }
    private static int fun(int st, int end, int[] ps){
        if(st==end) return 0;
        for(int i=0;i<end-st;i++){
            int fs=0,ss=0;
            if(st==0) fs=ps[st+i];
            else fs=ps[st+i]-ps[st-1];
            ss = ps[end]-ps[st+i];
            if(ss==fs){
                return Math.max(fun(st,st+i,ps),fun(st+i+1,end,ps))+1;
            }
        }
        return 0;
    }
