package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import Date.DateInputing;
import Model.ResultModel;

public class Main {

	public static DateInputing dataProcessing = null;
	public static void main(String[] args) {
	// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		try {
			dataProcessing = new DateInputing("E:\\data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("选择：1.查询路线；2.查询起点终点最短路径");
		int option1 = input.nextInt();
		while(option1!=1&&option1!=2) {
			System.out.println("选择：1.查询路线；2.查询起点终点最短路径");
			option1 = input.nextInt();
		}
		if(option1==1) {
			System.out.println("请输入路线名：");
			String name = input.next();
			//查询路线
			searchLineData(name);
		}
		else if(option1==2) {
			System.out.print("请输入起点：");
			String start = input.next();
			System.out.print("请输入终点：");
			String end = input.next();
			List<ResultModel> resultmodel = BFS(end,start);
			if(resultmodel!=null)
				ShortLine(resultmodel,start);
		}
		input.close();
		
	}	
	
	public static void searchLineData(String name) {         //依据线路名称查询站点
		int tmp=0;
		for(int i=0;i<dataProcessing.FinalLine.size();i++) {
			if(name.equals(dataProcessing.FinalLine.get(i).getLineName())) {
				for(int j=0;j<dataProcessing.FinalLine.get(i).getStations().size();j++) {
					System.out.print(dataProcessing.FinalLine.get(i).getStations().get(j) + "  ");
					tmp++;
				}
				break;
			}
		}
		if(tmp==0)
			System.out.println("线路不存在");
	}
	
	public static void ShortLine(List<ResultModel> resultmodel,String start) {    //输出最短路径以及换乘信息
		
		String last=start;
		int num=0;
		List<String> shortpath = new ArrayList<String>();
		for(int i=resultmodel.size()-1;i>=0;i--) {                //得到最短路径结果，存入shortpath
			if(resultmodel.get(i).getStartStation().equals(last)) {
				shortpath.add(last);
				last=resultmodel.get(i).getLastStation();
				num++;
			}
		}
		
		//判断换乘信息
		String Line1 = null;       
		String nowLine = null;     //当前线路
		int t,t1;
		
		for(int i=0;i<shortpath.size()-1;i++) {
			for(t=0;t<dataProcessing.FinalGraph.size();t++) {
				if(dataProcessing.FinalGraph.get(t).getStationName().equals(shortpath.get(i))) {
					break;
				}
			}
			for(t1=0;t1<dataProcessing.FinalGraph.size();t1++) {
				if(dataProcessing.FinalGraph.get(t1).getStationName().equals(shortpath.get(i+1))) {
					break;
				}
			}
			int h=0;
			for(int m=0;m<dataProcessing.FinalGraph.get(t).getLineName().size();m++) {
				for(int j=0;j<dataProcessing.FinalGraph.get(t1).getLineName().size();j++) {
					//找到当前栈和下一站的共同的线路，存入Line1
					if(dataProcessing.FinalGraph.get(t).getLineName().get(m).equals(dataProcessing.FinalGraph.get(t1).getLineName().get(j))) {
						if(i==0) {
							nowLine=dataProcessing.FinalGraph.get(t).getLineName().get(m);
							Line1=nowLine;
							System.out.println();
							System.out.println(nowLine);
							System.out.print(shortpath.get(i)+"   ");
						}
						else
							Line1=dataProcessing.FinalGraph.get(t).getLineName().get(m);
						h=1;
						break;
					}
				}
				if(h==1)
					break;
			}
			if(nowLine.equals(Line1)) {  //当前线路与后两站的线路相比较
				System.out.print(shortpath.get(i+1)+"   ");
			}
			else {
				nowLine=Line1;
				System.out.println("(换乘)");
				System.out.println(nowLine);
				System.out.print(shortpath.get(i+1)+"   ");
			}
		}
		
		
		
		System.out.println();
		System.out.println("共"+num+"站");
	}
	
	public static List<ResultModel> BFS(String start,String end) {
		List<ResultModel> resultmodel = new ArrayList<ResultModel>();
		int flag=0;
		for(int i=0;i<dataProcessing.FinalGraph.size();i++) {
			if(end.equals(dataProcessing.FinalGraph.get(i).getStationName())) {
				flag=1;
				break;
			}
		}
		if(flag==0){
			 System.out.print("终点站不存在");//判断终点站是否存在
			 return resultmodel;
		}
		flag=0;
		for(int i=0;i<dataProcessing.FinalGraph.size();i++) {
			if(start.equals(dataProcessing.FinalGraph.get(i).getStationName())) {
				flag=1;
				break;
			}
		}
		if(flag==0){
			System.out.print("起点站不存在");//判断起点站是否存在
			 return resultmodel;
		}
		if(end.equals(start)) {
			System.out.print("你已到达终点站");
	         return resultmodel;
		}
		
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		
		String last=null;
		
		ResultModel result = new ResultModel();        //结果类result存储BFS查找过的信息
		
		result.setLastStation(last);
		result.setStartStation(start);
		resultmodel.add(result);
		
		while(!queue.isEmpty()) {
			String tmp = queue.poll();  //队列头
			if(tmp.equals(end)) {       //当tmp是终站后跳出循环
				break;
			}
			
			int i = 0;
			for(i=0;i<dataProcessing.FinalGraph.size();i++) {      //找点站点
				if(tmp.equals(dataProcessing.FinalGraph.get(i).getStationName())) {
					break;
				}
			}
			for(int j=0;j<dataProcessing.FinalGraph.get(i).getStations().size();j++) {   //该站点的相邻战点
				int p=0;
				for(int t=0;t<resultmodel.size();t++) {   //判断站点是否有入过队列
					if(dataProcessing.FinalGraph.get(i).getStations().get(j).equals(resultmodel.get(t).getStartStation())){
						p=1;
						break;
					}
				}
				if(p==0) {      
					queue.add(dataProcessing.FinalGraph.get(i).getStations().get(j));
					//将找到的临站放入结果集result，然后压入结果类resultmodel
					result = new ResultModel();
					result.setLastStation(tmp);
					result.setStartStation(dataProcessing.FinalGraph.get(i).getStations().get(j));
					resultmodel.add(result);
					last=tmp;
				}
				
			}
			
		}
		
		return resultmodel;
	}

}
