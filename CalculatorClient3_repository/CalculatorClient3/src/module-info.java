module CalculatorClient3 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens controller to javafx.graphics, javafx.fxml;
}
