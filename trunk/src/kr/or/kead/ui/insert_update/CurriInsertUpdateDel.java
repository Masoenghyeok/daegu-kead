package kr.or.kead.ui.insert_update;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoProfessor;

public class CurriInsertUpdateDel extends JPanel implements ActionListener {
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblDepart;
	private JComboBox<Object> comboDepart;
	private JLabel lbl_subject;
	private JList<Object> listCurriculum;
	private JComboBox<String> comboSubejct;
	private DefaultListModel<Object> listModel;
	private HashMap<Integer, JLabel>hashLabel;
	
	
	private DaoProfessor daoProf;
	private DaoDepart daoDepart;
	private JButton btnCourseAdd;
	private JPanel panel_2;
	private JButton btnSave;
	private JButton btnCancel;
	
	private int labelCnt=0;
	
	private ArrayList<JLabel> arLabel;
	
	public CurriInsertUpdateDel() {
		daoDepart = new DaoDepart();
		daoProf = new DaoProfessor();
		arLabel = new ArrayList<>();
		initialize();
	}
	private void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		lblDepart = new JLabel("학      과");
		lblDepart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(lblDepart);
		
		
		ArrayList<String>arList = daoDepart.selectTableAllList();
		Object[] obj = arList.toArray();
		comboDepart = new JComboBox(obj);
		comboDepart.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboDepart.addActionListener(this);
		panel.setLayout(new GridLayout(0, 5, 5, 5));
		panel.add(comboDepart);
				
		lbl_subject = new JLabel("과      정");
		lbl_subject.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(lbl_subject);		
		
		btnCourseAdd = new JButton("추      가");
		btnCourseAdd.addActionListener(this);
		
		comboSubejct = new JComboBox();		
		comboSubejct.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		comboSubejct.addActionListener(this);
		panel.add(comboSubejct);
		btnCourseAdd.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel.add(btnCourseAdd);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		listCurriculum = new JList();
		panel_1.add(listCurriculum);
		listModel = new DefaultListModel<>();
		
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.SOUTH);
		
		btnSave = new JButton("저      장");
		btnSave.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel_2.add(btnSave);
		
		btnCancel = new JButton("취      소");
		btnCancel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		panel_2.add(btnCancel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCourseAdd) {
			btnCourseAddActionPerformed(e);
		}
		if (e.getSource() == comboDepart) {
			comboDepartActionPerformed(e);
		}
		if (e.getSource() == comboSubejct) {
			comboSubejctActionPerformed(e);
		}
	}
	protected void comboSubejctActionPerformed(ActionEvent e) {
	}
	protected void comboDepartActionPerformed(ActionEvent e) {
		StringTokenizer st = new StringTokenizer((String)comboDepart.getSelectedItem(), ":");
		ArrayList<String>arProf = daoProf.selectDepartCodeByCourse(Integer.parseInt(st.nextToken()));
		Object[] obj = arProf.toArray();
		int len = arProf.size();
		comboSubejct.removeAllItems();
		for(int i =0;i<len;i++) {			
			comboSubejct.addItem(arProf.get(i));
		}
	}
	protected void btnCourseAddActionPerformed(ActionEvent e) {
		StringTokenizer st = new StringTokenizer((String)comboDepart.getSelectedItem(), ":");
		st.nextToken();
		String str = st.nextToken()+" " + comboSubejct.getSelectedItem();
		ImageIcon icon = new ImageIcon("C:\\Users\\it28\\Pictures\\stop.jpg");
		final JLabel label = new JLabel(str);	
		label.setIcon(icon);
		hashLabel.(labelCnt, label);
		labelCnt++;
		arLabel.add(label);
		Object[] obj = arLabel.toArray();
//		listModel.addElement(label);		
//		listCurriculum.setModel(listModel);
		listCurriculum.setListData(obj);
//		listCurriculum.setCellRenderer(new ListCellRenderer<E>() {
//			
//		});
		listCurriculum.setCellRenderer(new LabelSet());
		listCurriculum.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent e) {				
				JLabel label = hashLabel.get(listCurriculum.getSelectedIndex());
				System.out.println(label);
				label.setVisible(false);
				label.updateUI();
				listCurriculum.updateUI();
								
			}
		});
		/*
		listCurriculum.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {				
				JList list = (JList)e.getSource();
		        if (e.getClickCount() == 2) {
		            int index = list.locationToIndex(e.getPoint());
		            list.		            
		        } else if (e.getClickCount() == 3) {   // Triple-click
		            int index = list.locationToIndex(e.getPoint());
		        }
		        
			}
			
		});
		*/
		validate();		
	}
	
	class LabelSet extends JLabel implements ListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			JLabel l = (JLabel) value;
			this.setText(l.getText());
			this.setIcon(l.getIcon());
			String file = l.getIcon().toString();
			
			if(isSelected) {
				String temp[] = file.split("\\.");
				if(temp.length > 0) {
					String newFile = temp[0] + "_1." + temp[1];					
					ImageIcon icon = new ImageIcon(newFile);
					this.setIcon(icon);
				}
				this.setBackground(listCurriculum.getBackground());
				this.setForeground(listCurriculum.getForeground());
			}else {
				this.setBackground(listCurriculum.getBackground());
				this.setForeground(listCurriculum.getForeground());
			}
			this.setOpaque(true);
			return this;
		}		
	}
	
	
	
	
	
}
