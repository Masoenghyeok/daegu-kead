package kr.or.kead.ui.insert_update;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kr.or.kead.service.DaoProfessor;

public class ProfessorInsertUpdate extends AbsInsertUpdate {
		
	
	public ProfessorInsertUpdate(Object obj) {
		super(obj, "교수 관리");		
	}

	@Override
	protected void initDao() {
		daoTable = new DaoProfessor();
	}

	@Override
	protected void updateInit() {
 

	}

	@Override
	protected Object getObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isValidCheck() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected JPanel getMainPanel() {
		JPanel centerPanel = new JPanel();
		
		
		
		return centerPanel;
	}

}
