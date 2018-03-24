import java.util.ArrayList;
import java.util.List;

import model.Patient;
import model.Sensor;

public class InputParser {
	private int moniter_period;
	private ArrayList<Patient> patientList;
	private ArrayList<Sensor> sensorList;
	
	void parsing(String content) {
		ArrayList<Patient> pList = new ArrayList<Patient>();
		ArrayList<Sensor> sList = new ArrayList<Sensor>();
		
		final String[] splittedStr = content.split("\n");
		List<String> strList = new ArrayList<String>();
		for (String s : splittedStr) {
			strList.add(s);
		}
		moniter_period = Integer.parseInt(strList.get(0));
		for(int i=1;i<strList.size();i=i+2) {
			Patient patient = parserPatient(strList.get(i));
			Sensor sensor = parserSensor(strList.get(i+1));
			pList.add(patient);
			sList.add(sensor);
		}
		patientList = pList;
		sensorList = sList;
	}
	
	Patient parserPatient(String content) {
		Patient patient = new Patient();
		final String[] splittedStr = content.split(" ");
		List<String> strList = new ArrayList<String>();
		for (String s : splittedStr) {
			strList.add(s);
		}
		
		patient.setName(strList.get(1));
		patient.setPeriod(Integer.parseInt(strList.get(2)));

		return patient;
	}
	
	Sensor parserSensor(String content) {
		Sensor sensor = new Sensor();
		final String[] splittedStr = content.split(" ");
		List<String> strList = new ArrayList<String>();
		for (String s : splittedStr) {
			strList.add(s);
		}
		
		sensor.setCategory(strList.get(0));
		sensor.setName(strList.get(1));
		sensor.setDataset(strList.get(2));
		sensor.setLowerBoundSafeRange(Integer.parseInt(strList.get(3)));
		sensor.setUpperBoundSafeRange(Integer.parseInt(strList.get(4)));
		sensor.setfinalTime(this.moniter_period);
		return sensor;
	}
	
	ArrayList<Patient> getPatientList() {
		return patientList;
	}
	
	ArrayList<Sensor> getSensorList() {
		return sensorList;
	}
	
	int getPeriod() {
		return moniter_period;
	}
}
