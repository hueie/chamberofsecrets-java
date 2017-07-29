package chamberofsecrets;

import java.util.concurrent.Semaphore;

public class MyLockerThread extends Thread {
	static Semaphore semaphore = new Semaphore(1);

	private String userid;
	private String filepath;

	MyLockerThread(String userid, String filepath) {
		this.userid = userid;
		this.filepath = filepath;
	}

	@Override
	public void run() {
		try {
			System.out.println(userid + " : acquiring lock...");
			System.out.println(userid + " : available Semaphore permits now: " + semaphore.availablePermits());

			semaphore.acquire();
			System.out.println(userid + " : got the permit!");
			try {
				
				new HelloJNI().sayHello(filepath);
				
			} finally {
				// calling release() after a successful acquire()
				System.out.println(userid + " : releasing lock...");
				semaphore.release();
				System.out.println(userid + " : available Semaphore permits now: " + semaphore.availablePermits());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
