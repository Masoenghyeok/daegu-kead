package kr.or.kead.ui.insert_update;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import kr.or.kead.domain.Depart;
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

public class StdInsertUpdate extends AbsInsertUpdate {
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
	private JPasswordField passWord;
	
	private DaoDepart daoDepart;
	private DaoHandicap daoHandicap;
	private InfoStudent std;
	private Depart depart;	
	static final Integer[] GRADE={ 1, 2, 3, 4, 5, 6};
	
	public StdInsertUpdate(Object obj) {
		super(obj, "학생 정보");		
	}

	@Override
	protected void initDao() {
		daoTable = new DaoInfoStudent();
		daoDepart = new DaoDepart();
		daoHandicap = new DaoHandicap();
		
	}

	@Override
	protected void updateInit() {
		std = (InfoStudent)obj;	
		if(std.getIdx() != 0) {
			Depart depart = new Depart();
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
			passWord.setText(std.getPassWord());
			depart = (Depart)new DaoDepart().selectTableById(std.getDepartCode());
			comboDepart.setSelectedItem(depart.getName());		
		}else {			
			this.dispose();
		}

	}

	@Override
	protected Object getObject() {
		depart = (Depart)daoDepart.selectCodeByName((String)comboDepart.getSelectedItem());
		GregorianCalendar startCal = new GregorianCalendar(textStartDate.getYear(),
		textStartDate.getMonth()-1,textStartDate.getDay());
		GregorianCalendar endCal = new GregorianCalendar(textEndDate.getYear(),
		textEndDate.getMonth()-1,textEndDate.getDay());	
		if (obj == null) {			
			int roomNum;
			if(textRoomNum.getRadioBtn1()) {
				roomNum = 500;
			}else {
				roomNum = Integer.parseInt(textRoomNum.getTxtRoomNum());
			}				
			std = new InfoStudent(textName.getText(), textJumin.getJumin(),
					startCal.getTime(), endCal.getTime(),
					textMobile.getPhone(),
					textTel.getPhone(),
					textAddr.getText(),	roomNum,
					daoHandicap.selectCodeHandiByName((String)comboStdType.getSelectedItem()),
					comboGrade.getSelectedIndex()+1
					, textEmail.getEmail(),
					depart.getCode(), new String(passWord.getPassword()));
			return std;
		}else {
			std = (InfoStudent)obj;
//			std.setIdx(stdIdx);
			std.setStdName(textName.getText());
			std.setJuminNum(textJumin.getJumin());
			std.setStartDate(startCal.getTime());
			std.setEndDate(endCal.getTime());		
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
			std.setPassWord(new String(passWord.getPassword()));
			return std;
		}
		
	}

	@Override
	protected boolean isValidCheck() {		
		try {
			if(!textRoomNum.getRadioBtn1()) {			
				Integer.parseInt(textRoomNum.getTxtRoomNum());
			}
		} catch (NumberFormatException e) {			
			return false;
		}
		boolean isField = textName.getText().equals("")||textAddr.getText().equals("")||
				textEmail.getEmail().equals("")||new String(passWord.getPassword()).equals("");		
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

	protected JPanel getMainPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(13, 2, 2,2 ));
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("궁서체", Font.BOLD, 12));
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblEmail);
		
		textEmail = new RegEmail();		
		centerPanel.add(textEmail);
		
		JLabel lblPass = new JLabel("비밀번호");
		lblPass.setFont(new Font("궁서체", Font.BOLD, 12));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblPass);
		
		passWord = new JPasswordField(10);
		passWord.setFont(new Font("궁서체", Font.BOLD, 12));
		passWord.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(passWord);
		
		JLabel lblName = new JLabel("성    명");
		lblName.setFont(new Font("궁서체", Font.BOLD, 12));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblName);
		
		textName = new JTextField();
		textName.addActionListener(this);
		textName.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(textName);
		textName.setColumns(10);
		
		JLabel lblJuminnum = new JLabel("주민번호");
		lblJuminnum.setFont(new Font("궁서체", Font.BOLD, 12));
		lblJuminnum.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblJuminnum);
		
		textJumin = new RegJumin();		
		centerPanel.add(textJumin);		
		
		JLabel lblStartDate = new JLabel("입학날짜");
		lblStartDate.setFont(new Font("궁서체", Font.BOLD, 12));
		lblStartDate.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblStartDate);
		
		textStartDate = new RegDate();
		GregorianCalendar now = new GregorianCalendar();	
		textStartDate.setTxtDate(now.get(Calendar.YEAR),
				now.get(GregorianCalendar.MONTH)+1,
				now.get(GregorianCalendar.DATE));
		centerPanel.add(textStartDate);		
		
		JLabel lblEndDate = new JLabel("수료날짜");
		lblEndDate.setFont(new Font("궁서체", Font.BOLD, 12));
		lblEndDate.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblEndDate);
		
		textEndDate = new RegDate();
		GregorianCalendar endDate = new GregorianCalendar(textStartDate.getYear()+1,
				textStartDate.getMonth(),textStartDate.getDay());
		endDate.add(Calendar.DATE, -1);		
		textEndDate.setTxtDate(endDate.get(Calendar.YEAR),
				endDate.get(Calendar.MONTH),
				endDate.get(Calendar.DATE));
		centerPanel.add(textEndDate);
		
		JLabel lblMobile = new JLabel("휴대전화");
		lblMobile.setFont(new Font("궁서체", Font.BOLD, 12));
		lblMobile.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblMobile);
		
		textMobile = new PhoneCheck(true);		
		centerPanel.add(textMobile);		
		
		JLabel lblTel = new JLabel("전화번호");
		lblTel.setFont(new Font("궁서체", Font.BOLD, 12));
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblTel);
		
		textTel = new PhoneCheck(false);		
		centerPanel.add(textTel);		
		
		JLabel lblAddr = new JLabel("주    소");
		lblAddr.setFont(new Font("궁서체", Font.BOLD, 12));
		lblAddr.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblAddr);
		
		
		
		textAddr = new JTextField();
		textAddr.setToolTipText("클릭하세요");
		textAddr.addActionListener(this);		
		textAddr.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(textAddr);
		textAddr.setColumns(10);
		textAddr.setEditable(false);

		textAddr.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);				
				JDialog postDialog = new PostGetAddr(textAddr);
				postDialog.setModal(true);
				postDialog.setVisible(true);
			}			
		});	
		
		JLabel lblRoomNum = new JLabel("기숙사/통학");
		lblRoomNum.setFont(new Font("궁서체", Font.BOLD, 12));
		lblRoomNum.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblRoomNum);
		
		textRoomNum = new RoomCheck();		
		centerPanel.add(textRoomNum);		
		
		JLabel lblStdType = new JLabel("장애유형");
		lblStdType.setFont(new Font("궁서체", Font.BOLD, 12));
		lblStdType.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblStdType);
		
		ArrayList<Object> handis = daoHandicap.selectDao();
		Object[] obj = handis.toArray();
		comboStdType = new JComboBox<>(obj);
		
		centerPanel.add(comboStdType);		
		
		JLabel lblGrade = new JLabel("등급");
		lblGrade.setFont(new Font("궁서체", Font.BOLD, 12));
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		centerPanel.add(lblGrade);
		
		comboGrade = new JComboBox<>(GRADE);		
		comboGrade.addActionListener(this);		
		centerPanel.add(comboGrade);		
		
		
		
		
		lblNewLabel = new JLabel("학과");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("궁서체", Font.BOLD, 12));
		centerPanel.add(lblNewLabel);
		
		
		ArrayList<Object> departs = daoDepart.selectNames();
		Object[] arObj = departs.toArray();		
		comboDepart = new JComboBox<Object>(arObj);
		centerPanel.add(comboDepart);
		
		return centerPanel;
	}

}
