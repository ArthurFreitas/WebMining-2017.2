package me.jhenrique.main;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.jhenrique.manager.TweetManager;
import me.jhenrique.manager.TwitterCriteria;
import me.jhenrique.model.Tweet;



public class Main {
	
	static void generateDates(){
		dates = new ArrayList<String>();
		for(int i = 1; i <= 9; i++){
			dates.add("2017-10-0"+i);
		}
		for(int i = 10; i <= 31; i++){
			dates.add("2017-10-"+i);
			
		}
		for(int i = 1; i <= 9; i++){
			dates.add("2017-11-0"+i);
		}
		for(int i = 10; i <= 30; i++){
			dates.add("2017-11-"+i);
			
		}
		
		
	}


	public static ArrayList<String> dates;
	
	public static void main(String[] args) throws IOException {
		/**
		 * Reusable objects
		 */
		TwitterCriteria criteria = null;
		
		generateDates();
		for(int i = 1; i < dates.size();i++){
			criteria = TwitterCriteria.create()
					.setQuerySearch("iphone x")
					.setSince(dates.get(i-1))
					.setUntil(dates.get(i))
					.setMaxTweets(500);
		
			int id= 0;
			for (Tweet t : TweetManager.getTweets(criteria)){
				Path file = Paths.get("tweets/"+dates.get(i)+"_"+ id++ + ".txt");
				List<String> lines = Arrays.asList(t.getText());
				Files.write(file, lines, Charset.forName("UTF-8"));
			}
		}
		

	}
	
}