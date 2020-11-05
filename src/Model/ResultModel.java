package Model;

public class ResultModel {
	String startStation=null;   //当前站点
	String lastStation=null;	//上一站点
	
	public String getLastStation() {
		return lastStation;
	}
	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}
//	boolean isExchange = false; //true为换乘，false为没有换乘
	
	public String getStartStation() {
		return startStation;
	}
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}
//	public boolean isExchange() {
//		return isExchange;
//	}
//	public void setExchange(boolean isExchange) {
//		this.isExchange = isExchange;
//	}
	
}
