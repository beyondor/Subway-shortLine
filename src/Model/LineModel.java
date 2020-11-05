package Model;

import java.util.List;

public class LineModel {    //线路信息

	String LineName;      //线路名称
	List<String> stations;   //线路所含站点
	
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
