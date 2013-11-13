package kr.or.kead.ui.menu;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

public class UserStateChange extends JPanel {
	private final JLabel lblNewLabel = new JLabel("성명");
	private final JTextField textField = new JTextField();
	private final JLabel lblNewLabel_1 = new JLabel("이메일");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblNewLabel_2 = new JLabel("학과");
	private final JTextField textField_2 = new JTextField();

	/**
	 * Create the panel.
	 */
	public UserStateChange() {
		textField_2.setColumns(10);
		textField.setColumns(10);
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC,
				ColumnSpec.decode("57px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("116px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("57px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("116px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("57px"),},
			new RowSpec[] {
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("21px"),
				FormFactory.LINE_GAP_ROWSPEC,
				RowSpec.decode("21px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		add(lblNewLabel, "2, 2, 3, 1, left, center");
		
		add(textField, "6, 2, 5, 1, fill, top");
		
		add(lblNewLabel_1, "2, 4, 3, 1, left, center");
		
		add(textField_2, "6, 4, 5, 1, fill, top");
		
		add(lblNewLabel_2, "2, 6, 3, 1, left, center");
		textField_1.setColumns(10);
		
		add(textField_1, "6, 6, 5, 1, fill, top");

	}

}
