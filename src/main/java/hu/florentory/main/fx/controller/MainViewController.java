package hu.florentory.main.fx.controller;

import hu.florentory.main.fx.model.ProductTableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class MainViewController {

    @FXML private Label logoLabel, pageTitle, inStockLabel;
    @FXML private Button dashboardButton, productButton, ordersButton, chatSendButton;
    @FXML private TextField chatInput, globalSearchField;
    @FXML private TextArea chatOutput;
    @FXML private TableView<ProductTableModel> productTable;
    @FXML private TableColumn<ProductTableModel, Long> colId;
    @FXML private TableColumn<ProductTableModel, String> colName;
    @FXML private TableColumn<ProductTableModel, Integer> colQuantity;
    @FXML private TableColumn<ProductTableModel, Double> colPrice;
    @FXML private TableColumn<ProductTableModel, String> colCategory;
    @FXML private TableColumn<ProductTableModel, Void> colAction;

    private final ObservableList<ProductTableModel> masterData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // TODO: oszlopok konfigurálása
        setupColumns();

        // TODO: adat betöltése a backendből
        loadData();

        // TODO: kereső logika
        setupSearch();

        chatSendButton.setOnAction(e -> sendChat());
    }

    private void setupColumns() {
        // Itt állítod be, hogyan töltődjön be az adat az oszlopokba
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
    }

    private void loadData() {
        // Ez majd a Spring backendből tölti a terméklistát
        // Például egy REST hívással vagy közvetlen service hívással (ha bean injektált)
        // Dummy adat egyelőre:
        masterData.addAll(
            new ProductTableModel(1L, "Rózsa", 120, 999.99, "Virág"),
            new ProductTableModel(2L, "Tulipán", 55, 599.49, "Virág")
        );
        productTable.setItems(masterData);
    }

    private void setupSearch() {
        globalSearchField.setOnKeyReleased((KeyEvent e) -> {
            String filter = globalSearchField.getText().toLowerCase().trim();
            if (filter.isEmpty()) {
                productTable.setItems(masterData);
            } else {
                ObservableList<ProductTableModel> filtered = masterData.filtered(
                    product -> product.getName().toLowerCase().contains(filter)
                );
                productTable.setItems(filtered);
            }
        });
    }

    private void sendChat() {
        String question = chatInput.getText();
        if (!question.isBlank()) {
            chatOutput.appendText("Kérdés: " + question + "\n");
            // TODO: válasz OpenAI-tól vagy Spring MCP-től
            chatOutput.appendText("Válasz: Itt lesz a válasz...\n\n");
            chatInput.clear();
        }
    }
}


