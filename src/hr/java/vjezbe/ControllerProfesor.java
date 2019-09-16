package hr.java.vjezbe;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.MetodeDatoteka;
import hr.java.vjezbe.entitet.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerProfesor implements Initializable {

	public static final String PROFESORI = "dat\\profesor.txt";

	@FXML
	public TableView<Profesor> profesorTableView;

	@FXML
	public TableColumn<Profesor, Integer> sifraColumn;
	@FXML
	public TableColumn<Profesor, String> imeColumn;
	@FXML
	public TableColumn<Profesor, String> prezimeColumn;
	@FXML
	public TableColumn<Profesor, String> titulaColumn;
	@FXML
	private TextField profesorSifraTextField;
	@FXML
	private TextField profesorImeTextField;
	@FXML
	private TextField profesorPrezimeTextField;
	@FXML
	private TextField profesorTitulaTextField;

	MetodeDatoteka metodeDatoteka = new MetodeDatoteka();
	List<String> listaProfesoraString = metodeDatoteka.getTxtAndRetrnListTest(PROFESORI);
	List<Profesor> listaProfesora = metodeDatoteka.fillProfFromFile(listaProfesoraString);

	ObservableList<Profesor> listaProfObs = FXCollections.observableArrayList(listaProfesora);

	public void fill() {

		//	profesorTableView.getItems().stream().filter(item -> item.getIme().equals(sifraColumn.toString())).findAny().ifPresent(item -> {profesorTableView.getSelectionModel().select(item);profesorTableView.scrollTo(item);});;
		List<Profesor> filtriraniProfesoriLista = new ArrayList<>();
		List<Profesor> filtriraniProfesoriListaPrez = new ArrayList<>();
		if (profesorImeTextField.getText().isEmpty() == false) {
			filtriraniProfesoriLista = listaProfesora.stream()
					.filter(m -> m.getIme().toLowerCase().contains(profesorImeTextField.getText().toString().toLowerCase()))
					.collect(Collectors.toList());

			ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(filtriraniProfesoriLista);
			profesorTableView.setItems(listaProfesora);

		} else {
			filtriraniProfesoriLista = listaProfesora;
			ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(filtriraniProfesoriLista);
			profesorTableView.setItems(listaProfesora);
		}
		if (profesorPrezimeTextField.getText().isEmpty() == false) {
			 filtriraniProfesoriListaPrez = listaProfesora.stream()
					.filter(m -> m.getPrezime().toLowerCase().contains(profesorPrezimeTextField.getText().toString().toLowerCase()))
					.collect(Collectors.toList());

			 ObservableList<Profesor> listaProfesora = FXCollections.observableArrayList(filtriraniProfesoriListaPrez);
				profesorTableView.setItems(listaProfesora);

		}



	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sifraColumn.setCellValueFactory(new PropertyValueFactory<>("sifra"));
		imeColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
		prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));
		titulaColumn.setCellValueFactory(new PropertyValueFactory<>("titula"));

		profesorTableView.setItems(listaProfObs);
		//listaProfesora.stream().forEach(m -> profesorTableView.getItems().add(m));

	}
}
