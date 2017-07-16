// HTMLParser Library $Name: v1_6_20060527 $ - A java-based parser for HTML
// http://sourceforge.org/projects/htmlparser
// Copyright (C) 2004 Derrick Oswald
//
// Revision Control Information
//
// $Source: /cvsroot/htmlparser/htmlparser/src/org/htmlparser/beans/HTMLLinkBean.java,v $
// $Author: derrickoswald $
// $Date: 2005/05/15 11:49:03 $
// $Revision: 1.23 $
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
//

package org.htmlparser.beans;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;


/**
 * Display the links from a URL.
 *
 * @author Derrick Oswald
 *         Created on December 24, 2002, 3:49 PM
 */
public class HTMLLinkBean implements Serializable {

    protected PropertyChangeListener propertyChangeListener;

    /**
     * The underlying bean that provides our htmlparser specific properties.
     */
    protected LinkBean mBean;

    /**
     * Creates a new HTMLTextBean.
     * This uses an underlying StringBean and displays the text.
     */
    public HTMLLinkBean() {
    }

    /**
     * Return the underlying bean object.
     * Creates a new one if it hasn't been initialized yet.
     *
     * @return The StringBean this bean uses to fetch text.
     */
    protected LinkBean getBean() {
        if (null == mBean)
            mBean = new LinkBean();
        return (mBean);
    }

    /**
     * Add a PropertyChangeListener to the listener list.
     * The listener is registered for all properties.
     * <p><em>Delegates to the underlying StringBean</em>
     *
     * @param listener The PropertyChangeListener to be added.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        getBean().addPropertyChangeListener(listener);
        setPropertyChangeListener(listener);
    }

    /**
     * Remove a PropertyChangeListener from the listener list.
     * This removes a registered PropertyChangeListener.
     * <p><em>Delegates to the underlying StringBean</em>
     *
     * @param listener The PropertyChangeListener to be removed.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        getBean().removePropertyChangeListener(listener);
        setPropertyChangeListener(null);
    }

    //
    // Properties
    //

    /**
     * Getter for property links.
     * <p><em>Delegates to the underlying StringBean</em>
     *
     * @return Value of property links.
     */
    public URL[] getLinks() {
        return (getBean().getLinks());
    }

    /**
     * Getter for property URL.
     * <p><em>Delegates to the underlying StringBean</em>
     *
     * @return Value of property URL.
     */
    public String getURL() {
        return (getBean().getURL());
    }

    /**
     * Setter for property URL.
     * <p><em>Delegates to the underlying StringBean</em>
     *
     * @param url New value of property URL.
     */
    public void setURL(String url) {
        getBean().setURL(url);
    }

    /**
     * Getter for property Connection.
     *
     * @return Value of property Connection.
     */
    public URLConnection getConnection() {
        return (getBean().getConnection());
    }

    /**
     * Setter for property Connection.
     *
     * @param connection New value of property Connection.
     */
    public void setConnection(URLConnection connection) {
        getBean().setConnection(connection);
    }

    //
    // PropertyChangeListener inteface
    //

    public PropertyChangeListener getPropertyChangeListener() {
        return propertyChangeListener;
    }

    public void setPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeListener = propertyChangeListener;
    }
}



