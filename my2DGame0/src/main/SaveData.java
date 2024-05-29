package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class SaveData {
	
	GamePanel gp;
	public SaveData(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		try {
			FileWriter fw = new FileWriter("saveData.txt");
			fw.write("" +gp.maxWave);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("saveData.txt"));
			
			try {
				String s = br.readLine();
				gp.maxWave = Integer.parseInt(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
			br.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

