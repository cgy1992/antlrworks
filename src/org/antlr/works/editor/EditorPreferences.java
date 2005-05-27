package org.antlr.works.editor;

import edu.usfca.xj.appkit.app.XJApplication;
import edu.usfca.xj.appkit.app.XJPreferences;

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

public class EditorPreferences {

    // General
    public static final String PREF_STARTUP_ACTION = "PREF_STARTUP_ACTION";
    public static final String PREF_TAB_WIDTH = "PREF_TAB_WIDTH";
    public static final String PREF_EDITOR_FONT = "PREF_EDITOR_FONT";
    public static final String PREF_EDITOR_FONT_SIZE = "PREF_EDITOR_FONT_SIZE";
    public static final String PREF_LOOK_AND_FEEL = "PREF_LOOK_AND_FEEL";

    public static final int DEFAULT_TAB_WIDTH = 8;
    public static final String DEFAULT_EDITOR_FONT = "Courier New";
    public static final int DEFAULT_EDITOR_FONT_SIZE = 12;

    public static final int STARTUP_NEW_DOC = 0;
    public static final int STARTUP_OPEN_LAST_DOC = 1;

    // Compiler
    public static final String PREF_JIKES_PATH = "PREF_JIKES_PATH";
    public static final String PREF_COMPILER = "PREF_COMPILER";

    public static final String DEFAULT_JIKES_PATH = "";
    public static final String DEFAULT_COMPILER = "javac";

    public static final String COMPILER_JAVAC = "javac";
    public static final String COMPILER_JIKES = "jikes";
    public static final String COMPILER_INTEGRATED = "integrated";

    // Statistics
    public static final String PREF_STATS_REMINDER_METHOD = "PREF_STATS_REMINDER_METHOD";
    public static final String PREF_STATS_REMINDER_NEXT_DATE = "PREF_STATS_REMINDER_NEXT_DATE";

    public static final int STATS_REMINDER_MANUALLY = 0;
    public static final int STATS_REMINDER_WEEKLY = 1;

    public static final int DEFAULT_STATS_REMINDER_METHOD = STATS_REMINDER_WEEKLY;

    // Updates
    public static final String PREF_UPDATE_TYPE = "PREF_UPDATE_TYPE";
    public static final String PREF_DOWNLOAD_PATH = "PREF_DOWNLOAD_PATH";
    public static final String PREF_UPDATE_NEXT_DATE = "PREF_UPDATE_NEXT_DATE";

    public static final int UPDATE_MANUALLY = 0;
    public static final int UPDATE_AT_STARTUP = 1;
    public static final int UPDATE_DAILY = 2;
    public static final int UPDATE_WEEKLY = 3;

    public static final int DEFAULT_UPDATE_TYPE = UPDATE_WEEKLY;
    public static final String DEFAULT_DOWNLOAD_PATH = System.getProperty("user.home");

    // Other
    public static final String PREF_USER_REGISTERED = "PREF_USER_REGISTERED";
    public static final String PREF_SERVER_ID = "PREF_SERVER_ID";

    public static final String PREF_OUTPUT_PATH = "KEY_OUTPUT_PATH";
    public static final String PREF_START_SYMBOL = "KEY_START_SYMBOL";

    public static void setOutputPath(String path) {
        getPreferences().setString(PREF_OUTPUT_PATH, path);
    }

    public static String getOutputPath() {
        return getPreferences().getString(PREF_OUTPUT_PATH, "/tmp/antlrworks/");
    }

    public static void setStartSymbol(String startSymbol) {
        getPreferences().setString(PREF_START_SYMBOL, startSymbol);
    }

    public static String getStartSymbol() {
        return getPreferences().getString(PREF_START_SYMBOL, "");
    }

    public static int getStartupAction() {
        return getPreferences().getInt(PREF_STARTUP_ACTION, STARTUP_OPEN_LAST_DOC);
    }

    public static int getTabWidth() {
        return getPreferences().getInt(PREF_TAB_WIDTH, DEFAULT_TAB_WIDTH);
    }

    public static String getEditorFont() {
        return getPreferences().getString(PREF_EDITOR_FONT, DEFAULT_EDITOR_FONT);
    }

    public static int getEditorFontSize() {
        return getPreferences().getInt(PREF_EDITOR_FONT_SIZE, DEFAULT_EDITOR_FONT_SIZE);
    }

    public static String getLookAndFeel() {
        return getPreferences().getString(PREF_LOOK_AND_FEEL, null);
    }

    public static void setJikesPath(String path) {
        getPreferences().setString(PREF_JIKES_PATH, path);
    }

    public static String getJikesPath() {
        return getPreferences().getString(PREF_JIKES_PATH, DEFAULT_JIKES_PATH);
    }

    public static String getCompiler() {
        return getPreferences().getString(PREF_COMPILER, DEFAULT_COMPILER);
    }

    public static int getStatsReminderType() {
        return getPreferences().getInt(PREF_STATS_REMINDER_METHOD, DEFAULT_STATS_REMINDER_METHOD);
    }

    public static void setStatsReminderNextDate(Calendar date) {
        getPreferences().setObject(PREF_STATS_REMINDER_NEXT_DATE, date);
    }

    public static Calendar getStatsReminderNextDate() {
        return (Calendar)getPreferences().getObject(PREF_STATS_REMINDER_NEXT_DATE, null);
    }

    public static int getUpdateType() {
        return getPreferences().getInt(PREF_UPDATE_TYPE, DEFAULT_UPDATE_TYPE);
    }

    public static void setUpdateNextDate(Calendar date) {
        getPreferences().setObject(PREF_UPDATE_NEXT_DATE, date);
    }

    public static Calendar getUpdateNextDate() {
        return (Calendar)getPreferences().getObject(PREF_UPDATE_NEXT_DATE, null);
    }

    public static void setDownloadPath(String path) {
        getPreferences().setString(PREF_DOWNLOAD_PATH, path);
    }

    public static String getDownloadPath() {
        return getPreferences().getString(PREF_DOWNLOAD_PATH, DEFAULT_DOWNLOAD_PATH);
    }

    public static void setUserRegistered(boolean flag) {
        getPreferences().setBoolean(PREF_USER_REGISTERED, flag);
    }

    public static boolean isUserRegistered() {
        return getPreferences().getBoolean(PREF_USER_REGISTERED, false);
    }

    public static void removeUserRegistration() {
        getPreferences().remove(PREF_USER_REGISTERED);
    }

    public static void setServerID(String id) {
        getPreferences().setString(PREF_SERVER_ID, id);
    }

    public static String getServerID() {
        return getPreferences().getString(PREF_SERVER_ID, null);
    }

    public static XJPreferences getPreferences() {
        return XJApplication.shared().getPreferences();
    }

}