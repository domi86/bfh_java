package java1;

public class Hello {

	public static void main(String[] args) {
		String name = getName(args);
		System.out.println("Hello " + name);
	}

	private static String getName(String[] args) {
		String name = "world";
		if (args.length > 0)
			name = args[0];
		return name;
	}
}
