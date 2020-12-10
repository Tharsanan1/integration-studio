/*
 * Copyright 2016 WSO2, Inc. (http://wso2.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.integrationstudio.datamapper.diagram.custom.configuration.operator.transformers;

import static org.wso2.integrationstudio.datamapper.diagram.custom.model.transformers.TransformerConstants.CONSTANT_NOT_OPERATOR;
import static org.wso2.integrationstudio.datamapper.diagram.custom.model.transformers.TransformerConstants.CONSTANT_TRUE;

import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.wso2.integrationstudio.datamapper.SchemaDataType;
import org.wso2.integrationstudio.datamapper.diagram.custom.exception.DataMapperException;
import org.wso2.integrationstudio.datamapper.diagram.custom.generator.DifferentLevelArrayMappingConfigGenerator;
import org.wso2.integrationstudio.datamapper.diagram.custom.generator.ForLoopBean;
import org.wso2.integrationstudio.datamapper.diagram.custom.model.DMOperation;
import org.wso2.integrationstudio.datamapper.diagram.custom.model.DMVariable;
import org.wso2.integrationstudio.datamapper.diagram.custom.util.ScriptGenerationUtil;

/**
 * This class extended from the {@link AbstractDMOperatorTransformer} abstract
 * class and generate script for NOT operation
 */
public class NOTOperatorTransformer extends AbstractDMOperatorTransformer {

	@Override
	public String generateScriptForOperation(Class<?> generatorClass, List<DMVariable> inputVariables,
			List<DMVariable> outputVariables, Map<String, List<SchemaDataType>> variableTypeMap,
			Stack<ForLoopBean> parentForLoopBeanStack, DMOperation operator, List<ForLoopBean> forLoopBeanList,
			Map<String, Integer> outputArrayVariableForLoop,
			Map<String, Integer> outputArrayRootVariableForLoop, List<String> unNamedVariables)
			throws DataMapperException {
		StringBuilder operationBuilder = new StringBuilder();
		operationBuilder.append(appendOutputVariable(operator, outputVariables, variableTypeMap, parentForLoopBeanStack,
				forLoopBeanList, outputArrayVariableForLoop, outputArrayRootVariableForLoop,
				unNamedVariables));
		if (DifferentLevelArrayMappingConfigGenerator.class.equals(generatorClass)) {
			if (inputVariables.size() == 0) {
				/* Default value is true */
				operationBuilder.append(CONSTANT_TRUE);
			} else {
				operationBuilder.append(CONSTANT_NOT_OPERATOR + "("
						+ ScriptGenerationUtil.getPrettyVariableNameInForOperation(inputVariables.get(0),
								variableTypeMap, parentForLoopBeanStack, true, forLoopBeanList,
								outputArrayVariableForLoop,
								outputArrayRootVariableForLoop,
								unNamedVariables)
						+ ")");
			}

			operationBuilder.append(";");

		} else {
			throw new IllegalArgumentException("Unknown MappingConfigGenerator type found : " + generatorClass);
		}
		return operationBuilder.toString();
	}

}
