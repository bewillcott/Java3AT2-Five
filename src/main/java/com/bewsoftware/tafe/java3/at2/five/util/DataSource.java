/*
 *  File Name:    DataSource.java
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

package com.bewsoftware.tafe.java3.at2.five.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DataSource class description.
 *
 * @author <a href="mailto:bw.opensource@yahoo.com">Bradley Willcott</a>
 *
 * @since 1.0
 * @version 1.0
 */
public class DataSource
{

    private static final String[] DATA =
    {
        "Plan Christmas holiday",
        "Work on the boat",
        "Clean the car",
        "Fix the back door",
        "Clean the garage",
        "Mow the lawn",
        "Paint the baby's room",
        "Get the car serviced",
        "Complete the Sales Report",
        "Plan the next presentation",
        "Apply for holidays: Dec-Jan"
    };

    /**
     * Provide test data for the Work To Do ListView
     *
     * @return a list of jobs to be done
     */
    public static final List<String> getData()
    {
        List<String> list = new ArrayList<>();

        list.addAll(Arrays.asList(DATA));

        return list;
    }

    private DataSource()
    {
    }
}
