package com.miliworks.virgo.task;

public interface ITask {
	public boolean init();
	public boolean doTask();
	public boolean finish();
}
