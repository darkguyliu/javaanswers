    public static long mandragora(List<Integer> H) {
    // Write your code here
        Integer[] array = new Integer[H.size()];
        H.toArray(array); // fill the array

        Arrays.sort(array);
        long plus = array[array.length-1];
        long mul = array.length;
        long curr = plus*mul;
        for(int i = array.length-2;i>0;i--){
            plus += array[i];
            mul--;
            long temp = plus * mul; 
            if(temp>curr) curr = temp;
            if(temp < curr){
                return curr;
            }
            else {curr = temp;}
        }
        return curr;    

    }

}
