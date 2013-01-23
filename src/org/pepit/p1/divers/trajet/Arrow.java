/**
 * @file org/pepit/p1/divers/trajet/Arrow.java
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

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;

public class Arrow {

    public Arrow(Point origin, Direction direction) {
	this.origin = new Point(origin);
	this.direction = direction;
    }
    
    public Arrow(Segment segment, int[] pts_x, int[] pts_y) {
	this.origin = new Point(pts_x[segment.getOrigin().x], pts_y[segment.getOrigin().y]);
	this.direction = segment.getDirection();
    }

    private void draw(Canvas canvas, int x, int y, int length) {
	Paint paint = new Paint();
	paint.setColor(COLOR);
	paint.setStyle(Style.FILL);

	Point pt1 = new Point(x, y);
	Point pt2 = null;

	Path path = new Path();

	switch (direction) {
	case NORTH:
	    pt2 = new Point(x, y - length);

	    path.moveTo(x + 10, y - length + 25);
	    path.lineTo(x, y - length - 5);
	    path.lineTo(x - 10, y - length + 25);
	    path.lineTo(x + 10, y - length + 25);
	    break;
	case EAST:
	    pt2 = new Point(x + length, y);

	    path.moveTo(x + length - 25, y + 10);
	    path.lineTo(x + length + 5, y);
	    path.lineTo(x + length - 25, y - 10);
	    path.lineTo(x + length - 25, y + 10);

	    break;
	case SOUTH:
	    pt2 = new Point(x, y + length);

	    path.moveTo(x - 10, y + length - 25);
	    path.lineTo(x, y + length + 5);
	    path.lineTo(x + 10, y + length - 25);
	    path.lineTo(x - 10, y + length - 25);
	    break;
	case WEST:
	    pt2 = new Point(x - length, y);

	    path.moveTo(x - length + 25, y - 10);
	    path.lineTo(x - length - 5, y);
	    path.lineTo(x - length + 25, y + 10);
	    path.lineTo(x - length + 25, y - 10);
	    break;
	case UNDEFINED:
	    pt2 = new Point(x, y + length);

	    paint.setColor(Color.RED);
	    paint.setTextSize(length);
	    canvas.drawText("?", pt2.x, pt2.y, paint);
	    break;
	}

	if (direction != Direction.UNDEFINED) {
	    path.close();
	    canvas.drawPath(path, paint);

	    paint.setStyle(Style.STROKE);
	    paint.setStrokeWidth(4);
	    canvas.drawLine(pt1.x, pt1.y, pt2.x, pt2.y, paint);
	}
    }

    public void draw(Canvas canvas, int length) {
	draw(canvas, origin.x, origin.y, length);
    }

    public Direction getDirection() {
	return direction;
    }
    
    public Point getOrigin() {
	return origin;
    }

    private Point origin;
    private Direction direction;

    public static final int COLOR = Color.rgb(11, 137, 32);
}