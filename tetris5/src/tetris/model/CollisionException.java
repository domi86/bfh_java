package tetris.model;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

public class CollisionException extends Exception {

	public CollisionException(String string) {
		super(string);
		System.out.println(java.time.Instant.now().toString() + ": " + string);
	}

}
