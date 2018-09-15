import java.util.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.*;
import java.io.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

class ScannerInput {
	public static void main(String[] args) throws IOException,
		AWTException, InterruptedException {
		// Scanner scanner = new Scanner(System.in);
		
		// String input = "";
		// while (input != "bye") {
		// 	input = scanner.nextLine();
		// 	System.out.println(input);
		// }
	
		// String command = "notepad.exe";
		// Runtime run = Runtime.getRuntime();
		// run.exec(command);

    // Get screen dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    // Create an instance of Robot class
    Robot robot = new Robot();

		// System.out.println(screenSize.getWidth());
		// System.out.println(screenSize.getHeight());

		// Move mouse to top of screen and click
    robot.mouseMove((int)(screenSize.getWidth()/2), 0);
    robot.mousePress(InputEvent.BUTTON1_MASK);
    robot.mouseRelease(InputEvent.BUTTON1_MASK);
    // Thread.sleep(500);
		
		// Coordinates of textbox and click
    robot.mouseMove(600, 375);
    // robot.mousePress(InputEvent.BUTTON1_MASK);
    // robot.mouseRelease(InputEvent.BUTTON1_MASK);

    // Type out name that was just scanned (hardcoded for now)
    // robot.keyPress(KeyEvent.VK_CONTROL);
    // robot.keyPress(KeyEvent.VK_A);
    // robot.keyRelease(KeyEvent.VK_A);
    // robot.keyRelease(KeyEvent.VK_CONTROL);

    // Put string input into clipboard and paste it
  //   String text = "Jonathan Derpy";
		// StringSelection stringSelection = new StringSelection(text);
		// Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		// clipboard.setContents(stringSelection, stringSelection);

		// robot.keyPress(KeyEvent.VK_CONTROL);
		// robot.keyPress(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_V);
		// robot.keyRelease(KeyEvent.VK_CONTROL);

		// Print the label
		// robot.keyPress(KeyEvent.VK_CONTROL);
  //   robot.keyPress(KeyEvent.VK_P);
  //   robot.keyRelease(KeyEvent.VK_P);
  //   robot.keyRelease(KeyEvent.VK_CONTROL);

  //   robot.keyPress(KeyEvent.VK_ENTER);
  //   robot.keyRelease(KeyEvent.VK_ENTER);
	}
}