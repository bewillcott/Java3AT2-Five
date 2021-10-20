/*
 *  File Name:    AboutController.java
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

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import static com.bewsoftware.tafe.java3.at2.five.util.Constants.BUILD_DATE;
import static com.bewsoftware.tafe.java3.at2.five.util.Constants.PRODUCT_TITLE;
import static com.bewsoftware.tafe.java3.at2.five.util.Constants.VERSION;

/**
 * FXML Controller class for the 'About.fxml' file.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 */
public class AboutController
{

    private static final String COPYRIGHT
            = "    This program is free software: you can redistribute it and/or "
            + "modify it under the terms of the GNU General Public License as "
            + "published by the Free Software Foundation, either version 3 of "
            + "the License, or (at your option) any later version.\n\n"
            + "    This program is distributed in the hope that it will be useful, "
            + "but WITHOUT ANY WARRANTY; without even the implied warranty of "
            + "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU "
            + "General Public License for more details.\n\n"
            + "    You should have received a copy of the GNU General Public "
            + "License along with this program.  If not, see "
            + "<http://www.gnu.org/licenses/>.\n";

    private static final String COPYRIGHT_NOTICE
            = "Copyright (c) 2021 Bradley Willcott";

    private static final String DESCRIPTION
            = "This program was developed for my Diploma in Software Development "
            + "at the South Metropolitan TAFE, Rockingham WA.\n\n"
            + "This application provides \"Drag-N-Drop\" functionality between "
            + "two lists, has a 2D Graphic arrow, and has help available.\n";

    @FXML
    private Label buildDateLabel;

    @FXML
    private Label copyrightLabel;

    @FXML
    private TextArea copyrightTextArea;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Label productLabel;

    @FXML
    private GridPane rootPane;

    @FXML
    private Label verionLabel;

    /**
     * Instantiate a new copy of the AboutController class.
     */
    public AboutController()
    {
        // Currently: NoOp.
    }

    /**
     * Initializes the controller class.
     */
    @FXML
    private void initialize()
    {
        productLabel.setText(PRODUCT_TITLE);
        verionLabel.setText(VERSION);
        buildDateLabel.setText(BUILD_DATE);
        descriptionTextArea.setText(DESCRIPTION);
        copyrightLabel.setText(COPYRIGHT_NOTICE);
        copyrightTextArea.setText(COPYRIGHT);
    }
}
