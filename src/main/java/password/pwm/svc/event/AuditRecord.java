/*
 * Password Management Servlets (PWM)
 * http://www.pwm-project.org
 *
 * Copyright (c) 2006-2009 Novell, Inc.
 * Copyright (c) 2009-2016 The PWM Project
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package password.pwm.svc.event;

import password.pwm.util.secure.PwmRandom;

import java.io.Serializable;
import java.util.Date;

public abstract class AuditRecord implements Serializable {
    protected AuditEvent.Type type;
    protected AuditEvent eventCode;
    protected String guid;
    protected Date timestamp = new Date();
    protected String message;
    protected String narrative;
    protected String xdasTaxonomy;
    protected String xdasOutcome;


    protected AuditRecord(
            final Date timestamp,
            final AuditEvent eventCode,
            final String message
    ) {
        this.type = eventCode.getType();
        this.eventCode = eventCode;
        this.message = message;

        this.timestamp = timestamp;
        this.guid = PwmRandom.getInstance().randomUUID().toString();
    }


    protected AuditRecord(final AuditEvent eventCode, final String message) {
        this(new Date(), eventCode, message);
        this.xdasOutcome = eventCode.getXdasOutcome();
        this.xdasTaxonomy = eventCode.getXdasTaxonomy();
    }

    public AuditEvent.Type getType() {
        return type;
    }

    public AuditEvent getEventCode() {
        return eventCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getGuid() {
        return guid;
    }

    public String getNarrative() {
        return narrative;
    }

    public String getXdasTaxonomy() {
        return xdasTaxonomy;
    }

    public String getXdasOutcome() {
        return xdasOutcome;
    }
}
