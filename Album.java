
public class Album extends Media {
	private String artist;
	private String songs;

	public Album(String code, String title, int no_copies, String artist, String songs) {
		super(code, title, no_copies);
		this.artist = artist;
		this.songs = songs;
	}

	public Album() {

	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSongs() {
		return songs;
	}

	public void setSongs(String songs) {
		this.songs = songs;
	}

	@Override
	public String toString() {
		return super.toString() + "- Artist: " + artist + " - Songs: " + songs;
	}
}
