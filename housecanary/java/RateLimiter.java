public class RateLimiter {
    public final long windowLen;
    public final int limit;
    private int current=0;
    private long startWindow=0;
    public RateLimiter(long ms, int limit) {
        this.windowLen=ms;
        this.limit=limit;
    }
    public synchronized boolean check() {
        return checkTime() == 0L;
    }
    public synchronized long checkTime() {
        long now=System.currentTimeMillis();
        long distance = now - startWindow;
        if (distance > windowLen) {
            startWindow=now;
            current=1;
            return 0;
        }
        return ++current <= limit
            ?0 :windowLen-distance;
    }
}
