package hr.java.vjezbe;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.entitet.Metode;
import hr.java.vjezbe.entitet.MetodeDatoteka;
import hr.java.vjezbe.entitet.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerStudent implements Initializable {

	public static final String STUDENTI = "dat\\student.txt";

	@FXML
	private TableView<Student> studentTableView;
	@FXML
	private TableColumn<Student, Integer> jmbagColumn;
	@FXML
	private TableColumn<Student, String> imeColumn;
	@FXML
	private TableColumn<Student, String> prezimeColumn;
	@FXML
	private TableColumn<Student, LocalDate> dateColumn;

	@FXML
	private TextField sifraStudentTextField;
	@FXML
	private TextField imeStudentTextField;
	@FXML
	private TextField prezimeStudentTextField;
	@FXML
	private TextField datumStudentTextField;

	MetodeDatoteka metodedatoteka = new MetodeDatoteka();

	List<String> listaStudentaString = metodedatoteka.getTxtAndRetrnListTest(STUDENTI);
	List<Student> listaStudenta = metodedatoteka.fillStudFromFile(listaStudentaString);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		List<Student> formatedDateListStud = new ArrayList<>();

		for (int i = 1; i < listaStudenta.size(); i++ ) {
			Student studentTemp = listaStudenta.get(i);
			studentTemp.getDatumRodenja().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			formatedDateListStud.add(studentTemp);
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Metode.FORMAT_DATE);
		jmbagColumn.setCellValueFactory(new PropertyValueFactory<>("jmbag"));
		imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
		prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("datumRodenja"));

		ObservableList<Student> listaStudosa = FXCollections.observableArrayList(listaStudenta);
		studentTableView.setItems(listaStudosa);


	}
}