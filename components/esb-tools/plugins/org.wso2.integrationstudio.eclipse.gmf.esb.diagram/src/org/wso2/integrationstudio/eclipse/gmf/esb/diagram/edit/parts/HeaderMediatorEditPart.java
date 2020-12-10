/*
 * Copyright (c) 2014-2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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
package org.wso2.integrationstudio.eclipse.gmf.esb.diagram.edit.parts;

import static org.wso2.integrationstudio.eclipse.gmf.esb.diagram.edit.parts.EditPartConstants.DEFAULT_PROPERTY_VALUE_TEXT;
import static org.wso2.integrationstudio.eclipse.gmf.esb.diagram.edit.parts.EditPartConstants.HEADER_MEDIATOR_ICON_PATH;

import org.apache.axiom.om.OMElement;
import org.apache.commons.lang.StringUtils;
import org.apache.synapse.SynapseException;
import org.apache.synapse.config.xml.HeaderMediatorSerializer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderItemLocator;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.infra.gmfdiag.css.CSSNodeImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.jaxen.JaxenException;
import org.wso2.integrationstudio.eclipse.gmf.esb.EsbNode;
import org.wso2.integrationstudio.eclipse.gmf.esb.HeaderAction;
import org.wso2.integrationstudio.eclipse.gmf.esb.HeaderValueType;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.Activator;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.custom.EsbGraphicalShapeWithLabel;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.custom.FixedBorderItemLocator;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.custom.FixedSizedAbstractMediator;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.custom.ShowPropertyViewEditPolicy;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.custom.editpolicy.FeedbackIndicateDragDropEditPolicy;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.custom.utils.CustomToolTip;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.edit.policies.HeaderMediatorCanonicalEditPolicy;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.edit.policies.HeaderMediatorItemSemanticEditPolicy;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.part.EsbVisualIDRegistry;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.validator.GraphicalValidatorUtil;
import org.wso2.integrationstudio.eclipse.gmf.esb.diagram.validator.MediatorValidationUtil;
import org.wso2.integrationstudio.eclipse.gmf.esb.impl.HeaderMediatorImpl;
import org.wso2.integrationstudio.eclipse.gmf.esb.internal.persistence.HeaderMediatorTransformer;
import org.wso2.integrationstudio.eclipse.logging.core.IIntegrationStudioLog;
import org.wso2.integrationstudio.eclipse.logging.core.Logger;

/**
 * @generated NOT
 */
public class HeaderMediatorEditPart extends FixedSizedAbstractMediator {

    private static IIntegrationStudioLog log = Logger.getLog(Activator.PLUGIN_ID);
    /**
     * @generated
     */
    public static final int VISUAL_ID = 3516;

    /**
     * @generated
     */
    protected IFigure contentPane;

    /**
     * @generated
     */
    public HeaderMediatorEditPart(View view) {
        super(view);
    }

    /**
     * @generated NOT
     */
    protected void createDefaultEditPolicies() {
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new HeaderMediatorItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new FeedbackIndicateDragDropEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new HeaderMediatorCanonicalEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        // For handle Double click Event.
        installEditPolicy(EditPolicyRoles.OPEN_ROLE, new ShowPropertyViewEditPolicy());
        // XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable
        // editpolicies
        // removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * @generated
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(EditPart child) {
                View childView = (View) child.getModel();
                switch (EsbVisualIDRegistry.getVisualID(childView)) {
                case HeaderMediatorInputConnectorEditPart.VISUAL_ID:
                case HeaderMediatorOutputConnectorEditPart.VISUAL_ID:
                    return new BorderItemSelectionEditPolicy();
                }
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }
        };
        return lep;
    }

    /**
     * @generated NOT
     */
    protected IFigure createNodeShape() {
        return primaryShape = new HeaderMediatorFigure(new Color(null, 255, 255, 255)) {
            public void setBounds(org.eclipse.draw2d.geometry.Rectangle rect) {
                super.setBounds(rect);
                if (this.getBounds().getLocation().x != 0 && this.getBounds().getLocation().y != 0) {
                    connectToMostSuitableElement();
                    reAllocate(rect);
                }
            };
        };
    }

    /**
     * @generated
     */
    public HeaderMediatorFigure getPrimaryShape() {
        return (HeaderMediatorFigure) primaryShape;
    }

    /**
     * @generated NOT
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof HeaderMediatorValueLiteralEditPart) {
            ((HeaderMediatorValueLiteralEditPart) childEditPart)
                    .setLabel(getPrimaryShape().getFigureHeaderMediatorPropertyValue());
            return true;
        }
        if (childEditPart instanceof HeaderMediatorDescriptionEditPart) {
            ((HeaderMediatorDescriptionEditPart) childEditPart)
                    .setLabel(getPrimaryShape().getHeaderMediatorDescriptionLabel());
            return true;
        }
        if (childEditPart instanceof HeaderMediatorInputConnectorEditPart) {
            IFigure borderItemFigure = ((HeaderMediatorInputConnectorEditPart) childEditPart).getFigure();
            BorderItemLocator locator = new FixedBorderItemLocator(getMainFigure(), borderItemFigure,
                    PositionConstants.WEST, 0.5);
            getBorderedFigure().getBorderItemContainer().add(borderItemFigure, locator);
            return true;
        }
        if (childEditPart instanceof HeaderMediatorOutputConnectorEditPart) {
            IFigure borderItemFigure = ((HeaderMediatorOutputConnectorEditPart) childEditPart).getFigure();
            BorderItemLocator locator = new FixedBorderItemLocator(getMainFigure(), borderItemFigure,
                    PositionConstants.EAST, 0.5);
            getBorderedFigure().getBorderItemContainer().add(borderItemFigure, locator);
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected boolean removeFixedChild(EditPart childEditPart) {
        if (childEditPart instanceof HeaderMediatorValueLiteralEditPart) {
            return true;
        }
        if (childEditPart instanceof HeaderMediatorDescriptionEditPart) {
            return true;
        }
        if (childEditPart instanceof HeaderMediatorInputConnectorEditPart) {
            getBorderedFigure().getBorderItemContainer()
                    .remove(((HeaderMediatorInputConnectorEditPart) childEditPart).getFigure());
            return true;
        }
        if (childEditPart instanceof HeaderMediatorOutputConnectorEditPart) {
            getBorderedFigure().getBorderItemContainer()
                    .remove(((HeaderMediatorOutputConnectorEditPart) childEditPart).getFigure());
            return true;
        }
        return false;
    }

    /**
     * @generated
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * @generated
     */
    protected void removeChildVisual(EditPart childEditPart) {
        if (removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * @generated
     */
    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        if (editPart instanceof IBorderItemEditPart) {
            return getBorderedFigure().getBorderItemContainer();
        }
        return getContentPane();
    }

    /**
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model
     * so you may safely remove <i>generated</i> tag and modify it.
     * 
     * @generated NOT
     */
    protected NodeFigure createMainFigure() {
        NodeFigure figure = createNodePlate();
        figure.setLayoutManager(new ToolbarLayout(true));
        IFigure shape = createNodeShape();
        figure.add(shape);
        contentPane = setupContentPane(shape);
        return figure;
    }

    /**
     * Default implementation treats passed figure as content pane.
     * Respects layout one may have set for generated figure.
     * 
     * @param nodeShape instance of generated figure class
     * @generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
        if (nodeShape.getLayoutManager() == null) {
            ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(5);
            nodeShape.setLayoutManager(layout);
        }
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * @generated
     */
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * @generated
     */
    protected void setForegroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setForegroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setBackgroundColor(Color color) {
        if (primaryShape != null) {
            primaryShape.setBackgroundColor(color);
        }
    }

    /**
     * @generated
     */
    protected void setLineWidth(int width) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineWidth(width);
        }
    }

    /**
     * @generated
     */
    protected void setLineType(int style) {
        if (primaryShape instanceof Shape) {
            ((Shape) primaryShape).setLineStyle(style);
        }
    }

    /*	*//**
           * @generated
           */
    /*
     * public EditPart getPrimaryChildEditPart() {
     * return getChildBySemanticHint(EsbVisualIDRegistry
     * .getType(HeaderMediatorValueLiteralEditPart.VISUAL_ID));
     * }
     */

    /**
     * @generated
     */
    public class HeaderMediatorFigure extends EsbGraphicalShapeWithLabel {

        /**
         * @generated
         */
        private WrappingLabel fFigureHeaderMediatorPropertyValue;

        private WrappingLabel headerMediatorDescriptionLabel;

        /**
         * @generated
         */
        public HeaderMediatorFigure(Color borderColor) {
            super(borderColor, false);
            this.setBackgroundColor(THIS_BACK);
            createContents();
        }

        /**
         * @generated NOT
         */
        private void createContents() {

            fFigureHeaderMediatorPropertyValue = new WrappingLabel();
            fFigureHeaderMediatorPropertyValue.setText(DEFAULT_PROPERTY_VALUE_TEXT);
            fFigureHeaderMediatorPropertyValue.setAlignment(SWT.CENTER);
            headerMediatorDescriptionLabel = getPropertyNameLabel();
        }

        /**
         * @generated NOT
         */
        public WrappingLabel getFigureHeaderMediatorPropertyValue() {
            return fFigureHeaderMediatorPropertyValue;
        }

        public WrappingLabel getHeaderMediatorDescriptionLabel() {
            return headerMediatorDescriptionLabel;
        }

        public String getIconPath() {
            return HEADER_MEDIATOR_ICON_PATH;
        }

        public String getNodeName() {
            return Messages.HeaderMediatorEditPart_NodeName;
        }

        public IFigure getToolTip() {
            if (StringUtils.isEmpty(toolTipMessage)) {
                toolTipMessage = Messages.HeaderMediatorEditPart_ToolTipMessage;
            }
            return new CustomToolTip().getCustomToolTipShape(toolTipMessage);
        }

    }

    @Override
    public void notifyChanged(Notification notification) {
        // this.getModel() will get EMF datamodel of the header mediator datamodel
        if (notification.getEventType() == Notification.SET && this.getModel() instanceof CSSNodeImpl) {
            // The following part will check for validation issues with the current data in
            // the model
            CSSNodeImpl model = (CSSNodeImpl) this.getModel();
            if (model.getElement() instanceof HeaderMediatorImpl) {
                HeaderMediatorImpl headerMediatorDataModel = (HeaderMediatorImpl) model.getElement();
                boolean hasError = false;
                try {
                    if (headerMediatorDataModel.getHeaderName().getPropertyValue().equals("")) {
                        hasError = true;
                    }
                    if (!hasError && headerMediatorDataModel.getHeaderAction() == HeaderAction.SET
                            && headerMediatorDataModel.getValueType() == HeaderValueType.EXPRESSION) {
                        if (headerMediatorDataModel.getValueExpression().getPropertyValue().equals("")) {
                            hasError = true;
                        }
                    }
                    if (hasError) {
                        GraphicalValidatorUtil.addValidationMark(this);
                    } else {
                        GraphicalValidatorUtil.removeValidationMark(this);
                    }
                } catch (Exception e) {
                    // Skip error since it's a validation related minor issue
                    log.error("Graphical validation error occured", e);
                }
            }
        }
        super.notifyChanged(notification);
    }

    /**
     * @generated
     */
    static final Color THIS_BACK = new Color(null, 230, 230, 230);

}
