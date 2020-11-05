package Date;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import Model.GraphModel;
import Model.LineModel;



public class DateInputing {

	public static List<LineModel> FinalLine = new ArrayList<LineModel>(); 	//地铁线路信息
	public List<GraphModel> FinalGraph = new ArrayList<GraphModel>();	//邻接表信息
	
	public DateInputing(String pathname) throws IOException {
		
		//读入文件
		File filename = new File(pathname);
		InputStreamReader input = new InputStreamReader(new FileInputStream(filename));
		BufferedReader br = new BufferedReader(input);
		
		String data="";
		while((data = br.readLine())!=null) {
			String[] tmpdata = data.split(" ");
			LineModel tmpline = new LineModel();
			
			//存储线路信息
			tmpline.setLineName(tmpdata[0]);
			List<String> LineStation = new ArrayList<String>();
			for(int i=1;i<tmpdata.length;i++) {      
				LineStation.add(tmpdata[i]);     //该线路的所有站点
			}
			tmpline.setStations(LineStation);
			FinalLine.add(tmpline);
			
			//将线路信息处理成邻接表
			String tmp1 = tmpdata[1];
			String tmp2 = tmpdata[2];
			for(int i=2;i<tmpdata.length;i++) {     
				
				List<String> stations = new ArrayList<String>();
				List<String> LineName = new ArrayList<String>();
				List<String> stations1 = new ArrayList<String>();
				List<String> LineName1 = new ArrayList<String>();
				
				
				GraphModel graphstation = new GraphModel();
				GraphModel graphstation1 = new GraphModel();
				//判断tmp1是否是已经存储过
				int isExit = 0;
				int m = 0;
				for(int j=0;j<FinalGraph.size();j++) {
					if(tmp1.equals(FinalGraph.get(j).getStationName())) {
						isExit = 1;
						m = j;      //tmp1在FinalGraph哪一个位置，存储到m中
						break;
					}
				}

				
				//判断tmp2是否是已经存储过
				int isExit1 = 0;
				int n = 0;
				for(int j=0;j<FinalGraph.size();j++) {
					if(tmp2.equals(FinalGraph.get(j).getStationName())) {
						isExit1 = 1;
						n = j;
						break;
					}
				}

				int p=0;
				if(isExit==0&&isExit1==0) {          //tmp1未存储过，tmp2未存储过
					graphstation.setStationName(tmp1);    //未存储过，新建graphstation类，将数据存入类中，最后压入FinalGraph类
					LineName.add(tmpdata[0]);
					stations.add(tmp2);
					graphstation.setLineName(LineName);
					graphstation.setStations(stations);
					
					graphstation1.setStationName(tmp2);
					LineName1.add(tmpdata[0]);
					stations1.add(tmp1);
					graphstation1.setLineName(LineName1);
					graphstation1.setStations(stations1);
					
					FinalGraph.add(graphstation);
					FinalGraph.add(graphstation1);

				}
				else if(isExit==0&&isExit1==1) {          //tmp1未存储过，tmp2已存储过
					graphstation.setStationName(tmp1);
					LineName.add(tmpdata[0]);
					stations.add(tmp2);
					graphstation.setLineName(LineName);
					graphstation.setStations(stations);
					
					List<String> station = FinalGraph.get(n).getStations();   //已存储过，则在FinalGraph第n个位置，在相邻战点后面加上
					List<String> linename = FinalGraph.get(n).getLineName();
					station.add(tmp1);
					for(int t=0;t<linename.size();t++) {
						if(tmpdata[0].equals(linename.get(t))) {
							p=1;
							break;
						}
					}
					if(p==0)
						linename.add(tmpdata[0]);
					FinalGraph.get(n).setLineName(linename);
					FinalGraph.get(n).setStations(station);
					
					FinalGraph.add(graphstation);
				}
				else if(isExit==1&&isExit1==0) {          //tmp1已存储过，tmp2未存储过
					graphstation.setStationName(tmp2);
					LineName1.add(tmpdata[0]);
					stations1.add(tmp1);
					graphstation.setLineName(LineName1);
					graphstation.setStations(stations1);
					
					List<String> station = FinalGraph.get(m).getStations();
					List<String> linename = FinalGraph.get(m).getLineName();
					station.add(tmp2);
					for(int t=0;t<linename.size();t++) {
						if(tmpdata[0].equals(linename.get(t))) {
							p=1;
							break;
						}
					}
					if(p==0)
						linename.add(tmpdata[0]);
					FinalGraph.get(m).setLineName(linename);
					FinalGraph.get(m).setStations(station);
					
					FinalGraph.add(graphstation);
				}
				else if(isExit==1&&isExit1==1) {          //tmp1，tmp2都已存储过
					List<String> station = FinalGraph.get(m).getStations();
					List<String> linename = FinalGraph.get(m).getLineName();
					station.add(tmp2);
					for(int t=0;t<linename.size();t++) {
						if(tmpdata[0].equals(linename.get(t))) {
							p=1;
							break;
						}
					}
					if(p==0)
						linename.add(tmpdata[0]);
					FinalGraph.get(m).setLineName(linename);
					FinalGraph.get(m).setStations(station);
					
					List<String> station1 = FinalGraph.get(n).getStations();
					List<String> linename1 = FinalGraph.get(n).getLineName();
					station1.add(tmp1);
					for(int t=0;t<linename1.size();t++) {
						if(tmpdata[0].equals(linename1.get(t))) {
							p=1;
							break;
						}
					}
					if(p==0)
						linename1.add(tmpdata[0]);
					FinalGraph.get(n).setLineName(linename1);
					FinalGraph.get(n).setStations(station1);
					
				}
				tmp1=tmp2;
				if(i+1<tmpdata.length)
					tmp2=tmpdata[i+1];
				else
					break;
				
			}
		}
		
	}
	
	
	
}
