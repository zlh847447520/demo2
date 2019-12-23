package cn.tedu.store.service.ex;


public class CartNotFoundException extends ServiceException {



	/**
	 * 购物和数据不存在的异常
	 */
	private static final long serialVersionUID = 1733172511671851674L;

	public CartNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CartNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
