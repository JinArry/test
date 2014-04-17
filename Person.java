
public class Person {
	private String user_Id;
	private String brand_Id;
	private double score;
	public Person() {
	}
	public Person(String user_Id,String brand_Id,double score) {
		this.user_Id=user_Id;
		this.brand_Id=brand_Id;
		this.score=score;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public String getBrand_Id() {
		return brand_Id;
		
	}
	public double getScore() {
		return score;
	}
	public String toString() {
		return user_Id+","+brand_Id+","+score;
		
	}
	

}
