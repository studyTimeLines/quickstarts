/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */

package org.switchyard.quickstarts.http.binding;

import javax.inject.Inject;

import org.switchyard.Context;
import org.switchyard.Property;
import org.switchyard.Scope;
import org.switchyard.component.bean.Service;
import org.switchyard.component.common.label.EndpointLabel;
import org.switchyard.component.http.composer.HttpComposition;
import org.switchyard.component.http.composer.HttpContextMapper;
import org.switchyard.component.http.composer.HttpRequestInfo;

/**
 * A SymbolService implementation.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
@Service(SymbolService.class)
public class SymbolServiceImpl implements SymbolService {

    @Inject
    private Context context;

    public String getSymbol(String companyName) {
        String symbol = "";
        if (companyName.equals("headers")) {
            StringBuffer headers = new StringBuffer();
            for (Property property : context.getProperties(Scope.MESSAGE)) {
                if (property.hasLabel(EndpointLabel.HTTP.label()) && (property.getValue() instanceof String)) {
                    headers.append(property.getName());
                    headers.append("=");
                    headers.append(property.getValue());
                }
            }
            return headers.toString();
        }
        if (companyName.equals("requestInfo")) {
            Property prop = context.getProperty(HttpComposition.HTTP_REQUEST_INFO);
            return ((HttpRequestInfo)prop.getValue()).toString();
        }

        // Note the property becomes lower cased when executed on AS7
        Property prop = context.getProperty("content-type");
        if (prop == null) {
            prop = context.getProperty("Content-type");
        }
        String contentType = (prop == null) ? null : (String)prop.getValue();
        if (contentType != null) {
            if (contentType.contains("text/plain")) {
                if (companyName.equalsIgnoreCase("vineyard")) {
                    symbol = "WINE";
                }
            }
        }
        // TODO: Currently not possible to set property on return path for CDI Beans
        /*if (symbol.equals("")) {
            context.setProperty(HttpContextMapper.HTTP_RESPONSE_STATUS, 404).addLabels(new String[]{EndpointLabel.HTTP.label()});
        }*/
        return symbol;
    }
}
