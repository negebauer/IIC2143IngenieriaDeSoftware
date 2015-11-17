package frontend.admin;

import java.net.URL;
import java.util.ArrayList;

import backend.courses.StudyProgram;
import backend.enums.School;
import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AStudyProgramManagerEditingViewController extends MViewController {
	
	@FXML
	Label labelName;
	@FXML
	Label labelYear;
	@FXML
	Label labelMaxCreditsPerSemester;
	@FXML
	Label labelMaxFailedCredits;
	@FXML
	Label labelSchool;
	@FXML
	TextField txBxNameStudyProgram;
	@FXML
	TextField txBxYearStudyProgram;
	@FXML
	TextField txBxMaxCreditsPerSemester;
	@FXML
	TextField txBxMaxFailedCredits;
	@FXML
	ChoiceBox<String> chBxSchoolStudyProgram;
	@FXML
	Button btnEditSemesters;
	@FXML
	Button btnSaveStudyProgram;
	
	
	public static URL view = Object.class.getResource("/frontend/admin/AStudyProgramManagerEditingView.fxml");
	
	public void setUp() {
		super.setUp();
		labelName.setText(Messages.getUILabel(UILabel.NAME));
		labelYear.setText(Messages.getUILabel(UILabel.YEAR));
		labelMaxCreditsPerSemester.setText(Messages.getUILabel(UILabel.MAXIMUM_OF_CREDITS_PER_SEMESTER));
		labelMaxFailedCredits.setText(Messages.getUILabel(UILabel.MAXIMUM_OF_FAILED_CREDITS));
		labelSchool.setText(Messages.getUILabel(UILabel.SCHOOL));
		btnEditSemesters.setText(Messages.getUILabel(UILabel.SEMESTER_CURRENT_SEMESTER_CREATE_NEW));
		btnEditSemesters.setText(Messages.getUILabel(UILabel.EDIT_SEMESTERS));		
		
		ArrayList<String> schools = new ArrayList<String>();
		for (School school : School.values()) {
			schools.add(school.toString());
		}
		chBxSchoolStudyProgram.setItems(FXCollections.observableArrayList(schools));
		
		StudyProgram studyProgram = Manager.INSTANCE.currentEditingStudyProgram;
		if (studyProgram != null) {
			fillFields(studyProgram);
		}
		
	}
	
	public void fillFields(StudyProgram studyProgram) {
		txBxNameStudyProgram.setText(studyProgram.getName());
		txBxYearStudyProgram.setText(studyProgram.getyearProgram() + "");
		txBxMaxCreditsPerSemester.setText(studyProgram.getMaxCreditsPerSemester() + "");
		txBxMaxFailedCredits.setText(studyProgram.getMaxFailedCredits() + "");
		chBxSchoolStudyProgram.getSelectionModel().select(studyProgram.getSchool().toString());
	}
	
	public void btnEditSemesters_Pressed() {
		saveStudyProgram();
		ViewUtilities.openView(ASemesterManagerMainViewController.view, view);
	}
	
	public void btnSaveStudyProgram_Pressed() {
		saveStudyProgram();
		Manager.INSTANCE.currentEditingStudyProgram = null;
		super.btnBack_Pressed();
	}
	
	public void saveStudyProgram() {
		StudyProgram currentProgram = Manager.INSTANCE.currentEditingStudyProgram;
		
		if (Manager.INSTANCE.currentEditingStudyProgram == null) {
			currentProgram = new StudyProgram(null, 0, null, null, 0, 0);
		}
		
		
		String name = txBxNameStudyProgram.getText();
		int year = Integer.parseInt(txBxYearStudyProgram.getText());
		int maxCreditsPerSemester = Integer.parseInt(txBxMaxCreditsPerSemester.getText());
		int maxFailedCredits = Integer.parseInt(txBxMaxFailedCredits.getText());
		
		School school = null;
		if (!chBxSchoolStudyProgram.getSelectionModel().isEmpty()) {
			school = School.valueOf(chBxSchoolStudyProgram.getSelectionModel().getSelectedItem());
		} else {
			school = School.defaultSchool();
		}
		
		currentProgram.setName(name);
		currentProgram.setMaxCreditsPerSemester(maxCreditsPerSemester);
		currentProgram.setMaxFailedCredits(maxFailedCredits);
		currentProgram.setSchool(school);
		currentProgram.setYearProgram(year);
		
		if (Manager.INSTANCE.currentEditingStudyProgram == null) {
			Manager.INSTANCE.currentEditingStudyProgram = currentProgram;
			Manager.INSTANCE.studyPrograms.add(currentProgram);
		}
		
	}
}
