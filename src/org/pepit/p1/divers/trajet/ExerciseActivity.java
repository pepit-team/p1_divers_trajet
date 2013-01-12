/**
 * @file org/pepit/p1/divers/trajet/ExerciseActivity.java
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class ExerciseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	
	Intent intent = getIntent();
	int colNumber = Integer.parseInt(intent.getStringExtra("colNumber"));
	int rowNumber = Integer.parseInt(intent.getStringExtra("rowNumber"));
	int minPathSize = Integer.parseInt(intent.getStringExtra("minPathSize"));
	int maxPathSize = Integer.parseInt(intent.getStringExtra("maxPathSize"));
	
	// create widgets
	grid = new GridView(this);
	grid.init(colNumber, rowNumber, minPathSize, maxPathSize);
	tab = new TabView(this);
	tab.init(grid, minPathSize, maxPathSize);
	
	// add widget to grid layout
	LinearLayout gridLayout = new LinearLayout(this);
	LinearLayout.LayoutParams gridLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	gridLayout.addView(grid);

	// add widget to tab layout
	LinearLayout tabLayout = new LinearLayout(this);
	LinearLayout.LayoutParams tabLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.WRAP_CONTENT, 4);

	tabLayout.addView(tab);

	// rootLayout
	LinearLayout rootLayout = new LinearLayout(this);
	LinearLayout.LayoutParams rootLayoutParams = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.MATCH_PARENT);

	rootLayout.addView(tabLayout, tabLayoutParams);
	rootLayout.addView(gridLayout, gridLayoutParams);
	rootLayout.setOrientation(LinearLayout.VERTICAL);

	// and set your layout like main content
	setContentView(rootLayout, rootLayoutParams);
    }

    private GridView grid;
    private TabView tab;
}
