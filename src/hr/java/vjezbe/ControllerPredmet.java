package hr.java.vjezbe;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.entitet.MetodeDatoteka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerPredmet implements Initializable {

	public static final String PREMDETI = "dat\\predmet.txt";
	@FXML
	private TableView<Predmet> predmetTableView;
	@FXML
	private TableColumn<Predmet, Integer> sifraColumn;
	@FXML
	private TableColumn<Predmet, String> nazivColumn;
	@FXML
	private TableColumn<Predmet, Integer> ectsColumn;
	@FXML
	private TableColumn<Predmet, String> nositeljColumn;

	@FXML
	private TextField sifraTextField;
	@FXML
	private TextField nazivTextField;
	@FXML
	private TextField ectsBodoviTextField;
	@FXML
	private TextField nositeljTextField;

	MetodeDatoteka metodeDatoteka = new MetodeDatoteka();
	List<String> listaPredString = metodeDatoteka.getTxtAndRetrnListTest(PREMDETI);
	List<String> listaProfString = metodeDatoteka.getTxtAndRetrnListTest(ControllerProfesor.PROFESORI);
	List<String> listaStudString = metodeDatoteka.getTxtAndRetrnListTest(ControllerStudent.STUDENTI);

	List<Profesor> listaProfesora = metodeDatoteka.fillProfFromFile(listaProfString);
	List<Student> listaStudenta = metodeDatoteka.fillStudFromFile(listaStudString);
	List<Predmet> listaPredmeta = metodeDatoteka.fillPredFromFile(listaPredString, listaProfesora, listaStudenta);

	ObservableList<Predmet> listaPredObsr = FXCollections.observableArrayList(listaPredmeta);


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sifraColumn.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		nazivColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
		ectsColumn.setCellValueFactory(new PropertyValueFactory<>("brojEctsBodova"));
		nositeljColumn.setCellValueFactory(new PropertyValueFactory<>("nositelj"));

		predmetTableView.setItems(listaPredObsr);


	}

}
