package controller;

import java.io.IOException;
import java.net.UnknownHostException;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 Calculation steps:
 1. enter digits of first number- f.e. void button0Clicked(ActionEvent event)
 2. create fist number - current number - public void addNumber(String number)
 3. select math operation - calculation Type -  void addAction(ActionEvent event) {calculationSetup("+");;
 4. write current number and calculation type in top left corner of calculator window - 
   public void calculationSetup(String calculationType)
 5. Program selects in method void calculate(ActionEvent event)  in switch corresponding case;
 6. enter  digits of second number  - which will be current number -
 7. method void calculate(ActionEvent event) will print in calculator window(Textfield) the result;
 8. For clear calculator window press 'C' button - void clearTextField(ActionEvent event);
   
 
*/

public class MainWindowController {

	private NetClient netClient;
	private Main main;
	
	public void setNetClient(NetClient netClient) {
		this.netClient = netClient;
	}

	@FXML
	private TextField textField; // calculator window;
	@FXML
	private Text savedNumbers; // expression which appears in top left corner of calculator window;

	private String firstNumber = "";
	private String currentNumber = "";
	private String calculationType;// type of math operation;
	
	@FXML
	void addAction() {
		calculationSetup("+");
	}
	@FXML
	void minusAction() {
		calculationSetup("-");
	}
	@FXML
	void divideAction() {
		calculationSetup("/");
	}
	@FXML
	void multiplicationAction() {
		calculationSetup("*");
	}
	public void calculationSetup(String calculationType) {
		this.calculationType = calculationType;
		firstNumber = currentNumber;
		currentNumber = "";
		savedNumbers.setText(firstNumber + " " + calculationType);
	}
	@FXML
	void calculate() throws UnknownHostException, IOException {
//		

		switch (calculationType) {
		case "+": {		 
			savedNumbers.setText(firstNumber + calculationType + currentNumber + " = ");
			String str = firstNumber + calculationType + currentNumber;
			System.out.println("Wysyłany string to: " + str);
			System.out.println("Sending to socket add expression: " + str);
			String resultString = netClient.sendWritingToStream(str);
			int result = Integer.parseInt(resultString);
			System.out.println("  Add result from server:" + result);
			textField.setText(String.valueOf(result));
			break;
		}
		case "-": {
			savedNumbers.setText(firstNumber + calculationType + currentNumber + " = ");
			String str = firstNumber + calculationType + currentNumber;
			System.out.println("Wysyłany string to: " + str);
			System.out.println("Sending to socket subtraction expression: " + str);
			String result = netClient.sendWritingToStream(str);
			System.out.println("Subtraction result from server:" + result);
			textField.setText(result);
			break;	
		}
		case "/": {
			savedNumbers.setText(firstNumber + calculationType + currentNumber + " = ");
			String str = Double.parseDouble(firstNumber) + calculationType + currentNumber;
			System.out.println("Wysyłany string to: " + str);
			System.out.println("Sending to socket division expression: " + str);
			String resultString = netClient.sendWritingToStream(str);
			double result = Double.parseDouble(resultString);
			System.out.println(" Divison result from server:" + result);
			textField.setText(String.valueOf(result));
			break;
		}
		case "*": {
			savedNumbers.setText(firstNumber + calculationType + currentNumber + " = ");
			String str = firstNumber + calculationType + currentNumber;
			System.out.println("The String to send is: " + str);
			System.out.println("Sending to socket multiplication expression: " + str);
			String result = netClient.sendWritingToStream(str);
			System.out.println("Multiplication result from server:" + result);
			textField.setText(result);
			break;
		}
		}
	}
	@FXML
	void clearTextField() {
		currentNumber = "";
		textField.setText("");
		savedNumbers.setText("");
	}
	@FXML
	void button0Clicked() {
		if (!currentNumber.equals("")) {
			addNumber("0");
		}
	}

	@FXML
	void button1Clicked() {
		addNumber("1");
	}
	@FXML
	void button2Clicked() {
		addNumber("2");
	}

	@FXML
	void button3Clicked() {
		addNumber("3");
	}
	@FXML
	void button4Clicked() {
		addNumber("4");
	}
	@FXML
	void button5Clicked() {
		addNumber("5");
	}
	@FXML
	void button6Clicked() {
		addNumber("6");
	}
	@FXML
	void button7Clicked() {
		addNumber("7");
	}
	@FXML
	void button8Clicked() {
		addNumber("8");
	}
	@FXML
	void button9Clicked() {
		addNumber("9");
	}
	public void updateTextField() {
		textField.setText(currentNumber);
	}
	public void addNumber(String number) {
		currentNumber += number; // creates number which user put by clicking on buttons
		updateTextField();
	}
}
