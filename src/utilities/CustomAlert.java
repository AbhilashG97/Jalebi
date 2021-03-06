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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;

/**
 *
 * @author Abhilash G <abhilashg@am.students.amrita.edu>
 */
public class CustomAlert {
    
    private final Alert alert;

    public CustomAlert(String errorMessage) {
        alert = new Alert(Alert.AlertType.WARNING, errorMessage, ButtonType.OK);
    }
    
        public CustomAlert(AlertType alertType, String errorMessage) {
        alert = new Alert(alertType, errorMessage, ButtonType.OK);
    }
    
    public void showAlert() {
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }
}
