/*
 * Copyright 2019 Abhilash G <abhilashg@am.students.amrita.edu>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utilities;

import java.io.UnsupportedEncodingException;

/**
 * A class which contains static fields and methods
 * 
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Constants {
    
    public static final String DEPARTMENT_CSE = "CSE";
    
    public static final String DEPARTMENT_ECE = "ECE";
    
    public static final String DEPARTMENT_EEE = "EEE";
    
    public static final String DEPARTMENT_ME = "ME";
    
    public static final String ADMINISTRATION_LIBRARY = "Library";   
    
    public static final String getRupeeSymbol() throws UnsupportedEncodingException {
        return new String("\u20B9".getBytes("UTF-8"), "UTF-8");
    }     
}
