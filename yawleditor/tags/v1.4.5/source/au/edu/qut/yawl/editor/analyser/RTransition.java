/*
 * Created on 16/02/2006
 * YAWLEditor v1.4 
 *
 * @author Moe Thandar Wyn
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */

package au.edu.qut.yawl.editor.analyser;

import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
   
public class RTransition extends RElement {
  private Set _removeSet = new HashSet();

  public RTransition(String id) {
    super(id);
  }

  public void setRemoveSet(Set removeSet) {
  	
  	 _removeSet.addAll(removeSet);
  	 //Add to populate _cancelledBySet.
     Iterator removeSetIter = removeSet.iterator();
     while (removeSetIter.hasNext()) {
	    RElement element = (RElement) removeSetIter.next();
	    element.addToCancelledBySet(this);
	    }      
   
    
    
  }

  public void setRemoveSet(RPlace p) {
    _removeSet.add(p);
    p.addToCancelledBySet(this);
  }

  public Set getRemoveSet() {
    if (_removeSet != null) {
      return _removeSet;
    }
    return null;
  }

  public void removeFromRemoveSet(RPlace p){
  	
  	_removeSet.remove(p);
  	
  }
  
  public boolean isCancelTransition() {
    return _removeSet.size() > 0;
  }
}
