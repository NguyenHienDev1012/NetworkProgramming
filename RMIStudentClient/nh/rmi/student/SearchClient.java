package nh.rmi.student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import nh.rmi.student.*;

public class SearchClient {
	static BufferedReader buffread;

	public static void main(String[] args) throws RemoteException,
			NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1999);
		ISearch search = (ISearch) reg.lookup("searchImpl");
		while (true) {

			System.out.println("Please type your requires!");
			System.out.println("1. FindByName");
			System.out.println("2. FindLessAge");
			System.out.println("3. FindLessScore");

			buffread = new BufferedReader(new InputStreamReader(System.in));
			String line;
			try {
				line = buffread.readLine();
				String[] analysis = line.split(" ");
				String keyword = analysis[0];
				String value = analysis[1];
				if (keyword.equalsIgnoreCase("FindByName")) {
					String result = search.findByName(value);
					System.out.println(result);
				}
				if (keyword.equalsIgnoreCase("FindLessAge")) {
					int age = Integer.parseInt(value);
					String result = search.findLessage(age);
					System.out.println(result);
				}
				if (keyword.equalsIgnoreCase("FindLessScore")) {
					double score = Double.parseDouble(value);
					String result = search.findLessScore(score);
					System.out.println(result);
				}
				if (keyword.equalsIgnoreCase(value)) {
					System.out.println("Quit cai qq");
			
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
