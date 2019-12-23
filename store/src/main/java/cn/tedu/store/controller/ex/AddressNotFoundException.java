package cn.tedu.store.controller.ex;

public class AddressNotFoundException extends FileUploadException {

	/**
	 * 文件为空
	 */
	private static final long serialVersionUID = 1241017634099485103L;

	public AddressNotFoundException() {
		super();
	}

	public AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressNotFoundException(String message) {
		super(message);
	}

	public AddressNotFoundException(Throwable cause) {
		super(cause);
	}
	

}
