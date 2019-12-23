package cn.tedu.store.controller.ex;

public class FileUploadException extends RuntimeException {

	/**
	 * 父类
	 */
	private static final long serialVersionUID = 3109483404798979386L;

	public FileUploadException() {
		super();

	}

	public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);

	}

	public FileUploadException(String message) {
		super(message);

	}

	public FileUploadException(Throwable cause) {
		super(cause);
	}
	
	}
