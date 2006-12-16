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

package info.jtrac.mylar;

import java.io.File;

import org.eclipse.mylar.tasks.core.ITaskRepositoryListener;
import org.eclipse.mylar.tasks.core.TaskRepository;

public class JtracTaskRepositoryListener implements ITaskRepositoryListener {

	private File configFile;
	
	public JtracTaskRepositoryListener(File configFile) {
		this.configFile = configFile;
		readConfig();
	}
	
	public void repositoriesRead() {
		// TODO Auto-generated method stub
		
	}

	public void repositoryAdded(TaskRepository repository) {
		// TODO Auto-generated method stub
		
	}

	public void repositoryRemoved(TaskRepository repository) {
		// TODO Auto-generated method stub
		
	}

	public void repositorySettingsChanged(TaskRepository repository) {
		// TODO Auto-generated method stub
		
	}
	
	public void readConfig() {
		
	}
	
	public void writeConfig() {
		
	}

}
