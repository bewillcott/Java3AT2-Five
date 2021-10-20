/*
 *  File Name:    ToDoListController.java
 *  Project Name: Java3AT2-Five
 *
 *  Copyright (c) 2021 Bradley Willcott
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * ****************************************************************
 * Name: Bradley Willcott
 * ID:   M198449
 * Date: 19 Oct 2021
 * ****************************************************************
 */

package com.bewsoftware.tafe.java3.at2.five.view;

import com.bewsoftware.tafe.java3.at2.five.App;
import com.bewsoftware.tafe.java3.at2.five.util.DataSource;
import com.bewsoftware.tafe.java3.at2.five.util.ViewController;
import java.beans.PropertyChangeEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeType;

import static com.bewsoftware.tafe.java3.at2.five.gfx2d.Arrow.build;
import static com.bewsoftware.tafe.java3.at2.five.view.ToDoListController.ArrowStatus.BAD_DROP;
import static com.bewsoftware.tafe.java3.at2.five.view.ToDoListController.ArrowStatus.CLEAR;
import static com.bewsoftware.tafe.java3.at2.five.view.ToDoListController.ArrowStatus.GOOD_DROP;
import static com.bewsoftware.tafe.java3.at2.five.view.ToDoListController.ArrowStatus.NO_DROP;

/**
 * FXML Controller class for the 'ToDoList.fxml' file.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class ToDoListController implements ViewController
{

    private App app;

    @FXML
    private Path arrowPath;

    private int selectedIndex = -1;

    @FXML
    private ListView<String> todoListView;

    @FXML
    private ListView<String> wipListView;

    @Override
    public void setApp(App app)
    {
        this.app = app;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        // NoOp
    }

    @Override
    public void setFocus()
    {
        // NoOp
    }

    @FXML
    private void initialize()
    {
        // Build long and skinny arrow
        arrowPath.getElements().addAll(build(100, 75).getElements());
        arrowPath.setStrokeType(StrokeType.INSIDE);
        arrowPath.setStroke(Color.BLACK);
        arrowPath.setFill(Color.BLACK);

        // Load up the Work To Do ListView
        todoListView.getItems().addAll(DataSource.getData());
    }

    /**
     * Drag was detected, start drag-and-drop gesture.
     *
     * @param event
     */
    @FXML
    private void todoListView_OnDragDetected(MouseEvent event)
    {
        /*
         * allow MOVE transfer mode
         */
        Dragboard db = todoListView.startDragAndDrop(TransferMode.MOVE);

        /*
         * put a string on dragboard
         */
        ClipboardContent content = new ClipboardContent();
        content.putString(todoListView.getSelectionModel().getSelectedItem());
        db.setContent(content);

        selectedIndex = todoListView.getSelectionModel().getSelectedIndex();

        arrowPath.setFill(BAD_DROP.color);
        app.setStatusText("");
        
        event.consume();
    }

    /**
     * The drag-and-drop gesture ended.
     *
     * @param event
     */
    @FXML
    private void todoListView_OnDragDone(DragEvent event)
    {
        /*
         * if the data was successfully moved, clear it
         */
        if (event.getTransferMode() == TransferMode.MOVE)
        {
            todoListView.getItems().remove(selectedIndex);
            app.setStatusText("Drag-N-Drop successful");
        }

             selectedIndex = -1;
       arrowPath.setFill(CLEAR.color);
        event.consume();
    }

    /**
     * Data dropped.
     *
     * @param event
     */
    @FXML
    private void todoListView_OnDragDropped(DragEvent event)
    {
        /*
         * if there is a string data on dragboard, read it and use it
         */
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString())
        {
            success = todoListView.getItems().add(db.getString());
        }

        /*
         * let the source know whether the string was successfully
         * transferred and used
         */
        event.setDropCompleted(success);

        event.consume();
    }

    /**
     * The drag-and-drop gesture entered the source.
     *
     * @param event
     */
    @FXML
    private void todoListView_OnDragEntered(DragEvent event)
    {
        /*
         * show to the user that it is an actual gesture target
         */
        if (event.getGestureSource() != todoListView
                && event.getDragboard().hasString())
        {
            arrowPath.setFill(GOOD_DROP.color);
        } else
        {
            arrowPath.setFill(BAD_DROP.color);
        }

        event.consume();
    }

    /**
     * Mouse moved away, remove the graphical cues.
     *
     * @param event
     */
    @FXML
    private void todoListView_OnDragExited(DragEvent event)
    {
        arrowPath.setFill(NO_DROP.color);
        event.consume();
    }

    /**
     * Data is dragged over the target.
     *
     * @param event
     */
    @FXML
    private void todoListView_OnDragOver(DragEvent event)
    {
        /*
         * accept it only if it is not dragged from the same node
         * and if it has a string data
         */
        if (event.getGestureSource() != todoListView
                && event.getDragboard().hasString())
        {
            /*
             * allow for moving
             */
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    //======================================================================
    /**
     * Drag was detected, start drag-and-drop gesture.
     *
     * @param event
     */
    @FXML
    private void wipListView_OnDragDetected(MouseEvent event)
    {
        /*
         * allow MOVE transfer mode
         */
        Dragboard db = wipListView.startDragAndDrop(TransferMode.MOVE);

        /*
         * put a string on dragboard
         */
        ClipboardContent content = new ClipboardContent();
        content.putString(wipListView.getSelectionModel().getSelectedItem());
        db.setContent(content);

        selectedIndex = wipListView.getSelectionModel().getSelectedIndex();

        arrowPath.setFill(BAD_DROP.color);
        arrowPath.setRotate(180.0);
        app.setStatusText("");

        event.consume();
    }

    /**
     * The drag-and-drop gesture ended.
     *
     * @param event
     */
    @FXML
    private void wipListView_OnDragDone(DragEvent event)
    {
        /*
         * if the data was successfully moved, clear it
         */
        if (event.getTransferMode() == TransferMode.MOVE)
        {
            wipListView.getItems().remove(selectedIndex);
            app.setStatusText("Drag-N-Drop successful");
        }

        selectedIndex = -1;
        arrowPath.setFill(CLEAR.color);
        arrowPath.setRotate(0.0);

        event.consume();
    }

    /**
     * Data dropped.
     *
     * @param event
     */
    @FXML
    private void wipListView_OnDragDropped(DragEvent event)
    {
        /*
         * if there is a string data on dragboard, read it and use it
         */
        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString())
        {
            success = wipListView.getItems().add(db.getString());
        }

        /*
         * let the source know whether the string was successfully
         * transferred and used
         */
        event.setDropCompleted(success);

        event.consume();
    }

    /**
     * The drag-and-drop gesture entered the source.
     *
     * @param event
     */
    @FXML
    private void wipListView_OnDragEntered(DragEvent event)
    {
        /*
         * show to the user that it is an actual gesture target
         */
        if (event.getGestureSource() != wipListView
                && event.getDragboard().hasString())
        {
            arrowPath.setFill(GOOD_DROP.color);
        } else
        {
            arrowPath.setFill(BAD_DROP.color);
        }

        event.consume();
    }

    /**
     * Mouse moved away, remove the graphical cues.
     *
     * @param event
     */
    @FXML
    private void wipListView_OnDragExited(DragEvent event)
    {
        /*
         * mouse moved away, remove the graphical cues
         */
        arrowPath.setFill(NO_DROP.color);
        event.consume();
    }

    /**
     * Data is dragged over the target.
     *
     * @param event
     */
    @FXML
    private void wipListView_OnDragOver(DragEvent event)
    {
        /*
         * accept it only if it is not dragged from the same node
         * and if it has a string data
         */
        if (event.getGestureSource() != wipListView
                && event.getDragboard().hasString())
        {
            /*
             * allow for moving
             */
            event.acceptTransferModes(TransferMode.MOVE);
        }

        event.consume();
    }

    /**
     * This enum is used to set the Fill color for the Arrow.
     */
    enum ArrowStatus
    {
        BAD_DROP(Color.RED),
        NO_DROP(Color.ORANGE),
        GOOD_DROP(Color.LAWNGREEN),
        CLEAR(Color.BLACK);

        public final Color color;

        private ArrowStatus(Color color)
        {
            this.color = color;
        }
    }
}
