class Solution {
    public String longestPalindrome(String s) {
        if(s.equals("")){
            return  "";
        }
        int[] len=new int[2];
        for(int i=0;i<s.length();i++){
            int[] len1=func(i,i,s);
            if(i!=s.length()-1){
                int[] len2=func(i,i+1,s);
                int[] len3=len1[1]-len1[0]>len2[1]-len2[0]?len1:len2;
                len=len[1]-len[0]>len3[1]-len3[0]?len:len3;
            }else{
                len=len[1]-len[0]>len1[1]-len1[0]?len:len1;
            }
        }
        return s.substring(len[0],len[1]+1);
    }
    private int[] func(int left,int right,String s){
        int[] res=new int[2];
        while(left>=0&&right<s.length()){
            if(s.charAt(left)==s.charAt(right)){
                res[0]=left;
                res[1]=right;
                left--;
                right++;
            }else{
                break;
            }
        }
        return res;
    }
}

class Solution {
    public String longestPalindrome(String s) {
        StringBuffer sb=new StringBuffer("^#");
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i)).append("#");
        }
        sb.append("&");
        String T=sb.toString();
        int[] P=new int[T.length()];
        int C=0;
        int R=0;
        for(int i=1;i<T.length()-1;i++){
            int i_prim=2*C-i;
            if(R>i){
                P[i]=Math.min(R-i,P[i_prim]);
            }else{
                P[i]=0;
            }

            while(T.charAt(i+1+P[i])==T.charAt(i-1-P[i])){
                P[i]++;
            }

            if(i+P[i]>R){
                C=i;
                R=i+P[i];
            }
        }

        int maxc=0;
        int maxlen=0;
        for(int i=1;i<P.length-1;i++){
            if(P[i]>maxlen){
                maxc=i;
                maxlen=P[i];
            }
        }
        int start=(maxc-maxlen)/2;
        return s.substring(start,start+maxlen);
    }
}


class Solution {
    public int maxProduct(int[] nums) {
        int res=nums[0];
        int[] max=new int[nums.length];
        int[] min=new int[nums.length];
        max[0]=nums[0];
        min[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            max[i]=Math.max(nums[i],Math.max(max[i-1]*nums[i],min[i-1]*nums[i]));
            min[i]=Math.min(nums[i],Math.min(max[i-1]*nums[i],min[i-1]*nums[i]));
            res=Math.max(res,max[i]);
        }
        return res;
    }
}


class Solution {
    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        if(nums.length<2){
            return nums[0];
        }
        int[] res1=new int[nums.length];
        res1[1]=nums[0];
        for(int i=2;i<res1.length;i++){
            res1[i]=Math.max(res1[i-1],res1[i-2]+nums[i-1]);
        }

        int[] res2=new int[nums.length];
        res2[1]=nums[1];
        for(int i=2;i<res2.length;i++){
            res2[i]=Math.max(res2[i-1],res2[i-2]+nums[i]);
        }

        return Math.max(res1[nums.length-1],res2[nums.length-1]);
    }
}


class Solution {
    public int nthUglyNumber(int n) {
        int[] dp=new int[n];
        dp[0]=1;
        int p2=0;
        int p3=0;
        int p5=0;
        for(int i=1;i<n;i++){
            dp[i]=Math.min(dp[p2]*2,Math.min(dp[p3]*3,dp[p5]*5));
            if(dp[i]==dp[p2]*2){
                p2++;
            }
            if(dp[i]==dp[p3]*3){
                p3++;
            }
            if(dp[i]==dp[p5]*5){
                p5++;
            }
        }
        return dp[n-1];
    }
}


class Solution {
    public int numSquares(int n) {
        while(n%4==0){
            n/=4;
        }
        if(n%8==7){
            return 4;
        }
        int a=0;
        while(a*a<=n){
            int b=(int)Math.sqrt(n-a*a);
            if(a*a+b*b==n){
                if(a==0||b==0){
                return 1;
                }
                return 2;
            }
            a++;
        }
        return 3;
    }
}

class Solution {
    public int numSquares(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp,4);
        dp[0]=0;
        for(int i=1;i<=n;i++){
            for(int j=1;j*j<=i;j++){
                if(i>=j*j){
                    dp[i]=Math.min(dp[i-j*j]+1,dp[i]);
                }
            }
        }
        return dp[n];
    }
}

