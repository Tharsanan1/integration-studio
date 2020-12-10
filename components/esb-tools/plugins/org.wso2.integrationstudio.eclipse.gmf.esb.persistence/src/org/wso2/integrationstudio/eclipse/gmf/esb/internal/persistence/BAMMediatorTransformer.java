/*
 * Copyright 2012 WSO2, Inc. (http://wso2.com)
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

package org.wso2.integrationstudio.eclipse.gmf.esb.internal.persistence;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.synapse.endpoints.Endpoint;
import org.apache.synapse.mediators.base.SequenceMediator;
import org.eclipse.emf.ecore.EObject;
import org.wso2.carbon.mediator.bam.config.stream.StreamConfiguration;
import org.wso2.integrationstudio.eclipse.gmf.esb.BAMMediator;
import org.wso2.integrationstudio.eclipse.gmf.esb.EsbNode;
import org.wso2.integrationstudio.eclipse.gmf.esb.persistence.TransformationInfo;
import org.wso2.integrationstudio.eclipse.gmf.esb.persistence.TransformerException;
import org.wso2.integrationstudio.eclipse.gmf.esb.persistence.ValidationConstansts;

public class BAMMediatorTransformer extends AbstractEsbNodeTransformer {

    @Override
    public void transform(TransformationInfo information, EsbNode subject) throws TransformerException {
        information.getParentSequence().addChild(createBAMMediator(subject, information, false));

        doTransform(information, ((BAMMediator) subject).getOutputConnector());
    }

    @Override
    public void createSynapseObject(TransformationInfo info, EObject subject, List<Endpoint> endPoints) {
    }

    @Override
    public void transformWithinSequence(TransformationInfo information, EsbNode subject, SequenceMediator sequence)
            throws TransformerException {
        sequence.addChild(createBAMMediator(subject, information, false));
        doTransformWithinSequence(information, ((BAMMediator) subject).getOutputConnector().getOutgoingLink(),
                sequence);

    }

    public static org.wso2.carbon.mediator.bam.BamMediator createBAMMediator(EsbNode subject, TransformationInfo information, boolean isForValidation)
            throws TransformerException {

        if (subject instanceof BAMMediator) {
            BAMMediator visualBAMMediator = (BAMMediator) subject;

            org.wso2.carbon.mediator.bam.BamMediator bamMediator = new org.wso2.carbon.mediator.bam.BamMediator();
            setCommonProperties(bamMediator, visualBAMMediator);
            if (StringUtils.isNotEmpty(visualBAMMediator.getServerProfile())) {
                bamMediator.setServerProfile(visualBAMMediator.getServerProfile());
            } else if(!isForValidation){
                // Set the default values for use the synapse transformers
                bamMediator.setServerProfile(ValidationConstansts.DEFAULT_XPATH_FOR_VALIDATION);
            }

            if (StringUtils.isNotEmpty(visualBAMMediator.getStreamName())) {
                if (StringUtils.isNotEmpty(visualBAMMediator.getStreamVersion())) {
                    StreamConfiguration streamConfiguration = new StreamConfiguration();
                    streamConfiguration.setName(visualBAMMediator.getStreamName());
                    streamConfiguration.setVersion(visualBAMMediator.getStreamVersion());
                    bamMediator.getStream().setStreamConfiguration(streamConfiguration);
                } else if(!isForValidation){
                    // Set the default values for use the synapse transformers
                    StreamConfiguration streamConfiguration = new StreamConfiguration();
                    streamConfiguration.setName(ValidationConstansts.DEFAULT_XPATH_FOR_VALIDATION);
                    streamConfiguration.setVersion(ValidationConstansts.DEFAULT_XPATH_FOR_VALIDATION);
                    bamMediator.getStream().setStreamConfiguration(streamConfiguration);
                }
            } else {
                // Set the default values for use the synapse transformers
                StreamConfiguration streamConfiguration = new StreamConfiguration();
                streamConfiguration.setName(ValidationConstansts.DEFAULT_XPATH_FOR_VALIDATION);
                streamConfiguration.setVersion(ValidationConstansts.DEFAULT_XPATH_FOR_VALIDATION);
                bamMediator.getStream().setStreamConfiguration(streamConfiguration);
            }
            return bamMediator;
        } else {
            throw new IllegalArgumentException(Messages.BAMMediatorTransformer_InvalidSubjectErrorMessage);
        }
    }
}
