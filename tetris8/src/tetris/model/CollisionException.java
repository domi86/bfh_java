package tetris.model;

public class CollisionException extends Exception {

	private static final long serialVersionUID = 1L;

	public CollisionException(String string) {
		super(string);
		System.out.println(java.time.Instant.now().toString() + ": " + string);
	}

}
