/**
 * @file org/pepit/p1/divers/trajet/GridModel.java
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

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Point;

public class GridModel {

    public GridModel(int colNumber, int rowNumber, int minPathSize, int maxPathSize) {
	this.colNumber = colNumber;
	this.rowNumber = rowNumber;
	this.minPathSize = minPathSize;
	this.maxPathSize = maxPathSize;
	path = new ArrayList<Segment>();
	r = new Random();
    }

    private boolean belongPath(Point pt) {
	int i = 0;
	boolean found = false;

	while (i < path.size() && !found) {
	    found = path.get(i).getOrigin().equals(pt.x, pt.y);
	    ++i;
	}
	return found;
    }

    public void build() {
	do {
	    int i = 0;

	    buildEnd(buildStart());
	    do {
		boolean ok = true;

		path.removeAll(path);
		buildFirst();
		while (ok && !isCompletePath()) {
		    ok = increasePath();
		}
		i++;
	    } while (!isCompletePath() && i < 10);
	} while (!isCompletePath()
		|| !(path.size() >= minPathSize && path.size() <= maxPathSize));
    }

    private void buildFirst() {
	if (start.x == 0) { // WEST
	    path.add(new Segment(new Point(start.x, start.y), Direction.EAST));
	} else if (start.x == colNumber - 1) { // EAST
	    path.add(new Segment(new Point(start.x, start.y), Direction.WEST));
	} else if (start.y == 0) { // NORTH
	    path.add(new Segment(new Point(start.x, start.y), Direction.SOUTH));
	} else { // SOUTH
	    path.add(new Segment(new Point(start.x, start.y), Direction.NORTH));
	}
    }

    private void buildEnd(int c_start) {
	int c_end = (c_start + r.nextInt(3) + 1) % 4;
	int x_end = -1;
	int y_end = -1;

	if (c_end == 0) { // NORTH
	    x_end = r.nextInt(colNumber - 2) + 1;
	    y_end = 0;
	} else if (c_end == 1) { // EAST
	    x_end = colNumber - 1;
	    y_end = r.nextInt(rowNumber - 2) + 1;
	} else if (c_end == 2) { // SOUTH
	    x_end = r.nextInt(colNumber - 2) + 1;
	    y_end = rowNumber - 1;
	} else if (c_end == 3) { // WEST
	    x_end = 0;
	    y_end = r.nextInt(rowNumber - 2) + 1;
	}
	end = new Point(x_end, y_end);
    }

    private int buildStart() {
	int c_start = r.nextInt(4);
	int x_start = -1;
	int y_start = -1;

	if (c_start == 0) { // NORTH
	    x_start = r.nextInt(colNumber - 2) + 1;
	    y_start = 0;
	} else if (c_start == 1) { // EAST
	    x_start = colNumber - 1;
	    y_start = r.nextInt(rowNumber - 2) + 1;
	} else if (c_start == 2) { // SOUTH
	    x_start = r.nextInt(colNumber - 2) + 1;
	    y_start = rowNumber - 1;
	} else if (c_start == 3) { // WEST
	    x_start = 0;
	    y_start = r.nextInt(rowNumber - 2) + 1;
	}
	start = new Point(x_start, y_start);
	return c_start;
    }

    public boolean check(Direction[] list) {
	int i = 0;

	while (i < size() || i < list.length) {
	    if (get(i).getDirection() != list[i])
		return false;
	    i++;
	}
	return true;
    }

    public Point end() {
	return end;
    }

    public Segment get(int i) {
	return path.get(i);
    }
    
    public int getColNumber() {
	return colNumber;
    }

    public int getRowNumber() {
	return rowNumber;
    }

    private boolean increasePath() {
	Segment a = path.get(path.size() - 1);
	Point begin = a.getDestination();
	Direction[] directions = new Direction[4];
	int i = 0;

	if (a.getDirection() != Direction.EAST
		&& (begin.x > 1 || (begin.x == 1 && end.x == 0 && end.y == begin.y))) {
	    if (!belongPath(Segment.destination(begin, Direction.WEST))) {
		directions[i] = Direction.WEST;
		i++;
	    }
	}
	if (a.getDirection() != Direction.WEST
		&& (begin.x < colNumber - 2 || (begin.x == colNumber - 2
			&& end.x == colNumber - 1 && end.y == begin.y))) {
	    if (!belongPath(Segment.destination(begin, Direction.EAST))) {
		directions[i] = Direction.EAST;
		i++;
	    }
	}
	if (a.getDirection() != Direction.SOUTH
		&& (begin.y > 1 || (begin.x == 1 && end.y == 0 && end.x == begin.x))) { // NORTH
	    if (!belongPath(Segment.destination(begin, Direction.NORTH))) {
		directions[i] = Direction.NORTH;
		i++;
	    }
	}
	if (a.getDirection() != Direction.NORTH
		&& (begin.y < rowNumber - 2 || (begin.y == rowNumber - 2
			&& end.y == rowNumber - 1 && end.x == begin.x))) { // SOUTH
	    if (!belongPath(Segment.destination(begin, Direction.SOUTH))) {
		directions[i] = Direction.SOUTH;
		i++;
	    }
	}
	if (i > 0) {
	    Direction new_dir = directions[r.nextInt(i)];
	    Segment b = new Segment(a.getDestination(), new_dir);

	    path.add(b);
	    return true;
	} else {
	    return false;
	}
    }

    private boolean isCompletePath() {
	return !path.isEmpty()
		&& path.get(path.size() - 1).getDestination()
			.equals(end.x, end.y);
    }

    public int size() {
	return path.size();
    }

    public Point start() {
	return start;
    }

    // parameters
    private int colNumber;
    private int rowNumber;
    private int minPathSize;
    private int maxPathSize;

    private Random r;
    private ArrayList<Segment> path;
    private Point start;
    private Point end;
}
