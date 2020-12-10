/**
 *
 * $Id$
 */
package org.wso2.integrationstudio.eclipse.ds.validation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.wso2.integrationstudio.eclipse.ds.DataServiceParameter;
import org.wso2.integrationstudio.eclipse.ds.DataSourceConfiguration;
import org.wso2.integrationstudio.eclipse.ds.Description;
import org.wso2.integrationstudio.eclipse.ds.EventTrigger;
import org.wso2.integrationstudio.eclipse.ds.Operation;
import org.wso2.integrationstudio.eclipse.ds.Query;
import org.wso2.integrationstudio.eclipse.ds.RegistryKeyProperty;
import org.wso2.integrationstudio.eclipse.ds.Resource;
import org.wso2.integrationstudio.eclipse.ds.ServiceStatus;

/**
 * A sample validator interface for {@link org.wso2.integrationstudio.eclipse.ds.DataService}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface DataServiceValidator {
	boolean validate();

	boolean validateMixed(FeatureMap value);
	boolean validateDescription(Description value);
	boolean validateConfig(EList<DataSourceConfiguration> value);
	boolean validateQuery(EList<Query> value);
	boolean validateEventTrigger(EList<EventTrigger> value);
	boolean validateOperation(EList<Operation> value);
	boolean validateResource(EList<Resource> value);
	boolean validateBaseURI(String value);
	boolean validateEnableBatchRequests(boolean value);
	boolean validateEnableBoxcarring(boolean value);
	boolean validateEnableDTP(boolean value);
	boolean validateName(String value);
	boolean validateServiceGroup(String value);
	boolean validateServiceNamespace(String value);
	boolean validateServiceStatus(ServiceStatus value);
	boolean validateDisableStreaming(boolean value);
	boolean validateEnableSec(boolean value);
	boolean validatePolicy(RegistryKeyProperty value);
	boolean validateTransports(String value);
	boolean validateFeatureAllowRoles(DataServiceParameter value);
	boolean validateDisableLegacyBoxcarringMode(boolean value);
}
