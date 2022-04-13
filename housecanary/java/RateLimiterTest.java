public class RateLimiterTest {
    public static void main(String[] args) throws Exception {
        test(1000, 100, 400);
        test(500, 10, 50);
        test(1000, 1, 5);
    }
    private static void test(long ms, int limit, int tries) throws Exception {
        System.out.println("-----------------");
        System.out.println("MS: "+ms+" Limit: "+limit+" Tries: "+tries);
        final RateLimiter rl=new RateLimiter(ms, limit);
        final long start=System.currentTimeMillis();
        int success=0, fail=0;
        for (int i=0; i<tries; i++)
            if (rl.check())
                success++;
            else  {
                Thread.sleep(ms/2);
                fail++;
            }
        final double period=System.currentTimeMillis()-start;
        final double per=((double)period) / ((double)success);
        final double shouldTake=((double)(ms * success)) / ((double)limit);
        System.out.println("Succeeded: "+success+" Failed: "+fail);
        System.out.println("Elapsed: "+period+" ms");
        System.out.println("Target:  "+shouldTake+" ms");
        System.out.println("ms/hit: "+per);
        System.out.println("-----------------");
    }
}