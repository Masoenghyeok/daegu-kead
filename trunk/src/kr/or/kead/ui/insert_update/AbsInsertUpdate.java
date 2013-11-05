package kr.or.kead.ui.insert_update;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import kr.or.kead.service.DaoTable;

public abstract class AbsInsertUpdate extends JDialog implements ActionListener {
	private JPanel btnPanel;
	private JPanel mainPanel;
	private JButton btnOk;
	private JButton btnCancel;
	protected Object obj;
	private String title;
	protected DaoTable daoTable;
	private int res;
	
	public AbsInsertUpdate(Object obj, String title) {
		this.obj = obj;
		this.title = title;	
		initialize();
		pack();
		setModal(true);
	}
	
	private void initialize() {
		initDao();								// 상속받은 클래스의 initDao()수행
		setLayout(new BorderLayout(0, 0));
		
		btnPanel = new JPanel();
		add(btnPanel, BorderLayout.SOUTH);
		
		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		btnPanel.add(btnOk);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		btnPanel.add(btnCancel);
				
		
		mainPanel = getMainPanel();
		add(mainPanel, BorderLayout.CENTER);
		
		if(obj != null) {	// 수정
			setTitle(title + "수정");
			btnOk.setText("수정");
			updateInit();
		}else { 			// 삽입
			setTitle(title + "추가");
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOk) {			
			btnOkActionPerformed(e);
		}else if (e.getSource() == btnCancel) {
			dispose();
		}
	}	
	
	protected void btnOkActionPerformed(ActionEvent e) {		
		if (isValidCheck()) {								// 데이터 유효성 체크
			Object obj = getObject();						// 필요한 클래스를 가지고 옴			
			if (e.getActionCommand() == "추가") {			
				isOK(daoTable.insertDao(obj), "삽입");
			}else {	
				System.out.println(obj);
				int i = daoTable.updateDao(obj);
				System.out.println("i = " +i);
//				isOK(daoTable.updateDao(obj), "수정");
				isOK(i, "수정");
			}
			dispose();
		}		
	}
	
	private void isOK(int result, String msg) {
		this.res = result;
		if(result == 0) {
			JOptionPane.showMessageDialog(null, msg + "완료"); 
		}else {
			JOptionPane.showMessageDialog(null, msg + "실패");
		}		
	}
	
	public int showDialog() {
		return res; 									// -1 또는 0
	}


	protected abstract void initDao();			
	protected abstract void updateInit();
	protected abstract Object getObject();
	protected abstract boolean isValidCheck();
	protected abstract JPanel getMainPanel();
	
}
