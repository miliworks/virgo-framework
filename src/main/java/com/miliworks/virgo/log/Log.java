package com.miliworks.virgo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO:
public class Log {
	private static Logger log = LoggerFactory.getLogger(Log.class);

	public static void v(String tag, String msg) {
		log.trace(msg);
	}

	public static void d(String tag, String msg) {
		log.debug(msg);
	}

	public static void i(String tag, String msg) {
		log.info(msg);
	}

	public static void w(String tag, String msg) {
		log.warn(msg);
	}

	public static void e(String tag, String msg) {
		log.error(msg);
	}
}
