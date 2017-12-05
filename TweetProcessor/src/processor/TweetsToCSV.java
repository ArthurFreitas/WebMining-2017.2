package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TweetsToCSV {

	private static StringBuilder builder = new StringBuilder();
	private static Map<String,Integer> map = new HashMap<String,Integer>(); 
	private static int TWEETAMMOUNT = 500;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		File[] files = new File("../tweets/hasopinion").listFiles();
		processFiles(files);
		String header = "Date,has_opinion,no_opinion";
		builder.append(header);
		
		map.forEach((k,v) -> {
			builder.append("\n");
			builder.append(k);
			builder.append(",");
			builder.append(v);
			builder.append(",");
			builder.append(TWEETAMMOUNT-v);
			
			/*builder.append("\n");
			builder.append(k);
			builder.append(",");
			builder.append(NOOPINION);
			builder.append(",");
			builder.append(TWEETAMMOUNT-v);*/
		});
		
		try {
			PrintWriter out = new PrintWriter("../data/tweet_data.csv");
			out.print(builder.toString());
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void processFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {				
				processFiles(file.listFiles()); // Calls same method again.				
			} else {				
				String date = file.getName().substring(0, 10);														
				if(map.containsKey(date)) {
					int value = map.get(date);
					map.replace(date, ++value);					
				}else {
					map.put(date, 1);
				}
			}
		}
	}

}
