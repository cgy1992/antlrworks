package org.antlr.works.util;

import edu.usfca.xj.appkit.app.XJApplication;
import edu.usfca.xj.appkit.update.XJUpdateManager;
import edu.usfca.xj.appkit.utils.BrowserLauncher;
import edu.usfca.xj.appkit.utils.XJAlert;
import edu.usfca.xj.foundation.XJSystem;
import edu.usfca.xj.foundation.XJUtils;
import edu.usfca.xj.foundation.timer.XJScheduledTimerDelegate;
import org.antlr.Tool;
import org.antlr.works.dialog.DialogReports;
import org.antlr.works.editor.EditorPreferences;

import java.awt.*;
import java.io.IOException;
import java.util.Calendar;

/*

[The "BSD licence"]
Copyright (c) 2004-05 Jean Bovet
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

public class HelpManager implements XJScheduledTimerDelegate {

    public void scheduledTimerFired(boolean startup) {
        checkUpdatesAuto(startup);
        checkStatsReminder();
    }

    public static void submitStats(Container parent) {
        new DialogReports(parent).runModal();
    }

    public static void sendFeedback(Container parent) {
        StringBuffer url = new StringBuffer(Localizable.getLocalizedString(Localizable.FEEDBACK_URL));
        url.append("?ANTLRVersion=");
        url.append(XJUtils.encodeToURL(Tool.Version));
        url.append("&ANTLRWorksVersion=");
        url.append(XJUtils.encodeToURL(XJApplication.getAppVersionShort()));
        url.append("&OS=");
        url.append(XJUtils.encodeToURL(XJSystem.getOSName()));
        url.append("&JavaVersion=");
        url.append(XJUtils.encodeToURL(XJSystem.getJavaRuntimeVersion()));

        try {
            BrowserLauncher.openURL(url.toString());
        } catch (IOException e) {
            XJAlert.display(parent, "Error", "Cannot display the feedback page because:\n"+e+"\n\nTo report a feedback, go to "+url+".");
        }
    }

    public static void checkUpdates(Container parent, boolean automatic) {
        XJUpdateManager um = new XJUpdateManager(parent, null);
        um.checkForUpdates(Localizable.getLocalizedString(Localizable.APP_VERSION_SHORT),
                           Localizable.getLocalizedString(Localizable.UPDATE_XML_URL),
                           EditorPreferences.getDownloadPath(),
                           automatic);
    }

    public static void checkUpdatesAuto(boolean atStartup) {
        int method = EditorPreferences.getUpdateType();
        boolean check = false;

        if(method == EditorPreferences.UPDATE_MANUALLY)
            check = false;
        else if(method == EditorPreferences.UPDATE_AT_STARTUP && atStartup)
            check = true;
        else {
            Calendar currentCalendar = Calendar.getInstance();
            Calendar nextUpdateCalendar = EditorPreferences.getUpdateNextDate();

            if(nextUpdateCalendar == null || currentCalendar.equals(nextUpdateCalendar) || currentCalendar.after(nextUpdateCalendar)) {

                switch(method) {
                    case EditorPreferences.UPDATE_DAILY:
                        check = nextUpdateCalendar != null;
                        currentCalendar.add(Calendar.DATE, 1);
                        EditorPreferences.setUpdateNextDate(currentCalendar);
                        break;
                    case EditorPreferences.UPDATE_WEEKLY:
                        check = nextUpdateCalendar != null;
                        currentCalendar.add(Calendar.DATE, 7);
                        EditorPreferences.setUpdateNextDate(currentCalendar);
                        break;
                }
            }
        }

        if(check) {
            checkUpdates(null, true);
        }
    }

    public void checkStatsReminder() {
        int method = EditorPreferences.getStatsReminderType();
        boolean remind = false;

        if(method == EditorPreferences.STATS_REMINDER_MANUALLY)
            remind = false;
        else {
            Calendar currentCalendar = Calendar.getInstance();
            Calendar nextUpdateCalendar = EditorPreferences.getStatsReminderNextDate();

            if(nextUpdateCalendar == null || currentCalendar.equals(nextUpdateCalendar) || currentCalendar.after(nextUpdateCalendar)) {

                switch(method) {
                    case EditorPreferences.STATS_REMINDER_WEEKLY:
                        remind = nextUpdateCalendar != null;
                        currentCalendar.add(Calendar.DATE, 7);
                        EditorPreferences.setStatsReminderNextDate(currentCalendar);
                        break;
                }
            }
        }

        if(remind) {
            new DialogReports(null).runModal();
        }
    }
}