/**
 * @file org/pepit/p1/divers/trajet/ExerciseView.java
 * 
 * PepitModel: an educational software
 * This file is a part of the PepitModel environment
 * http://pepit.be
 *
 * Copyright (C) 2012-2013 Pepit Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.pepit.p1.divers.trajet;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ExerciseView {

    public ExerciseView(Context ctx, int colNumber, int rowNumber,
	    int minPathSize, int maxPathSize) {
	context = ctx;
	// create widgets
	grid = new GridView(ctx);
	grid.init(colNumber, rowNumber, minPathSize, maxPathSize);
	tab = new TabView(ctx);
	tab.init(grid, minPathSize, maxPathSize);

	// add widget to grid layout
	LinearLayout gridLayout = new LinearLayout(ctx);
	LinearLayout.LayoutParams gridLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.WRAP_CONTENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	gridLayout.addView(grid);

	// add widget to tab layout
	LinearLayout tabLayout = new LinearLayout(ctx);
	LinearLayout.LayoutParams tabLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.WRAP_CONTENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 4);

	tabLayout.addView(tab);

	// rootLayout
	rootLayout = new LinearLayout(ctx);
	rootLayout.addView(tabLayout, tabLayoutParams);
	rootLayout.addView(gridLayout, gridLayoutParams);
	rootLayout.setOrientation(LinearLayout.VERTICAL);
    }

    public boolean check() {
	return tab.check();
    }
    
    public void displayCorrectMessage() {
	Toast.makeText(context, "Bravo !", Toast.LENGTH_LONG).show();
    }

    public void displayErrorMessage() {
	Toast.makeText(context, "Erreur", Toast.LENGTH_LONG).show();
    }

    public LinearLayout getLayout() {
	return rootLayout;
    }

    private Context context;
    private LinearLayout rootLayout;
    private GridView grid;
    private TabView tab;
}