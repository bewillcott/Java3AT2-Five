/*
 *  File Name:    Arrow.java
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

package com.bewsoftware.tafe.java3.at2.five.gfx2d;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 * Creates an arrow, defined by
 * {@linkplain javafx.scene.shape.PathElement PathElements} contained in the
 * {@linkplain Path} instance, of which this class extends.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class Arrow extends Path
{
    private static final double[][] COORDS = new double[][]
    {
        {// Starting point: MoveTo
            0.0, 5.0
        },
        {// hLine: LineTo
            30.0, 5.0
        },
        {// VLine: LineTo
            30.0, 0.0
        },
        {// ALine: LineTo
            45.0, 10.0
        },
        {// ALine: LineTo
            30.0, 20.0
        },
        {// VLine: LineTo
            30.0, 15.0
        },
        {// HLine: LineTo
            0.0, 15.0
        },
        {// VLine: LineTo
            0.0, 5.0
        }
    };

    private Arrow()
    {
    }

    /**
     * Build the Path for a 2D Arrow.
     * <p>
     * - 'x' and 'y' coordinates set to '0.0'.<br>
     * - 'scaleX' and 'scaleY' set to '100'.
     *
     * @return new Arrow/Path containing the elements for drawing the arrow,
     */
    public static final Arrow build()
    {
        return build(0, 0, 100, 100);
    }

    /**
     * Build the Path for a 2D Arrow.
     * <p>
     * - 'x' and 'y' coordinates set to '0.0'.
     *
     * @param scaleX Scale of the X dimension of the drawing (%):
     *               0 {@literal < } scaleX%
     * @param scaleY Scale of the Y dimension of the drawing (%):
     *               0 {@literal < } scaleY%
     *
     * @return new Arrow/Path containing the elements for drawing the arrow,
     *         if {@code scale} is greater than {@code 0}.
     */
    public static final Arrow build(int scaleX, int scaleY)
    {
        return build(0, 0, scaleX, scaleY);
    }

    /**
     * Build the Path for a 2D Arrow.
     *
     * @param x      Start X value
     * @param y      Start Y value
     * @param scaleX Scale of the X dimension of the drawing (%):
     *               0 {@literal < } scaleX%
     * @param scaleY Scale of the Y dimension of the drawing (%):
     *               0 {@literal < } scaleY%
     *
     * @return new Arrow/Path containing the elements for drawing the arrow,
     *         if {@code scale} is greater than {@code 0}.
     */
    public static final Arrow build(double x, double y, int scaleX, int scaleY)
    {
        Arrow path = null;

        if (scaleX > 0 && scaleY > 0)
        {
            //Creating a Path
            path = new Arrow();
            double percentX = ((double) scaleX) / 100;
            double percentY = ((double) scaleY) / 100;

            //Moving to the starting point
            path.getElements().add(new MoveTo(x + (COORDS[0][0] * percentX), y + (COORDS[0][1] * percentY)));

            for (int i = 1; i < COORDS.length; i++)
            {
                path.getElements().add(new LineTo(x + (COORDS[i][0] * percentX), y + (COORDS[i][1] * percentY)));
            }
        }

        return path;
    }
}
