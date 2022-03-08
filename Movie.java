
public class Movie extends Media {
	private String rate;

	public Movie(String code, String title, int no_copies, String rate) {
		super(code, title, no_copies);
		this.rate = rate;
	}

	public Movie() {

	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}


	@Override
	public String toString() {
		return super.toString() + "- Rate: " + rate;
	}
}
