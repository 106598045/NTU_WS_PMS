package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sensor {
	private String category;
	private String name;
	private String dataset;
	private int lowerBoundSafeRange;
	private int upperBoundSafeRange;
	private int finalTime;
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCategory() {
		return this.category;
	}
	
	public void setName(String sensorName) {
		this.name = sensorName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDataset(String dataset) {
		this.dataset = dataset;
	}
	
	public String getDataset() {
		return this.dataset;
	}
	
	public void setLowerBoundSafeRange(int lowerBound) {
		this.lowerBoundSafeRange = lowerBound;
	}
	
	public int getLowerBoundSafeRange() {
		return this.lowerBoundSafeRange;
	}
	
	public void setUpperBoundSafeRange(int upperBound) {
		this.upperBoundSafeRange = upperBound;
	}
	
	public int getUpperBoundSafeRange() {
		return this.upperBoundSafeRange;
	}
	
	public void setfinalTime(int finalTime) {
		this.finalTime = finalTime;
	}
	
	public int getfinalTime() {
		return this.finalTime;
	}
}
