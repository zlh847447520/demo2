package cn.tedu.store.service.ex;

public class ServiceException extends RuntimeException {

	/**
	 * 业务异常的基数
	 */
	private static final long serialVersionUID = 980104530291206274L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	

}
