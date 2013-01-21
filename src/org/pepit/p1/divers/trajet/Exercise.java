/**
 * @file org/pepit/p1/divers/trajet/Exercise.java
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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.pepit.plugin.Info;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Exercise implements org.pepit.plugin.Interface {

    /**
     * Plugin metadata
     */
    public Info getInfo() {
	org.pepit.plugin.Info info = new org.pepit.plugin.Info();
	info.level = org.pepit.plugin.Level.P1;
	info.subject = org.pepit.plugin.Subject.MISCELLANEOUS;
	info.theme = "Reconstituer un trajet";
	info.version = 1;
	String pepitPage = "http://www.pepit.be/exercices/primaire1/exercicesdivers/suivretrajet/page.html";
	try {
	    info.pepitPage = new URL(pepitPage);
	} catch (MalformedURLException e) {
	    Log.e("Pepit", "Bad URL: " + pepitPage);
	}
	return info;
    }

    public LinearLayout getExercisePresentationLayout(Context ctx,
	    File rootResource) {
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.MATCH_PARENT);
	params.gravity = Gravity.CENTER_HORIZONTAL;

	LinearLayout lil = new LinearLayout(ctx);
	lil.setLayoutParams(params);
	lil.setOrientation(LinearLayout.VERTICAL);
	lil.setBackgroundColor(0xFF99CC66);

	TextView tv1 = new TextView(ctx);
	tv1.setGravity(Gravity.CENTER_HORIZONTAL);
	tv1.setTextColor(Color.BLACK);
	tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
	tv1.setText("TRAJET");
	lil.addView(tv1);

	TextView tv2 = new TextView(ctx);
	tv2.setGravity(Gravity.CENTER_HORIZONTAL);
	tv2.setTextColor(Color.BLACK);
	tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
	tv2.setText("Décrire un déplacement");
	lil.addView(tv2);

	TextView tv3 = new TextView(ctx);
	tv3.setGravity(Gravity.CENTER_HORIZONTAL);
	tv3.setTextColor(Color.BLACK);
	tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	tv3.setText("Adaptation de la fiche \"51\" de \"Basile et les maths\" (1A).");
	lil.addView(tv3);

	TextView tv4 = new TextView(ctx);
	tv4.setGravity(Gravity.CENTER_HORIZONTAL);
	tv4.setTextColor(Color.BLACK);
	tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	tv4.setText("Editions Wolters Plantyn.");
	lil.addView(tv4);

	return lil;
    }

    public String[] getExerciseList() {
	String[] l = { "Grilles de 10 x 5", "Grilles de 15 x 10",
		"Grilles de 20 x 15", "Grilles de 25 x 20",
		"Grilles de 30 x 25" };

	return l;
    }

    public LinearLayout getExplanationPresentationLayout(Context ctx,
	    File rootResource, int selectedExercise) {
	LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		LinearLayout.LayoutParams.MATCH_PARENT,
		LinearLayout.LayoutParams.MATCH_PARENT);
	params.gravity = Gravity.CENTER_HORIZONTAL;

	LinearLayout lil = new LinearLayout(ctx);
	lil.setLayoutParams(params);
	lil.setOrientation(LinearLayout.VERTICAL);
	lil.setBackgroundColor(0xFF99CC66);

	TextView tv1 = new TextView(ctx);
	tv1.setGravity(Gravity.CENTER_HORIZONTAL);
	tv1.setTextColor(Color.BLACK);
	tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
	tv1.setText("Comment jouer ?");
	lil.addView(tv1);

	TextView tv2 = new TextView(ctx);
	tv2.setGravity(Gravity.CENTER_HORIZONTAL);
	tv2.setTextColor(Color.BLACK);
	tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
	String[] t = this.getExerciseList();
	tv2.setText("\n" + t[selectedExercise] + "\n");
	lil.addView(tv2);

	TextView tv3 = new TextView(ctx);
	tv3.setGravity(Gravity.CENTER_HORIZONTAL);
	tv3.setTextColor(Color.BLACK);
	tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	tv3.setText("Adaptation de la fiche \"51\" de \"Basile et les maths\" (1A).");
	lil.addView(tv3);

	TextView tv4 = new TextView(ctx);
	tv4.setGravity(Gravity.CENTER_HORIZONTAL);
	tv4.setTextColor(Color.BLACK);
	tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
	tv4.setText("Editions Wolters Plantyn.");
	lil.addView(tv4);

	return lil;
    }

    public String[] getModuleList(int exercise) {
	String[] l = { "Module 1", "Module 2", "Module 3", "Module 4",
		"Module 5" };

	return l;
    }

    public LinearLayout getQuestionLayout(Context ctx, File rootResource,
	    int selectedExercise, int selectedModule, int numQuestion) {
	switch (selectedExercise) {
	case 0:
	    view = new ExerciseView(ctx, 10, 5, 10, 10);
	    break;
	case 1:
	    view = new ExerciseView(ctx, 15, 10, 15, 15);
	    break;
	case 2:
	    view = new ExerciseView(ctx, 20, 15, 20, 20);
	    break;
	case 3:
	    view = new ExerciseView(ctx, 25, 20, 25, 25);
	    break;
	case 4:
	    view = new ExerciseView(ctx, 30, 25, 30, 30);
	    break;
	}
	return view.getLayout();
    }

    public int getQuestionCount(int selectedExercise, int selectedModule) {
	return 1;
    }

    public void startQuestionSequence() {
    }

    public void finishQuestionSequence() {
    }

    public String getNextQuestionButtonText() {
	return "Valider";
    }

    public boolean currentAnswerIsRight() {
	return view.check();
    }

    public void showAnswerIsRight() {
	view.displayCorrectMessage();
    }

    public void showAnswerIsWrong() {
	view.displayErrorMessage();
    }

    public int getScore() {
	// TODO Auto-generated method stub
	return 0;
    }

    private ExerciseView view = null;
}
