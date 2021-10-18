/*
 *  File Name:    App.java
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

package com.bewsoftware.tafe.java3.at2.five;

import com.bewsoftware.tafe.java3.at2.five.util.ViewController;
import com.bewsoftware.tafe.java3.at2.five.util.Views;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.bewsoftware.tafe.java3.at2.five.util.Constants.PRODUCT_TITLE;
import static com.bewsoftware.tafe.java3.at2.five.util.Constants.log;
import static javafx.application.Application.launch;

/**
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class App extends Application
{
    /**
     * Property tag for the active view.
     */
    public static final String PROP_ACTIVEVIEW = "activeView";

    /**
     * Property tag for the status text.
     */
    public static final String PROP_STATUSTEXT = "statusText";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    private Views activeView;

    /**
     * The data needs to be Saved!
     */
    private Stage primaryStage;

    private final transient PropertyChangeSupport propertyChangeSupport;

    private BorderPane rootLayout;

    private String statusText;

    public App()
    {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.statusText = "";
    }

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public Stage getPrimaryStage()
    {
        return primaryStage;
    }

    /**
     * Set the statusText
     *
     * @param statusText new value of statusText
     */
    public void setStatusText(String statusText)
    {
        String temp = statusText != null ? statusText : "";

        String oldStatusText = this.statusText;
        this.statusText = temp;
        propertyChangeSupport.firePropertyChange(PROP_STATUSTEXT, oldStatusText, temp);
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout()
    {
        try
        {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            ViewController listener = loader.getController();
            listener.setApp(this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (IOException ex)
        {
            log(ex.toString());
        }
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    /**
     * Shows the view inside the root layout.
     *
     * @param view to display
     */
    public void showView(Views view)
    {
        if (this.activeView != view)
        {
            // Notify everyone of pending change
            Views oldActiveView = this.activeView;
            this.activeView = view;
            propertyChangeSupport.firePropertyChange(PROP_ACTIVEVIEW, oldActiveView, activeView);

            try
            {
                // Load new view.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(App.class.getResource("view/" + view + ".fxml"));
                AnchorPane newView = (AnchorPane) loader.load();

                ViewController controller = loader.getController();
                controller.setApp(this);

                // Set view into the center of root layout.
                rootLayout.setCenter(newView);
                controller.setFocus();
            } catch (IOException ex)
            {
                log(ex.toString());
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(PRODUCT_TITLE);

        initRootLayout();
    }
}
