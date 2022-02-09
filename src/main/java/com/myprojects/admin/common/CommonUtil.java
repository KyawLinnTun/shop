package com.myprojects.admin.common;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.util.HtmlUtils;

public class CommonUtil {

	private static Logger logger = Logger.getLogger(CommonUtil.class);

	public static String prepareProfileImagePath(String imagePath, HttpServletRequest req) {
		String urlScheme = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
		String basePath = urlScheme + "/" + CommonConstant.IMAGE_PATH + "/";
		if (imagePath != null && !imagePath.isEmpty()) {
			return basePath + imagePath;
		}
		return "";
	}

	public static boolean isValidateEmail(String email) {
		final Pattern EMAIL_REGEX = Pattern.compile(CommonConstant.EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		return EMAIL_REGEX.matcher(email).matches();
	}

	public static String changeDateToString(String format, Date date) {
		if (date == null) {
			return null;
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		return new SimpleDateFormat(format).format(date);
	}

	public static Date changeStringToDate(String format, String dateString) {

		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		try {
			return new SimpleDateFormat(format).parse(dateString.trim());
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static Time changeStringToTime(String format, String timeString) {

		if (timeString == null || timeString.trim().isEmpty()) {
			return null;
		}

		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_TIME_FORMAT;
		}

		try {
			return new java.sql.Time(new SimpleDateFormat(format).parse(timeString.trim()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static String changeFormatOfDateString(String fromFormat, String toFormat, String dateString) {

		if (fromFormat == null || toFormat == null || dateString == null || fromFormat.trim().isEmpty()
				|| toFormat.trim().isEmpty() || dateString.trim().isEmpty()) {
			return "";
		}

		Date tempDate = CommonUtil.changeStringToDate(fromFormat, dateString);

		if (tempDate == null) {
			return "";
		}

		String formattedString = CommonUtil.changeDateToString(toFormat, tempDate);

		return (formattedString == null) ? "" : formattedString;
	}

	public static Date getStartTimeOfTheDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	public static Date getEndTimeOfTheDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static boolean isValidDateFormat(String format, String dateString) {
		if (format == null || format.trim().isEmpty()) {
			format = CommonConstant.STD_DATE_TIME_FORMAT;
		}

		if (dateString == null || dateString.trim().isEmpty()) {
			return false;
		}

		try {
			SimpleDateFormat simpleDate = new SimpleDateFormat(format);
			simpleDate.setLenient(false);
			simpleDate.parse(dateString.trim());
		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public static boolean isValidStringForDateOnlyFormat(String dateString) {
		String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(dateString).matches();
	}

	public static boolean isValidStringForDateTimeFormat(String dateTimeString) {
		String regex = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9] ((1[0-2]|0?[1-9]):([0-5][0-9]) ([AaPp][Mm]))$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(dateTimeString).matches();
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			@SuppressWarnings("unused")
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static String getTodayOrYesterdayStringWithTime(Date date) {
		if (date == null) {
			return "";
		}

		Calendar calendar = fromDateToCalendar(date);
		Calendar now = Calendar.getInstance();

		if ((now.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
				&& (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))) {

			if (now.get(Calendar.DATE) == calendar.get(Calendar.DATE)) {
				return ("TODAY(" + CommonUtil.changeDateToString(CommonConstant.STD_TIME_FORMAT, date) + ")");
			} else if (now.get(Calendar.DATE) - calendar.get(Calendar.DATE) == 1) {
				return ("YESTERDAY(" + CommonUtil.changeDateToString(CommonConstant.STD_TIME_FORMAT, date) + ")");
			}

		}

		return "";
	}

	public static Calendar fromDateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date getYesterdayDate() {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public static int getWeeksOfCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		return calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}

	public static int totalPages(int totalCount, int showItemCount) {
		int totalPage = 0;
		if (totalCount > 0) {
			totalPage = totalCount / showItemCount;
			if ((totalCount % showItemCount) > 0) {

				totalPage += 1;
			}

		}
		return totalPage;
	}

	public static int[] calculatePagination(int totalCount, int pageNumber, int totalPage) {
		int[] countArray = new int[2];
		int previousNo = 0;
		int nextNo = 0;
		int showPaginCount = CommonConstant.SHOW_PAGINATION_COUNT;

		if (pageNumber <= showPaginCount) {
			previousNo = 0;
			nextNo = showPaginCount;
			if (nextNo < totalPage) {
				nextNo += 1;
			} else {
				nextNo = totalPage + 1;
			}
		} else if (pageNumber > showPaginCount) {

			previousNo = pageNumber / showPaginCount * showPaginCount;
			nextNo = previousNo + showPaginCount + 1;
			if (nextNo > totalPage) {
				previousNo -= nextNo - totalPage - 1;
				nextNo = totalPage + 1;

			}

		}

		countArray[0] = previousNo;
		countArray[1] = nextNo;
		return countArray;

	}

	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public static String isoTimeZone(Date date) {

		logger.info("dage >> " + date);

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		logger.info("UTC >> " + now.getTime());

		TimeZone timeZone = now.getTimeZone();

		DateFormat df = new SimpleDateFormat(CommonConstant.STD_ISO_TIME_FORMAT);

		df.setTimeZone(timeZone);

		return df.format(date);
	}

	public static Date getPreviousAndNextDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	// write base64 file to given file path with given file name
	public static void writeBase64File(String data, String name, String saveDirectory)
			throws IllegalStateException, IOException {
		byte[] decoded = Base64.decodeBase64(data);
		File dest = new File(saveDirectory, name);
		FileUtils.writeByteArrayToFile(dest, decoded);
	}

	public static Date getMMCurrentDate(String zone, String formatStr) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern(formatStr);

		final ZoneId zoneId = ZoneId.of(zone);

		final ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), zoneId);

		return changeStringToDate(formatStr, format.format(zonedDateTime));

	}

	public static Date getMMCurrentDateChangeMintues(String zone, String formatStr, int minutes) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern(formatStr);

		final ZoneId zoneId = ZoneId.of(zone);

		final ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), zoneId).plusMinutes(minutes);

		return changeStringToDate(formatStr, format.format(zonedDateTime));

	}

	public static String prepareImagePath(String imagePath, HttpServletRequest req) {
		String urlScheme = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort();
		String basePath = urlScheme + "/" + CommonConstant.IMAGE_PATH + "/";

		if (validString(imagePath)) {
			return basePath + imagePath;
		}

		return urlScheme + req.getContextPath() + "/" + CommonConstant.IMAGE_RESOURCES_PATH + "/"
				+ CommonConstant.DEFAULT_USER_ICON;
	}

	public static String parseString(String str) {
		return HtmlUtils.htmlEscape(str);
	}

	public static Date addAndReductTimeFromDate(Date date, int n, String flag) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (flag.equalsIgnoreCase("dd"))
			cal.add(Calendar.DAY_OF_YEAR, n);
		if (flag.equalsIgnoreCase("hh"))
			cal.add(Calendar.HOUR_OF_DAY, n);
		if (flag.equalsIgnoreCase("mm"))
			cal.add(Calendar.MINUTE, n);
		if (flag.equalsIgnoreCase("ss"))
			cal.add(Calendar.SECOND, n);
		return cal.getTime();
	}

	public static boolean isValidSessionTimeRange(String startTime, String endTime) {
		Time sTime = CommonUtil.changeStringToTime(CommonConstant.STD_TIME_FORMAT2, startTime);
		Time eTime = CommonUtil.changeStringToTime(CommonConstant.STD_TIME_FORMAT2, endTime);
		return sTime.compareTo(eTime) <= 0;
	}

	public static String isoTimeZoneMM(Date date) {

		Calendar now = Calendar.getInstance(TimeZone.getTimeZone("UTC + 6:30h"));

		TimeZone timeZone = now.getTimeZone();

		DateFormat df = new SimpleDateFormat(CommonConstant.STD_DATE_TIME_FORMAT);

		df.setTimeZone(timeZone);

		return df.format(date);
	}

	public static boolean validString(String val) {
		return val != null && !val.trim().isEmpty();
	}

	public static boolean validLong(Long val) {
		return val != null && val.compareTo(0L) > 0;
	}

	public static boolean validInteger(Integer val) {
		return val != null && val.compareTo(0) > 0;
	}

	public static boolean validDouble(Double val) {
		return val != null && val.compareTo(0D) > 0;
	}

	public static boolean validBigDecimal(BigDecimal val) {
		return val != null && val.compareTo(BigDecimal.ZERO) > 0;
	}

	@SuppressWarnings("rawtypes")
	public static boolean validList(List value) {
		return value != null && !value.isEmpty();
	}

	public static void main(String[] args) {

		/*
		 * System.out.println(getMMCurrentDate(CommonConstant.MM_TIME_ZONE,
		 * CommonConstant.STD_DATE_TIME_FORMAT));
		 * 
		 * System.out.println(getMMCurrentDateChangeMintues(
		 * CommonConstant.MM_TIME_ZONE, CommonConstant.STD_DATE_TIME_FORMAT, -35));
		 */
		/*
		 * System.out.println(new Date());
		 * System.out.println(addAndReductTimeFromDate(new Date(), -5, "mm"));
		 */

		/*
		 * Date date = changeStringToDate(CommonConstant.STD_ISO_TIME_FORMAT,
		 * "2019-09-26T07:30:40.200287Z");
		 * 
		 * System.out.println(isoTimeZoneMM(date));
		 */
	}

	public static String removeStartZeroFromPh(String ph) {
		if (CommonUtil.validString(ph)) {
			ph = ph.replaceAll("\\s", "").trim();
		}
		if (CommonUtil.validString(ph) && ph.startsWith("0")) {
			return ph.replaceFirst("0", "").trim();
		}

		return ph;

	}

	public static String removeDecimalWithAmountCommma(BigDecimal amount) {
		if (CommonUtil.validBigDecimal(amount)) {
			DecimalFormat df = new DecimalFormat("#,###");
			return df.format(amount);
		}
		return "";
	}

	public static String stringAmountCommma(String number) {
		double amount = Double.parseDouble(number);
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(amount);
	}

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("") || str.isEmpty()) {
			return true;
		}

		return false;
	}

}
