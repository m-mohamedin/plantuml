/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 *

 * This file is part of Smetana.
 * Smetana is a partial translation of Graphviz/Dot sources from C to Java.
 *
 * (C) Copyright 2009-2022, Arnaud Roques
 *
 * This translation is distributed under the same Licence as the original C program:
 * 
 *************************************************************************
 * Copyright (c) 2011 AT&T Intellectual Property 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: See CVS logs. Details at http://www.graphviz.org/
 *************************************************************************
 *
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC
 * LICENSE ("AGREEMENT"). [Eclipse Public License - v 1.0]
 * 
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES
 * RECIPIENT'S ACCEPTANCE OF THIS AGREEMENT.
 * 
 * You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package h;

import smetana.core.UnsupportedStarStruct;

final public class ST_RTree extends UnsupportedStarStruct {


	public ST_Node_t___ root;
	// "Node_t *root",
	public final ST_SplitQ_t split = new ST_SplitQ_t();
	public int MinFill;
	// "long ElapsedTime",
	// "float UserTime, SystemTime",
	public int Deleting;
	public int StatFlag;
	// "int InsertCount",
	// "int DeleteCount",
	// "int ReInsertCount",
	// "int InSplitCount",
	// "int DeSplitCount",
	public int ElimCount;
	// "int EvalCount",
	// "int InTouchCount",
	// "int DeTouchCount",
	public int SeTouchCount;
	// "int CallCount",
	// "float SplitMeritSum",
	public int RectCount;
	public int NodeCount;
	public int LeafCount, NonLeafCount;

	public int EntryCount;



}

// struct RTree {
// Node_t *root;
//
// SplitQ_t split;
//
// /* balance criterion for node splitting */
// int MinFill;
//
// /* times */
// long ElapsedTime;
// float UserTime, SystemTime;
//
// int Deleting;
//
// /* variables for statistics */
// int StatFlag; /* tells if we are counting or not */
// /* counters affected only when StatFlag set */
// int InsertCount;
// int DeleteCount;
// int ReInsertCount;
// int InSplitCount;
// int DeSplitCount;
// int ElimCount;
// int EvalCount;
// int InTouchCount;
// int DeTouchCount;
// int SeTouchCount;
// int CallCount;
// float SplitMeritSum;
//
// /* counters used even when StatFlag not set */
// int RectCount;
// int NodeCount;
// int LeafCount, NonLeafCount;
// int EntryCount;
// int SearchCount;
// int HitCount;
//
// };