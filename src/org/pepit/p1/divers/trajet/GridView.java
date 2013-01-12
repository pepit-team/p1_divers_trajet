/**
 * @file org/pepit/p1/divers/trajet/GridView.java
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("DrawAllocation")
public class GridView extends View {

    public GridView(Context context) {
	super(context);
    }

    public GridView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
    }

    public GridView(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    private void computeDimensions() {
	pts_x = new int[model.getColNumber()];
	pts_y = new int[model.getRowNumber()];

	margin_x = Constants.MARGIN_X;
	margin_y = Constants.MARGIN_Y;

	width = this.getWidth() - (margin_x * 2);
	height = this.getHeight() - (margin_y * 2);

	space_x = width / (model.getColNumber() - 1);
	space_y = height / (model.getRowNumber() - 1);

	width = space_x * (model.getColNumber() - 1);
	height = space_y * (model.getRowNumber() - 1);

	for (int i = 0; i < pts_x.length; i++) {
	    if (i == 0)
		pts_x[i] = 0 + margin_x;
	    else
		pts_x[i] = 0 + margin_x + (space_x * i);
	}
	for (int i = 0; i < pts_y.length; i++) {
	    if (i == 0)
		pts_y[i] = 0 + margin_y;
	    else
		pts_y[i] = 0 + margin_y + (space_y * i);
	}
    }

    private void drawPath(Canvas canvas, Paint paint) {
	for (int i = 0; i < model.size(); ++i) {
	    Segment s = model.get(i);
	    Arrow a = new Arrow(s, pts_x, pts_y);

	    if (a.getDirection() == Direction.NORTH
		    || a.getDirection() == Direction.SOUTH)
		a.draw(canvas, space_y);
	    else
		a.draw(canvas, space_x);
	}

	paint.setColor(Color.RED);
	canvas.drawCircle(pts_x[model.start().x], pts_y[model.start().y], 7,
		paint);

	paint.setColor(Color.GREEN);
	canvas.drawCircle(pts_x[model.end().x], pts_y[model.end().y], 7, paint);
    }

    public GridModel getModel() {
	return model;
    }

    public void init(int colNumber, int rowNumber, int minPathSize, int maxPathSize) {
	model = new GridModel(colNumber, rowNumber, minPathSize, maxPathSize);
	model.build();
    }

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);

	if (model != null) {
	    computeDimensions();

	    Paint paint = new Paint();
	    paint.setColor(Color.DKGRAY);
	    paint.setStrokeWidth(2);

	    for (int i : pts_x) {
		Point p1 = new Point(i, margin_y);
		Point p2 = new Point(i, (height + margin_y));
		canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
	    }

	    for (int i : pts_y) {
		Point p1 = new Point(margin_x, i);
		Point p2 = new Point((width + margin_x), i);
		canvas.drawLine(p1.x, p1.y, p2.x, p2.y, paint);
	    }

	    drawPath(canvas, paint);
	}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
	return super.onTouchEvent(event);
    }

    private GridModel model;

    int[] pts_x;
    int[] pts_y;

    int margin_x;
    int margin_y;

    int width;
    int height;

    int space_x;
    int space_y;
}