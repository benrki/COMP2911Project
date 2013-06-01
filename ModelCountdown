
public class ModelCountdown implements ModelTimer{

    private long countdown;
    private long startTime;
    private long pauseStartTime;
    private long pausedTime;
    public ModelCountdown(long remainingSeconds) {
        this.countdown = remainingSeconds*1000;
        this.startTime = System.currentTimeMillis();
        this.pauseStartTime = 0;
        this.pausedTime = 0;
    }
    
    @Override
    public long getTimer() {
        if(this.pauseStartTime!=0) {
            this.pausedTime = this.pausedTime + System.currentTimeMillis() - this.pauseStartTime;
            this.pauseStartTime = System.currentTimeMillis();
        }
        return this.countdown - (System.currentTimeMillis() - (this.startTime+this.pausedTime)); 
    }
    
    @Override
    public void pauseTimer() {
        this.pauseStartTime = System.currentTimeMillis();
    }

    @Override
    public void unpauseTimer() {
        this.pausedTime = this.pausedTime + System.currentTimeMillis() - this.pauseStartTime;
        this.pauseStartTime = 0;
    }

}
