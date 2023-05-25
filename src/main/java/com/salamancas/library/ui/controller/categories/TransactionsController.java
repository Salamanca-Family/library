package com.salamancas.library.ui.controller.categories;

import com.salamancas.library.model.legacy.Copy;
import com.salamancas.library.model.persistence.table.User;
import com.salamancas.library.util.Options;
import com.salamancas.library.util.sql.HibernateUtil;
import com.salamancas.library.util.sql.SQLUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
    private ListView<User> lstUsers;
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
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ObservableList<User> list = FXCollections.observableArrayList(session.createQuery("from User", User.class).list());
        list.forEach(user -> {

        });
        session.close();

        FilteredList<User> filteredList = new FilteredList<>(list);
        filteredList.setPredicate(data -> true);

        txfUsersSearchBar.textProperty().addListener((observableValue, oldValue, newValue) -> filteredList.setPredicate(data -> {
            if(newValue.equals("")) {
                return true;
            }
			return data.toString().toLowerCase().contains(newValue.toLowerCase());
        }));

        lstUsers.setItems(filteredList);
    }
}
