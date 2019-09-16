package hr.java.vjezbe;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.entitet.Ispit;
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

public class ControllerIspit implements Initializable {

	public static final String ISPITI = "dat\\ispit.txt";

	@FXML
	private TableView<Ispit> ispitTableView;
	@FXML
	private TableColumn<Ispit, String> predmetColumn;
	@FXML
	private TableColumn<Ispit, String> studentColumn;
	@FXML
	private TableColumn<Ispit, Integer> ocjenaColumn;
	@FXML
	private TableColumn<Ispit, LocalDateTime> vrijemeIspitaColumn;

	@FXML
	private TextField predmetTextField;
	@FXML
	private TextField profesorTextField;
	@FXML
	private TextField ocjenaTextField;
	@FXML
	private TextField vrijemeIspitaTextField;

	MetodeDatoteka metodeDatoteka = new MetodeDatoteka();
	List<String> listaIspitaString = metodeDatoteka.getTxtAndRetrnListTest(ISPITI);
	List<String> listaProfString = metodeDatoteka.getTxtAndRetrnListTest(ControllerProfesor.PROFESORI);
	List<String> listaPredString = metodeDatoteka.getTxtAndRetrnListTest(ControllerPredmet.PREMDETI);
	List<String> listaStudString = metodeDatoteka.getTxtAndRetrnListTest(ControllerStudent.STUDENTI);

	List<Profesor> listaProfesora = metodeDatoteka.fillProfFromFile(listaProfString);
	List<Student> listaStudenta = metodeDatoteka.fillStudFromFile(listaStudString);
	List<Predmet> listaPredmeta = metodeDatoteka.fillPredFromFile(listaPredString, listaProfesora, listaStudenta);
	List<Ispit> listaIspita = metodeDatoteka.fillIspitFromFile(listaIspitaString, listaPredmeta, listaStudenta);

	ObservableList<Ispit> listaIspitaObs = FXCollections.observableArrayList(listaIspita);

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		predmetColumn.setCellValueFactory(new PropertyValueFactory<>("predmet"));
		studentColumn.setCellValueFactory(new PropertyValueFactory<>("student"));
		ocjenaColumn.setCellValueFactory(new PropertyValueFactory<>("ocjena"));
		vrijemeIspitaColumn.setCellValueFactory(new PropertyValueFactory<>("datumIVrijeme"));

		ispitTableView.setItems(listaIspitaObs);

	}

}
