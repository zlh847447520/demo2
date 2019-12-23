package cn.tedu.store.controller.ex;

public class FileIOException extends FileUploadException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4580366731149383807L;

	public FileIOException() {
		super();
	}

	public FileIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FileIOException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileIOException(String message) {
		super(message);
	}

	public FileIOException(Throwable cause) {
		super(cause);
	}
	

}
