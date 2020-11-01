package org.acme.resteasy.Database;

import org.acme.resteasy.model.Classes;
import org.acme.resteasy.model.Students;

import java.util.HashMap;
import java.util.Map;

public class Database {

        private static Map<Long, Students> dbStudentsMap = new HashMap<Long,Students>();
        private static Map<String, Classes> dbClassesMap = new HashMap<String, Classes>();

        public static Map<Long,Students> getAllStudents ()
            {
                return dbStudentsMap;
            }
            public static Map<String,Classes> getAllClasses ()
            {
                return dbClassesMap;
            }
}
