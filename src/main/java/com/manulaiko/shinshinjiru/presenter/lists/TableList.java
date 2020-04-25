package com.manulaiko.shinshinjiru.presenter.lists;

import com.manulaiko.shinshinjiru.api.model.dto.MediaListGroup;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Table list.
 * ===========
 *
 * The presenter for the list TableView.
 *
 * @author Manulaiko <manulaiko@gmail.com>
 */
@SuppressWarnings("unchecked")
@Slf4j
public class TableList extends TableView<TableEntry> {
    /**
     * Parses the entries and creates the columns.
     *
     * @param entries Table entries.
     *
     * @return This instance
     */
    public TableList initialize(MediaListGroup entries) {
        this.createColumns();

        super.setItems(
                entries.getEntries()
                       .parallelStream()
                       .map(TableEntry::new)
                       .collect(Collectors.toCollection(FXCollections::observableArrayList))
        );
        super.setPrefSize(960, 450);

        return this;
    }

    /**
     * Creates the columns.
     */
    private void createColumns() {
        super.getColumns().addAll(
                createColumn("Name", .5, TableEntry::getName),
                createColumn("Progress", .125, TableEntry::getProgress),
                createColumn("Score", .125, TableEntry::getScore),
                createColumn("Notes", .25, TableEntry::getNotes)
        );
    }

    /**
     * Creates and returns a table column.
     *
     * @param title    Column text.
     * @param size     Column total size in percentage relative to the table view.
     * @param property Column data property.
     *
     * @return Created cell;
     */
    private TableColumn<TableEntry, String> createColumn(
            String title, double size, Function<TableEntry, StringProperty> property
    ) {
        var col = new TableColumn<TableEntry, String>(title);

        col.prefWidthProperty().bind(super.widthProperty().multiply(size).subtract(5));
        col.setCellValueFactory(c -> property.apply(c.getValue()));

        return col;
    }
}
