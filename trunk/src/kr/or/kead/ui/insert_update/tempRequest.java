package kr.or.kead.ui.insert_update;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Font;

public class tempRequest extends JPanel {
	private JLabel lblNewLabel;
	private JComboBox combo_depart;
	private JLabel lblNewLabel_1;
	private JComboBox combo_prof;
	private JLabel lblNewLabel_2;
	private JComboBox combo_course;

	/**
	 * Create the panel.
	 */
	public tempRequest() {

		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(3, 2, 5, 5));
		
		lblNewLabel = new JLabel("학과");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(lblNewLabel);
		
		combo_depart = new JComboBox();
		combo_depart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(combo_depart);
		
		lblNewLabel_1 = new JLabel("교수명");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(lblNewLabel_1);
		
		combo_prof = new JComboBox();
		combo_prof.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(combo_prof);
		
		lblNewLabel_2 = new JLabel("개설강좌");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(lblNewLabel_2);
		
		combo_course = new JComboBox();
		combo_course.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		add(combo_course);
	}

}
