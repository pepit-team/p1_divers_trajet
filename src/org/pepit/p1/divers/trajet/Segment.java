/**
 * @file org/pepit/p1/divers/trajet/Segment.java
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

import android.graphics.Point;

public class Segment {

    public Segment(Point point, Direction direction) {
	this.origin = new Point(point);
	this.direction = direction;
    }

    public Direction getDirection() {
	return direction;
    }
    
    static public Point destination(Point pt, Direction direction) {
	int dest_x = -1;
	int dest_y = -1;

	if (direction == Direction.SOUTH) {
	    dest_x = pt.x;
	    dest_y = pt.y + 1;
	} else if (direction == Direction.NORTH) {
	    dest_x = pt.x;
	    dest_y = pt.y - 1;
	} else if (direction == Direction.EAST) {
	    dest_x = pt.x + 1;
	    dest_y = pt.y;
	} else { // WEST
	    dest_x = pt.x - 1;
	    dest_y = pt.y;
	}
	return new Point(dest_x, dest_y);	
    }

    public Point getDestination() {
	return Segment.destination(origin, direction);
    }

    public Point getOrigin() {
	return origin;
    }

    private Point origin;
    private Direction direction;
}