package cn.tedu.store.service.ex;


public class PasswordNotMatcheException extends ServiceException {

	
	/**
	 * 密码异常
	 */
	private static final long serialVersionUID = -6209564048941381667L;

	public PasswordNotMatcheException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatcheException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatcheException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatcheException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PasswordNotMatcheException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
