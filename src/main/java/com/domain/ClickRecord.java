package com.domain;

public class ClickRecord {

	public ClickRecord() {
	}

	public ClickRecord(int numberOfClicks) {
		this.numberOfClicks = numberOfClicks;
	}

	public int numberOfClicks;

	public void setNumberOfClicks(int numberOfClicks) {
		this.numberOfClicks = numberOfClicks;
	}

	public int getNumberOfClicks() {
		return this.numberOfClicks;
	}
}
