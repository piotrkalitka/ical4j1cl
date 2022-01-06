/*
 *  Copyright (c) 2021, Ben Fortuna
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *   o Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *   o Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *
 *   o Neither the name of Ben Fortuna nor the names of any other contributors
 *  may be used to endorse or promote products derived from this software
 *  without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package net.fortuna.ical4j.validate.component;

import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VJournal;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.validate.ComponentValidator;
import net.fortuna.ical4j.validate.ValidationException;
import net.fortuna.ical4j.validate.Validator;

import java.util.Optional;

public class VJournalValidator implements Validator<VJournal> {

    @Override
    public void validate(VJournal target) throws ValidationException {
        ComponentValidator.VJOURNAL.validate(target);

        final Optional<Status> status = target.getProperty(Property.STATUS);
        if (status.isPresent() && !Status.VJOURNAL_DRAFT.getValue().equals(status.get().getValue())
                && !Status.VJOURNAL_FINAL.getValue().equals(status.get().getValue())
                && !Status.VJOURNAL_CANCELLED.getValue().equals(status.get().getValue())) {
            throw new ValidationException("Status property [" + status + "] may not occur in VJOURNAL");
        }
    }
}