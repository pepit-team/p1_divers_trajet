/**
 * @file org/pepit/p1/divers/trajet/TabView.java
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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class TabView extends View {

    public TabView(Context context) {
	super(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
    }

    public TabView(Context context, AttributeSet attrs) {
	super(context, attrs);
    }

    private void check() {
	if (grid.getModel().check(path)) {
	    Toast.makeText(getContext(), "Bravo ! Le parcours est correct",
		    Toast.LENGTH_LONG).show();
	}
    }

    private void computeDimensions() {
	margin_x = Constants.MARGIN_X;
	margin_y = Constants.MARGIN_Y;

	width = this.getWidth() - (margin_x * 2);
	height = this.getHeight() - (margin_y * 2);

	space_x = width / maxPathSize;
	space_y = height;

	width = space_x * maxPathSize;
	height = space_y;

	for (int i = 0; i < pts_x.length; i++) {
	    if (i == 0)
		pts_x[i] = 0 + margin_x;
	    else
		pts_x[i] = 0 + margin_x + (space_x * i);
	}

	pts_y[0] = 0 + margin_y;
	pts_y[1] = height + margin_y;
    }

    private void drawTab(Canvas canvas) {
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

    }

    private void drawPath(Canvas canvas) {
	if (path != null) {
	    if (!flag_init) {
		for (int i = 0; i < path.length; i++) {
		    path[i] = Direction.UNDEFINED;
		}
		flag_init = true;
	    }

	    for (int i = 0; i < path.length; ++i) {
		Direction a = path[i];

		if (a == Direction.UNDEFINED) {
		    (new Arrow(new Point(pts_x[i] + (space_x / 4), height
			    - space_y), a)).draw(canvas, space_y
			    - (margin_y * 3));
		} else if (a == Direction.NORTH) {
		    (new Arrow(new Point(pts_x[i] + space_x / 2, space_y), a))
			    .draw(canvas, space_y - (margin_y * 3));
		} else if (a == Direction.EAST) {
		    (new Arrow(new Point(pts_x[i] + margin_x, space_y / 2), a))
			    .draw(canvas, space_x - (margin_x * 3));
		} else if (a == Direction.SOUTH) {
		    (new Arrow(
			    new Point(pts_x[i] + (space_x / 2), margin_y * 2),
			    a)).draw(canvas, space_y - (margin_y * 3));
		} else if (a == Direction.WEST) {
		    (new Arrow(new Point(pts_x[i] + (space_x - margin_x),
			    space_y / 2), a)).draw(canvas, space_x
			    - (margin_x * 3));
		}
	    }
	}

    }

    private void init() {
	pts_x = new int[maxPathSize + 1];
	pts_y = new int[2];

	height = 0;
	width = 0;

	space_x = 0;
	space_y = 0;

	margin_x = 0;
	margin_y = 0;

	path = null;

	flag_init = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);
	if (grid != null) {
	    computeDimensions();
	    drawTab(canvas);
	    drawPath(canvas);
	}
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
	int touch_x = (int) event.getX();
	int touch_y = (int) event.getY();

	for (int i = 1; i < pts_x.length; i++) {
	    if (touch_x > pts_x[i - 1] && touch_x < pts_x[i]
		    && touch_y > pts_y[0] && touch_y < pts_y[1]) {
		changeStateCase(i - 1);
		invalidate();
		check();
	    }
	}
	return super.onTouchEvent(event);
    }

    public void init(GridView grid, int minPathSize, int maxPathSize) {
	this.grid = grid;
	path = new Direction[grid.getModel().size()];
	this.minPathSize = minPathSize;
	this.maxPathSize = maxPathSize;
	init();
    }

    private void changeStateCase(int i) {
	if (path[i] == Direction.UNDEFINED) {
	    path[i] = Direction.NORTH;
	} else if (path[i] == Direction.NORTH) {
	    path[i] = Direction.EAST;
	} else if (path[i] == Direction.EAST) {
	    path[i] = Direction.SOUTH;
	} else if (path[i] == Direction.SOUTH) {
	    path[i] = Direction.WEST;
	} else if (path[i] == Direction.WEST) {
	    path[i] = Direction.NORTH;
	}
    }

    // parameters
    int minPathSize;
    int maxPathSize;

    private GridView grid;

    private int height;
    private int width;

    private int[] pts_x;
    private int[] pts_y;

    private int space_x;
    private int space_y;

    private int margin_x;
    private int margin_y;

    private Direction[] path;

    private boolean flag_init;
}
