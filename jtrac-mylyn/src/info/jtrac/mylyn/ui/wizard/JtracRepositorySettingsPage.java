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

package info.jtrac.mylyn.ui.wizard;

import info.jtrac.mylyn.JtracClient;
import info.jtrac.mylyn.domain.JtracVersion;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.mylyn.tasks.core.RepositoryTemplate;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.AbstractRepositoryConnectorUi;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.mylyn.web.core.WebClientUtil;
import org.eclipse.swt.widgets.Composite;

public class JtracRepositorySettingsPage extends AbstractRepositorySettingsPage {

	public JtracRepositorySettingsPage(AbstractRepositoryConnectorUi repositoryUi) {
		super("JTrac Connection Settings", "Example: http://myserver/jtrac", repositoryUi);
		setNeedsEncoding(false);
	}

	@Override
	protected void createAdditionalControls(Composite parent) {
		for (RepositoryTemplate template : connector.getTemplates()) {
			serverUrlCombo.add(template.repositoryUrl);
		}				
	}

	@Override
	protected boolean isValidUrl(String name) { 
		if ((name.startsWith(URL_PREFIX_HTTPS) || name.startsWith(URL_PREFIX_HTTP)) && !name.endsWith("/")) {
			try {
				new URL(name);
				return true;
			} catch (MalformedURLException e) {
			}
		}
		return false;
	}

	@Override
	public boolean isPageComplete() {
		boolean isComplete = false;
		String url = getServerUrl();
		String label = getRepositoryLabel();
		isComplete = isValidUrl(url) && label != null && label.length() > 0;
		return isComplete;
	}	
	
	@Override
	protected Validator getValidator(TaskRepository repository) {
		return new JtracValidator(repository);
	}	
	
	public class JtracValidator extends Validator {

		final String repositoryUrl;		
		final String username;
		final String password;
		final Proxy proxy;

		public JtracValidator(TaskRepository repository) {
			this.repositoryUrl = repository.getUrl();
			this.username = repository.getUserName();
			this.password = repository.getPassword();
			this.proxy = repository.getProxy();			
		}

		@Override
		public void run(IProgressMonitor monitor) throws CoreException {
			try {
				final String serverUrl = getServerUrl();
				final String username = getUserName();
				final String password = getPassword();
				final Proxy proxy;
				if (getUseDefaultProxy()) {
					proxy = WebClientUtil.getPlatformProxy();
				} else {
					proxy = WebClientUtil.getProxy(getProxyHostname(), getProxyPort(), getProxyUserName(), getProxyPassword());
				}
				getWizard().getContainer().run(true, false, new IRunnableWithProgress() {
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
						monitor.beginTask("Connecting...", IProgressMonitor.UNKNOWN);
						try {				
							JtracClient client = new JtracClient(serverUrl, username, password, proxy);
							JtracVersion version = client.getJtracVersion();
						} catch (Exception e) {
							throw new InvocationTargetException(e);
						} finally {
							monitor.done();
						}
					}
				});
				MessageDialog.openInformation(null, "Success", "Repository is valid.");
			} catch (InvocationTargetException e) {
				MessageDialog.openWarning(null, "Error", e.getCause().getMessage());
			} catch (Exception e) {
				MessageDialog.openWarning(null, "Error", e.getMessage());
			}
		}
	}

}
