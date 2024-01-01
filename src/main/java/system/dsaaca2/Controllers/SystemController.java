package system.dsaaca2.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import system.dsaaca2.Datastructures.SillyList;
import system.dsaaca2.Main;
import system.dsaaca2.Models.Game;
import system.dsaaca2.Models.GamePort;
import system.dsaaca2.Models.GamesMachine;
import system.dsaaca2.Models.ListedTogether;
import system.dsaaca2.utils.Utilities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static system.dsaaca2.Controllers.GameAPI.*;

public class SystemController implements Initializable {
    public static SystemController sysControll = new SystemController();
    public TextField searchMachines;
    public ListView<Object> searchResults = new ListView<>();
    //TODO set listview as ? instead of object for easier type checking for new methods below
    public TextField searchGamesAndPorts;
    public ComboBox<String> machineFilter;
    public ComboBox<String> machineSort;
    public ComboBox<String> gameAndPortFilter;
    public ComboBox<String> gameAndPortSort;
    public ImageView imageViewer;
    public ImageView gameImage;
    public Label gameName = new Label();
    public Label gameYearLabel = new Label();

    public void switchSceneBack(ActionEvent actionEvent) {
        Main.mainStage.setScene(Main.gameScene);
    }

    public void populateSearchFiltersAndSorts() {
        /*Adds filtering/sorting options*/
        machineFilter.getItems().addAll("Name", "Description", "Year", "Manufacturer", "Type", "Media");
        machineSort.getItems().addAll("Price Highest ---> Lowest ", "Price Lowest ---> Highest", "Oldest ---> Newest");
        gameAndPortFilter.getItems().addAll("Game Name", "Games by Publisher", "Games by Description", "Developers", "Games Machine", "Year");
        gameAndPortSort.getItems().addAll("A ---> Z", "Newest ---> Oldest ", "Oldest ---> Newest");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sysControll = this;
        populateSearchFiltersAndSorts();
    }

    /*A list that stores found results from searches and is then added to the listview
     * for the purpose of sorting later on (maybe)*/

    SillyList<GamesMachine> machineSearchResults = new SillyList<>();
    SillyList<ListedTogether> gameAndPortsResults = new SillyList<>();

    public void searchMachines() {
        searchResults.getItems().clear();
        machineSearchResults.clear();
        String filterChoice = machineFilter.getSelectionModel().getSelectedItem();
        String search = searchMachines.getText().toLowerCase();
        boolean added = false;
        if (!search.isEmpty()) {
            if (filterChoice != null) {
                if ("Name".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getName().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm);
                            added = true;
                        }
                    }
                } else if ("Description".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getDescription().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(gm);
                            added = true;
                        }
                    }
                } else if ("Year".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (String.valueOf(gm.getYear()).startsWith(search)) {
                            searchResults.getItems().add(gm);
                            added = true;
                        }
                    }
                } else if ("Manufacturer".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getManufacturer().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm);
                            added = true;
                        }
                    }
                } else if ("Type".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getType().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm);
                            added = true;
                        }
                    }
                } else if ("Media".equalsIgnoreCase(filterChoice)) {
                    for (GamesMachine gm : allMachines) {
                        if (gm.getMedia().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(gm);
                            added = true;
                        }
                    }
                }
                if (!added)
                    Utilities.showWarningAlert("OOPS", "NO RESULTS FOUND");
            } else
                Utilities.showWarningAlert("OOPS", "PICK A FILTER PLEASE");
        } else
            Utilities.showWarningAlert("OOPS", "ENTER SOMETHING TO SEARCH FOR");
    }

    public void searchGamesAndPorts() {
        searchResults.getItems().clear();
        gameAndPortsResults.clear();
        String filterChoice = gameAndPortFilter.getSelectionModel().getSelectedItem();
        String search = searchGamesAndPorts.getText().toLowerCase();
        boolean added = false;

        if (!search.isEmpty()) {
            if (filterChoice != null) {
                if ("game name".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getName().toLowerCase().startsWith(search.toLowerCase())|| g.getName().toLowerCase().contains(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                        /*all ports made for that machine*/
                        for (GamePort p : g.getPorts()) {
                            if (p.getGameName().toLowerCase().startsWith(search.toLowerCase())|| p.getGameName().toLowerCase().contains(search.toLowerCase())) {
                                searchResults.getItems().add(p.toString());
                                added = true;
                            }
                        }
                    }
                } else if ("games by publisher".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getPublisher().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                } else if ("year".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (String.valueOf(g.getYear()).startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                        for (GamePort p : g.getPorts()) {
                            if (String.valueOf(p.getYear()).startsWith(search)) {
                                searchResults.getItems().add(p.toString());
                                added = true;
                            }
                        }
                    }
                } else if ("developers".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getDevelopers().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                    for (GamePort p : allGamePorts) {
                        if (p.getDevelopers().startsWith(search)) {
                            searchResults.getItems().add(p.toString());
                            added = true;
                        }
                    }
                } else if ("games by description".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getDescription().toLowerCase().startsWith(search.toLowerCase())) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                } else if ("games machine".equalsIgnoreCase(filterChoice)) {
                    for (Game g : allGames) {
                        if (g.getGamesMachine().getName().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(g.toString());
                            added = true;
                        }
                    }
                    for (GamePort p : allGamePorts) {
                        if (p.getNewPortName().toLowerCase().startsWith(search)) {
                            searchResults.getItems().add(p.toString());
                            added = false;
                        }
                    }
                }
                if (!added)
                    Utilities.showWarningAlert("OOPS", "NO RESULTS FOUND");
            } else
                Utilities.showWarningAlert("FILTER NOT SELECTED", "PLEASE SELECT A FILTER");
        } else
            Utilities.showWarningAlert("EMPTY", "THE SEARCH FIELD IS EMPTY, PLEASE ENTER SOMETHING TO SEARCH");
    }

    public void drillDownGameMachine(){
        GamesMachine gm = (GamesMachine) searchResults.getSelectionModel().getSelectedItem();
                return;
                 /* Going to have it that when u double click drill down will happen
                 * Three different hashtables will be used here
                 In a instance of Game machine it will drill down once to games
                 * in an instance of Game it will drill Once to ports*/


    }

    public void selectForDetails() throws IOException {
        /*adding other parts tomorrow
         * IF an instance of Game is chosen from search, pop up shows with all details
         * of a game and its pic*/

        Object selected = searchResults.getSelectionModel().getSelectedItem();
        System.out.println(selected.getClass());
        if (selected == null) {
            // Handle the case when nothing is selected
            return;
        }

        if (selected instanceof Game) {
            showGameDetailsPopup((Game) selected);
                System.out.println(selected.getClass());
        } else {
            Utilities.showWarningAlert("ERR", "ERR");
        }
    }

    public void showGameDetailsPopup(Game selected) throws IOException {
        gameName.setText(selected.getName().toUpperCase());
        Main.viewPopup("/gameViewer.fxml", selected.getName().toUpperCase() + " DETAILS");

    }

}






