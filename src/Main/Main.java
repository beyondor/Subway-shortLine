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
		
		System.out.println("ѡ��1.��ѯ·�ߣ�2.��ѯ����յ����·��");
		int option1 = input.nextInt();
		while(option1!=1&&option1!=2) {
			System.out.println("ѡ��1.��ѯ·�ߣ�2.��ѯ����յ����·��");
			option1 = input.nextInt();
		}
		if(option1==1) {
			System.out.println("������·������");
			String name = input.next();
			//��ѯ·��
			searchLineData(name);
		}
		else if(option1==2) {
			System.out.print("��������㣺");
			String start = input.next();
			System.out.print("�������յ㣺");
			String end = input.next();
			List<ResultModel> resultmodel = BFS(end,start);
			if(resultmodel!=null)
				ShortLine(resultmodel,start);
		}
		input.close();
		
	}	
	
	public static void searchLineData(String name) {         //������·���Ʋ�ѯվ��
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
			System.out.println("��·������");
	}
	
	public static void ShortLine(List<ResultModel> resultmodel,String start) {    //������·���Լ�������Ϣ
		
		String last=start;
		int num=0;
		List<String> shortpath = new ArrayList<String>();
		for(int i=resultmodel.size()-1;i>=0;i--) {                //�õ����·�����������shortpath
			if(resultmodel.get(i).getStartStation().equals(last)) {
				shortpath.add(last);
				last=resultmodel.get(i).getLastStation();
				num++;
			}
		}
		
		//�жϻ�����Ϣ
		String Line1 = null;       
		String nowLine = null;     //��ǰ��·
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
					//�ҵ���ǰջ����һվ�Ĺ�ͬ����·������Line1
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
			if(nowLine.equals(Line1)) {  //��ǰ��·�����վ����·��Ƚ�
				System.out.print(shortpath.get(i+1)+"   ");
			}
			else {
				nowLine=Line1;
				System.out.println("(����)");
				System.out.println(nowLine);
				System.out.print(shortpath.get(i+1)+"   ");
			}
		}
		
		
		
		System.out.println();
		System.out.println("��"+num+"վ");
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
			 System.out.print("�յ�վ������");//�ж��յ�վ�Ƿ����
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
			System.out.print("���վ������");//�ж����վ�Ƿ����
			 return resultmodel;
		}
		if(end.equals(start)) {
			System.out.print("���ѵ����յ�վ");
	         return resultmodel;
		}
		
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(start);
		
		String last=null;
		
		ResultModel result = new ResultModel();        //�����result�洢BFS���ҹ�����Ϣ
		
		result.setLastStation(last);
		result.setStartStation(start);
		resultmodel.add(result);
		
		while(!queue.isEmpty()) {
			String tmp = queue.poll();  //����ͷ
			if(tmp.equals(end)) {       //��tmp����վ������ѭ��
				break;
			}
			
			int i = 0;
			for(i=0;i<dataProcessing.FinalGraph.size();i++) {      //�ҵ�վ��
				if(tmp.equals(dataProcessing.FinalGraph.get(i).getStationName())) {
					break;
				}
			}
			for(int j=0;j<dataProcessing.FinalGraph.get(i).getStations().size();j++) {   //��վ�������ս��
				int p=0;
				for(int t=0;t<resultmodel.size();t++) {   //�ж�վ���Ƿ����������
					if(dataProcessing.FinalGraph.get(i).getStations().get(j).equals(resultmodel.get(t).getStartStation())){
						p=1;
						break;
					}
				}
				if(p==0) {      
					queue.add(dataProcessing.FinalGraph.get(i).getStations().get(j));
					//���ҵ�����վ��������result��Ȼ��ѹ������resultmodel
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
