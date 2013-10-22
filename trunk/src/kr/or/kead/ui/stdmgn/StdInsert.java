package kr.or.kead.ui.stdmgn;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.InfoStudent;
import kr.or.kead.module.RegDate;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.menu.MenuMgn;

import java.awt.FlowLayout;

public class StdInsert extends JDialog implements ActionListener {
	private JTextField textName;	
	private RegJumin textJumin;
	private RegDate textStartDate;
	private RegDate textEndDate;
	private RegMobile textMobile;
	private RegMobile textTel;
	private JTextField textAddr;
	private RegDormitory textRoomNum;
	private JComboBox<String> comboStdType;
	private JComboBox<String> comboGrade;
	private JTextField textEmail;
	private JButton btnCancel;
	private JButton btnInsert;
	private String compareMenu= null;
	private InfoStudent std = new InfoStudent();
	private DaoTable dao;
	private int stdId;
	private MenuMgn menuMgn;
		
	
	static final String[] type={"지체장애","뇌병변장애","시각장애","청각장애",
		"언어장애", "안면장애",
		"신장장애","심장장애","간장앨","호흡기장애",
		"장루,요루장애","간질장애",
		"지적장애","자폐성장애","정신장애"};
	static final int[] typeValue={		
		111,112,113,114,115,116,
		121,122,123,124,125,126,
		211,212,
		221
	};
	
	static final int[] grade={ 1, 2, 3, 4, 5, 6};
	
	static final String[] mobileNum={
		"010","011","017","019"
	};
	
	static final String[] telNum={
		"02", "031", "032", "033", "041", "042", "043", "044",
		"051", "052", "053", "054", "055", "061", "062", "063", "064"
	};
	
	/**
	 * Create the panel.
	 */
	public StdInsert(MenuMgn menuMgn) {
		this.setTitle("학생추가");
		makeComponent();
		this.menuMgn = menuMgn;
	}
	/**
	 * @wbp.parser.constructor
	 */
	public StdInsert(int stdId, MenuMgn menuMgn) {
		this.setTitle("학생수정");
		this.stdId = stdId;
		makeComponent();
		fillText(stdId);
		this.menuMgn = menuMgn;
	}	
	
	public void makeComponent() {
		getContentPane().setLayout(new GridLayout(12, 2, 2,2 ));		
		JLabel lblName = new JLabel("성    명");
		lblName.setFont(new Font("궁서체", Font.BOLD, 12));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblName);
		
		textName = new JTextField();
		textName.addActionListener(this);
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textName);
		textName.setColumns(10);
		
	
		
		JLabel lblJuminnum = new JLabel("주민번호");
		lblJuminnum.setFont(new Font("궁서체", Font.BOLD, 12));
		lblJuminnum.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblJuminnum);
		
		textJumin = new RegJumin();		
		FlowLayout flowLayout = (FlowLayout) textJumin.getLayout();
		getContentPane().add(textJumin);		
		
		JLabel lblStartDate = new JLabel("입학날짜");
		lblStartDate.setFont(new Font("궁서체", Font.BOLD, 12));
		lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblStartDate);
		
		textStartDate = new RegDate();
		GregorianCalendar now = new GregorianCalendar();	
		textStartDate.setTxtDate(now.get(Calendar.YEAR),
				now.get(GregorianCalendar.MONTH)+1,
				now.get(GregorianCalendar.DATE));
		getContentPane().add(textStartDate);
		
		
		JLabel lblEndDate = new JLabel("수료날짜");
		lblEndDate.setFont(new Font("궁서체", Font.BOLD, 12));
		lblEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEndDate);
		
		textEndDate = new RegDate();
		GregorianCalendar endDate = new GregorianCalendar(textStartDate.getYear()+1,
				textStartDate.getMonth(),textStartDate.getDay());
		endDate.add(Calendar.DATE, -1);		
		textEndDate.setTxtDate(endDate.get(Calendar.YEAR),
				endDate.get(Calendar.MONTH),
				endDate.get(Calendar.DATE));
		getContentPane().add(textEndDate);
		
		JLabel lblMobile = new JLabel("휴대전화");
		lblMobile.setFont(new Font("궁서체", Font.BOLD, 12));
		lblMobile.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblMobile);
		
		textMobile = new RegMobile(1);			
		getContentPane().add(textMobile);
		
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setFont(new Font("궁서체", Font.BOLD, 12));
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTel);
		
		textTel = new RegMobile(2);		
		getContentPane().add(textTel);
		
		
		JLabel lblAddr = new JLabel("주    소");
		lblAddr.setFont(new Font("궁서체", Font.BOLD, 12));
		lblAddr.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAddr);
		
		textAddr = new JTextField();
		textAddr.addActionListener(this);
		textAddr.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textAddr);
		textAddr.setColumns(10);
		
		JLabel lblRoomNum = new JLabel("기숙사/통학");
		lblRoomNum.setFont(new Font("궁서체", Font.BOLD, 12));
		lblRoomNum.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblRoomNum);
		
		textRoomNum = new RegDormitory();		
		getContentPane().add(textRoomNum);		
		
		JLabel lblStdType = new JLabel("장애유형");
		lblStdType.setFont(new Font("궁서체", Font.BOLD, 12));
		lblStdType.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblStdType);
		
		comboStdType = new JComboBox<>();
		int len = type.length;
		for(int i=0;i<len;i++) {
			comboStdType.addItem(type[i]);
		}				
		getContentPane().add(comboStdType);
		
		
		JLabel lblGrade = new JLabel("등급");
		lblGrade.setFont(new Font("궁서체", Font.BOLD, 12));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblGrade);
		
		comboGrade = new JComboBox<>();
		len = grade.length;
		for(int i=0;i<len;i++) {
			comboGrade.addItem(grade[i] + "급");
		}
		comboGrade.addActionListener(this);		
		getContentPane().add(comboGrade);
		
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("궁서체", Font.BOLD, 12));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.addActionListener(this);
		textEmail.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textEmail);
		textEmail.setColumns(10);
		
		btnInsert = new JButton("저장");	
		btnInsert.addActionListener(this);
		
		
		getContentPane().add(btnInsert);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		
		
		getContentPane().add(btnCancel);
		this.setVisible(true);			
		pack();
	}	
	
	
	private void fillText(int stdId) {
		dao = new DaoInfoStudent();
		std = dao.selectStudentById(stdId);	
		if(std.getIdx() != 0) {
			textName.setText(std.getStdName());
			StringTokenizer juminNum = new StringTokenizer(std.getJuminNum(), "-");
			textJumin.setTxtJumin1(juminNum.nextToken(), juminNum.nextToken());
			StringTokenizer start_st = new StringTokenizer(String.valueOf(std.getStartDate()), "-");			
			textStartDate.setTxtDate(Integer.parseInt(start_st.nextToken()),
					Integer.parseInt(start_st.nextToken()),
					Integer.parseInt(start_st.nextToken()));
			StringTokenizer end_st = new StringTokenizer(String.valueOf(std.getEndDate()), "-");
			textEndDate.setTxtDate(Integer.parseInt(end_st.nextToken()), 
					Integer.parseInt(end_st.nextToken()), 
					Integer.parseInt(end_st.nextToken()));	
			comboStdType.setSelectedIndex(getType(std.getStdType()));
			comboGrade.setSelectedIndex(std.getGrade()-1);
			StringTokenizer mobile = new StringTokenizer(std.getMobile(), "-");
			textMobile.setMobile(getMobile(mobile.nextToken()), mobile.nextToken(), mobile.nextToken());
			StringTokenizer tel = new StringTokenizer(std.getMobile(), "-");
			textTel.setMobile(getMobile(tel.nextToken()), tel.nextToken(), tel.nextToken());			
			textAddr.setText(std.getStdAddr());
			if(std.getRoomNum() == 500) {
				textRoomNum.setRadioBtn1(true);
			}else {
				textRoomNum.setRadioBtn1(false);
				textRoomNum.setRadioBtn2(true);
				textRoomNum.setTxtRoomNum(String.valueOf(std.getRoomNum()));
			}			
			textEmail.setText(std.getEmail());
			btnInsert.setText("수정");
		}else {			
			this.dispose();
		}
	}



	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == textName) {
			textJumin.requestFocus();
		}else if (e.getSource() == textJumin) {
			textMobile.requestFocus();
		}else if (e.getSource() == textMobile) {
			textTel.requestFocus();
		}else if (e.getSource() == textTel) {
			textAddr.requestFocus();
		}else if (e.getSource() == textAddr) {
			textRoomNum.requestFocus();
		}else if (e.getSource() == textRoomNum) {
			comboStdType.requestFocus();
		}else if (e.getSource() == textEmail) {
			btnInsert.requestFocus();		
		}else if (e.getSource() == comboStdType) {
			comboGrade.requestFocus();
		}else if (e.getSource() == comboGrade) {
			textEmail.requestFocus();
		}else if (e.getSource() == btnInsert) {
			if(e.getActionCommand().equals("저장")) {					
				btnInsertActionPerformed(e);
			}else if(e.getActionCommand().equals("수정")) {
				btnModifyActionPerformed(e);				
			}
		}else if (e.getSource() == btnCancel) {			
			btnCancelActionPerformed(e);
			
		}
	}
	
	private void btnModifyActionPerformed(ActionEvent e) {
		dao = new DaoInfoStudent();
			if(isFieldCheck()) {
				GregorianCalendar startCal = new GregorianCalendar(textStartDate.getYear(),
						textStartDate.getMonth(),textStartDate.getDay());
				GregorianCalendar endCal = new GregorianCalendar(textEndDate.getYear(),
						textEndDate.getMonth(),textEndDate.getDay());
				std.setIdx(stdId);
				std.setStdName(textName.getText());
				std.setJuminNum(textJumin.getTxtJumin1() + "-"+ textJumin.getTxtJumin2());
				std.setStartDate(startCal.getTime());
				std.setStartDate(endCal.getTime());
				std.setMobile(mobileNum[textMobile.getComboFN().getSelectedIndex()]+"-"+
				textMobile.getTxtSN()+ "-" + textMobile.getTxtTN());
				std.setTel(telNum[textTel.getComboFN().getSelectedIndex()]+"-"+
						textTel.getTxtSN()+ "-" + textTel.getTxtTN());				
				std.setStdAddr(textAddr.getText());
				if(textRoomNum.getRadioBtn1()) {
					std.setRoomNum(500);
				}else {
					std.setRoomNum(Integer.parseInt(textRoomNum.getTxtRoomNum()));
				}				
				std.setStdType(typeValue[comboStdType.getSelectedIndex()]);
				std.setGrade(comboGrade.getSelectedIndex()+1);
				std.setEmail(textEmail.getText());				
				if(dao.updateDao(std) == 0){					
					this.dispose();
					menuMgn.refreshList();
				}else {
					JOptionPane.showMessageDialog(null, "수정을 완료하였습니다.");
				}
			}else {
				JOptionPane.showMessageDialog(null, "수정을 완료하지 못하였습니다.");
			}		
	}



	protected void btnCancelActionPerformed(ActionEvent e) {
		cleanTextField();		
	}

	protected void btnInsertActionPerformed(ActionEvent e) {
		if(isFieldCheck()) {
			GregorianCalendar startCal = new GregorianCalendar(textStartDate.getYear(),
					textStartDate.getMonth()-1,textStartDate.getDay());
			GregorianCalendar endCal = new GregorianCalendar(textEndDate.getYear(),
					textEndDate.getMonth()-1,textEndDate.getDay());
			int roomNum;
			if(textRoomNum.getRadioBtn1()) {
				roomNum = 500;
			}else {
				roomNum = Integer.parseInt(textRoomNum.getTxtRoomNum());
			}
			std = new InfoStudent(textName.getText(), textJumin.getTxtJumin1() + "-"+ textJumin.getTxtJumin2(),
					startCal.getTime(), endCal.getTime(), mobileNum[textMobile.getComboFN().getSelectedIndex()]+"-"+
					textMobile.getTxtSN()+ "-" + textMobile.getTxtTN(),
					telNum[textTel.getComboFN().getSelectedIndex()]+"-"+
					textTel.getTxtSN()+ "-" + textTel.getTxtTN(),
					textAddr.getText(),	roomNum,
					typeValue[comboStdType.getSelectedIndex()], comboGrade.getSelectedIndex()+1
					, textEmail.getText());		
			dao = new DaoInfoStudent();
					
			if(dao.insertDao(std) == 0 ) {
				cleanTextField();
				menuMgn.refreshList();
				JOptionPane.showMessageDialog(null, "저장을 완료하였습니다.");				
				this.dispose();
			}else {				
				JOptionPane.showMessageDialog(null, "저장을 완료 하지 못하였습니다.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "필수입력요소가 입력되지 않았습니다.");
		}				
	}
	private boolean isFieldCheck() {
		boolean compare = true;
		if(textName.getText().equals("") || textJumin.getTxtJumin1().equals("")||
				textJumin.getTxtJumin2().equals("")|| textMobile.getTxtSN().equals("") ||				
				textMobile.getTxtTN().equals("") ||	textTel.getTxtSN().equals("") ||
				textTel.getTxtTN().equals("") || textAddr.getText().equals("") ||				
				textEmail.getText().equals("")) {
			compare = false;
		}		
		return compare;		
	}	
	public void cleanTextField() {
		textName.setText("");
		textJumin.setTxtJumin1("", "");
		Calendar now = Calendar.getInstance();
		textStartDate.setTxtDate(now.get(Calendar.YEAR),
				now.get(Calendar.MONTH)+1,
				now.get(Calendar.DATE));
		textEndDate.setTxtDate(now.get(Calendar.YEAR) + 1,
				now.get(Calendar.MONTH)+1,
				now.get(Calendar.DATE)-1);
		textAddr.setText("");
		textMobile.setMobile(0, "", "");
		textTel.setMobile(0, "", "");		
		comboStdType.setSelectedIndex(0);
		comboGrade.setSelectedIndex(0);
		textEmail.setText("");
	}
	public static int getType(int stdType) {		
		switch(stdType) {
		case 111 : return 0;
		case 112 : return 1;
		case 113 : return 2;
		case 114 : return 3;
		case 115 : return 4;
		case 116 : return 5;
		
		case 121 : return 6;
		case 122 : return 7;
		case 123 : return 8;
		case 124 : return 9;
		case 125 : return 10;
		case 126 : return 11;
		
		case 211 : return 12;
		case 212 : return 13;
		case 221 : return 14;
		default :
			return 0;		
		}		
	}
	public int getMobile(String mobile) {
		switch(mobile) {
		case "010" : return 0;
		case "011" : return 1;
		case "017" : return 2;
		case "019" : return 3;
		default : return 0;		
		}
	}
}
