package hu.florentory.main.fx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.florentory.main.dto.ProductResponse;
import hu.florentory.main.fx.model.ProductTableModel;
import hu.florentory.main.service.ProductService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

@Component
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

    @Autowired
    private ProductService productService;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            setupColumns();
            loadData();
            setupSearch();
            chatSendButton.setOnAction(e -> sendChat());
        });
    }

    private void setupColumns() {
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        colAction.setCellFactory(getActionCellFactory());
    }

    private void loadData() {
        List<ProductResponse> responses = productService.getAllProduct();
        masterData.clear();
        for (ProductResponse r : responses) {
            ProductTableModel model = new ProductTableModel(
                    r.getId(),
                    r.getName(),
                    r.getQuantity(),
                    r.getPrice(),
                    r.getCategoryName()
            );
            masterData.add(model);
        }
        productTable.setItems(masterData);
        updateInStockCount();
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

                // Fontos: újra be kell állítani a művelet oszlopot is
                colAction.setCellFactory(getActionCellFactory());
            }
        });
    }

    private void sendChat() {
        String question = chatInput.getText();
        if (!question.isBlank()) {
            chatOutput.appendText("Kérdés: " + question + "\n");
            chatOutput.appendText("Válasz: Itt lesz a válasz...\n\n");
            chatInput.clear();
        }
    }

    private void updateInStockCount() {
        int total = masterData.stream().mapToInt(ProductTableModel::getQuantity).sum();
        inStockLabel.setText(total + " készleten");
    }

    private Callback<TableColumn<ProductTableModel, Void>, TableCell<ProductTableModel, Void>> getActionCellFactory() {
        return param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Törlés");

            {
                deleteBtn.setOnAction(event -> {
                    ProductTableModel product = getTableView().getItems().get(getIndex());
                    getTableView().getItems().remove(product);
                    masterData.remove(product);
                    updateInStockCount();
                    chatOutput.appendText("Törölve: " + product.getName() + "\n");

                    // TODO: productService.deleteProductById(product.getId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        };
    }
}





