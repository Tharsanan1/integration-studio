package org.wso2.integrationstudio.eclipse.registry.base.startup;

import org.eclipse.ui.IStartup;
import org.wso2.integrationstudio.eclipse.platform.ui.startup.RegisterUIControl;
import org.wso2.integrationstudio.eclipse.registry.base.ui.util.RegistryBrowserUIControl;

public class RegistryStartup implements IStartup{

	
	public void earlyStartup() {
		RegistryBrowserUIControl control = new RegistryBrowserUIControl();
		RegisterUIControl uiControl = new RegisterUIControl();
		uiControl.registerUIControls("RegistryBrowserUIControl", control);
	}

}
