package engine;

public class TimeThread extends Thread {

	private long sleepTime;

	public TimeThread(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		while(true) {
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stop();

		}
	}

}
