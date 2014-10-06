package forest.rice.feeld.k.medalist.entity;

public class Medal {
	
	public String name;
	public String id;
	public String family;
	public String phase;
	public String yomi;
	public String product;
	public String frame;
	public String watch;
	public String game;
	public String sort_num;
	
	private Medal() {
	}
	
	public static Medal createMedal(String line){
		String[] str = line.split("\t", 10);
		if(str.length != 10) {
			System.out.println(str);
			return null;
		}
		if(str[0].equals("name")){
			System.out.println(str);
			return null;
		}
		
		Medal medal = new Medal();
		
		medal.name = (str[0] != null) ? str[0] : "";
		medal.id = (str[1] != null) ? str[1] : "";
		medal.family = (str[2] != null) ? str[2] : "";
		medal.phase = (str[3] != null) ? str[3] : "";
		medal.yomi = (str[4] != null) ? str[4] : "";
		medal.product = (str[5] != null) ? str[5] : "";
		medal.frame = (str[6] != null) ? str[6] : "";
		medal.watch = (str[7] != null) ? str[7] : "";
		medal.game = (str[8] != null) ? str[8] : "";
		medal.sort_num = (str[9] != null) ? str[9] : "";
		return medal;
	}

}
