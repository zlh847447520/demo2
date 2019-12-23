package cn.tedu.store.controller.ex;

public class FileEmptyException extends FileUploadException {

	/**
	 * 文件为空
	 */
	private static final long serialVersionUID = 1241017634099485103L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileEmptyException(String message) {
		super(message);
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
	}
	

}
