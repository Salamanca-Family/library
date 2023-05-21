package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.Copy;
import com.salamancas.library.model.User;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    @FXML
    private TableView<Copy> tblCopies;
    @FXML
    private TableColumn<Copy, String> copySerial;
    @FXML
    private TableColumn<Copy, String> copyTitle;
    @FXML
    private TableColumn<Copy, String> copyStatus;
    @FXML
    private TableView<User> tblUsers;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> schoolClass;
    @FXML
    private TextField txfBookSearchBar;
    Options options;

    SQLUtils sqlUtils;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        options = Options.getInstance();
        sqlUtils = SQLUtils.getInstance();

    }
}
