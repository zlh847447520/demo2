package cn.tedu.store.controller.ex;

public class AccessDenideException extends FileUploadException {

	/**
	 * 文件为空
	 */
	private static final long serialVersionUID = 1241017634099485103L;

	public AccessDenideException() {
		super();
	}

	public AccessDenideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccessDenideException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDenideException(String message) {
		super(message);
	}

	public AccessDenideException(Throwable cause) {
		super(cause);
	}
	

}
