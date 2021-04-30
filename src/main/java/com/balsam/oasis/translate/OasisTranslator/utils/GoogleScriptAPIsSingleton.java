package com.balsam.oasis.translate.OasisTranslator.utils;

import java.util.HashMap;

public class GoogleScriptAPIsSingleton extends HashMap<Integer, String> {

    private GoogleScriptAPIsSingleton() {}

    private static class SingletonHelper {
        private static final GoogleScriptAPIsSingleton instance = new GoogleScriptAPIsSingleton();
    }

    public static GoogleScriptAPIsSingleton getInstance() {
        return SingletonHelper.instance;
    }
}
