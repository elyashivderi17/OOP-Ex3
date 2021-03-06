package Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Geom.Point3D;

public class Game {
	private ArrayList<Packman>arr=new ArrayList<>();
	private ArrayList<Fruit> array=new ArrayList<>();
	public Game(ArrayList<Packman>arr,ArrayList<Fruit> array) {
		this.arr=arr;	
		this.array=array;
	}
	public Game() {
		arr=new ArrayList<>();
		array=new ArrayList<>();
	}
	public Game(Game g) {
		arr=g.arr;
		array=g.array;
	}
	public String toString() {
		String s="Type,id,Lat,Lon,Alt,Speed/Weight,Radius,"+arr.size()+","+array.size()+"\n";
		for(int i=0;i<arr.size();i++) {
			s+="P,"+arr.get(i).toString()+"\n";
		}
		for(int i=0;i<array.size();i++) {
			s+="F,"+array.get(i).toString()+"\n";
		}
		return s;
	}
	public static void main(String[] args) {
//		Packman p=new Packman(0,4, 2, 4, 4, 2);
//		Packman p2=new Packman(1,4, 2, 4, 4, 2);
//		Fruit f=new Fruit(0, 6, 6, 6, 6);
//		Fruit f2=new Fruit(1, 4, 6, 2, 6);
//		ArrayList<Packman>arr=new ArrayList<>();
//		ArrayList<Fruit> array=new ArrayList<>();
//		arr.add(p);
//		arr.add(p2);
//		array.add(f);
//		array.add(f2);
//		Game g=new Game(arr, array);
//		System.out.println(g.toString());
		load("C:\\Users\\barge\\Desktop\\����� ����� ���� 3\\data\\game_1543684662657.csv");
	}
	public static Game load(String CsvFile) 
	{
		String line = "";
		String cvsSplitBy = ",";
		Game g=new Game();
		try (BufferedReader br = new BufferedReader(new FileReader(CsvFile))) 
		{
			line=br.readLine();
			while ((line = br.readLine()) != null) 
			{
				String[] userInfo = line.split(cvsSplitBy);
				if(userInfo[0].equals("P")) {
					g.arr.add(new Packman(Integer.parseInt(userInfo[1]), new Point3D(userInfo[2]+","+userInfo[3]+","+userInfo[4]),Double.parseDouble(userInfo[5]),Double.parseDouble(userInfo[6])));
				}
				else if(userInfo[0].equals("F"))
					g.array.add(new Fruit(Integer.parseInt(userInfo[1]), new Point3D(userInfo[2]+","+userInfo[3]+","+userInfo[4]),Double.parseDouble(userInfo[5])));
			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		save(g);
		return g;//����� �������� ������ ����
	}
	public static void save(Game g) {
		//String fileName="game"+java.time.LocalDateTime.now()+".csv";
		//fileName.replaceAll(":",".");
		//System.out.println(fileName);
		String fileName="game.csv";
		String newfilepath="data\\"+fileName;
		PrintWriter pw=null;
		try 
		{
			pw = new PrintWriter(new File(newfilepath));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		pw.write(g.toString());
		pw.close();
		System.out.println("saved");
	}
}
