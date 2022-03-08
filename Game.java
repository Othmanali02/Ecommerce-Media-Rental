
public class Game extends Media {

	private double weight;
	
	public Game(String code, String title, int no_copies, double weight) {
		super(code, title, no_copies);
		this.weight = weight;
	}

	public Game() {

	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return super.toString() + " - Weight: " + weight;
	}
}
