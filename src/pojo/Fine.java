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
package pojo;

import java.io.Serializable;
import java.util.LinkedHashMap;
import utilities.Constants;

/**
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class Fine implements Serializable {
    
    private LinkedHashMap<String, Integer> fineHashMap;

    public Fine() {
        fineHashMap = new LinkedHashMap<>();
        initializeFineHashMap();
    }
        
    private void initializeFineHashMap() {
        fineHashMap.put(Constants.DEPARTMENT_CSE, -1);
        fineHashMap.put(Constants.DEPARTMENT_EEE, -1);
        fineHashMap.put(Constants.DEPARTMENT_ECE, -1);
        fineHashMap.put(Constants.DEPARTMENT_ME, -1);
        fineHashMap.put(Constants.ADMINISTRATION_LIBRARY, -1);        
    }
    
    // getters and setters   
    public int getCSEDepartmentFine() {
        return fineHashMap.get(Constants.DEPARTMENT_CSE);
    }
    
    public void setCSEDepartmentFine(int amount) {
        fineHashMap.put(Constants.DEPARTMENT_CSE, amount);
    }
    
    public int getECEDepartmentFine() {
        return fineHashMap.get(Constants.DEPARTMENT_ECE);
    }
    
    public void setECEDepartmentFine(int amount) {
        fineHashMap.put(Constants.DEPARTMENT_ECE, amount);
    }

    public int getEEEDepartmentFine() {
        return fineHashMap.get(Constants.DEPARTMENT_EEE);
    }
    
    public void setEEEDepartmentFine(int amount) {
        fineHashMap.put(Constants.DEPARTMENT_EEE, amount);
    }
    public int getMEDepartmentFine() {
        return fineHashMap.get(Constants.DEPARTMENT_ME);
    }
    
    public void setMEDepartmentFine(int amount) {
        fineHashMap.put(Constants.DEPARTMENT_ME, amount);
    }
    
    public int getLibraryFine() {
        return fineHashMap.get(Constants.ADMINISTRATION_LIBRARY);
    }
    
    public void setLibraryFine(int amount) {
        fineHashMap.put(Constants.ADMINISTRATION_LIBRARY, amount);
    }

    public LinkedHashMap<String, Integer> getFineHashMap() {
        return fineHashMap;
    }

    public void setFineHashMap(LinkedHashMap<String, Integer> fineHashMap) {
        this.fineHashMap = fineHashMap;
    }

    @Override
    public String toString() {
        return "Fine{" + "fineHashMap=" + fineHashMap + '}';
    }
}
