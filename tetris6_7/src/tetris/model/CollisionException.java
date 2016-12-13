package tetris.model;

public class CollisionException extends Exception {

	public CollisionException(String string) {
		super(string);
		System.out.println(java.time.Instant.now().toString() + ": " + string);
	}

}
