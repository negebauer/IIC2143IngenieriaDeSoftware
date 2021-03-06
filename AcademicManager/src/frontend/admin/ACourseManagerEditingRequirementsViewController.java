package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.Course;
import backend.courses.Course.AddOrRemoveRequirementResponse;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MCourseSearcherSelectorViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ACourseManagerEditingRequirementsViewController extends MCourseSearcherSelectorViewController {

	@FXML
	ListView<String> listRequirements;
	@FXML
	Button btnAddRequirement;
	@FXML
	Button btnRemoveRequirement;
	@FXML
	Label labelSelectCourseAsRequirement;
	@FXML
	Label labelModificationResult;

	public static URL view = Object.class.getResource("/frontend/admin/ACourseManagerEditingRequirementsView.fxml");

	@Override
	public void setUp() {
		super.setUp();

		btnAddRequirement.setText(Messages.getUILabel(UILabel.ADD_REQUIREMENT));
		btnRemoveRequirement.setText(Messages.getUILabel(UILabel.REMOVE_REQUIREMENT));
		labelSelectCourseAsRequirement.setText(Messages.getUILabel(UILabel.SELECT_REQUIREMENT));
		labelModificationResult.setText("");

		ArrayList<String> requirements = new ArrayList<String>();
		for (Course requirement : Manager.INSTANCE.currentEditignCourse.getRequirements()) {
			requirements.add(requirement.getInitials());
		}
		listRequirements.setItems(FXCollections.observableArrayList(requirements));
	}

	public void btnAddRequirement_Pressed() {
		if (!chBxSelectedCourse.getSelectionModel().isEmpty()
				& chBxSelectedCourse.getItems().contains(chBxSelectedCourse.getSelectionModel().getSelectedItem())) {
			String rawCourseInfo = chBxSelectedCourse.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse
							.addRequirement(course);
					if (response.success) {
						ObservableList<String> currentRequirements = listRequirements.getItems();
						currentRequirements.add(getParsedCourse(initials, section, name));
						listRequirements.setItems(FXCollections.observableArrayList(currentRequirements));
						labelModificationResult.setText(Messages.getUILabel(UILabel.SUCCESS));
					} else {
						labelModificationResult
								.setText(Messages.getUILabel(UILabel.NOT_ADDED) + ": " + response.response);
					}
					break;
				}
			}
		} else {
			ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION) + "(" + Messages.getUILabel(UILabel.ADD_REQUIREMENT) + ")");
		}
	}

	public void btnRemoveRequirement_Pressed() {
		if (!listRequirements.getSelectionModel().isEmpty()) {
			String rawCourseInfo = listRequirements.getSelectionModel().getSelectedItem();
			String[] parsed = getParsedInitialsSectionName(rawCourseInfo);
			String initials = parsed[0];
			int section = Integer.valueOf(parsed[1]);
			String name = parsed[2];
			for (Course course : coursesToShow) {
				if (course.getInitials().equals(initials) && course.getSection() == section
						&& course.getName().equals(name)) {
					AddOrRemoveRequirementResponse response = Manager.INSTANCE.currentEditignCourse
							.removeRequirement(course);
					if (response.success) {
						ObservableList<String> currentRequirements = listRequirements.getItems();
						currentRequirements.remove(getParsedCourse(initials, section, name));
						listRequirements.setItems(FXCollections.observableArrayList(currentRequirements));
						labelModificationResult.setText(Messages.getUILabel(UILabel.SUCCESS));
					} else {
						labelModificationResult
								.setText(Messages.getUILabel(UILabel.NOT_REMOVED) + ": " + response.response);
					}
					break;
				}
			}
		} else {
			 ViewUtilities.showAlert(Messages.getUILabel(UILabel.ERROR_SELECTION)
			 + "(" + Messages.getUILabel(UILabel.REMOVE_REQUIREMENT) + ")");
		}
	}
}
