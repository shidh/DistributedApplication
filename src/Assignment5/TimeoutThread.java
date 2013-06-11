package Assignment5V2;

/**
 * java线程超时控制的实现
 * 
 * 超时控制一般使用阻塞时间比较长的操作上,有可能是和远程数据库的连接,也有可能是网络下载,在程序超时后, 往往需要进行一些操作,比如退出线程,或者重新执行.
 * 本线程设置了一个超时时间 该线程开始运行后，经过指定超时时间， 该线程会抛出一个未检查异常通知调用该线程的程序超时
 * 在超时结束前可以调用该类的cancel方法取消计时
 * 
 * @author cuilk
 */
public class TimeoutThread extends Thread {

	/**
	 * 计时器超时时间
	 */
	private long timeout;

	/**
	 * 计时是否被取消
	 */
	private boolean isCanceled = false;

	/**
	 * 当计时器超时时抛出的异常
	 */
	private TimeoutException timeoutException;

	/**
	 * 构造器
	 * 
	 * @param timeout
	 *            指定超时的时间
	 */
	public TimeoutThread(long timeout, TimeoutException timeoutErr,Thread currentThread) {
		super();
		this.timeout = timeout;
		this.timeoutException = timeoutErr;
		// 设置本线程为守护线程
		 this.setDaemon(true);
		 this.setUncaughtExceptionHandler(new UncaughtExceptionHandlerImpl(currentThread));
	}

	/**
	 * 取消计时
	 */
	public synchronized void cancel() {
		isCanceled = true;
	}

	/**
	 * 启动超时计时器
	 */
	public void run() {
		try {

			Thread.sleep(timeout);
			if (!isCanceled){
				throw timeoutException;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean hasException() {
		return isCanceled;
	}
	/**
	 * 
	 * 对线程中抛出异常的处理
	 * 
	 * @author cuilk	 	 
          */
	private class UncaughtExceptionHandlerImpl implements Thread.UncaughtExceptionHandler{

		private Thread currentThread;
		
		public UncaughtExceptionHandlerImpl(Thread currentThread){
			this.currentThread=currentThread;
		}
		
		public void uncaughtException(Thread t, Throwable e) {
			currentThread.interrupt();
		}
		
	}

}
