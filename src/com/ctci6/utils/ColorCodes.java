package com.ctci6.utils;

/**
 * Class used for ANSI Color manipulation in a console supporting ANSI color
 * codes
 */
public class ColorCodes {

	// with normal background
		public static final String RESET = "\u001B[0m";
		public static final String BLACK = "\u001B[30;1m";
		public static final String RED = "\u001B[31;1m";
		public static final String GREEN = "\u001B[32;1m";
		public static final String YELLOW = "\u001B[33;1m";
		public static final String BLUE = "\u001B[34;1m";
		public static final String PURPLE = "\u001B[35;1m";
		public static final String CYAN = "\u001B[36;1m";
		public static final String WHITE = "\u001B[37;1m";

		// with black background
		public static final String B_RESET = "\u001B[0m";
		public static final String B_BLACK = "\u001B[30;40;1m";
		public static final String B_RED = "\u001B[31;40;1m";
		public static final String B_GREEN = "\u001B[32;40;1m";
		public static final String B_YELLOW = "\u001B[33;40;1m";
		public static final String B_BLUE = "\u001B[34;40;1m";
		public static final String B_PURPLE = "\u001B[35;40;1m";
		public static final String B_CYAN = "\u001B[36;40;1m";
		public static final String B_WHITE = "\u001B[37;40;1m";
}
