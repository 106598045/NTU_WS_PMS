package model;

public class Patient {
	private String name;
	private int period;
	
	public void setName(String patientName) {
		this.name = patientName;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPeriod(int patientMonitoringPeriod) {
		this.period = patientMonitoringPeriod;
	}
	
	public int getPeriod() {
		return this.period;
	}
}
