    static int beautifulQuadruples(int a, int b, int c, int d) {
        /*
         * Write your code here.
         */       
         
         int count = 0;
         Map<Integer,Integer> firstHalf = new HashMap<>();
        //  Map<Integer,Integer> secondHalf = new HashMap<>();
         int[] arr = new int[4];
         arr[0] = a;
         arr[1] = b;
         arr[2] = c;
         arr[3] = d;
         Arrays.sort(arr);
         int acc = 0;
         for(int ai = 1;ai<=arr[2];ai++)
         {
             for(int bi = ai;bi<=arr[3];bi++)
             {
                 int xor = ai^bi;
                 firstHalf.put(xor, firstHalf.getOrDefault(xor, 0)+1);
                 acc++;
             }
         }
         for(int x = 1;x<=arr[1];x++)
         {
             for(int w=1;w<=Math.min(arr[0],x);w++)
             {
                 int xor = w^x;
                 count= count + acc - firstHalf.getOrDefault(xor, 0);
             }
             int y=x;
             for(int z=x;z<=arr[3];z++)
             {
                 int xor = y^z;
                 firstHalf.put(xor, firstHalf.getOrDefault(xor, 0)-1);
                 acc--;
             }
             
         }
         return count;

    }
