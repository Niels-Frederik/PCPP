package exercises09;

public class SecCounter {
  public int milSeconds;
  private boolean running;

  public SecCounter( int s, boolean r){
    milSeconds = s;
    running= r;
  }
	
  public void setRunning(boolean running) {
    this.running= running;
  }

  public boolean incr(){
    if (running) milSeconds += 100;
    return running;
  }

  public boolean running(){
    return this.running;
  }
}
