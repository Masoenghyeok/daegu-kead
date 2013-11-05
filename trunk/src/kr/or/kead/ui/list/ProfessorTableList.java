package kr.or.kead.ui.list;

import kr.or.kead.service.DaoProfessor;
import kr.or.kead.ui.list.model.ProfessorCustomTableModel;

public class ProfessorTableList extends AbsTableList {
	
	public ProfessorTableList(int height) {
		super(new ProfessorCustomTableModel(), height);
	}

	@Override
	public void setColumnSize() {
		columnSize.add(40);
		columnSize.add(120);
		columnSize.add(80);
		columnSize.add(120);
	}

}
