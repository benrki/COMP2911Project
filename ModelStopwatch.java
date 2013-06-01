import java.io.File;


public class ModelStopwatch implements ModelTimer{

    private long startingTime;
    private long startTime;
    private long pauseStartTime;
    private long pausedTime;
    
    public ModelStopwatch (long startingTime) {
        this.startingTime = startingTime;
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
        return System.currentTimeMillis() - (this.startTime+this.pausedTime) + this.startingTime; 
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
