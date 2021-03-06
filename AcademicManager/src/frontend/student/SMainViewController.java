package frontend.student;

import java.net.URL;

import backend.manager.Manager;
import backend.others.Messages;
import backend.others.Messages.UILabel;
import backend.users.Student;
import frontend.main.MViewController;
import frontend.others.ViewUtilities;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SMainViewController extends MViewController {

	@FXML
	Button btnShowSchedule;
	@FXML
	Button btnEditSemester;	
	@FXML
	Button btnCurricularAdvance;
	@FXML
	Button btnCourseFinder;
	@FXML
	Button btnAcademicHistory;
	@FXML
	Button btnStudyProgram;
	@FXML
	Button btnForums;	
	@FXML
	Label labelWhatDoNext;
	@FXML
	Label labelWelcomeMessage;
	@FXML
	Label labelUserInformation;

	public final static URL VIEW = Object.class.getResource("/frontend/student/SMainView.fxml");
	private Student USER = (Student) Manager.INSTANCE.currentUser;
	
	@Override
	public void setUp() {
		super.setUp();
		
		ViewUtilities.setButtonText(btnEditSemester, UILabel.STUDENT_EDIT_SEMESTER);
		ViewUtilities.setButtonText(btnCurricularAdvance, UILabel.STUDENT_CURRICULAR_ADVANCE);
		ViewUtilities.setButtonText(btnShowSchedule, UILabel.STUDENT_SEE_SCHEDULE);
		ViewUtilities.setButtonText(btnStudyProgram, UILabel.STUDENT_STUDY_PROGRAM);
		ViewUtilities.setButtonText(btnCourseFinder, UILabel.STUDENT_SEARCH_COURSE);
		ViewUtilities.setButtonText(btnAcademicHistory, UILabel.STUDENT_ACADEMIC_HISTORY);
		ViewUtilities.setButtonText(btnForums, UILabel.STUDENT_SHOW_FORUM);
		
		labelWhatDoNext.setText(Messages.getUILabel(UILabel.WHAT_TO_DO_NEXT_QUESTION));
		labelWelcomeMessage.setText(Messages.getUILabel(UILabel.WELCOME_MESSAGE));
		labelUserInformation.setText(USER.getStudentInformation());
	}

	public void btnShowSchedule_Pressed() {
		ViewUtilities.openView(SShowScheduleController.VIEW, VIEW);
	}

	public void btnEditSemester_Pressed() {
		ViewUtilities.openView(SCurrentSemesterViewController.VIEW, VIEW);
	}

	public void btnCurricularAdvance_Pressed() {
		ViewUtilities.openView(SCurricularAdvanceController.VIEW, VIEW);
	}
	
	public void btnCourseFinder_Pressed() {
		ViewUtilities.openView(SCourseSearcherViewController.VIEW, VIEW);
	}

	public void btnAcademicHistory_Pressed() {
		ViewUtilities.openView(SAcademicHistoryViewController.VIEW, VIEW);
	}
	
	public void btnStudyProgram_Pressed() {
		ViewUtilities.openView(SStudyProgramViewController.VIEW, VIEW);
	}
	
	public void btnForums_Pressed() {
		ViewUtilities.openView(SForumsViewController.VIEW, VIEW);
	}
}