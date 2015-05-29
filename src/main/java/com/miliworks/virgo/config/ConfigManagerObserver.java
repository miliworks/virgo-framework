package com.miliworks.virgo.config;

public interface ConfigManagerObserver {
	public void onConfigChanged(String configPoint);
}
