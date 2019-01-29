package Game;

import java.io.IOException;

/* Gurveer Singh Chahal
 * Dec. 19, 2018
 * Main
 */

public class Main {

	public static void main(String[] args) throws IOException {
		Instructions i = new Instructions("");
		String line = i.getInstruction();
		new startup_Menu(line);
		
	}

}
