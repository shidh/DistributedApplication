package Assignment5V2;

/**
 * 抛出异常类,该类继承了RuntimeException，原因是run方法不能抛出已检测异常。
 * 
 */
public class TimeoutException extends RuntimeException {
	/**
	 * 序列化号
	 */
	private static final long serialVersionUID = -8078853655388692688L;

	public TimeoutException(String errMessage) {
		super(errMessage);
	}
}

