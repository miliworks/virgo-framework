package com.miliworks.virgo.test;

import com.miliworks.virgo.log.Log;

public class TestLog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Log.v("TagA", "test verbose message");
		Log.d("TagA", "test debug message");
		Log.i("TagA", "test info message");
		Log.w("TagA", "test warning message");
		Log.e("TagA", "test error message");
	}

}
