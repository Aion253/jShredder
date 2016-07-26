package net.frostbite.jshredder;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ShredRandom {
	private static SecureRandom random = new SecureRandom();

	public static String nextRandom() {
	  return new BigInteger(130, random).toString(32);
	}
}
