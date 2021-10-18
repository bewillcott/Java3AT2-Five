/*
 *  File Name:    RootLayoutController.java
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
import com.bewsoftware.tafe.java3.at2.five.util.ViewController;
import com.bewsoftware.tafe.java3.at2.five.util.Views;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import static com.bewsoftware.tafe.java3.at2.five.util.Constants.log;
import static com.bewsoftware.tafe.java3.at2.five.util.Views.BLANK;
import static com.bewsoftware.tafe.java3.at2.five.util.Views.CSVTABLE;

/**
 * FXML Controller class for the 'RootLayout.fxml' file.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class RootLayoutController implements ViewController
{

    @FXML
    private MenuItem aboutMenuItem;

    private App app;

    @FXML
    private MenuItem manualMenuItem;

    @FXML
    private Label statusLabel;

    /**
     * Instantiate a new copy of RootLayoutController class.
     */
    public RootLayoutController()
    {
        // NoOp
    }

    @Override
    public void setApp(App app)
    {
        this.app = app;
        app.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        switch (evt.getPropertyName())
        {
            case App.PROP_ACTIVEVIEW ->
            {
                // Views being opened:
                switch ((Views) evt.getNewValue())
                {
                    case BLANK ->
                    {
                        statusLabel.setText("");
                    }

                    case CSVTABLE ->
                    {
                    }

                    default ->
                    {
                        // That's all
                    }
                }

                // Views being closed:
                if (evt.getOldValue() != null)
                {
                    switch ((Views) evt.getOldValue())
                    {
                        case BLANK ->
                        {
                            // NoOp
                        }

                        case CSVTABLE ->
                        {
                        }

                        default ->
                        {
                            // That's all
                        }
                    }
                }
            }

            case App.PROP_STATUSTEXT ->
            {
                statusLabel.setText((String) evt.getNewValue());
            }

            default ->
            {
                // NoOp
            }
        }
    }

    @Override
    public void setFocus()
    {
        // NoOp
    }

    /**
     * Handle the Help/About menu item event.
     *
     * @param event
     */
    @FXML
    private void handleAboutMenuItem(ActionEvent event)
    {
        showAboutDialog();
        event.consume();
    }

    /**
     * Handle the About/Manual menu item event.
     *
     * @param event
     */
    @FXML
    private void handleManualMenuItem(ActionEvent event)
    {
// TODO Add code
        event.consume();
    }

    /**
     * Handle the File/Close menu item event.
     *
     * @param event
     */
    @FXML
    private void handleExitMenuItem(ActionEvent event)
    {
        app.getPrimaryStage().fireEvent(
                new WindowEvent(
                        app.getPrimaryStage(),
                        WindowEvent.WINDOW_CLOSE_REQUEST
                )
        );

        event.consume();
    }

    /**
     * Controller initialization.
     */
    @FXML
    private void initialize()
    {
        // TODO Do I need this?
    }

    /**
     * Opens the About dialog.
     */
    private void showAboutDialog()
    {
        try
        {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/About.fxml"));
            GridPane page = (GridPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("About");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(app.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException ex)
        {
            log(ex.toString());
        }
    }
}
