package com.bajaj.ADID.utils;

/**
 * @author Mahesh Shelke
 *
 *         Aug 26, 2019 : 10:10:37 AM
 */
import static com.bajaj.ADID.utils.FormatUtils.isNullOrEmpty;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Utils {
	public static String getExceptionTrace(Exception ex) {
		try {
			StringWriter strbufLog = new StringWriter();
			ex.printStackTrace(new PrintWriter(strbufLog));
			return strbufLog.toString();
		} catch (Exception e) {
			return "";
		}
	}

	public static String genRRN() {
		String formattedDate = "BFL";
		DateFormat writeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		if (date != null) {
			formattedDate += writeFormat.format(date);
		}
		return formattedDate;
	}

	public static String getCurrentDate() {
		String formattedDate = "";
		DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		if (date != null) {
			formattedDate = writeFormat.format(date);
		}
		return formattedDate;
	}

	public static String parseDateYYYYMMDD(String date) {
		SimpleDateFormat sdfc = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			date2 = sdfc.parse(date);
			return sdf1.format(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String convertDate(String date) {
		String date1 = null;
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-dd-MM");
		SimpleDateFormat sfdcFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		try {
			if (date != null) {
				date2 = sqlFormat.parse(date);
				return date1 = sfdcFormat.format(date2);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}

	public static String convertDateAndTimestamp(String date) {
		String date1 = null;
		SimpleDateFormat sfdcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat frontendFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date date3 = null;
		try {
			if (date != null) {
				date3 = frontendFormat.parse(date);
				return date1 = sfdcFormat.format(date3);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}

	public static long dateStringtoLong(String date) {
		SimpleDateFormat sfdcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date d = null;
		try {
			d = sfdcFormat.parse(date);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return d.getTime();
	}
	
	public static String longStringToDateString(String longDate) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		long milliSeconds= Long.parseLong(longDate);
		

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	public static String parseDateYYYYMMDDWithTimeStamp(String date) {
		String date1 = null;// 05/27/2016 15:40:12 2018-03-05 15:40:12 dd-mm-yyyy
		// 05-03-2018 15:40:12 2018-03-05 15:40:12
		SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sfdcFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date date2 = null;
		try {
			if (date != null) {
				date2 = sqlFormat.parse(date);
				return date1 = sfdcFormat.format(date2);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date1;
	}

	public static String getCurrentDateWithTimestamp() {
		String formattedDate = "BFL";
		DateFormat writeFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		if (date != null) {
			formattedDate += writeFormat.format(date);
		}
		return formattedDate;
	}

	public static String getDateddMMyyyy() throws ParseException {
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMyyyy");
		String Date_1 = sdf1.format(date);
		return Date_1;
	}

	public static String checkEmploymentType(String data) {
		String dataValue = null;
		if (!isNullOrEmpty(data) && data.equals("E")) {
			dataValue = "Self Employed - Professional";
		} else if (!isNullOrEmpty(data) && data.equals("S")) {
			dataValue = "Salaried";
		}
		return dataValue;
	}

	public static String checkAddressType(String data) {
		String dataValue = null;
		if (!isNullOrEmpty(data) && data.equals("Self-Owned")) {
			dataValue = "OWN";
		} else if (!isNullOrEmpty(data) && data.equals("Rented")) {
			dataValue = "RENT";
		}
		return dataValue;
	}

	public static String checkMarritalStatus(String data) {
		String dataValue = null;
		if (!isNullOrEmpty(data) && data.equals("M")) {
			dataValue = "Married";
		} else if (!isNullOrEmpty(data) && data.equals("S")) {
			dataValue = "Single";
		}
		return dataValue;
	}

	public static String checkGenderMaster(String data) {
		String dataValue = null;
		if (!isNullOrEmpty(data) && data.equals("M")) {
			dataValue = "Male";
		} else if (!isNullOrEmpty(data) && data.equals("F")) {
			dataValue = "Female";
		}
		return dataValue;
	}

	public static String checkAssetCategory(String data) {
		String dataValue = null;
		if (!isNullOrEmpty(data) && data.equals("iPad")) {
			dataValue = "I-PAD";
		} else if (!isNullOrEmpty(data) && data.equals("iPhone")) {
			dataValue = "I Phone 6 Plus- 128GB";
		} else if (!isNullOrEmpty(data) && data.equals("CPU")) {
			dataValue = "MACBOOK";
		} else if (!isNullOrEmpty(data) && (data.equals("Accessories") || data.equals("Watch")
				|| data.equals("Apple TV") || data.equals("AppleCare") || data.equals("iPod"))) {
			dataValue = "APPLE";
		}
		return dataValue;
	}

	public static void main(String[] args) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			String data = "{displayname=displayName: Mahesh Shelke2, givenname=givenName: Mahesh, samaccounttype=sAMAccountType: 805306368, primarygroupid=primaryGroupID: 513, objectclass=objectClass: top, person, organizationalPerson, user, objectcategory=objectCategory: CN=Person,CN=Schema,CN=Configuration,DC=bfl,DC=com, mail=mail: mahesh.shelke@bajajfinserv.in, msds-phoneticdepartment=msDS-PhoneticDepartment: API - Delivery, cn=cn: Mahesh Shelke2, useraccountcontrol=userAccountControl: 512, userprincipalname=userPrincipalName: mahesh.shelke@bajajfinserv.in, manager=manager: CN=Amit Tiwari 2,OU=BFL_EMPLOYEES,DC=bfl,DC=com, dscorepropagationdata=dSCorePropagationData: 20230530121836.0Z, 20230530120843.0Z, 20230120143516.0Z, 20230105112003.0Z, 16010714223649.0Z, distinguishedname=distinguishedName: CN=Mahesh Shelke2,OU=BFL_EMPLOYEES,DC=bfl,DC=com, whenchanged=whenChanged: 20230609063017.0Z, whencreated=whenCreated: 20200913062810.0Z, st=st: MAHARASHTRA, l=l: Pune, description=description: 994367, lastlogontimestamp=lastLogonTimestamp: 133307658170988992, objectguid=objectGUID: W�I���I�����I�, sn=sn: Shelke, c=c: IN, usnchanged=uSNChanged: 2384555375, usncreated=uSNCreated: 1669258567, objectsid=objectSid: ";
			String[] array = data.split(",");
			for (String string : array) {
				if (string.contains("=") && string.contains(":")) {
					result.put(string.split("=")[0], string.split(":")[1]);
				}
			}

			System.out.println("result == " + result);
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
