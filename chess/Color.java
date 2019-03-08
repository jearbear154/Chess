package chess;

//denotes the color of the chess pieces
public enum Color {
	W,B;

	@Override
	public String toString() {
		return (this == W)? "W" : "B";
	}
}
