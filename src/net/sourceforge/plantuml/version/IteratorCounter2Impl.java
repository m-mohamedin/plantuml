/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 */
package net.sourceforge.plantuml.version;

import java.util.List;

import net.sourceforge.plantuml.StringLocated;

public class IteratorCounter2Impl implements IteratorCounter2 {

	private List<StringLocated> data;
	private int nb;

	public void copyStateFrom(IteratorCounter2 other) {
		final IteratorCounter2Impl source = (IteratorCounter2Impl) other;
		this.nb = source.nb;
		this.data = source.data;
	}

	public IteratorCounter2Impl(List<StringLocated> data) {
		this(data, 0);
	}

	private IteratorCounter2Impl(List<StringLocated> data, int nb) {
		this.data = data;
		this.nb = nb;
	}

	public int currentNum() {
		return nb;
	}

	public boolean hasNext() {
		return nb < data.size();
	}

	public StringLocated next() {
		return data.get(nb++);
	}

	public StringLocated peek() {
		return data.get(nb);
	}

	public StringLocated peekPrevious() {
		if (nb == 0) {
			return null;
		}
		return data.get(nb - 1);
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}

	public IteratorCounter2 cloneMe() {
		return new IteratorCounter2Impl(data, nb);
	}

}
