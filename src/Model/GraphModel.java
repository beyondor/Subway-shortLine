package Model;

import java.util.List;

public class GraphModel {    //�ڽӱ�

	String StationName;    //ͷ�ڵ�
	List<String> stations; //����ڵ�
	List<String> LineName; //������·����
	boolean isVisited = false;

	public String getStationName() {
		return StationName;
	}

	public void setStationName(String stationName) {
		StationName = stationName;
	}

	public List<String> getLineName() {
		return LineName;
	}

	public void setLineName(List<String> lineName) {
		LineName = lineName;
	}

	public List<String> getStations() {
		return stations;
	}

	public void setStations(List<String> stations) {
		this.stations = stations;
	}

	
}
