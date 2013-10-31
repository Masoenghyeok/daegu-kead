package kr.or.kead.ui.stdmgn;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.stream.events.StartDocument;

import kr.or.kead.domain.Depart;
import kr.or.kead.domain.Handicap;
import kr.or.kead.domain.InfoStudent;
import kr.or.kead.module.PhoneCheck;
import kr.or.kead.module.PostGetAddr;
import kr.or.kead.module.RegDate;
import kr.or.kead.module.RegEmail;
import kr.or.kead.module.RegJumin;
import kr.or.kead.module.RoomCheck;
import kr.or.kead.service.DaoDepart;
import kr.or.kead.service.DaoHandicap;
import kr.or.kead.service.DaoInfoStudent;
import kr.or.kead.service.DaoTable;
import kr.or.kead.ui.menu.MenuMgn;

@SuppressWarnings("serial")
public class StdInsert extends JDialog implements ActionListener {
	private JTextField textName;	
	private RegJumin textJumin;
	private RegDate textStartDate;
	private RegDate textEndDate;
	private PhoneCheck textMobile;
	private PhoneCheck textTel;
	private JTextField textAddr;	
	private RoomCheck textRoomNum;
	private JComboBox<Object> comboStdType;
	private JComboBox<Integer> comboGrade;
	private RegEmail textEmail;
	private JLabel lblNewLabel;
	private JComboBox<Object> comboDepart;
	private JButton btnCancel;
	private JButton btnInsert;	
	@SuppressWarnings("unused")
	private String compareMenu= null;
	private InfoStudent std;
	private DaoTable dao;
	private DaoDepart daoDepart;
	private DaoHandicap daoHandicap;
	private int stdIdx;
	private MenuMgn menuMgn;
	private Depart depart;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
	static final Integer[] grade={ 1, 2, 3, 4, 5, 6};
	
	/*
	public StdInsert(InfoStudent std, MenuMgn menuMgn) {
		init();
	}
	*/
	public StdInsert(MenuMgn menuMgn) {
		this.setTitle("학생추가");
		init();
		this.menuMgn = menuMgn;
	}
	
	public StdInsert(int stdIdx, MenuMgn menuMgn) {
		this.setTitle("학생수정");		
		init();
		this.stdIdx = stdIdx;
		fillText(this.stdIdx);
		this.menuMgn = menuMgn;
	}	
	
	public void init() {		
		std = new InfoStudent();
		dao = new DaoInfoStudent();
		daoDepart = new DaoDepart();
		daoHandicap = new DaoHandicap();
		
		getContentPane().setLayout(new GridLayout(13, 2, 2,2 ));		
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
		
		textMobile = new PhoneCheck(true);		
		getContentPane().add(textMobile);		
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setFont(new Font("궁서체", Font.BOLD, 12));
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTel);
		
		textTel = new PhoneCheck(false);		
		getContentPane().add(textTel);		
		
		JLabel lblAddr = new JLabel("주    소");
		lblAddr.setFont(new Font("궁서체", Font.BOLD, 12));
		lblAddr.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAddr);
		
		
		
		textAddr = new JTextField();
		textAddr.setToolTipText("클릭하세요");
		textAddr.addActionListener(this);		
		textAddr.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(textAddr);
		textAddr.setColumns(10);
		textAddr.setEditable(false);

		textAddr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				System.out.println("focus in");
				PostGetAddr postDialog = new PostGetAddr(textAddr);
				postDialog.setVisible(true);
			}			
		});	
		
		JLabel lblRoomNum = new JLabel("기숙사/통학");
		lblRoomNum.setFont(new Font("궁서체", Font.BOLD, 12));
		lblRoomNum.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblRoomNum);
		
		textRoomNum = new RoomCheck();		
		getContentPane().add(textRoomNum);		
		
		JLabel lblStdType = new JLabel("장애유형");
		lblStdType.setFont(new Font("궁서체", Font.BOLD, 12));
		lblStdType.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblStdType);
		
		ArrayList<Object> handis = daoHandicap.selectDao();
		Object[] obj = handis.toArray();
		comboStdType = new JComboBox<>(obj);
		
		getContentPane().add(comboStdType);		
		
		JLabel lblGrade = new JLabel("등급");
		lblGrade.setFont(new Font("궁서체", Font.BOLD, 12));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblGrade);
		
		comboGrade = new JComboBox<>(grade);		
		comboGrade.addActionListener(this);		
		getContentPane().add(comboGrade);		
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("궁서체", Font.BOLD, 12));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEmail);
		
		textEmail = new RegEmail();		
		getContentPane().add(textEmail);
		
		
		lblNewLabel = new JLabel("학과");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("궁서체", Font.BOLD, 12));
		getContentPane().add(lblNewLabel);
		
		
		ArrayList<Object> departs = daoDepart.selectNames();
		Object[] arObj = departs.toArray();		
		comboDepart = new JComboBox<Object>(arObj);
		getContentPane().add(comboDepart);
		
		btnInsert = new JButton("저장");	
		btnInsert.addActionListener(this);
		
		getContentPane().add(btnInsert);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);		
		
		getContentPane().add(btnCancel);
		this.setVisible(true);			
		pack();
	}	
	
	private void fillText(int stdIdx) {
		std = (InfoStudent)dao.selectTableById(stdIdx);		
		if(std.getIdx() != 0) {
			textName.setText(std.getStdName());			
			textJumin.setJumin(std.getJuminNum());
			StringTokenizer start_st = new StringTokenizer(String.valueOf(std.getStartDate()), "-");			
			textStartDate.setTxtDate(Integer.parseInt(start_st.nextToken()),
					Integer.parseInt(start_st.nextToken()),
					Integer.parseInt(start_st.nextToken()));
			StringTokenizer end_st = new StringTokenizer(String.valueOf(std.getEndDate()), "-");
			textEndDate.setTxtDate(Integer.parseInt(end_st.nextToken()), 
					Integer.parseInt(end_st.nextToken()), 
					Integer.parseInt(end_st.nextToken()));	
			comboStdType.setSelectedItem(daoHandicap.selectTableById(std.getStdType()));
			comboGrade.setSelectedIndex(std.getGrade()-1);			
			textMobile.setPhone(std.getMobile());			
			textTel.setPhone(std.getTel());			
			textAddr.setText(std.getStdAddr());
			if(std.getRoomNum() == 500) {
				textRoomNum.setRadioBtn1(true);
			}else {
				textRoomNum.setRadioBtn1(false);
				textRoomNum.setRadioBtn2(true);
				textRoomNum.setTxtRoomNum(String.valueOf(std.getRoomNum()));
			}			
			textEmail.setEmail(std.getEmail());
			depart = (Depart)new DaoDepart().selectTableById(std.getDepartCode());
			comboDepart.setSelectedItem(depart.getName());
			btnInsert.setText("수정");
		}else {			
			this.dispose();
		}
	}
	
	public void actionPerformed(ActionEvent e) {			
		if (e.getSource() == btnInsert) {
			if(isValidateCheck()) {	
				if(e.getActionCommand().equals("저장")) {					
					btnInsertActionPerformed(e);
				}else if(e.getActionCommand().equals("수정")) {
					btnModifyActionPerformed(e);				
				}
			}
		}else if (e.getSource() == btnCancel) {			
			dispose();		
		}		
	}
	
	private boolean isValidateCheck() {
		boolean isField = textName.getText().equals("")||textAddr.getText().equals("")||
				textEmail.getEmail().equals("");
		if (isField) {
			JOptionPane.showMessageDialog(null, "빈칸을 채우세요.");
			textName.requestFocus();
			return false;
		}else {
			String str = textEmail.getEmail();			
			Pattern pattern = Pattern.compile("(\\w+\\.)*\\w+@(\\w+\\.)+[A-Za-z]+");
			Matcher match = pattern.matcher(str);
			if (!match.find()) {
				JOptionPane.showMessageDialog(null, "메일 형식이 잘못되었습니다.");
				return false;
			}
		}
		return true;
	}
	
	protected void btnInsertActionPerformed(ActionEvent e) {		
//		GregorianCalendar startCal = new GregorianCalendar(textStartDate.getYear(),
//				textStartDate.getMonth()-1,textStartDate.getDay());
//		GregorianCalendar endCal = new GregorianCalendar(textEndDate.getYear(),
//				textEndDate.getMonth()-1,textEndDate.getDay());		
		int roomNum;
		if(textRoomNum.getRadioBtn1()) {
			roomNum = 500;
		}else {
			roomNum = Integer.parseInt(textRoomNum.getTxtRoomNum());
		}				
		try {
			depart = (Depart)daoDepart.selectCodeByName((String)comboDepart.getSelectedItem());
			
			std = new InfoStudent(textName.getText(), textJumin.getJumin(),
					sdf.parse(textStartDate.toString()), sdf.parse(textEndDate.toString()),
					textMobile.getPhone(),
					textTel.getPhone(),
					textAddr.getText(),	roomNum,
					daoHandicap.selectCodeHandiByName((String)comboStdType.getSelectedItem()),
					comboGrade.getSelectedIndex()+1
					, textEmail.getEmail(),
					depart.getCode());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}				
		if(dao.insertDao(std) == 0 ) {
			cleanTextField();
			menuMgn.refreshList(new StdList(new CustomStdTableModel(), menuMgn));
			JOptionPane.showMessageDialog(null, "저장을 완료하였습니다.");				
			this.dispose();
		}else {				
			JOptionPane.showMessageDialog(null, "저장을 완료 하지 못하였습니다.");
		}						
	}

	private void btnModifyActionPerformed(ActionEvent e) {		
		std.setIdx(stdIdx);
		std.setStdName(textName.getText());
		std.setJuminNum(textJumin.getJumin());
		try {
			std.setStartDate(sdf.parse(textStartDate.toString()));
			std.setEndDate(sdf.parse(textEndDate.toString()));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}		
		std.setMobile(textMobile.getPhone());			
		std.setTel(textTel.getPhone());				
		std.setStdAddr(textAddr.getText());
		if(textRoomNum.getRadioBtn1()) {
			std.setRoomNum(500);
		}else {
			std.setRoomNum(Integer.parseInt(textRoomNum.getTxtRoomNum()));
		}				
		std.setStdType(daoHandicap.selectCodeHandiByName((String)comboStdType.getSelectedItem()));
		std.setGrade(comboGrade.getSelectedIndex()+1);
		std.setEmail(textEmail.getEmail());
		depart = (Depart)daoDepart.selectCodeByName((String)comboDepart.getSelectedItem());
		std.setDepartCode(depart.getCode());
		if(dao.updateDao(std) == 0){					
			this.dispose();
			menuMgn.refreshList(new StdList(new CustomStdTableModel(), menuMgn));
			cleanTextField();			
		}else {
			JOptionPane.showMessageDialog(null, "수정에 실패하였습니다.");
		}					
	}
	
	public void cleanTextField() {
		textName.setText("");
		textJumin.setJumin(" - ");
		Calendar now = Calendar.getInstance();
		textStartDate.setTxtDate(now.get(Calendar.YEAR),
				now.get(Calendar.MONTH)+1,
				now.get(Calendar.DATE));
		textEndDate.setTxtDate(now.get(Calendar.YEAR) + 1,
				now.get(Calendar.MONTH)+1,
				now.get(Calendar.DATE)-1);
		textAddr.setText("");
		textMobile.setPhone(" - - ");
		textTel.setPhone(" - - ");		
		comboStdType.setSelectedIndex(0);
		comboGrade.setSelectedIndex(0);
		textEmail.setEmail(" @ ");
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
