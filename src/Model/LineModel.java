package Model;

import java.util.List;

public class LineModel {    //��·��Ϣ

	String LineName;      //��·����
	List<String> stations;   //��·����վ��
	
	public String getLineName() {
		return LineName;
	}
	public void setLineName(String lineName) {
		LineName = lineName;
	}
	public List<String> getStations() {
		return stations;
	}
	public void setStations(List<String> stations) {
		this.stations = stations;
	}
	
	
}
