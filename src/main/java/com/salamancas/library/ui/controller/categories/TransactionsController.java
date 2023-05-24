package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.Copy;
import com.salamancas.library.model.User;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
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
    private TableColumn<Copy, String> copyPublisher;
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
    private TextField txfCopiesSearchBar;
    @FXML
    private TextField txfUsersSearchBar;

    Options options;
    SQLUtils sqlUtils;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        options = Options.getInstance();
        sqlUtils = SQLUtils.getInstance();

        initCopies();
        initUsers();
    }

    private void initCopies() {
        copySerial.setCellValueFactory(data -> data.getValue().serialProperty());
        copyTitle.setCellValueFactory(data -> data.getValue().titleProperty());
        copyPublisher.setCellValueFactory(data -> data.getValue().publisherProperty());
        copyStatus.setCellValueFactory(data -> data.getValue().statusProperty());

        ResultSet rs = sqlUtils.exequteSelectQuery("""
				select COPY.COPY_ID, B.BOOK_ID, P.PUBLISHER_ID, COPY_SERIAL_NUMBER, BOOK_TITLE, PUBLISHER_NAME, COPY_ISBN, DATE_FROM, DATE_TO
				from COPY
				inner join BOOK B on B.BOOK_ID = COPY.BOOK_ID
				inner join PUBLISHER P on P.PUBLISHER_ID = COPY.PUBLISHER_ID
				left join BORROW on BORROW.COPY_ID = COPY.COPY_ID;""");

        ObservableList<Copy> list = FXCollections.observableArrayList(Copy.fromResultSet(rs));
        FilteredList<Copy> filteredList = new FilteredList<>(list);
        filteredList.setPredicate(data -> true);

        txfCopiesSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
            if(newValue.equals("")) {
                return true;
            }
            return data.getTitle().toLowerCase().contains(newValue.toLowerCase())
                    || data.getPublisher().toLowerCase().contains(newValue.toLowerCase())
                    || data.getSerial().toLowerCase().contains(newValue.toLowerCase())
                    || data.getStatus().toLowerCase().contains(newValue.toLowerCase());
        }));

        tblCopies.setItems(filteredList);
    }

    private void initUsers(){
        name.setCellValueFactory(data -> data.getValue().nameProperty());
        surname.setCellValueFactory(data -> data.getValue().surnameProperty());
        type.setCellValueFactory(data -> data.getValue().typeProperty());
        schoolClass.setCellValueFactory(data -> data.getValue().schoolClassProperty());

        ObservableList<User> list = FXCollections.observableArrayList(User.fromResultSet(sqlUtils.exequteSelectQuery("""
				select USER.USER_ID, SIC.CLASS_ID, HT.CLASS_ID, T2.TYPE_ID,
				       USER_NAME, USER_SURNAME, USER_BIRTH_DATE, USER_ADDRESS, TOW.TOWN_NAME, TYPE_NAME, Y.YEAR_NAME, Y2.YEAR_NAME, C.CLASS_INDEX, C2.CLASS_INDEX
				from USER
				left join STUDENT_IN_CLASS SIC on USER.USER_ID = SIC.USER_ID
				left join CLASS C on C.CLASS_ID = SIC.CLASS_ID
				left join HOMEROOM_TEACHER HT on USER.USER_ID = HT.USER_ID
				left join CLASS C2 on C2.CLASS_ID = HT.CLASS_ID
				left join TYPE_OF_USER TOU on USER.USER_ID = TOU.USER_ID
				left join TYPE T2 on T2.TYPE_ID = TOU.TYPE_ID
				left join YEAR Y on Y.YEAR_ID = C.YEAR_ID
				left join YEAR Y2 on Y2.YEAR_ID = C2.YEAR_ID
				left join TOWN_OF_USER TOOU on TOOU.USER_ID = USER.USER_ID
				left join TOWN TOW on TOW.TOWN_ID = TOOU.TOWN_ID
				where HT.DATE_TO is null and SIC.DATE_TO is null and TOU.DATE_TO is null and T2.TYPE_ID != 4;""")));
        FilteredList<User> filteredList = new FilteredList<>(list);
        filteredList.setPredicate(data -> true);

        txfUsersSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
            if(newValue.equals("")) {
                return true;
            }
            return data.getName().toLowerCase().contains(newValue.toLowerCase())
                    || data.getSurname().toLowerCase().contains(newValue.toLowerCase())
                    || data.getType().toLowerCase().contains(newValue.toLowerCase())
                    || data.getSchoolClass().toLowerCase().contains(newValue.toLowerCase());
        }));

        tblUsers.setItems(filteredList);
    }
}
