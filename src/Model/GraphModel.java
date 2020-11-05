package Model;

import java.util.List;

public class GraphModel {    //邻接表

	String StationName;    //头节点
	List<String> stations; //相近节点
	List<String> LineName; //所在线路名称
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
