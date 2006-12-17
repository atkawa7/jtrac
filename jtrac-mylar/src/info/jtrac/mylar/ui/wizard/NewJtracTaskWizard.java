/*
 * Copyright 2002-2005 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.jtrac.mylar.ui.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

public class NewJtracTaskWizard extends Wizard implements INewWizard {

	private NewJtracTaskPage newTaskPage;
		
	public NewJtracTaskWizard() {		
		setWindowTitle("New JTrac Task Wizard");
	}
	
	public void init(IWorkbench workbench, IStructuredSelection selection) {
	}	
	
	@Override
	public void addPages() {
		newTaskPage = new NewJtracTaskPage();
		newTaskPage.setWizard(this);
		addPage(newTaskPage);
	}	
	
	@Override
	public boolean canFinish() {
		return true;
	}	
	
	@Override
	public boolean performFinish() {
		return true;
	}


}
