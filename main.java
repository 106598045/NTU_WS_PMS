import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;

import model.Patient;
import model.Sensor;

public class main {
    public static void main(String[] args) {
    	InputParser parser = new InputParser();
    	String content = getInputByFile(args[0]);
    	parser.parsing(content);
    	ArrayList<Patient> patientList = parser.getPatientList();
    	ArrayList<Sensor> sensorList = 	parser.getSensorList();
    	for(int i=0;i<patientList.size();i++) {
    		Thread t1 = new MonitorThread(patientList.get(i),sensorList.get(i));
    		t1.setPriority( Thread.MAX_PRIORITY - i ); 
    		t1.start();
    	}
    
    }
    
    static String getInputByFile(String filePath) {
    	StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String contents = sb.toString();
    	return contents;
    }
    
}

class MonitorThread extends Thread{
	private Patient patient;
	private Sensor sensor;
	private int count;
	
	public MonitorThread(Patient patient,Sensor sensor) {
		this.patient = patient;
		this.sensor = sensor;
	}
	
	public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(sensor.getDataset()));
            String line;
            ArrayList<String> dataRecord = new ArrayList<String>();
            while (count <= sensor.getfinalTime()) {
            	String result = "";
            	if((line=br.readLine())!= null) {
            		int data = Integer.parseInt(line);
            		if(data >= 0 && (data < sensor.getLowerBoundSafeRange() || data > sensor.getUpperBoundSafeRange())) {
            			System.out.println("["+count+"] "+patient.getName()+" is in danger ! Cause : "+sensor.getName()+" "+data);
            			result = "["+count+"] "+data;
            		}else if(data >= 0 && data >= sensor.getLowerBoundSafeRange() && data <= sensor.getUpperBoundSafeRange()){
            			result = "["+count+"] "+data;
            		}else{
            			System.out.println("["+count+"] "+sensor.getName()+" falls");
            			result = "["+count+"] -1";
            		}
            	}else {
            		System.out.println("["+count+"] "+sensor.getName()+" falls");
            		result = "["+count+"] -1";
            	}
            	
            	if(result != "") dataRecord.add(result);
            	count += patient.getPeriod();
            	sleep(patient.getPeriod());
            }
            
            System.out.println("patient "+patient.getName());
            System.out.println(sensor.getCategory()+" "+sensor.getName());
            for(int i=0;i < dataRecord.size();i++) {
            	System.out.println(dataRecord.get(i));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }	
	}
}