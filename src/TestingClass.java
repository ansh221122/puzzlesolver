import java.util.HashMap;
import java.util.Map;



public class TestingClass {

	static class Tree{
		
		String content;
		Tree child[]=new Tree[500];
		
		private Map<Integer,String> possibility=new HashMap<Integer,String>();
		public String getPossibility(Integer key) {
			return possibility.get(key);
		}
		
		public Map<Integer, String> getPossibilityMap() {
			return possibility;
		}
		public void setPossibility(Integer key, String value) {
			possibility.put(key, value);
		}
		boolean isEndofWord;
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public boolean isEndofWord() {
			return isEndofWord;
		}
		public void setEndofWord(boolean isEndofWord) {
			this.isEndofWord = isEndofWord;
		}

		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = new int[500];
		Tree tree = new Tree();
		for(int t:test)
		{
			System.out.println(t);
		}

	}

}
